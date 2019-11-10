package org.jggn.cassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CassandraBenchmarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraBenchmarkApplication.class, args);
	}

}
