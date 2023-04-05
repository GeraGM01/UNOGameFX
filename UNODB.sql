-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: unobd
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `credenciales`
--

DROP TABLE IF EXISTS `credenciales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credenciales` (
  `Id_Credencial` int NOT NULL AUTO_INCREMENT,
  `Id_Jugador` int DEFAULT NULL,
  `Correo` varchar(50) NOT NULL,
  `Clave` varchar(20) NOT NULL,
  PRIMARY KEY (`Id_Credencial`),
  KEY `Id_Jugador` (`Id_Jugador`),
  CONSTRAINT `credenciales_ibfk_1` FOREIGN KEY (`Id_Jugador`) REFERENCES `jugador` (`Id_Jugador`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credenciales`
--

LOCK TABLES `credenciales` WRITE;
/*!40000 ALTER TABLE `credenciales` DISABLE KEYS */;
INSERT INTO `credenciales` VALUES (1,14,'alejandro@gmail.com','12345'),(2,15,'danielReyes@correo.com','dani123'),(3,16,'',''),(4,17,'patricia@correo.com','prueba'),(5,6,'andrea123@correo.com','12345'),(6,19,'osvaldo1@gmail.com','osvaldo123');
/*!40000 ALTER TABLE `credenciales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallepartida`
--

DROP TABLE IF EXISTS `detallepartida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallepartida` (
  `Id_Detalle` int NOT NULL AUTO_INCREMENT,
  `Id_Jugador` int DEFAULT NULL,
  `Puntaje_Partida` int DEFAULT NULL,
  `RankingPartida` int DEFAULT NULL,
  `winOrLose` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Detalle`),
  KEY `Id_Jugador` (`Id_Jugador`),
  CONSTRAINT `detallepartida_ibfk_1` FOREIGN KEY (`Id_Jugador`) REFERENCES `jugador` (`Id_Jugador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepartida`
--

LOCK TABLES `detallepartida` WRITE;
/*!40000 ALTER TABLE `detallepartida` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallepartida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadisticas`
--

DROP TABLE IF EXISTS `estadisticas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadisticas` (
  `Id_Estadistica` int NOT NULL AUTO_INCREMENT,
  `Id_Jugador` int DEFAULT NULL,
  `Tiempo_Jugado` int DEFAULT NULL,
  `Partidas_Ganadas` int DEFAULT NULL,
  `Partidas_Perdidas` int DEFAULT NULL,
  PRIMARY KEY (`Id_Estadistica`),
  KEY `Id_Jugador` (`Id_Jugador`),
  CONSTRAINT `estadisticas_ibfk_1` FOREIGN KEY (`Id_Jugador`) REFERENCES `jugador` (`Id_Jugador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadisticas`
--

LOCK TABLES `estadisticas` WRITE;
/*!40000 ALTER TABLE `estadisticas` DISABLE KEYS */;
/*!40000 ALTER TABLE `estadisticas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `Id_Jugador` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(40) NOT NULL,
  `Usuario` varchar(20) NOT NULL,
  PRIMARY KEY (`Id_Jugador`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (5,'gerardo','geraz'),(6,'Andrea','andrea1'),(7,'Jonathan','jona'),(8,'Diana','diana12'),(9,'Antonio','toño'),(10,'Edgar','edg12'),(11,'Edgar','edg12'),(12,'Paola','pao'),(13,'Raul Mtz','raulMtz'),(14,'Alejandro Mtz','ale45'),(15,'Daniel','daniel25'),(16,'',''),(17,'Patricia','patriciaG.01'),(18,'Andrea','andrea1'),(19,'Osvaldo','osva123');
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-28 10:41:41
