DROP DATABASE IF EXISTS uvplatform;
CREATE DATABASE uvplatform;
USE uvplatform;

CREATE TABLE USER (
EMAIL VARCHAR(50) PRIMARY KEY,
NAME VARCHAR(50) NOT NULL,
SURNAME VARCHAR(50),
SEX CHAR,
PASSWORD VARCHAR(50) NOT NULL,
USER_TYPE TINYINT(1) NOT NULL,
SERIAL VARCHAR(10),
OFFICE VARCHAR(150),
PHONE VARCHAR(10)
);

CREATE TABLE SYSTEM_ATTRIBUTE (
SLUG VARCHAR(50) PRIMARY KEY,
VALUE VARCHAR(100) NOT NULL,
FK_USER VARCHAR(50) NOT NULL,
FOREIGN KEY (FK_USER) REFERENCES USER(EMAIL)
);

CREATE TABLE STATE (
ID_STATE INTEGER PRIMARY KEY AUTO_INCREMENT,
DESCRIPTION VARCHAR(100) NOT NULL
);

CREATE TABLE ENTE (
ID_ENTE INT(20) NOT NULL AUTO_INCREMENT,
EMAIL VARCHAR(50) NOT NULL,
NAME VARCHAR(100) NOT NULL,
SITE VARCHAR(50) NOT NULL,
STATO TINYINT NOT NULL DEFAULT 0,
PRIMARY KEY (ID_ENTE)
);

CREATE TABLE REQUEST (
ID_REQUEST INTEGER PRIMARY KEY AUTO_INCREMENT,
CERTIFICATE_SERIAL VARCHAR(50) NOT NULL,
LEVEL VARCHAR(7) NOT NULL,
RELEASE_DATE DATE NOT NULL,
EXPIRY_DATE DATE NOT NULL,
YEAR YEAR NOT NULL,
REQUESTED_CFU TINYINT(2) NOT NULL,
SERIAL INTEGER(10) NOT NULL,
VALIDATED_CFU TINYINT(2) NOT NULL,
FK_USER VARCHAR(50) NOT NULL,
FK_CERTIFIER INTEGER(20) NOT NULL,
FK_STATE INTEGER(20) NOT NULL,
FOREIGN KEY(FK_USER) REFERENCES USER(EMAIL),
FOREIGN KEY(FK_STATE) REFERENCES STATE(ID_STATE),
FOREIGN KEY(FK_CERTIFIER) REFERENCES ENTE(ID_ENTE)
);

CREATE TABLE INTERNSHIP_E (
ID_IE INTEGER PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(50) NOT NULL,
DURATION_CONVENTION INTEGER NOT NULL,
DATE_CONVENTION DATE NOT NULL,
AVAILABILITY INTEGER NOT NULL,
INFO LONGTEXT,
FK_TUTOR VARCHAR(50) NOT NULL,
FOREIGN KEY (FK_TUTOR) REFERENCES USER(EMAIL) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE INTERNSHIP_I (
ID_II INTEGER PRIMARY KEY AUTO_INCREMENT,
TUTOR_NAME VARCHAR(50) NOT NULL,
THEME VARCHAR(400) NOT NULL,
AVAILABILITY INTEGER,
RESOURCES VARCHAR(400),
GOALS VARCHAR(400),
FK_TUTOR VARCHAR(50) NOT NULL,
FOREIGN KEY (FK_TUTOR) REFERENCES USER(EMAIL) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE REQUEST_INTERNSHIP (
ID_REQUEST_I INTEGER PRIMARY KEY AUTO_INCREMENT,
TYPE VARCHAR(20) NOT NULL,
STATE VARCHAR(50),
FK_USER1 VARCHAR(50) NOT NULL,
FK_USER2 VARCHAR(50) NOT NULL,
FK_IE INTEGER,
FK_II INTEGER,
FOREIGN KEY(FK_USER1) REFERENCES USER(EMAIL),
FOREIGN KEY(FK_USER2) REFERENCES USER(EMAIL),
FOREIGN KEY(FK_IE) REFERENCES INTERNSHIP_E(ID_IE),
FOREIGN KEY(FK_II) REFERENCES INTERNSHIP_I(ID_II)
);

CREATE TABLE ATTACHED (
ID_ATTACHED INTEGER PRIMARY KEY AUTO_INCREMENT,
FILENAME VARCHAR(50) NOT NULL,
FK_REQUEST INTEGER DEFAULT NULL,
FK_USER VARCHAR(50) NOT NULL,
FK_REQUEST_I INTEGER DEFAULT NULL,
FOREIGN KEY(FK_REQUEST) REFERENCES REQUEST(ID_REQUEST),
FOREIGN KEY(FK_USER) REFERENCES USER(EMAIL),
FOREIGN KEY(FK_REQUEST_I) REFERENCES REQUEST_INTERNSHIP(ID_REQUEST_I)
);

INSERT INTO USER (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE)
VALUES('segreteria@unisa.it','Segreteria','Studenti','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','1');

INSERT INTO USER (EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE)
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

INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('rdeprisco@unisa.it','Roberto','De Prisco','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio ADA", "089969719");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('adebonis@unisa.it','Annalisa','De Bonis','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio Turing", "089969219");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('bcarpentieri@unisa.it','Bruno','Carpentieri','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio ISIS", "089969319");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('gpolese@unisa.it','Giuseppe','Polese','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio ADA", "089969714");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('storre@unisa.it','Salvatore','la Torre','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio di Verifica di Correttezza e SintesiAutomatica di Sistemi Digitali", "089969759");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('msebillo@unisa.it','Monica','Sebillo','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','3', "Laboratorio di Sistemi Informativi Geografici", "089969769");

INSERT INTO INTERNSHIP_I (ID_II, FK_TUTOR, TUTOR_NAME, THEME, AVAILABILITY, RESOURCES, GOALS)
VALUES (1, "rdeprisco@unisa.it", "Roberto De Prisco", "Mobile computing", 15, "Programmazione Object Oriented", "Padroneggiare Kotlin");
INSERT INTO INTERNSHIP_I (ID_II, FK_TUTOR, TUTOR_NAME, THEME, AVAILABILITY, RESOURCES, GOALS)
VALUES (2, "adebonis@unisa.it", "Annalisa de Bonis", "Algoritmi avanzati in Python", 60, "Progettazione Algoritmi", "Scrivere algoritmi con complessità lineare ");
INSERT INTO INTERNSHIP_I (ID_II, FK_TUTOR, TUTOR_NAME, THEME, AVAILABILITY, RESOURCES, GOALS)
VALUES (3, "bcarpentieri@unisa.it", "Bruno Carpentieri", "Compressione dati", 5, "Gestione dei dati", "System administrator");
INSERT INTO INTERNSHIP_I (ID_II, FK_TUTOR, TUTOR_NAME, THEME, AVAILABILITY, RESOURCES, GOALS)
VALUES (4, "gpolese@unisa.it", "Giuseppe Polese", "Studio dei Big Data", 2, "Gestione delle basi dati", "Studio dei Big data");
INSERT INTO INTERNSHIP_I (ID_II, FK_TUTOR, TUTOR_NAME, THEME, AVAILABILITY, RESOURCES, GOALS)
VALUES (5, "storre@unisa.it", "Salvatore La Torre", "Verifica automatica di correttezza dei programmi", 35, "Per avere un’idea dell’area di riferimentoconsultare il materiale del corso di Tecniche Automatiche per La Correttezza del Software (http://www.disrv.unisa.it/professori/latorre/didattica/TACS/)", "Sviluppare nuove metodologie. Integrare metodologie esistenti. Testare e confrontare metodologie esistenti. Partecipare allo sviluppo di tool di verifica. Ricercare nuovi risultati teorici");
INSERT INTO INTERNSHIP_I (ID_II, FK_TUTOR, TUTOR_NAME, THEME, AVAILABILITY, RESOURCES, GOALS)
VALUES (6, "msebillo@unisa.it", "Monica Sebillo", "Sistemi Informativi Geografici e applicazioni per Mobile GIS", 25, "http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807", "Sviluppo di servizi di intelligenza territoriale ");

INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@kineton.it','Kineton srl','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Napoli", "089452719");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('consulting@mate.it','Mate consulting','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Salerno", "089452799");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@italdata.it','Italdata spa','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Avellino", "089412719");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@technodesign.it','TechnoDesig','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Fisciano", "089152719");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@accasoftware.it','Acca','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Avellino", "089456719");
INSERT INTO USER (EMAIL, NAME, PASSWORD, USER_TYPE, OFFICE, PHONE)
VALUES('info@espansione.com','Espansione srl','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','4', "Avellino", "088452719");

INSERT INTO INTERNSHIP_E (ID_IE, FK_TUTOR, NAME, DURATION_CONVENTION, DATE_CONVENTION, AVAILABILITY, INFO)
VALUES (1, "info@kineton.it", "Kineton	s.r.l", 3, '2017-01-12', 30, "Kineton is here for all your engineering needs. And whether it’s tech, automotive, or communications, we’re on the cutting edge.");
INSERT INTO INTERNSHIP_E (ID_IE, FK_TUTOR, NAME, DURATION_CONVENTION, DATE_CONVENTION, AVAILABILITY, INFO)
VALUES (2, "consulting@mate.it", "Mate Consulting	s.r.l.", 5, '2017-09-12', 25, "Creiamo valore aggiunto per i nostri clienti Progettiamo nuove opportunità di business. Sviluppiamo sistemi integrati");
INSERT INTO INTERNSHIP_E (ID_IE, FK_TUTOR, NAME, DURATION_CONVENTION, DATE_CONVENTION, AVAILABILITY, INFO)
VALUES (3, "info@italdata.it", "Italdata S.p.a.", 3, '2017-09-12', 16, "Italdata S.p.A., società nel settore dell'Information & Communication Technology, è specializzata nello sviluppo di servizi e soluzioni nelle aree dell'e-Learning e social networking e dei servizi avanzati per la Mobilità, della sicurezza e delle soluzioni per la business intelligence.");
INSERT INTO INTERNSHIP_E (ID_IE, FK_TUTOR, NAME, DURATION_CONVENTION, DATE_CONVENTION, AVAILABILITY, INFO)
VALUES (4, "info@technodesign.it", "TechnoDesig	S.r.l.", 5, '2017-09-22', 5, "La vendita diretta e le consegne in tanti uffici cittadini sono soltanto due dei  nostri fiori all’occhiello , per non parlare della presenza on line caratterizzata da una vasta diponibilità del materiale grazie a un fornito magazzino.");
INSERT INTO INTERNSHIP_E (ID_IE, FK_TUTOR, NAME, DURATION_CONVENTION, DATE_CONVENTION, AVAILABILITY, INFO)
VALUES (5, "info@accasoftware.it", "Acca Software S.p.a", 10, '2015-11-12', 24, "ACCA è il leader italiano del software tecnico e del BIM. Con i suoi software ha rivoluzionato il mondo dell’edilizia contribuendo alla digitalizzazione dell’attività di ingegneri, geometri, architetti, studi professionali, imprese edili e pubbliche amministrazioni.
ACCA vanta oggi una gamma di oltre 90 prodotti (molti dei quali diventati in pochi anni veri e propri standard del settore) ed è la prima azienda italiana ad aver lanciato sul mercato software BIM certificati IFC.");
INSERT INTO INTERNSHIP_E (ID_IE, FK_TUTOR, NAME, DURATION_CONVENTION, DATE_CONVENTION, AVAILABILITY, INFO)
VALUES (6, "info@espansione.com", "Espansione	s.r.l.", 2, '2019-09-30', 7, "La mission era ed è semplice ed esplicita: orientare gli operatori della sanità, del termalismo, dell’assistenza socio-sanitaria a mettersi in discussione per migliorarsi, attraverso la formazione continua.");
