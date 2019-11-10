package org.jggn.cassandra.models;

import com.datastax.driver.core.Row;

public class PonyDTO {

	public Pony dto(Row t) {
		Pony p = new Pony();
		p.setPonyId(t.getUUID("pony_id"));
		p.setName(t.getString("pony_name"));
		p.setBirthPlace(t.getString("birth_place"));
		p.setGenre(EnumGenre.valueOf(t.getString("pony_genre")));
		p.setAge(t.getInt("age"));
		p.setType(EnumType.valueOf(t.getString("pony_type")));
		return p;
	}
}
