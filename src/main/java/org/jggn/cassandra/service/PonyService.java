package org.jggn.cassandra.service;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.jggn.cassandra.models.EnumType;
import org.jggn.cassandra.models.Pony;
import org.jggn.cassandra.repository.PonyManualRepository;
import org.jggn.cassandra.repository.PonyRepository;
import org.jggn.cassandra.utils.CassandraPager;
import org.jggn.cassandra.utils.PonyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class PonyService {

	@Autowired
	PonyGenerator ponyGenerator;
	@Autowired
	PonyRepository ponyRepository;
	@Autowired
	PonyManualRepository ponyManualRepository;
	@Autowired
	CassandraPager cassandraPager;

	public List<Pony> generatePonies(Integer nb) {
		return ponyGenerator.generatePonies(nb);
	}

	public void saveAll(List<Pony> ponies) {
		ponyRepository.saveAll(ponies);
	}

	public Slice<Pony> getAllByType(Pageable p, EnumType type) {
		Instant i1 = Instant.now();
		CompletableFuture<Long> future = ponyRepository.countByType(type);
		Instant i1p = Instant.now();
		Long l = null;
		try {
			l = future.get(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
		}
		Instant i2 = Instant.now();
		try {
			Slice<Pony> poniesPage = (Slice<Pony>) cassandraPager.getSlice(ponyRepository, "findAllByType", p, type);
			if (l != null) {
				return new PageImpl<>(poniesPage.getContent(), poniesPage.getPageable(), l);
			}
			return poniesPage;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Slice<Pony> getAllByTypeManual(Pageable p, EnumType type) {
		Instant i1 = Instant.now();
		CompletableFuture<Long> future = ponyManualRepository.countByType(type);
		Instant i1p = Instant.now();
		Long l = null;
		try {
			l = future.get(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
		}
		Instant i2 = Instant.now();
		Slice<Pony> poniesPage = ponyManualRepository.findAllByType(p, type);
		if (l != null) {
			return new PageImpl<>(poniesPage.getContent(), poniesPage.getPageable(), l);
		}
		return poniesPage;
	}

}
