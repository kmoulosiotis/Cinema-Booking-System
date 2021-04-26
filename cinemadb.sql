-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: cinemadb
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book_history`
--

DROP TABLE IF EXISTS `book_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `num_tickets` int(11) NOT NULL,
  `amount` float NOT NULL,
  `order_time` datetime NOT NULL,
  `shows_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_history_shows1_idx` (`shows_id`),
  CONSTRAINT `fk_book_history_shows1` FOREIGN KEY (`shows_id`) REFERENCES `shows` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cinemas`
--

DROP TABLE IF EXISTS `cinemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinemas` (
  `cinema_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `screen` int(11) NOT NULL,
  `address` varchar(45) NOT NULL,
  `adult` float NOT NULL,
  `child` float NOT NULL,
  `student` float NOT NULL,
  `family` float NOT NULL,
  PRIMARY KEY (`cinema_id`),
  UNIQUE KEY `UNIQUE` (`name`,`screen`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `movies_id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(150) NOT NULL,
  `director` varchar(45) NOT NULL,
  `cast` varchar(150) NOT NULL,
  `details` text NOT NULL,
  `rating` float NOT NULL,
  `poster` mediumblob NOT NULL,
  PRIMARY KEY (`movies_id`),
  UNIQUE KEY `movie_name_UNIQUE` (`movie_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shows`
--

DROP TABLE IF EXISTS `shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movies_id` int(11) NOT NULL,
  `cinema_id` int(11) NOT NULL,
  `movie_name` varchar(150) NOT NULL,
  `cinema_name` varchar(45) NOT NULL,
  `screen` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `seats` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`cinema_id`,`date`,`time`),
  KEY `fk_shows_movies_idx` (`movies_id`),
  KEY `fk_shows_cinemas_idx` (`cinema_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_shows_cinemas1` FOREIGN KEY (`cinema_id`) REFERENCES `cinemas` (`cinema_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_shows_movies` FOREIGN KEY (`movies_id`) REFERENCES `movies` (`movies_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `today_shows`
--

DROP TABLE IF EXISTS `today_shows`;
/*!50001 DROP VIEW IF EXISTS `today_shows`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `today_shows` AS SELECT 
 1 AS `id`,
 1 AS `movies_id`,
 1 AS `cinema_id`,
 1 AS `movie_name`,
 1 AS `cinema_name`,
 1 AS `screen`,
 1 AS `date`,
 1 AS `time`,
 1 AS `seats`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `week_shows`
--

DROP TABLE IF EXISTS `week_shows`;
/*!50001 DROP VIEW IF EXISTS `week_shows`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `week_shows` AS SELECT 
 1 AS `id`,
 1 AS `movies_id`,
 1 AS `cinema_id`,
 1 AS `movie_name`,
 1 AS `cinema_name`,
 1 AS `screen`,
 1 AS `date`,
 1 AS `time`,
 1 AS `seats`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `today_shows`
--

/*!50001 DROP VIEW IF EXISTS `today_shows`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `today_shows` AS select `s`.`id` AS `id`,`s`.`movies_id` AS `movies_id`,`s`.`cinema_id` AS `cinema_id`,`s`.`movie_name` AS `movie_name`,`s`.`cinema_name` AS `cinema_name`,`s`.`screen` AS `screen`,`s`.`date` AS `date`,`s`.`time` AS `time`,`s`.`seats` AS `seats` from ((`shows` `s` join `movies` `m`) join `cinemas` `c`) where ((`s`.`date` = curdate()) and (`s`.`movies_id` = `m`.`movies_id`) and (`s`.`cinema_id` = `c`.`cinema_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `week_shows`
--

/*!50001 DROP VIEW IF EXISTS `week_shows`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `week_shows` AS select `s`.`id` AS `id`,`s`.`movies_id` AS `movies_id`,`s`.`cinema_id` AS `cinema_id`,`s`.`movie_name` AS `movie_name`,`s`.`cinema_name` AS `cinema_name`,`s`.`screen` AS `screen`,`s`.`date` AS `date`,`s`.`time` AS `time`,`s`.`seats` AS `seats` from ((`shows` `s` join `movies` `m`) join `cinemas` `c`) where ((yearweek(`s`.`date`,1) = yearweek(curdate(),1)) and (`s`.`movies_id` = `m`.`movies_id`) and (`s`.`cinema_id` = `c`.`cinema_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-29 20:02:19
