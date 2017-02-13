-- MySQL dump 10.13  Distrib 5.7.13, for Win64 (x86_64)
--
-- Host: localhost    Database: inventory
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `customerID` varchar(20) NOT NULL,
  `customerFName` varchar(20) NOT NULL,
  `customerLName` varchar(20) DEFAULT NULL,
  `customerAddress` varchar(200) NOT NULL,
  `addressState` varchar(45) NOT NULL,
  `zip` int(11) NOT NULL,
  `nameonCard` varchar(45) NOT NULL,
  `cardNumber` int(32) NOT NULL,
  `CVV` int(11) NOT NULL,
  `expiryDate` varchar(5) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `customeID_UNIQUE` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('gout.kannan','Goutham','Kannan','2801 S King Dr','IL',60616,'Goutham Kannan',525,100,'02/20','yo'),('iambigot@trump.com','Donald','Trump','Somewhere in racist land	','RL',1111,'Amazing Wall',1111,111,'2121','temp'),('temp','temp','temp','temp','vi',303,'temp',4645454,101,'4545','temp'),('wall@trump.com','Donald','Trump','Donald','RL',121,'Donald',4245,312,'44','temp');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `idOrders` varchar(45) NOT NULL,
  `idCustomer` varchar(45) NOT NULL,
  `idproduct` varchar(45) NOT NULL,
  `shippingAddress` varchar(500) NOT NULL,
  PRIMARY KEY (`idOrders`),
  UNIQUE KEY `idOrders_UNIQUE` (`idOrders`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `itemID` varchar(20) DEFAULT NULL,
  `reviews` varchar(500) DEFAULT NULL,
  `starRating` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storelist`
--

DROP TABLE IF EXISTS `storelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storelist` (
  `itemID` varchar(20) NOT NULL,
  `itemName` varchar(50) NOT NULL,
  `itemDescription` varchar(500) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `price` float NOT NULL,
  `discount` int(11) DEFAULT NULL,
  `dealerName` varchar(20) NOT NULL,
  `isnew` varchar(1) NOT NULL,
  PRIMARY KEY (`itemID`,`itemName`),
  UNIQUE KEY `itemID_UNIQUE` (`itemID`),
  UNIQUE KEY `itemName_UNIQUE` (`itemName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storelist`
--

LOCK TABLES `storelist` WRITE;
/*!40000 ALTER TABLE `storelist` DISABLE KEYS */;
INSERT INTO `storelist` VALUES ('1','peanuts','brown and salted ',12,2,0,'planters','Y'),('2','Cookie','Chocolate chip added Oreo is a sandwich cookie consisting of two chocolate wafers with a sweet creme filling in between.',200,1,0,'M&M','Y'),('3','Oreo Cookie','Oreo is a sandwich cookie consisting of two chocolate wafers with a sweet creme filling in between',200,2.5,0,'Tressco','Y'),('5','COFFEE-MATE','The Original - Non Dairy Creamer 24.00Oz',10,8.5,0,'whomsoever','y');
/*!40000 ALTER TABLE `storelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-25 22:56:46
