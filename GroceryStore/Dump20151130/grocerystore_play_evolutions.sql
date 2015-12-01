-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: grocerystore
-- ------------------------------------------------------
-- Server version	5.6.27-0ubuntu0.14.04.1

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
-- Dumping data for table `play_evolutions`
--

LOCK TABLES `play_evolutions` WRITE;
/*!40000 ALTER TABLE `play_evolutions` DISABLE KEYS */;
INSERT INTO `play_evolutions` VALUES (1,'4505135d3ab029a84089efab4d65a67ed59f41c8','2015-11-30 15:25:32','create table article (\nid                        bigint auto_increment not null,\nname                      varchar(255),\nsku                       varchar(255) not null,\ndescription               TEXT,\nconstraint uq_article_sku unique (sku),\nconstraint pk_article primary key (id))\n;\n\ncreate table price (\nid                        bigint auto_increment not null,\ncost                      FLOAT,\nprice_date                datetime,\narticle_id                bigint,\nconstraint pk_price primary key (id))\n;\n\nalter table price add constraint fk_price_article_1 foreign key (article_id) references article (id) on delete restrict on update restrict;\ncreate index ix_price_article_1 on price (article_id);','SET FOREIGN_KEY_CHECKS=0;\n\ndrop table article;\n\ndrop table category;\n\ndrop table price;\n\nSET FOREIGN_KEY_CHECKS=1;','applied',''),(2,'fe6c588d77b591595ab34aca6bc070cb5bd590a9','2015-11-30 15:25:33','ALTER TABLE article ADD availability tinyint(1) default 0;','ALTER TABLE article DROP availability;','applied',''),(3,'ddce8aec8a1bb16c15f13314b015e40c4636e854','2015-11-30 15:25:33','create table category (\nid              bigint auto_increment not null,\nname            varchar(255),\navailability    tinyint(1) default 0,\nprimary key (id)\n);','drop table category;','applied',''),(4,'e5c10877aadca11b20672a9b7b1a8c40dd24a7e3','2015-11-30 15:25:35','create table article_category (\nid                        bigint auto_increment not null,\ncreate_date               datetime,\narticle_id                bigint,\ncategory_id               bigint,\nconstraint pk_article_category primary key (id))\n;\n\nalter table article_category add constraint fk_article_category_article_1 foreign key (article_id) references article (id) on delete restrict on update restrict;\ncreate index ix_article_category_article_1 on article_category (article_id);\nalter table article_category add constraint fk_article_category_category_2 foreign key (category_id) references category (id) on delete restrict on update restrict;\ncreate index ix_article_category_category_2 on article_category (category_id);','SET FOREIGN_KEY_CHECKS=0;\n\ndrop table article_category;\n\nSET FOREIGN_KEY_CHECKS=1;','applied',''),(5,'974b30f408597dc317b32ae5c3550c380e37bff7','2015-12-01 14:45:57','create table image (\nid                        bigint auto_increment not null,\npublic_id                 varchar(255),\nimage_url                 varchar(255),\nsecret_image_url          varchar(255),\narticle_id                bigint,\nconstraint pk_image primary key (id))\n;\n\nalter table image add constraint fk_image_article_1 foreign key (article_id) references article (id) on delete restrict on update restrict;\ncreate index ix_image_article_1 on image (article_id);','SET FOREIGN_KEY_CHECKS=0;\n\ndrop table image;\n\nSET FOREIGN_KEY_CHECKS=1;','applied','');
/*!40000 ALTER TABLE `play_evolutions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-01 22:38:52
