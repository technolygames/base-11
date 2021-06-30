-- MySQL dump 10.16  Distrib 10.1.37-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	10.1.37-MariaDB

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
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `almacen` (
  `codigo` int(5) DEFAULT NULL,
  `producto` varchar(25) DEFAULT NULL,
  `marca` varchar(30) DEFAULT NULL,
  `fecha_entrada` varchar(10) DEFAULT NULL,
  `proveedor` varchar(35) DEFAULT NULL,
  `cantidad` int(5) DEFAULT NULL,
  `existencia` varchar(20) DEFAULT NULL,
  KEY `codigo` (`codigo`),
  CONSTRAINT `almacen_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `productos` (`codigo`)
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
  `identificador` int(5) unsigned zerofill NOT NULL,
  `nombre_empleado` varchar(20) DEFAULT NULL,
  `apellidop_empleado` varchar(25) DEFAULT NULL,
  `apellidom_empleado` varchar(25) DEFAULT NULL,
  `edad` int(3) DEFAULT NULL,
  `experiencia` char(15) DEFAULT NULL,
  `nivel_academico` char(15) DEFAULT NULL,
  `estado_civil` char(15) DEFAULT NULL,
  PRIMARY KEY (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `codigo` int(5) NOT NULL,
  `producto` varchar(25) DEFAULT NULL,
  `marca` varchar(30) DEFAULT NULL,
  `fecha_compra` varchar(10) DEFAULT NULL,
  `cantidad` int(5) DEFAULT NULL,
  `precio` int(10) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (2987,'\"Coca-cola 2L\"','\"Coca-cola\"','15/09/2019',1,28),(3246,'\"Lechera\"','\"Nestlé\"','12/09/2019',2,20),(8675,'\"Sabritas\"','\"Sabritas\"','15/09/2019',2,14),(9678,'\"Coca-cola 600ML\"','\"Coca-cola\"','15/09/2019',1,16),(9697,'\"Takis\"','\"Barcel\"','14/09/2019',4,12),(9758,'\"Sabritones\"','\"Sabritas\"','15/09/2019',2,24);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `identificador` int(5) NOT NULL,
  `nombre_repartidor` varchar(20) DEFAULT NULL,
  `apellidop_repartidor` varchar(25) DEFAULT NULL,
  `apellidom_repartidor` varchar(25) DEFAULT NULL,
  `marca` varchar(30) DEFAULT NULL,
  `empresa` varchar(30) DEFAULT NULL,
  `producto` varchar(25) DEFAULT NULL,
  `cantidad` int(5) DEFAULT NULL,
  `fecha_entrega` varchar(10) DEFAULT NULL,
  `codigo` int(5) DEFAULT NULL,
  `nombre_receptor` varchar(20) DEFAULT NULL,
  `apellidop_receptor` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `contraseña` int(5) NOT NULL,
  `usuario` char(15) DEFAULT NULL,
  `fecha_creacion` varchar(15) DEFAULT NULL,
  `fecha_ingreso` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`contraseña`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (55089,'\"Arelí\"','04/08/2019','14/09/2019'),(55239,'\"Armando\"','12/09/2019','15/09/2019'),(55376,'\"Pablo\"','07/09/2019','14/09/2019'),(55685,'\"Saúl\"','09/09/2019','15/09/2019'),(55986,'\"Carmen\"','01/09/2019','15/09/2019');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-15 15:08:27
