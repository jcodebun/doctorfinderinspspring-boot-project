-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: doctorfinderdb
-- ------------------------------------------------------
-- Server version	8.0.30

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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`doctorfinderdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `doctorfinderdb`;
--
-- Table structure for table `d_appointment`
--

DROP TABLE IF EXISTS `d_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `d_appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `appointment_id` bigint DEFAULT NULL,
  `date_of_appointment` varchar(255) DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  `slot` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `doctor` bigint DEFAULT NULL,
  `user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5ve4aeq81h7ygbkvlaq6fv98n` (`doctor`),
  KEY `FKmbsjjkbsxea1pam6qoitkyr9h` (`user`),
  CONSTRAINT `FK5ve4aeq81h7ygbkvlaq6fv98n` FOREIGN KEY (`doctor`) REFERENCES `d_doctor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKmbsjjkbsxea1pam6qoitkyr9h` FOREIGN KEY (`user`) REFERENCES `d_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_appointment`
--

LOCK TABLES `d_appointment` WRITE;
/*!40000 ALTER TABLE `d_appointment` DISABLE KEYS */;
INSERT INTO `d_appointment` VALUES (1,NULL,'2023-04-23 20:28:45.444000',NULL,NULL,NULL,'2023-05-07',1,'3 pm - 4 pm','PENDING',2,1,2);
/*!40000 ALTER TABLE `d_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d_doctor`
--

DROP TABLE IF EXISTS `d_doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `d_doctor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `dr_fees` decimal(19,2) DEFAULT NULL,
  `dr_experience` varchar(255) DEFAULT NULL,
  `dr_name` varchar(255) DEFAULT NULL,
  `dr_location` varchar(255) DEFAULT NULL,
  `speciality_id` bigint DEFAULT NULL,
  `speciality` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaiomskbuq1ralpekpodeofama` (`speciality`),
  CONSTRAINT `FKaiomskbuq1ralpekpodeofama` FOREIGN KEY (`speciality`) REFERENCES `d_speciality` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_doctor`
--

LOCK TABLES `d_doctor` WRITE;
/*!40000 ALTER TABLE `d_doctor` DISABLE KEYS */;
INSERT INTO `d_doctor` VALUES (1,'Admin@admin.com','2023-04-23 20:27:05.266000','Admin@admin.com','2023-04-23 20:27:05.266000',200.00,'10','DoctorOne','Pune',1,1),(2,'Admin@admin.com','2023-04-23 20:27:26.894000','Admin@admin.com','2023-04-23 20:27:26.894000',1000.00,'20','DoctorTwo','Mumbai',2,2);
/*!40000 ALTER TABLE `d_doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d_speciality`
--

DROP TABLE IF EXISTS `d_speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `d_speciality` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_speciality`
--

LOCK TABLES `d_speciality` WRITE;
/*!40000 ALTER TABLE `d_speciality` DISABLE KEYS */;
INSERT INTO `d_speciality` VALUES (1,'Admin@admin.com','2023-04-23 20:26:19.593000','Admin@admin.com','2023-04-23 20:26:19.593000','Cardiologist'),(2,'Admin@admin.com','2023-04-23 20:26:24.268000','Admin@admin.com','2023-04-23 20:26:24.268000','Opthomologist'),(3,'Admin@admin.com','2023-04-23 20:26:28.794000','Admin@admin.com','2023-04-23 20:26:28.794000','ENT');
/*!40000 ALTER TABLE `d_speciality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d_user`
--

DROP TABLE IF EXISTS `d_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `d_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_datetime` datetime(6) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_user`
--

LOCK TABLES `d_user` WRITE;
/*!40000 ALTER TABLE `d_user` DISABLE KEYS */;
INSERT INTO `d_user` VALUES (1,'root','2023-04-23 20:26:05.142000','root','2023-04-23 20:26:05.142000','Admin','Male','Admin','Admin@admin.com','888888888','Admin@123',1,'ADMIN'),(2,'root','2023-04-23 20:28:12.611000','root','2023-04-23 20:28:12.611000','Test','Female','Test','test@test.com','8888888888','Test@123',2,'USER');
/*!40000 ALTER TABLE `d_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-23 20:32:12
