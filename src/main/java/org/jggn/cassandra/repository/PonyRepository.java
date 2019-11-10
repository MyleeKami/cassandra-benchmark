package org.jggn.cassandra.repository;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.jggn.cassandra.models.EnumType;
import org.jggn.cassandra.models.Pony;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
@Repository
public interface PonyRepository extends CassandraRepository<Pony,UUID> {

	@Async
	CompletableFuture<Long> countByType(EnumType type);

	Slice<Pony> findAllByType(Pageable p,EnumType type);
}
