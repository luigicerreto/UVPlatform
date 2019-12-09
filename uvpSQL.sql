DROP DATABASE IF EXISTS uvplatform;
CREATE DATABASE uvplatform;
USE uvplatform;

CREATE TABLE USER (
EMAIL varchar(50) not null,
NAME varchar(50) not null,
SURNAME varchar(50) not null,
SEX char not null,
PASSWORD varchar(50) not null,
USER_TYPE tinyint(1) not null,
SERIAL char(10),
OFFICE varchar(50),
phone int(10),
primary key (EMAIL)
);

CREATE TABLE SYSTEM_ATTRIBUTE (
SLUG varchar(50) not null, 
VALUE varchar(100) not null, 
FK_USER varchar(50) not null,
primary key (SLUG),
foreign key (FK_USER) references USER(EMAIL)
);


CREATE TABLE REQUEST (
ID_REQUEST int(20) not null AUTO_INCREMENT,
CERTIFICATE_SERIAL VARCHAR(50) not null,
LEVEL varchar(7) not null,
RELEASE_DATE date not null, 
EXPIRY_DATE date not null, 
YEAR year not null, 
REQUESTED_CFU tinyint(2) not null, 
SERIAL int(10) not null, 
VALIDATED_CFU tinyint(2) not null, 
FK_USER varchar(50) not null,
FK_CERTIFIER int(20) not null, 
FK_STATE int(20) not null, 
primary key(ID_REQUEST)
);

CREATE TABLE ATTACHED (
ID_ATTACHED int(20) not null AUTO_INCREMENT,
FILENAME varchar(50) not null,
FK_REQUEST int(20) DEFAULT null,
FK_USER varchar(50) not null,
FK_REQUEST_I int(10) DEFAULT null,
primary key(ID_ATTACHED)
);


CREATE TABLE ENTE (
ID_ENTE int(20) not null AUTO_INCREMENT,
EMAIL varchar(50) not null,
NAME varchar(100) not null,
SITE varchar(50) not null,
STATO TINYINT not null DEFAULT 0,
primary key (ID_ENTE)
);

CREATE TABLE STATE (
ID_STATE int(20) not null AUTO_INCREMENT, 
DESCRIPTION varchar(100) not null,
primary key (ID_STATE)
);

CREATE TABLE internship_e (
id_ie int(10) not null AUTO_INCREMENT,
name varchar(50) not null,
duration_convention int(3) not null,
date_convention DATE not null,
availability int(3) not null,
info LONGTEXT, 
primary key(id_ie)
);

CREATE TABLE internship_i (
id_ii int(10) not null AUTO_INCREMENT,
tutor_name varchar(50) not null,
theme varchar(50) not null,
availability int(3),
resources varchar(200),
goals varchar(100),
primary key(id_ii)
);

CREATE TABLE request_internship (
id_request_i int(10) not null,
type varchar(20) not null,
FK_USER1 varchar(50) not null,
FK_USER2 varchar(50) not null,
FK_IE int(10),
FK_II int(10),
primary key(id_request_i)
);

CREATE TABLE do (
FK_USER varchar(50),
id_ie int(10),
primary key(FK_USER, id_ie)
);

CREATE TABLE perform (
FK_USER varchar(50),
id_ii int(10),
primary key(FK_USER, id_ii)
);


USE uvplatform;

ALTER TABLE REQUEST 
ADD foreign key(FK_USER) references USER(EMAIL);

ALTER TABLE REQUEST 
ADD foreign key(FK_STATE) references STATE(ID_STATE);

ALTER TABLE REQUEST 
ADD foreign key(FK_CERTIFIER) references ENTE(ID_ENTE);

ALTER TABLE ATTACHED
ADD foreign key(FK_REQUEST) references REQUEST(ID_REQUEST);

ALTER TABLE ATTACHED
ADD foreign key(FK_USER) references USER(EMAIL);

ALTER TABLE ATTACHED 
ADD foreign key(FK_REQUEST_I) references request_internship(ID_REQUEST_I);


ALTER TABLE request_internship 
ADD foreign key(FK_USER1) references user(EMAIL),
ADD foreign key(FK_USER2) references user(EMAIL),
ADD foreign key(FK_IE) references internship_e(id_ie),
ADD foreign key(FK_II) references internship_i(id_ii);


ALTER TABLE do 
ADD foreign key(FK_USER) references user(EMAIL),
ADD foreign key(id_ie) references internship_e(id_ie);


ALTER TABLE perform 
ADD foreign key(FK_USER) references user(EMAIL),
ADD foreign key(id_ii) references internship_i(id_ii);


USE uvplatform;
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE)
VALUES('segreteria@unisa.it','Segreteria','Studenti','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','1'); 

INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE)
VALUES('fferrucci@unisa.it','Filomena','Ferrucci','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','2');

INSERT INTO STATE 
VALUES (1,'Parzialmente Completata');
INSERT INTO STATE 
VALUES (2,'In elaborazione dalla Segreteria');
INSERT INTO STATE 
VALUES (3,'In elaborazione dall&quot; Amministratore');
INSERT INTO STATE 
VALUES (4,'Accettata e In elaborazione dal Consiglio Didattico');
INSERT INTO STATE 
VALUES (5,'Rifiutata e In elaborazione dal Consiglio Didattico');
INSERT INTO STATE 
VALUES (6,'Conclusa e Accettata');
INSERT INTO STATE 
VALUES (7,'Conclusa e Rifiutata');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-partially-completed', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-secretary', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-admin', '3','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-educational-advice-1', '4','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-educational-advice-2', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-accepted', '6','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-refused', '7','fferrucci@unisa.it');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-number-max-upload', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-allowed-extension-upload', '.pdf','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-matriculation-year-range', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-min-cfu', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-max-cfu', '12','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-upload-path', '//home//vale//newWorkspace//EV_EnglishValidation//uploads//','fferrucci@unisa.it');

INSERT INTO ENTE
VALUES ('1', '','Cambridge Assessment English','', 1);
INSERT INTO ENTE
VALUES ('2', '','City and Guilds (Pitman)','', 1);
INSERT INTO ENTE
VALUES ('3', '','Edexcel /Pearson Ltd','', 1);
INSERT INTO ENTE
VALUES ('4', '','Educational Testing Service (ETS)','', 1);
INSERT INTO ENTE
VALUES ('5', '','English Speaking Board (ESB)','', 1);
INSERT INTO ENTE
VALUES ('6', '','International English Language Testing System (IELTS)','', 1);
INSERT INTO ENTE
VALUES ('7', '','Pearson - LCCI','', 1);
INSERT INTO ENTE
VALUES ('8', '','Pearson - EDI','', 1);
INSERT INTO ENTE
VALUES ('9', '','Trinity College London (TCL)','', 1);
INSERT INTO ENTE
VALUES ('10', '','Department of English, Faculty of Arts - University of Malta','', 1);
INSERT INTO ENTE
VALUES ('11', '','NQAI - ACELS','', 1);
INSERT INTO ENTE
VALUES ('12', '','Ascentis','', 1);
INSERT INTO ENTE
VALUES ('13', '','AIM Awards','', 1);
INSERT INTO ENTE
VALUES ('14', '','Learning Resource Network (LRN)','', 1);
INSERT INTO ENTE
VALUES ('15', '','British Institutes','', 1);
INSERT INTO ENTE
VALUES ('16', '','Gatehouse Awards Ltd','', 1);
INSERT INTO ENTE
VALUES ('17', '','LanguageCert','', 1);



