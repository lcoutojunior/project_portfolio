CREATE TABLE IF NOT EXISTS member(
	project_id bigint NOT NULL,
	person_id bigint NOT NULL,
	CONSTRAINT pk_project_members PRIMARY KEY (project_id, person_id),
	CONSTRAINT fk_project_members_project FOREIGN KEY (project_id)
        REFERENCES project (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_project_members_person FOREIGN KEY (person_id)
        REFERENCES person (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);