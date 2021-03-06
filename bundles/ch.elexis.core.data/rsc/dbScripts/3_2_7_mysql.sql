ALTER TABLE KONTAKT engine=InnoDB;

CREATE TABLE ZUSATZADRESSE (
	ID       	VARCHAR(41) NOT NULL,
 	LASTUPDATE 	BIGINT DEFAULT NULL,
  	DELETED 	CHAR(1) DEFAULT '0',
	PRIMARY KEY (ID),
	Kontakt_ID		VARCHAR(25)  NOT NULL,
	STRASSE1		VARCHAR(255),
	STRASSE2		VARCHAR(255),
	PLZ				VARCHAR(6),		
	ORT				VARCHAR(255),
	LAND			CHAR(3),			
	TYP 			CHAR(4) DEFAULT '0',
	Anschrift		TEXT
);

ALTER TABLE ZUSATZADRESSE ADD CONSTRAINT FK_ZUSATZADRESSE_KONTAKT_ID FOREIGN KEY (Kontakt_ID) REFERENCES KONTAKT(ID);
