CREATE TABLE IF NOT EXISTS project(
	id bigserial NOT NULL,
	name VARCHAR(200) NOT NULL,
	begin_date DATE,
	expected_end_date DATE,
	actual_end_date DATE,
	description VARCHAR(5000),
	status VARCHAR(45),
	total_budget FLOAT,
	risk VARCHAR(45),
	manager_id bigint NOT NULL,
	CONSTRAINT pk_project PRIMARY KEY (id),
	CONSTRAINT fk_manager FOREIGN KEY (manager_id)
	REFERENCES person (id) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);