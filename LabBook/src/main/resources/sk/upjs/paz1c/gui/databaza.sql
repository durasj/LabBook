CREATE DATABASE  IF NOT EXISTS `lab_book` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lab_book`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lab_book
-- ------------------------------------------------------
-- Server version	5.6.35

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','123','admin@adminiak.sk'),(2,'adminka','456','adminka@adminocka.sk'),(3,'admin2','admin','admin2@ad.sk');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `available` tinyint(4) NOT NULL,
  `laboratory_id_laboratory` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_item`),
  KEY `fk_item_laboratory1_idx` (`laboratory_id_laboratory`),
  CONSTRAINT `fk_item_laboratory1` FOREIGN KEY (`laboratory_id_laboratory`) REFERENCES `laboratory` (`id_laboratory`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'detektor',2,0,1),(2,'mikroskop',1,1,2),(29,'laser',1,1,NULL),(31,'STM',1,0,66),(32,'SEM',1,0,66);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratory`
--

DROP TABLE IF EXISTS `laboratory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratory` (
  `id_laboratory` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_laboratory`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratory`
--

LOCK TABLES `laboratory` WRITE;
/*!40000 ALTER TABLE `laboratory` DISABLE KEYS */;
INSERT INTO `laboratory` VALUES (1,'optika','suteren'),(2,'technologia',NULL),(66,'kotolna','suteren');
/*!40000 ALTER TABLE `laboratory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id_note` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(1000) NOT NULL,
  `timestamp` datetime NOT NULL,
  `user_id_user` int(11) NOT NULL,
  `task_id_task` int(11) DEFAULT NULL,
  `project_id_project` int(11) DEFAULT NULL,
  `item_id_item` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_note`,`user_id_user`),
  KEY `fk_comment_user1_idx` (`user_id_user`),
  KEY `fk_comment_task1_idx` (`task_id_task`),
  KEY `fk_comment_project1_idx` (`project_id_project`),
  KEY `fk_comment_item1_idx` (`item_id_item`),
  CONSTRAINT `fk_comment_item1` FOREIGN KEY (`item_id_item`) REFERENCES `item` (`id_item`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_project1` FOREIGN KEY (`project_id_project`) REFERENCES `project` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_task1` FOREIGN KEY (`task_id_task`) REFERENCES `task` (`id_task`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (2,'prsi prsi len sa leje','2018-11-13 00:00:00',2,NULL,1,NULL),(3,'macicka sa z okna smeje\nmacicka sa z okna smeje\nmacicka sa z okna smeje\nmacicka sa z okna smeje','2018-11-13 00:00:00',1,3,NULL,NULL),(150,'Nieco ci to  Nieco ci to  Nieco ci to  Nieco ci to  Nieco ci to  \nNieco ci to  Nieco ci to  Nieco ci to  Nieco ci to  Nieco ci to    ','2018-12-11 14:24:39',1,1,NULL,NULL);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id_project` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `active` tinyint(4) NOT NULL,
  `date_from` date DEFAULT NULL,
  `date_until` date DEFAULT NULL,
  `each_item_available` tinyint(4) NOT NULL,
  `user_id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_project`,`user_id_user`),
  KEY `fk_project_user1_idx` (`user_id_user`),
  CONSTRAINT `fk_project_user1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'spravenie getAll',0,'2018-11-13','2018-11-22',0,1),(2,'nezaspat',1,'2018-11-13',NULL,0,2);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `project_id_project` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `active` tinyint(4) NOT NULL,
  `date_time_from` datetime DEFAULT NULL,
  `date_time_until` datetime DEFAULT NULL,
  `each_item_available` tinyint(4) NOT NULL,
  `laboratory_id_laboratory` int(11) DEFAULT NULL,
  `user_id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_task`,`user_id_user`),
  KEY `fk_task_project1_idx` (`project_id_project`),
  KEY `fk_task_laboratory1_idx` (`laboratory_id_laboratory`),
  KEY `fk_task_user1_idx` (`user_id_user`),
  CONSTRAINT `fk_task_laboratory1` FOREIGN KEY (`laboratory_id_laboratory`) REFERENCES `laboratory` (`id_laboratory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_project1` FOREIGN KEY (`project_id_project`) REFERENCES `project` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_user1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,1,'prva faza spravenia getAll2',0,'2018-11-13 00:00:00','2018-12-14 00:00:00',0,NULL,1),(2,2,'nezaspanie kodenim',0,NULL,NULL,1,NULL,2),(3,1,'druha faza spravenia getAll',0,'2018-11-13 00:00:00','2018-11-13 00:00:00',0,1,1);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_has_item`
--

DROP TABLE IF EXISTS `task_has_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_has_item` (
  `task_id_task` int(11) NOT NULL,
  `item_id_item` int(11) NOT NULL,
  PRIMARY KEY (`task_id_task`,`item_id_item`),
  KEY `fk_task_has_item_item1_idx` (`item_id_item`),
  KEY `fk_task_has_item_task1_idx` (`task_id_task`),
  CONSTRAINT `fk_task_has_item_item1` FOREIGN KEY (`item_id_item`) REFERENCES `item` (`id_item`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_has_item_task1` FOREIGN KEY (`task_id_task`) REFERENCES `task` (`id_task`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_has_item`
--

LOCK TABLES `task_has_item` WRITE;
/*!40000 ALTER TABLE `task_has_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_has_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `password` varchar(15) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'first*','heslo','first@first.sk'),(2,'Oliver','N/aA4Er,%-','oliver.vahovsky@gmail.com'),(4,'Laura','2357','laura.vistanova@gmail.com');
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

-- Dump completed on 2019-01-27 14:23:50
