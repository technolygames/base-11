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
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `codigo_emp` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `nombre_emp` varchar(20) NOT NULL,
  `apellidop_emp` varchar(20) NOT NULL,
  `apellidom_emp` varchar(20) NOT NULL,
  `puesto` char(15) NOT NULL,
  `experiencia` varchar(20) NOT NULL,
  `grado_estudios` char(15) NOT NULL,
  `edad` int(3) NOT NULL,
  `imagen` blob DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_sesion` date DEFAULT NULL,
  PRIMARY KEY (`codigo_emp`)
) ENGINE=InnoDB AUTO_INCREMENT=99881 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (23756,'Prueba','Prueba','Prueba','Empleado','Nula','Bachillerato',20,'[B@667e9509','2020-02-09','2020-02-09'),(87695,'Prueba','Prueba','Prueba','Empleado','Nula','Bachillerato',20,'[B@d476856','2020-02-09','2020-02-09'),(93746,'Prueba','Prueba','Prueba','Empleado','Servicio social','Bachillerato',20,'java.io.FileInputStream@58f4d20a','2020-02-16','2020-02-16'),(99876,'Prueba','Prueba','Prueba','Programador','Servicio social','Bachillerato',18,NULL,'2019-10-19','2020-02-16');
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
  `marca_prod` varchar(20) NOT NULL,
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
INSERT INTO `productos` VALUES (97696,'Prueba','Prueba',2,12,'2020-01-10');
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

-- Dump completed on 2020-02-16 11:01:46
