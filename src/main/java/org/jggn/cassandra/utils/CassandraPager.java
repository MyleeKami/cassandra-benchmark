package org.jggn.cassandra.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.exceptions.InvalidTypeException;

@Component
public class CassandraPager {
	@Value("${application.fetchSize}")
	int fetchSize;
	
	public Slice<?> getSlice(CassandraRepository<?,?> repository,String method,Pageable p,Object ...objects) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Method m = getMethodMatchingName(repository.getClass(),method);
		if(!m.getReturnType().getName().equals(Slice.class.getName()))
		{
			throw new InvalidTypeException("the expected return type is not a Slice!");
		}
		Long offset = (long)p.getPageSize()*(long)p.getPageNumber();
		int customSize=(int) (offset>fetchSize?offset:fetchSize);
		int targetPage=(int) (offset/fetchSize);
		Pageable customPageable = PageRequest.of(0,customSize ,p.getSort());
		Slice<?> slice = (Slice<?>) m.invoke(repository, convertParametersToSingleArray(customPageable,objects));
		CassandraPageRequest cassandraPageable = (CassandraPageRequest) slice.getPageable();
		int i =0;
		while(slice.hasNext() && i<targetPage)
		{
			customPageable=customPageable.next();
			CassandraPageRequest cpr = CassandraPageRequest.of(customPageable,cassandraPageable.getPagingState());
			slice = (Slice<?>) m.invoke(repository, convertParametersToSingleArray(cpr,objects));	
			i++;
		}
		
		//we have a page of a fetchSize elements, we need to get a portion of these elements
		int localOffset = (int) (offset%fetchSize);
		boolean hasNext = slice.hasNext()||(localOffset+p.getPageSize())<slice.getSize();
		return new SliceImpl<>(slice.stream().skip(localOffset).limit(p.getPageSize()).collect(Collectors.toList()),p,hasNext);
		
	}
	
	private Object[] convertParametersToSingleArray(Object... objects)
	{
		List<Object> objectsList = new ArrayList<>();
		for (Object object : objects) {
			if(object instanceof Object[] )
			{
				objectsList.addAll(Arrays.stream((Object[])object).collect(Collectors.toList()));
			}
			else
			{
				objectsList.add(object);
			}
		}
		return objectsList.toArray();
	}
	private Method getMethodMatchingName(Class<?> classVal,String methodName)
	{
		return Arrays.stream(classVal.getMethods()).filter(m -> m.getName().equals(methodName)).findFirst().get();
	}

}
