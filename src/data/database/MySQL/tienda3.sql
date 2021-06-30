-- MariaDB dump 10.17  Distrib 10.4.6-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	10.4.6-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `almacen` (
  `codigo_alm` int(5) NOT NULL,
  `codigo_prod` int(5) NOT NULL,
  `codigo_prov` int(5) NOT NULL,
  `nombre_prod` varchar(25) NOT NULL,
  `nombre_prov` varchar(20) NOT NULL,
  `marca_prod` varchar(20) NOT NULL,
  `stock` char(20) NOT NULL,
  `fecha_ingreso` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
INSERT INTO `almacen` VALUES (10901,12415,26486,'Takis Fuego','Raúl','Barcel','Agotado','2019-10-30'),(10901,20134,26486,'Takis Guacamole','Raúl','Barcel','Llegada','2019-10-29'),(4654,54,454,'hv','cgv','cvc','cbv','2012-06-19'),(10901,34768,23987,'Takis','Raúl','Barcel','Llegada','2019-11-21');
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `password` varchar(80) NOT NULL,
  `codigo_emp` int(11) NOT NULL,
  `nombre_emp` varchar(25) NOT NULL,
  `apellidop_emp` varchar(30) NOT NULL,
  `apellidom_emp` varchar(30) NOT NULL,
  `puesto` char(15) NOT NULL,
  `experiencia` varchar(20) NOT NULL,
  `grado_estudios` char(20) NOT NULL,
  `edad` int(3) NOT NULL,
  `imagen` blob DEFAULT NULL,
  `fecha_registro` date NOT NULL,
  `fecha_sesion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES ('Gtaswag5563',99876,'Prueba','Prueba','Prueba','Programador','Servicio social','Bachillerato',18,NULL,'2020-08-10','2020-11-06'),('1234',1234,'Prueba','Prueba','Prueba','Programador','Nula','Bachillerato',18,'java.io.FileInputStream@35139c4','2020-09-20','2020-09-20'),('1234',1234,'Prueba','Prueba','Prueba','Prueba','Prueba','Bachillerato',18,'java.io.FileInputStream@71b3c33c','2020-09-21','2020-09-21'),('1234',1234,'Prueba','Prueba','Prueba','Prueba','Prueba','Prueba',18,'java.io.FileInputStream@6cb14350','2020-09-21','2020-09-21'),('1234',1234,'Prueba','Prueba','Prueba','Prueba','Prueba','Prueba',18,'java.io.FileInputStream@288ba379','2020-09-21','2020-09-21'),('1234',1234,'Prueba','Prueba','Prueba','Prueba','Nula','Bachillerato',19,'java.io.FileInputStream@500c65ed','2020-11-06','2020-11-06'),('1234',1234,'Prueba','Prueba','Prueba','Prueba','Prueba','Prueba',19,'java.io.FileInputStream@25b69d59','2020-11-06','2020-11-06');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `codigo_prod` int(5) NOT NULL,
  `nombre_prod` varchar(20) NOT NULL,
  `marca_prod` varchar(25) NOT NULL,
  `cantidad` int(5) NOT NULL,
  `precio` int(4) NOT NULL,
  `fecha_compra` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (34873,'Crema','Lala',2,20,'2019-11-08'),(34857,'Takis','Barcel',2,12,'2019-11-21'),(23,'446','jkhj',4,43,'2019-11-23');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-08 20:45:57
