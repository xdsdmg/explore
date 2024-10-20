/*
SQLyog Community v12.4.3 (32 bit)
MySQL - 5.7.44 : Database - explore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`explore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `explore`;

/*Table structure for table `topic` */

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `content` text COLLATE utf8mb4_bin,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `topic` */

insert  into `topic`(`id`,`title`,`user_id`,`content`,`created_at`,`updated_at`) values 
(1,'test',42,NULL,'2024-05-20 01:36:19','2024-05-20 01:36:19'),
(2,'abcd',42,NULL,'2024-05-20 02:00:28','2024-05-20 02:00:28'),
(3,'1234',42,NULL,'2024-06-07 05:11:41','2024-06-07 05:11:41'),
(4,'1234',42,'','2024-06-29 16:22:10','2024-06-29 16:22:10'),
(5,'1234',42,'Hello Markdown!aaaaaaaa','2024-06-29 16:22:41','2024-06-29 16:22:41');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `pwd` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `email` varchar(30) COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `activated` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 - nonactivated; 1 - activated',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`pwd`,`email`,`activated`,`created_at`,`updated_at`) values 
(42,'zhangchi','b69f29db87f8a750afd373f373a891d893b2ea92064ea2e5cc56e5de716dc7a5','xdsdmg@163.com',1,'2024-05-19 12:08:22','2024-05-19 12:08:22'),
(43,'ZhangChi','1d08bec70abdd57c9ec321349f8fbeebc2ff893abcaeb6f4ef46ee31c310a14f','2216336843@qq.com',1,'2024-08-07 15:47:43','2024-08-07 15:47:43');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
