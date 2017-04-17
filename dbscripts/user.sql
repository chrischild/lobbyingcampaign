-- Table: projectcitizen."user"

-- DROP TABLE projectcitizen."user";

CREATE TABLE projectcitizen."user"
(
  id bigserial NOT NULL,
  username text NOT NULL,
  password text,
  salt text,
  address text,
  name text,
  email text NOT NULL,
  alerts boolean,
  token text,
  CONSTRAINT user_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE projectcitizen."user"
  OWNER TO "B@ck3Nd";
