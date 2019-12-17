DROP DATABASE IF EXISTS uvplatform;
CREATE DATABASE uvplatform;
USE uvplatform;

CREATE TABLE USER (
EMAIL varchar(50) not null,
NAME varchar(50) not null,
SURNAME varchar(50) ,
SEX char not null,
PASSWORD varchar(50) not null,
USER_TYPE tinyint(1) not null,
SERIAL char(10),
OFFICE varchar(150),
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
theme varchar(400) not null,
availability int(3),
resources varchar(400),
goals varchar(400),
primary key(id_ii)
);

CREATE TABLE request_internship (
id_request_i int(10) not null AUTO_INCREMENT,
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

INSERT INTO internship_i (id_ii, tutor_name, theme, availability, resources, goals)
VALUES (1, "Roberto De Prisco", "Mobile computing", 15, "Programmazione Object Oriented", "Padroneggiare Kotlin");
INSERT INTO internship_i (id_ii, tutor_name, theme, availability, resources, goals)
VALUES (2, "Annalisa de Bonis", "Algoritmi avanzati in Python", 60, "Progettazione Algoritmi", "Scrivere algoritmi con complessità lineare "); 
INSERT INTO internship_i (id_ii, tutor_name, theme, availability, resources, goals)
VALUES (3, "Bruno Carpentieri", "Compressione dati", 5, "Gestione dei dati", "System administrator");
INSERT INTO internship_i (id_ii, tutor_name, theme, availability, resources, goals)
VALUES (4, "Giuseppe Polese", "Studio dei Big Data", 2, "Gestione delle basi dati", "Studio dei Big data");
INSERT INTO internship_i (id_ii, tutor_name, theme, availability, resources, goals)
VALUES (5, "Salvatore La Torre", "Verifica automatica di correttezza dei programmi", 35, "Per avere un’idea dell’area di riferimentoconsultare il materiale del corso di Tecniche Automatiche per La Correttezza del Software (http://www.disrv.unisa.it/professori/latorre/didattica/TACS/)", "Sviluppare nuove metodologie. Integrare metodologie esistenti. Testare e confrontare metodologie esistenti. Partecipare allo sviluppo di tool di verifica. Ricercare nuovi risultati teorici");
INSERT INTO internship_i (id_ii, tutor_name, theme, availability, resources, goals)
VALUES (6, "Monica Sebillo", "Sistemi Informativi Geografici e applicazioni per Mobile GIS", 25, "http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807", "Sviluppo di servizi di intelligenza territoriale ");

INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('rdeprisco@unisa.it','Roberto','De Prisco','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio ADA", 089969719);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('adebonis@unisa.it','Annalisa','De Bonis','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio Turing", 089969219);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('bcarpentieri@unisa.it','Bruno','Carpentieri','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio ISIS", 089969319);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('gpolese@unisa.it','Giuseppe','Polese','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio ADA", 089969714);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('storre@unisa.it','Salvatore','la Torre','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio di Verifica di Correttezza e SintesiAutomatica di Sistemi Digitali", 089969759);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('msebillo@unisa.it','Monica','Sebillo','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio di Sistemi Informativi Geografici", 089969769);


INSERT INTO perform (FK_USER, id_ii)
VALUES ("rdeprisco@unisa.it",1);
INSERT INTO perform (FK_USER, id_ii)
VALUES ("adebonis@unisa.it",2);
INSERT INTO perform (FK_USER, id_ii)
VALUES ("bcarpentieri@unisa.it",3);
INSERT INTO perform (FK_USER, id_ii)
VALUES ("gpolese@unisa.it",4);
INSERT INTO perform (FK_USER, id_ii)
VALUES ("storre@unisa.it",5);
INSERT INTO perform (FK_USER, id_ii)
VALUES ("msebillo@unisa.it",6);



INSERT INTO internship_e (id_ie, name, duration_convention, date_convention, availability, info)
VALUES (1, "Kineton	s.r.l", 3, '2017-01-12', 30, "Kineton is here for all your engineering needs. And whether it’s tech, automotive, or communications, we’re on the cutting edge.");
INSERT INTO internship_e (id_ie, name, duration_convention, date_convention, availability, info)
VALUES (2, "Mate Consulting	s.r.l.", 5, '2017-09-12', 25, "Creiamo valore aggiunto per i nostri clienti Progettiamo nuove opportunità di business. Sviluppiamo sistemi integrati");
INSERT INTO internship_e (id_ie, name, duration_convention, date_convention, availability, info)
VALUES (3, "Italdata S.p.a.", 3, '2017-09-12', 16, "Italdata S.p.A., società nel settore dell'Information & Communication Technology, è specializzata nello sviluppo di servizi e soluzioni nelle aree dell'e-Learning e social networking e dei servizi avanzati per la Mobilità, della sicurezza e delle soluzioni per la business intelligence.");
INSERT INTO internship_e (id_ie, name, duration_convention, date_convention, availability, info)
VALUES (4, "TechnoDesig	S.r.l.", 5, '2017-09-22', 5, "La vendita diretta e le consegne in tanti uffici cittadini sono soltanto due dei  nostri fiori all’occhiello , per non parlare della presenza on line caratterizzata da una vasta diponibilità del materiale grazie a un fornito magazzino.");
INSERT INTO internship_e (id_ie, name, duration_convention, date_convention, availability, info)
VALUES (5, "Acca Software S.p.a", 10, '2015-11-12', 24, "ACCA è il leader italiano del software tecnico e del BIM. Con i suoi software ha rivoluzionato il mondo dell’edilizia contribuendo alla digitalizzazione dell’attività di ingegneri, geometri, architetti, studi professionali, imprese edili e pubbliche amministrazioni.
ACCA vanta oggi una gamma di oltre 90 prodotti (molti dei quali diventati in pochi anni veri e propri standard del settore) ed è la prima azienda italiana ad aver lanciato sul mercato software BIM certificati IFC.");
INSERT INTO internship_e (id_ie, name, duration_convention, date_convention, availability, info)
VALUES (6, "Espansione	s.r.l.", 2, '2019-09-30', 7, "La mission era ed è semplice ed esplicita: orientare gli operatori della sanità, del termalismo, dell’assistenza socio-sanitaria a mettersi in discussione per migliorarsi, attraverso la formazione continua.");



INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@kineton.it','Kineton srl','','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Napoli", 089452719);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('consulting@mate.it','Mate consulting','','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Salerno", 089452799);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@italdata.it','Italdata spa','','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Avellino", 089412719);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@technodesign.it','TechnoDesig','','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Fisciano", 089152719);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@accasoftware.it','Acca','','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Avellino", 089456719);
INSERT INTO user (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@espansione.com','Espansione srl','','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Avellino", 088452719);


INSERT INTO do(FK_USER, id_ie)
VALUES ("info@kineton.it", 1);
INSERT INTO do(FK_USER, id_ie)
VALUES ("consulting@mate.it", 2);
INSERT INTO do(FK_USER, id_ie)
VALUES ("info@italdata.it", 3);
INSERT INTO do(FK_USER, id_ie)
VALUES ("info@technodesign.it", 4);
INSERT INTO do(FK_USER, id_ie)
VALUES ("info@accasoftware.it", 5);
INSERT INTO do(FK_USER, id_ie)
VALUES ("info@espansione.com", 6);





