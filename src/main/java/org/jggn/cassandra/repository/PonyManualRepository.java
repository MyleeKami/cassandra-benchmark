package org.jggn.cassandra.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.jggn.cassandra.models.EnumType;
import org.jggn.cassandra.models.Pony;
import org.jggn.cassandra.models.PonyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
@Repository
public class PonyManualRepository{

	@Autowired
	Session session;
	@Value("${application.fetchSize}")
	int fetchSize;
	
	@Async
	public CompletableFuture<Long> countByType(EnumType type)
	{
		CompletableFuture<Long> future = new CompletableFuture<>();
		ResultSet set =session.execute("select count(*) from ponies.ponies_by_type where pony_type='"+type.toString()+"'");
		future.complete(set.one().getLong(0));
		return future;
	}

	public Slice<Pony> findAllByType(Pageable p,EnumType type)
	{
		Statement st = new SimpleStatement("select * from ponies.ponies_by_type where pony_type='"+type.toString()+"'");
		st.setFetchSize(fetchSize);
		Long offset = (long)p.getPageSize()*(long)p.getPageNumber();
		int customSize=(int) (offset>fetchSize?fetchSize:offset);
		int targetPage=(int) (offset/fetchSize);
		String requestedPage = null;
		// This will be absent for the first page
		
		ResultSet rs = session.execute(st);
		PagingState nextPage = rs.getExecutionInfo().getPagingState();
		int i=0;
		// This will be null if there are no more pages
		while(targetPage>i && !rs.isExhausted())
		{
			st.setPagingState(nextPage);
			rs = session.execute(st);
			nextPage = rs.getExecutionInfo().getPagingState();
			i++;
		}
		//offset
		PonyDTO ponyDTO = new PonyDTO();
		int localOffset = (int) (offset%customSize);
		boolean hasNext = rs.isExhausted()||(localOffset+p.getPageSize())<rs.getAvailableWithoutFetching();
		List<Row> rows=rs.all().stream().skip(localOffset).limit(p.getPageSize()).collect(Collectors.toList());
		return new SliceImpl<>(rows.stream().map(t-> ponyDTO.dto(t)).collect(Collectors.toList()),p,hasNext);
	}
}
