CREATE DATABASE  IF NOT EXISTS `molla` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `molla`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: molla
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `username` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `role` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'user1','$2a$10$sJ9tHdQegwVIpo1h4AnwuePGdRVRQmGY2Rd3YmG1.wcLJx9Lt8wVS',1),(2,2,'user2','$2a$10$sJ9tHdQegwVIpo1h4AnwuePGdRVRQmGY2Rd3YmG1.wcLJx9Lt8wVS',1),(3,3,'admin','$2a$10$sJ9tHdQegwVIpo1h4AnwuePGdRVRQmGY2Rd3YmG1.wcLJx9Lt8wVS',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `subtitle` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `link` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `product_price` float DEFAULT NULL,
  `ship` float DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `bill_date` date NOT NULL,
  `state` int DEFAULT '0',
  `receiver` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address_shipment` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone_shipment` varchar(255) DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `note` text,
  `promotional_codeid` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `promotional_codeid` (`promotional_codeid`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`promotional_codeid`) REFERENCES `promotional_code` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,1,65780000,0,65780000,'2024-04-30',0,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(2,1,62350000,0,62350000,'2024-05-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(3,1,31380000,0,31380000,'2024-05-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(4,1,44870000,0,44870000,'2024-03-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(5,1,52280000,0,52280000,'2024-02-20',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(6,1,76980000,0,76980000,'2024-01-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(7,2,27290000,0,27290000,'2023-12-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(8,2,69870000,0,69870000,'2023-05-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(9,2,3290000,0,3290000,'2022-06-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(10,2,11990000,0,11990000,'2023-09-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL),(11,2,54580000,0,54580000,'2023-01-30',6,'Phạm Hùng Phong','chung cư dream home 2, đường số 59, phường 14, Gò Vấp, Tp.HCM, Việt Nam','0981141044','hungphong.canhan@gmail.com','',NULL);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `line_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `line_id` (`line_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`line_id`) REFERENCES `lineitem` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Điện thoại di động','Mô tả về điện thoại di động'),(2,'Máy tính xách tay','Mô tả về máy tính xách tay'),(3,'Phụ kiện điện tử','Mô tả về phụ kiện điện tử');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favourite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `pro_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`pro_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `favourite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `favourite_ibfk_2` FOREIGN KEY (`pro_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineitem`
--

DROP TABLE IF EXISTS `lineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lineitem` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pro_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT '1',
  `subtotal` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `lineitem_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineitem`
--

LOCK TABLES `lineitem` WRITE;
/*!40000 ALTER TABLE `lineitem` DISABLE KEYS */;
INSERT INTO `lineitem` VALUES (1,7,1,27290000),(2,8,1,38490000),(3,9,3,47070000),(4,5,1,3290000),(5,6,1,11990000),(6,9,2,31380000),(7,1,1,29590000),(8,6,1,11990000),(9,5,1,3290000),(10,4,1,24990000),(11,7,1,27290000),(12,8,2,76980000),(13,7,1,27290000),(14,8,1,38490000),(15,9,2,31380000),(16,5,1,3290000),(17,6,1,11990000),(18,4,1,24990000),(19,1,1,29590000);
/*!40000 ALTER TABLE `lineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `picture` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'Samsung','Mô tả về Samsung',NULL),(2,'Apple','Mô tả về Apple',NULL),(3,'Sony','Mô tả về Sony',NULL),(4,'Asus','Hãng laptop asus','Asus-Logo-1995-present.png');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT 'Chưa đặt tên',
  `picture` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `cate_id` bigint NOT NULL,
  `manu_id` bigint NOT NULL,
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `purchase_price` float DEFAULT NULL,
  `selling_price` float DEFAULT NULL,
  `sold` int DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `event_id` bigint DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cate_id` (`cate_id`),
  KEY `manu_id` (`manu_id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cate_id`) REFERENCES `category` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`manu_id`) REFERENCES `manufacturer` (`id`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`event_id`) REFERENCES `promotional_event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Điện thoại iPhone 15 Pro Max 256GB','iphone-15-pro-max-black-1-1.jpg',1,2,'Điện thoại iPhone 15 Pro Max 256GB',27000000,29590000,0,5,1,1),(4,'Điện thoại Samsung Galaxy S23 Ultra 5G 8GB/256GB','samsung-galaxy-s23-ultra-green-thumbnew-600x600.jpg',1,1,'Điện thoại Samsung Galaxy S23 Ultra 5G 8GB/256GB',23000000,24990000,0,5,NULL,0),(5,'Điện thoại Samsung Galaxy A05 6GB/128GB','samsung-galaxy-a05-black-thumbnew-600x600.jpg',1,1,'Điện thoại Samsung Galaxy A05 6GB/128GB',3000000,3290000,0,2,NULL,3),(6,'Điện thoại Samsung Galaxy A55 5G 12GB/256GB ','samsung-galaxy-a55-5g-xanh-thumb-1-600x600.jpg',1,1,'Điện thoại Samsung Galaxy A55 5G 12GB/256GB ',11000000,11990000,0,5,NULL,2),(7,'Laptop Apple MacBook Air 13 inch M3 8GB/256GB (MRXV3SA/A) ','macbook-air-13-inch-m3-2024-050324-020626-600x600.jpg',2,2,'Laptop Apple MacBook Air 13 inch M3 8GB/256GB (MRXV3SA/A) ',25290000,27290000,0,0,NULL,2),(8,'Laptop Apple MacBook Pro 14 inch M3 8GB/512GB (MTL73SA/A)','macbook-pro-14-inch-m3-2023-311023-095050-600x600.jpg',2,2,'Laptop Apple MacBook Pro 14 inch M3 8GB/512GB (MTL73SA/A)',36490000,38490000,0,5,NULL,2),(9,'Laptop Asus Vivobook 15 X1504ZA i5 1235U/16GB/1TB/Win11 (NJ1528W)','asus-vivobook-15-x1504za-i5-1nj1528w-1.jpg',2,4,'Laptop Asus Vivobook 15 X1504ZA i5 1235U/16GB/1TB/Win11 (NJ1528W)',13690000,15690000,0,0,NULL,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotional_code`
--

DROP TABLE IF EXISTS `promotional_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotional_code` (
  `id` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `type_id` bigint DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `exp_date` date DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `promotional_code_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `promotional_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotional_code`
--

LOCK TABLES `promotional_code` WRITE;
/*!40000 ALTER TABLE `promotional_code` DISABLE KEYS */;
INSERT INTO `promotional_code` VALUES ('CODE001',1,15,'2024-04-30',100),('CODE002',2,NULL,'2024-06-15',50);
/*!40000 ALTER TABLE `promotional_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotional_code_seq`
--

DROP TABLE IF EXISTS `promotional_code_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotional_code_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotional_code_seq`
--

LOCK TABLES `promotional_code_seq` WRITE;
/*!40000 ALTER TABLE `promotional_code_seq` DISABLE KEYS */;
INSERT INTO `promotional_code_seq` VALUES (1);
/*!40000 ALTER TABLE `promotional_code_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotional_event`
--

DROP TABLE IF EXISTS `promotional_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotional_event` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `discount_percent` float DEFAULT NULL,
  `pro_date` date DEFAULT NULL,
  `exp_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotional_event`
--

LOCK TABLES `promotional_event` WRITE;
/*!40000 ALTER TABLE `promotional_event` DISABLE KEYS */;
INSERT INTO `promotional_event` VALUES (1,'Lễ giỗ tổ Hùng Vương',10,'2024-04-10','2024-04-20'),(2,'Lễ 30/4',10,'2024-04-30','2024-05-01'),(3,'Lễ sinh nhật',10,'2024-04-10','2024-04-12'),(4,'Khuyến mãi mùa hè',20,'2024-06-01','2024-06-30');
/*!40000 ALTER TABLE `promotional_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotional_type`
--

DROP TABLE IF EXISTS `promotional_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotional_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotional_type`
--

LOCK TABLES `promotional_type` WRITE;
/*!40000 ALTER TABLE `promotional_type` DISABLE KEYS */;
INSERT INTO `promotional_type` VALUES (1,'Free ship'),(2,'Sale off');
/*!40000 ALTER TABLE `promotional_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `review_date` date DEFAULT NULL,
  `pro_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `title` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`),
  KEY `pro_id` (`pro_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `product` (`id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `review_chk_1` CHECK (((`rating` >= 0) and (`rating` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'2024-05-30',8,1,5,'Tốt','TốtTốtTốtTốtTốtTốt'),(2,'2024-05-30',4,1,5,'Tốt','TốtTốtTốtTốtTốt'),(3,'2024-05-30',6,1,5,'Tốt','TốtTốtTốtTốt'),(4,'2024-05-30',1,1,5,'Tốt','TốtTốtTốtTốtTốtTốtTốtTốtTốt'),(5,'2024-05-30',5,1,2,'Tốt','TốtTốtTốtTốt');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pro_id` bigint DEFAULT NULL,
  `state` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_picture`
--

DROP TABLE IF EXISTS `sub_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_picture` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pro_id` bigint DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `sub_picture_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_picture`
--

LOCK TABLES `sub_picture` WRITE;
/*!40000 ALTER TABLE `sub_picture` DISABLE KEYS */;
INSERT INTO `sub_picture` VALUES (1,1,'iphone-15-pro-max-white-thumbnew-200x200.jpg'),(2,1,'iphone-15-pro-max-gold-thumbnew-200x200.jpg'),(3,1,'iphone-15-pro-max-blue-2-1.jpg'),(4,1,'iphone-15-pro-max-black-1-1.jpg'),(5,4,'samsung-galaxy-s23-ultra-green-thumbnew-600x600.jpg'),(6,4,'samsung-galaxy-s23-ultra-kem-1-1.jpg'),(7,4,'samsung-galaxy-s-ultra-tim-1.jpg'),(8,4,'samsung-galaxy-s23-ultra-1-2.jpg'),(9,4,'samsung-galaxy-s23-ultra-xanh-1.jpg'),(10,5,'samsung-galary-a05-xanh-glr-1.jpg'),(11,5,'samsung-galaxy-a05-bac-1-1.jpg'),(12,5,'samsung-galaxy-a05-den-glr-1.jpg'),(13,5,'samsung-galaxy-a05-black-thumbnew-600x600.jpg'),(14,6,'samsung-galaxy-a55-5g-den-1.jpg'),(15,6,'samsung-galaxy-a55-5g-tim-1.jpg'),(16,6,'samsung-galaxy-a55-5g-xanh-1-1.jpg'),(17,6,'samsung-galaxy-a55-5g-xanh-thumb-1-600x600.jpg'),(18,7,'macbook-air-13-inch-m3-2024-1-2.jpg'),(19,7,'macbook-air-13-inch-m3-2024-1-1.jpg'),(20,7,'macbook-air-13-inch-m3-2024-1.jpg'),(21,7,'macbook-air-13-inch-m3-2024-1-3.jpg'),(22,8,'macbook-pro-14-inch-m3-2023-acv-1.jpg'),(23,8,'macbook-pro-14-inch-m3-2023-asd-1.jpg'),(24,8,'macbook-pro-14-inch-m3-2023-311023-095050-600x600.jpg'),(25,9,'vi-vn-asus-vivobook-15-x1504za-i5-nj1528w-slider-1.jpg'),(26,9,'asus-vivobook-15-x1504za-i5-1nj1528w-1.jpg');
/*!40000 ALTER TABLE `sub_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `support_message`
--

DROP TABLE IF EXISTS `support_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `support_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `question` text,
  `answer` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `support_message`
--

LOCK TABLES `support_message` WRITE;
/*!40000 ALTER TABLE `support_message` DISABLE KEYS */;
INSERT INTO `support_message` VALUES (1,'2024-05-14','Pham Hung Phong','@gmail','Làm sao để tìm lại mật khẩu?','Bạn có thể tìm lại mật khẩu bằng cách ...'),(2,'2024-05-14','Pham Hung Phong','@gmail','Chính sách giao hàng là gì?',NULL),(3,'2024-05-14','Pham Hung Phong','@gmail','Làm sao để hoàn trả đơn hàng?',NULL);
/*!40000 ALTER TABLE `support_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL,
  `expire_time` datetime(6) NOT NULL,
  `is_used` bit(1) NOT NULL,
  `token` varchar(255) NOT NULL,
  `acc_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp12c48ha0uskqujh5k9jd81m2` (`acc_id`),
  CONSTRAINT `FKp12c48ha0uskqujh5k9jd81m2` FOREIGN KEY (`acc_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_seq`
--

DROP TABLE IF EXISTS `token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_seq`
--

LOCK TABLES `token_seq` WRITE;
/*!40000 ALTER TABLE `token_seq` DISABLE KEYS */;
INSERT INTO `token_seq` VALUES (1);
/*!40000 ALTER TABLE `token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `line_id` bigint DEFAULT NULL,
  `bill_id` bigint DEFAULT NULL,
  `is_review` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `line_id` (`line_id`),
  KEY `bill_id` (`bill_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`line_id`) REFERENCES `lineitem` (`id`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,1,0),(2,2,1,0),(3,3,2,0),(4,4,2,0),(5,5,2,0),(6,6,3,0),(7,7,4,1),(8,8,4,1),(9,9,4,1),(10,10,5,1),(11,11,5,0),(12,12,6,1),(13,13,7,0),(14,14,8,0),(15,15,8,0),(16,16,9,0),(17,17,10,0),(18,18,11,0),(19,19,11,0);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fullname` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `email` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `state` int DEFAULT '1',
  `is_notify` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nguyễn Văn A','1990-01-01','123 Đường ABC, Quận XYZ, TP. HCM',123456789,'nguyenvana@example.com',1,1),(2,'Trần Thị B','1995-05-10','456 Đường XYZ, Quận ABC, TP. HCM',987654321,'tranthib@example.com',1,1),(3,'Lê Văn C','1985-12-20','789 Đường DEF, Quận MNO, TP. HCM',135792468,'levanc@example.com',1,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'molla'
--

--
-- Dumping routines for database 'molla'
--
/*!50003 DROP PROCEDURE IF EXISTS `findBestSellerProduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `findBestSellerProduct`()
BEGIN
	SELECT *
	FROM product
	where product.state = 2;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `findNewProduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `findNewProduct`()
BEGIN
	SELECT *
	FROM product
	where product.state = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `findOnSaleProduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `findOnSaleProduct`()
BEGIN
	SELECT *
	FROM product
	where product.state = 3;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `findProductsByCategoryAndManufacturer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `findProductsByCategoryAndManufacturer`(IN cate_ids VARCHAR(255), IN manu_ids VARCHAR(255), IN min_price FLOAT, IN max_price FLOAT)
BEGIN
	IF FIND_IN_SET('0', cate_ids) > 0 AND FIND_IN_SET('0', manu_ids) > 0 THEN
	SET @query = CONCAT('SELECT * FROM product WHERE selling_price BETWEEN ', min_price, ' AND ', max_price);
	ELSEIF FIND_IN_SET('0', cate_ids) > 0 THEN
		SET @query = CONCAT('SELECT * FROM product WHERE manu_id IN (', manu_ids, ') AND selling_price BETWEEN ',min_price,' AND ', max_price);
	ELSEIF FIND_IN_SET('0', manu_ids) > 0 THEN
		SET @query = CONCAT('SELECT * FROM product WHERE cate_id IN (', cate_ids, ')');
	ELSE
		SET @query = CONCAT('SELECT * FROM product WHERE cate_id IN (', cate_ids, ') AND manu_id IN (', manu_ids, ') AND selling_price BETWEEN ',min_price,' AND ', max_price);
	END IF;
	PREPARE statement FROM @query;
	EXECUTE statement;
	DEALLOCATE PREPARE statement;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `findTop4Product` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `findTop4Product`()
BEGIN
	SELECT *
	FROM product
	ORDER BY sold desc
	LIMIT 4;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `search` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `search`(IN str VARCHAR(255))
BEGIN
	SELECT product.id, product.name, product.picture, product.cate_id, product.manu_id, product.description, product.purchase_price, product.selling_price, product.sold, product.rating, product.event_id, product.state
	FROM product
		INNER JOIN 
			category
		ON product.cate_id = category.id
        INNER JOIN
			manufacturer
		ON product.manu_id = manufacturer.id
	WHERE 
		product.name like concat('%',str,'%') or
		category.name like concat('%',str,'%') or
        manufacturer.name like concat('%',str,'%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-30 23:23:59
