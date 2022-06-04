-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: microservices
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `userid` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'$2a$10$OVmlbrLvavBQckXuukLUe.DHsP/mJdY0dgFeIliMfLUxQ5QEgigjW','ROLE_LOCAL','rishu'),(2,'$2a$10$GA/bwXUPo0AdXc.tp1u/XO3MptZZ6Op3ECdKBQbW3L1TJJPcfS/ni','ROLE_LOCAL','praveen'),(3,'$2a$10$YTJP32kbrOYMBouHnjlkX.DCBfITRj30ajmKX75Bzw4oveLd2qWCW','ROLE_LOCAL','vikas'),(4,'$2a$10$/2DGgt90PZX0HuavS07WFuBwXAcaATAHjOOirzE3Ojq.pzw2vUCaq','ROLE_ADMIN','rishab'),(5,'$2a$10$lDRl8NZq6lRQtlkLt8AvguEy5ABe4yh/YqlTlnHNzWwwSWV.P9YPu','ROLE_ADMIN','rishabh'),(6,'$2a$10$aP13VffR5z08eFCGCBYqLe14/rQlU/GNdK4g2rMMAI6hA36VTavY2','ROLE_LOCAL','vikas'),(7,'$2a$10$JmQoiogMxbLUj4ZqXbnWvONhl6n1G1UzH0ZsSop482Fp7bz3wZcbO','ROLE_LOCAL','vinod');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `projectid` bigint NOT NULL,
  `projectstartdate` date DEFAULT NULL,
  `projectenddate` date DEFAULT NULL,
  `budgetallotted` int DEFAULT NULL,
  `budgetused` int DEFAULT NULL,
  `typeofproject` varchar(45) DEFAULT NULL,
  `projectname` varchar(45) DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`projectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'2022-05-12','2022-09-02',170000,88000,'client','Indigo',1),(2,'2022-02-22','2022-05-12',200000,8000,'client','Payback',3),(3,'2022-02-22','2022-05-12',150000,8000,'client','CRISIL',3),(4,'2022-02-22','2022-05-12',450000,75000,'INTERNAL','Tally',2),(5,'2022-02-22','2022-05-12',300000,75000,'client','Asian',4);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `officeid` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `projectid` bigint NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'bangalore','ICFNEXT','55686','1111',3,'ROLE_LOCAL','Vikas_T_S'),(2,'kanpur','ICFNEXT','55683','$2a$12$c8SO6acw1O5tH3fvyrfOQuA0YC8uoFbDiVrbrXMmldZImEQUNDYuy',2,'ROLE_ADMIN','rishabh'),(3,'kanpur','ICF','55684','3333',3,'ROLE_LOCAL','vinod'),(4,'kanpur','ICFNEXT','55682','4444',5,'ROLE_LOCAL','sibi'),(5,'kanpur','ICFNEXT','55681','5555',4,'ROLE_LOCAL','ashok'),(6,'kanpur','ICFNEXT','55680','6666',1,'ROLE_LOCAL','manoj');
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

-- Dump completed on 2022-06-04 18:32:33
