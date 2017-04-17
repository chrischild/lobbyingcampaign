-- Table: projectcitizen.userroles

-- DROP TABLE projectcitizen.userroles;

CREATE TABLE projectcitizen.userroles
(
  id bigserial NOT NULL,
  userid integer,
  roleid integer,
  CONSTRAINT userroles_pk PRIMARY KEY (id),
  CONSTRAINT ur_roleid_fk FOREIGN KEY (roleid)
      REFERENCES projectcitizen.roles (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ur_userid_fk FOREIGN KEY (userid)
      REFERENCES projectcitizen."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE projectcitizen.userroles
  OWNER TO postgres;

-- Index: projectcitizen.fki_ur_roleid_fk

-- DROP INDEX projectcitizen.fki_ur_roleid_fk;

CREATE INDEX fki_ur_roleid_fk
  ON projectcitizen.userroles
  USING btree
  (roleid);

-- Index: projectcitizen.fki_ur_userid_fk

-- DROP INDEX projectcitizen.fki_ur_userid_fk;

CREATE INDEX fki_ur_userid_fk
  ON projectcitizen.userroles
  USING btree
  (userid);

