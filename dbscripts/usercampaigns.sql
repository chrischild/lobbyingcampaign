-- Table: projectcitizen.usercampaigns

-- DROP TABLE projectcitizen.usercampaigns;

CREATE TABLE projectcitizen.usercampaigns
(
  id bigserial NOT NULL,
  "user" integer NOT NULL,
  campaign integer NOT NULL,
  CONSTRAINT usercampaignpk PRIMARY KEY (id),
  CONSTRAINT campignfk FOREIGN KEY (campaign)
      REFERENCES projectcitizen.campaign (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE projectcitizen.usercampaigns
  OWNER TO postgres;

-- Index: projectcitizen.fki_campignfk

-- DROP INDEX projectcitizen.fki_campignfk;

CREATE INDEX fki_campignfk
  ON projectcitizen.usercampaigns
  USING btree
  (campaign);

-- Index: projectcitizen.fki_userfk

-- DROP INDEX projectcitizen.fki_userfk;

CREATE INDEX fki_userfk
  ON projectcitizen.usercampaigns
  USING btree
  ("user");

