-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: budgetmanagementsystem
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称（会议内容）',
  `price` double DEFAULT NULL COMMENT '会议标准费用',
  `comment` text,
  `experts` int(11) DEFAULT NULL COMMENT '需要专家人数',
  `people` int(11) DEFAULT '0' COMMENT '参会人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会议费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
INSERT INTO `conference` VALUES (1,'项目启动会',200,NULL,3,0),(2,'中期',200,NULL,3,0),(3,'预验收',200,NULL,3,0),(4,'验收',200,NULL,2,0),(5,'学术会议',5000,NULL,3,0);
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `consultation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '专家信息（职称等）',
  `price` double DEFAULT NULL COMMENT '咨询费',
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='咨询费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation`
--

LOCK TABLES `consultation` WRITE;
/*!40000 ALTER TABLE `consultation` DISABLE KEYS */;
INSERT INTO `consultation` VALUES (1,'专家',1000,NULL);
/*!40000 ALTER TABLE `consultation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '型号规格',
  `price` double DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT '子类别',
  `img` varchar(100) DEFAULT NULL COMMENT '图片url',
  `comment` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'戴尔Precision T5820(Xeon W-2123/64GB/512GB+4TB/P2000)',23700,NULL,NULL,NULL),(2,'UltraLAB P490(14664-MAX)',60000,NULL,NULL,NULL),(3,'Wiseteam CP265 C33128-SAEK12',149900,NULL,NULL,NULL),(4,'凌炫Pt8040（E221T-ECEP6）',499900,NULL,NULL,NULL),(5,'惠普战99（4RV70PA）',7999,NULL,NULL,NULL),(6,'联想ThinkStation P900(Xeon E5-2630 v3/512GB/K2200)',41300,NULL,NULL,NULL),(7,'戴尔Precision T7920塔式系列(P7920T-S4110NLCN01)',24300,NULL,NULL,NULL),(8,'UltraLAB Alpha720(425256-SB28TD)',275000,NULL,NULL,NULL),(9,'UltraLAB H490(14664-M5TCX)',47000,NULL,NULL,NULL),(10,'UltraLAB EX620(23496-M528TC)',140000,NULL,NULL,NULL),(11,'戴尔Precision 7530系列(Promo-M7530I78750-Online1)',18600,NULL,NULL,NULL),(12,'联想ThinkStation P920(Xeon Bronze 3106/8GB/1TB/P600)',14500,NULL,NULL,NULL),(13,'戴尔Precision T5820(p5820t-w2102nlcn01)',11299.999999999998,NULL,NULL,NULL),(14,'戴尔Precision T7820(P7820T-31045NLCN01)',15900,NULL,NULL,NULL),(15,'UltraLAB V490(14864-MBSDD)',115000,NULL,NULL,NULL),(16,'联想ThinkStation P318(酷睿i7-7700/16GB/1TB/GTX1080)',14900,NULL,NULL,NULL),(17,'HP Z8 G4(Z3Z16AV-SC001)',40000,NULL,NULL,NULL),(18,'苹果Mac Pro(ME253CH/A)',21900,NULL,NULL,NULL);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internationalcommunication`
--

DROP TABLE IF EXISTS `internationalcommunication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `internationalcommunication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际合作交流费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internationalcommunication`
--

LOCK TABLES `internationalcommunication` WRITE;
/*!40000 ALTER TABLE `internationalcommunication` DISABLE KEYS */;
INSERT INTO `internationalcommunication` VALUES (1,'澳大利亚',39000,NULL),(2,'北卡莱罗纳',21000,NULL);
/*!40000 ALTER TABLE `internationalcommunication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `labour`
--

DROP TABLE IF EXISTS `labour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `labour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名目（本硕博专）',
  `price` double DEFAULT NULL COMMENT '单价（人月）',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='劳务费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labour`
--

LOCK TABLES `labour` WRITE;
/*!40000 ALTER TABLE `labour` DISABLE KEYS */;
INSERT INTO `labour` VALUES (1,'博士生',2000,2,NULL),(2,'硕士生',1000,3,NULL),(3,'专职',11536,1,NULL);
/*!40000 ALTER TABLE `labour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称/规格',
  `price` double DEFAULT NULL COMMENT '单价',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `img` varchar(100) DEFAULT NULL COMMENT '图片链接',
  `comment` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='材料费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'硬盘',2000,NULL,NULL,NULL),(2,'加密移动硬盘',3000,NULL,NULL,NULL);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `power`
--

DROP TABLE IF EXISTS `power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `power` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='燃料动力费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power`
--

LOCK TABLES `power` WRITE;
/*!40000 ALTER TABLE `power` DISABLE KEYS */;
/*!40000 ALTER TABLE `power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称名目',
  `price` double DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产权费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (1,'著作权',2000,NULL),(2,'知识产权',5000,NULL),(3,'广西省电子地图数据',10000,NULL),(4,'广西省货车 UGC 数据',10000,NULL),(5,'广西省物流 UGC 数据',10000,NULL),(6,'论文发表费',4000,NULL),(7,'广西省交通流量视频数据',10000,NULL);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testandprocess`
--

DROP TABLE IF EXISTS `testandprocess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `testandprocess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称、内容',
  `price` double DEFAULT NULL COMMENT '单价',
  `comment` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测试化验加工费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testandprocess`
--

LOCK TABLES `testandprocess` WRITE;
/*!40000 ALTER TABLE `testandprocess` DISABLE KEYS */;
INSERT INTO `testandprocess` VALUES (1,'测试化验加工费',150000,NULL);
/*!40000 ALTER TABLE `testandprocess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel`
--

DROP TABLE IF EXISTS `travel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `travel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dest` varchar(50) DEFAULT NULL COMMENT '目的地',
  `price` double DEFAULT NULL COMMENT '往返价格',
  `food` double DEFAULT NULL COMMENT '伙食标准',
  `traffic` double DEFAULT NULL COMMENT '交通标准',
  `accommodation` double DEFAULT NULL COMMENT '住宿标准',
  `comment` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='差旅费';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel`
--

LOCK TABLES `travel` WRITE;
/*!40000 ALTER TABLE `travel` DISABLE KEYS */;
INSERT INTO `travel` VALUES (1,'上海',2000,100,80,500,NULL),(2,'南宁',2000,100,80,350,NULL),(3,'桂林',2000,100,80,350,NULL),(4,'北海',2000,100,80,350,NULL);
/*!40000 ALTER TABLE `travel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workstation`
--

DROP TABLE IF EXISTS `workstation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `workstation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spec` varchar(100) DEFAULT NULL COMMENT '型号规格',
  `price` double DEFAULT NULL COMMENT '单价（万）',
  `img` varchar(100) DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `workstation_spec_uindex` (`spec`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工作站';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workstation`
--

LOCK TABLES `workstation` WRITE;
/*!40000 ALTER TABLE `workstation` DISABLE KEYS */;
INSERT INTO `workstation` VALUES (35,'戴尔Precision T5820(Xeon W-2123/64GB/512GB+4TB/P2000)',23700,NULL),(36,'UltraLAB P490(14664-MAX)',60000,NULL),(37,'HP Z4 G4(1JP11AV-SC001)',17000,NULL),(38,'Wiseteam CP265 C33128-SAEK12',149900,NULL),(39,'凌炫Pt8040（E221T-ECEP6）',499900,NULL),(40,'联想ThinkStation P520c(Xeon W-2123/16GB/256GB+1TB/P2000)',14400,NULL),(41,'戴尔Precision T7920塔式系列(P7920T-S4110NLCN01)',24300,NULL),(42,'UltraLAB Alpha720(425256-SB28TD)',275000,NULL),(43,'UltraLAB H490(14664-M5TCX)',47000,NULL),(44,'UltraLAB EX620(23496-M528TC)',140000,NULL),(45,'戴尔Precision 7530系列(Promo-M7530I78750-Online1)',18600,NULL),(46,'戴尔Precision 5530系列(Promo-M5530I58300-Online1)',9999,NULL);
/*!40000 ALTER TABLE `workstation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-19 23:24:24
