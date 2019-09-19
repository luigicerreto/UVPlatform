-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: englishvalidation
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attached`
--

DROP TABLE IF EXISTS `attached`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attached` (
  `ID_ATTACHED` int(20) NOT NULL AUTO_INCREMENT,
  `FILENAME` varchar(50) NOT NULL,
  `FK_REQUEST` int(20) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_ATTACHED`),
  KEY `FK_REQUEST` (`FK_REQUEST`),
  KEY `FK_USER` (`FK_USER`),
  CONSTRAINT `attached_ibfk_1` FOREIGN KEY (`FK_REQUEST`) REFERENCES `request` (`id_request`),
  CONSTRAINT `attached_ibfk_2` FOREIGN KEY (`FK_USER`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attached`
--

LOCK TABLES `attached` WRITE;
/*!40000 ALTER TABLE `attached` DISABLE KEYS */;
INSERT INTO `attached` VALUES (1,'certificato.pdf',1,'prova@unisa.it'),(2,'richiesta.pdf',1,'prova@unisa.it'),(3,'certificato.pdf',2,'g.prova@studenti.unisa.it'),(4,'richiesta.pdf',2,'g.prova@studenti.unisa.it'),(5,'richiesta.pdf',9,'04wmljf0wy.@studenti.unisa.it'),(6,'certificato',9,'04wmljf0wy.@studenti.unisa.it');
/*!40000 ALTER TABLE `attached` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ente`
--

DROP TABLE IF EXISTS `ente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ente` (
  `ID_ENTE` int(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `SITE` varchar(50) NOT NULL,
  `STATO` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_ENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ente`
--

LOCK TABLES `ente` WRITE;
/*!40000 ALTER TABLE `ente` DISABLE KEYS */;
INSERT INTO `ente` VALUES (1,'','Cambridge Assessment English','',1),(2,'','City and Guilds (Pitman)','',1),(3,'','Edexcel /Pearson Ltd','',1),(4,'','Educational Testing Service (ETS)','',1),(5,'','English Speaking Board (ESB)','',1),(6,'','International English Language Testing System (IELTS)','',1),(7,'emailTest@libero.it','Pearson - LCCI','',1),(8,'','Pearson - EDI','www.google.it',1),(9,'','Trinity College London (TCL)','',1),(10,'','Department of English, Faculty of Arts - University of Malta','',1),(11,'','NQAI - ACELS','',1),(12,'','Ascentis','',1),(13,'','AIM Awards','',1),(14,'','Learning Resource Network (LRN)','',1),(15,'','British Institutes','',1),(16,'','Gatehouse Awards Ltd','',1),(17,'','LanguageCert','',1);
/*!40000 ALTER TABLE `ente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request` (
  `ID_REQUEST` int(20) NOT NULL AUTO_INCREMENT,
  `CERTIFICATE_SERIAL` varchar(50) NOT NULL,
  `LEVEL` varchar(7) NOT NULL,
  `RELEASE_DATE` date NOT NULL,
  `EXPIRY_DATE` date NOT NULL,
  `YEAR` year(4) NOT NULL,
  `REQUESTED_CFU` tinyint(2) NOT NULL,
  `SERIAL` int(10) NOT NULL,
  `VALIDATED_CFU` tinyint(2) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  `FK_CERTIFIER` int(20) NOT NULL,
  `FK_STATE` int(20) NOT NULL,
  PRIMARY KEY (`ID_REQUEST`),
  KEY `FK_USER` (`FK_USER`),
  KEY `FK_STATE` (`FK_STATE`),
  KEY `FK_CERTIFIER` (`FK_CERTIFIER`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`FK_USER`) REFERENCES `user` (`email`),
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`FK_STATE`) REFERENCES `state` (`id_state`),
  CONSTRAINT `request_ibfk_3` FOREIGN KEY (`FK_CERTIFIER`) REFERENCES `ente` (`id_ente`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'B.6.56546','A1','2017-05-25','2018-05-25',2018,3,512103579,6,'prova@unisa.it',7,3),(2,'A00000052','GRADE 1','2014-04-01','2019-04-01',2018,3,512105846,6,'g.prova@studenti.unisa.it',1,4),(3,'A00000021','C2','2018-07-14','2023-07-14',2018,3,512106974,3,'m.prova@studenti.unisa.it',8,3),(4,'A00000055','B1','2019-01-14','2019-05-14',2019,3,512102369,0,'g.cirinella1@studenti.unisa.it',3,7),(5,'A00000077','A2','2019-01-14','2019-01-15',2019,6,512106987,3,'g.cirinella2@studenti.unisa.it',1,6),(6,'A00000022','C1','2019-01-14','2019-01-22',2019,3,512104456,6,'g.cirinella3@studenti.unisa.it',3,5),(7,'A00000088','A1','2019-01-15','2019-01-20',2019,6,512108547,3,'g.cirinella4@studenti.unisa.it',1,2),(8,'A00000001','A1','2015-02-14','2020-02-14',2018,6,512103578,0,'a.prova@studenti.unisa.it',1,2),(9,'A00000001','A1','2015-02-14','2020-02-14',2018,3,345643322,4,'04wmljf0wy.@studenti.unisa.it',5,2),(10,'A00000077','B1','2015-02-14','2020-02-14',2019,6,512468975,3,'g.cirinella2@studenti.unisa.it',1,2),(11,'A00000004','A2','2015-02-14','2020-02-14',2019,3,512469875,6,'x.x@studenti.unisa.it',1,1);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `state` (
  `ID_STATE` int(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_STATE`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'Parzialmente Completata'),(2,'In elaborazione dalla Segreteria'),(3,'In elaborazione dall&quot; Amministratore'),(4,'Accettata e In elaborazione dal Consiglio Didattico'),(5,'Rifiutata e In elaborazione dal Consiglio Didattico'),(6,'Conclusa e Accettata'),(7,'Conclusa e Rifiutata');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_attribute`
--

DROP TABLE IF EXISTS `system_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `system_attribute` (
  `SLUG` varchar(50) NOT NULL,
  `VALUE` varchar(100) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  PRIMARY KEY (`SLUG`),
  KEY `FK_USER` (`FK_USER`),
  CONSTRAINT `system_attribute_ibfk_1` FOREIGN KEY (`FK_USER`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_attribute`
--

LOCK TABLES `system_attribute` WRITE;
/*!40000 ALTER TABLE `system_attribute` DISABLE KEYS */;
INSERT INTO `system_attribute` VALUES ('request-accepted','6','fferrucci@unisa.it'),('request-allowed-extension-upload','.pdf','fferrucci@unisa.it'),('request-matriculation-year-range','5','fferrucci@unisa.it'),('request-max-cfu','12','fferrucci@unisa.it'),('request-min-cfu','1','fferrucci@unisa.it'),('request-number-max-upload','2','fferrucci@unisa.it'),('request-partially-completed','1','fferrucci@unisa.it'),('request-refused','7','fferrucci@unisa.it'),('request-upload-path','//home//vale//newWorkspace//EV_EnglishValidation//uploads','fferrucci@unisa.it'),('request-working-admin','3','fferrucci@unisa.it'),('request-working-educational-advice-1','4','fferrucci@unisa.it'),('request-working-educational-advice-2','5','fferrucci@unisa.it'),('request-working-secretary','2','fferrucci@unisa.it');
/*!40000 ALTER TABLE `system_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `EMAIL` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `SURNAME` varchar(50) NOT NULL,
  `SEX` char(1) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `USER_TYPE` tinyint(1) NOT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('04wmljf0wy.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('3glwlrjzdr.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('4b8hihfxm5.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('4fqnvaebhj.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('7rz67sjure.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('8uqzgwq8tg.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('984hapm2ku.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('a.prova@studenti.unisa.it','Andrea','Iannaccone','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('aakjzfyjcy.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('b6cnnxcawa.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('dpp6p5qwm5.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('eonkbugt7g.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('f6zwmkzcgb.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('fferrucci@unisa.it','Luigia','Melchionno','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',2),('frbier1g8i.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g.c@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g.cirinella1@studenti.unisa.it','Giuseppe','Cirinella','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g.cirinella2@studenti.unisa.it','Giuseppe','Cirinella','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g.cirinella3@studenti.unisa.it','Giuseppe','Cirinella','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g.cirinella4@studenti.unisa.it','Giuseppe','Cirinella','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g.prova2@studenti.unisa.it','Giuseppina','Cirina','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g.prova@studenti.unisa.it','Giacomo','Lorenzin','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g0pa5qcmxu.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('g9kan58sfe.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('gkcq3sys5z.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('jcggdxgazu.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('k2y5ytd75x.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('k9tp2cdsmn.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('loginerror@studenti.unisa.it','test','test','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',3),('m.prova@studenti.unisa.it','Michele','Paolella','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('nxsfan3tr9.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('ooibivln8l.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('peavppeng2.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('prova@unisa.it','Paolo','Benigno','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('qnvcaaltlh.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('rx1xgbuf9n.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('segreteria@unisa.it','Segreteria','Studenti','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',1),('tqytizh1nj.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('x.x@studenti.unisa.it','test','test','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0),('yd3d23uwph.@studenti.unisa.it','Giuseppe','Cirino','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-22 11:29:19
