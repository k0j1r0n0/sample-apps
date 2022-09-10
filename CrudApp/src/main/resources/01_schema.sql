DROP TABLE IF EXISTS key_value_table;

CREATE TABLE key_value_table
(
   id SERIAL NOT NULL,
   key VARCHAR(100) NOT NULL,
   value VARCHAR(100) NOT NULL,
   PRIMARY KEY(id)
);