-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: appanalytics
-- ------------------------------------------------------
-- Server version	5.6.38-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_mail`
--

DROP TABLE IF EXISTS `tbl_mail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mail` (
  `MAIL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TOKEN` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `IS_LATEST` bit(1) DEFAULT NULL,
  `EXPIRATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REC_INS_DT` timestamp NULL DEFAULT NULL,
  `REC_LAST_UPD_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `REC_INS_USR_ID` int(11) DEFAULT NULL,
  `REC_LAST_UPD_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`MAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_mail`
--

LOCK TABLES `tbl_mail` WRITE;
/*!40000 ALTER TABLE `tbl_mail` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_mail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_oauth_access_tokens`
--

DROP TABLE IF EXISTS `tbl_oauth_access_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_oauth_access_tokens` (
  `ACCESS_TOKEN` varchar(255) NOT NULL,
  `CLIENT_ID` varchar(255) NOT NULL,
  `EXPIRATION_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REC_INS_DT` timestamp NULL DEFAULT NULL,
  `REC_LAST_UPD_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REC_INS_USR_ID` int(11) DEFAULT NULL,
  `REC_LAST_UPD_ID` int(11) DEFAULT NULL,
  `CREDENTIAL_ID` int(11) NOT NULL,
  PRIMARY KEY (`ACCESS_TOKEN`),
  KEY `fk_tbl_oauth_access_tokens_tbl_user_credentials1_idx` (`CREDENTIAL_ID`),
  CONSTRAINT `fk_tbl_oauth_access_tokens_tbl_user_credentials1` FOREIGN KEY (`CREDENTIAL_ID`) REFERENCES `tbl_user_credentials` (`CREDENTIAL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_oauth_access_tokens`
--

LOCK TABLES `tbl_oauth_access_tokens` WRITE;
/*!40000 ALTER TABLE `tbl_oauth_access_tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_oauth_access_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user_credentials`
--

DROP TABLE IF EXISTS `tbl_user_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_credentials` (
  `CREDENTIAL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) DEFAULT NULL,
  `GOOGLE_ID` varchar(45) DEFAULT NULL,
  `FACEBOOK_ID` varchar(45) DEFAULT NULL,
  `TWITTER_ID` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `MOBILE_NUMBER` varchar(55) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `SALT` varchar(45) DEFAULT NULL,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  `EMAIL_VERIFIED` bit(1) DEFAULT NULL,
  `TERMS_OF_USE` bit(1) DEFAULT NULL,
  `REC_INS_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `REC_LAST_UPD_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REC_INS_USR_ID` int(11) DEFAULT NULL,
  `REC_LAST_UPD_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`CREDENTIAL_ID`),
  KEY `fk_tbl_user_credentials_tbl_user_profile_idx` (`USER_ID`),
  CONSTRAINT `fk_tbl_user_credentials_tbl_user_profile` FOREIGN KEY (`USER_ID`) REFERENCES `tbl_user_profile` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user_credentials`
--

LOCK TABLES `tbl_user_credentials` WRITE;
/*!40000 ALTER TABLE `tbl_user_credentials` DISABLE KEYS */;
INSERT INTO `tbl_user_credentials` VALUES (1,'vicky',NULL,NULL,NULL,'vicky@gmail.com',NULL,'cc029e8a8024b77eed7478a3ce3487d30a33e15a35d1568da067e795c8f35dd6','/yJmpI4+g/8HiophF6eD3jcC+XS/pi/BbRcmqOlGTUQ=','','\0','','2018-10-31 15:27:51',NULL,9,NULL,9);
/*!40000 ALTER TABLE `tbl_user_credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user_profile`
--

DROP TABLE IF EXISTS `tbl_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_profile` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `F_NAME` varchar(45) DEFAULT NULL,
  `L_NAME` varchar(45) DEFAULT NULL,
  `GENDER` varchar(45) DEFAULT NULL,
  `ROLE_ID` varchar(45) DEFAULT NULL,
  `REC_INC_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `REC_LAST_UPD_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REC_INS_USR_ID` int(11) DEFAULT NULL,
  `REC_LAST_UPD_USR_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user_profile`
--

LOCK TABLES `tbl_user_profile` WRITE;
/*!40000 ALTER TABLE `tbl_user_profile` DISABLE KEYS */;
INSERT INTO `tbl_user_profile` VALUES (9,'vicky','M',NULL,NULL,NULL,'2018-10-31 15:27:51',9,NULL);
/*!40000 ALTER TABLE `tbl_user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-01 13:17:59
