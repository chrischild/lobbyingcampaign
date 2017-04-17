-- Table: projectcitizen.campaign

-- DROP TABLE projectcitizen.campaign;

CREATE TABLE projectcitizen.campaign
(
  id integer NOT NULL,
  goal money NOT NULL,
  tags text,
  cause name NOT NULL,
  contributions money NOT NULL DEFAULT 0.00,
  goal_met boolean,
  CONSTRAINT campaign_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE projectcitizen.campaign
  OWNER TO postgres;
