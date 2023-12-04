CREATE TABLE IF NOT EXISTS person(
	id bigserial NOT NULL,
	name character varying(100) NOT NULL,
	birth_date date,
	cpf character varying(14),
	employee boolean,
	manager boolean,
	CONSTRAINT pk_person PRIMARY KEY (id)
);