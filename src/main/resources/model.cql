CREATE KEYSPACE ponies
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 
  };

CREATE TABLE ponies.ponies_by_type(
  pony_name TEXT,
  pony_id UUID,
  pony_genre TEXT,
  birth_place TEXT,
  pony_type TEXT,
  age int,
  PRIMARY KEY((pony_type), pony_id,age )
) WITH CLUSTERING ORDER BY (pony_id desc,age desc);