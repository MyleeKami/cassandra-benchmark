package org.jggn.cassandra.models;

import java.util.UUID;


import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.Row;

import lombok.Data;
import lombok.NoArgsConstructor;
@Table(value = "ponies_by_type")
@Data
@NoArgsConstructor
public class Pony {

	@PrimaryKeyColumn(name = "pony_id", ordinal = 1)
	UUID ponyId;
	@Column(value = "pony_name")
	String name;
	@Column(value="birth_place")
	String birthPlace;
	@Column(value="pony_genre")
	EnumGenre genre;	
	@Column(value="age")
	Integer age;
	@PrimaryKeyColumn(name = "pony_type",type = PrimaryKeyType.PARTITIONED)
	EnumType type;

}
