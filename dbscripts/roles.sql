-- Table: projectcitizen.roles

-- DROP TABLE projectcitizen.roles;

CREATE TABLE projectcitizen.roles
(
  id bigserial NOT NULL,
  role text,
  CONSTRAINT roles_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE projectcitizen.roles
  OWNER TO postgres;
