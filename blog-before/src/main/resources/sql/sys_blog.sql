/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.40 : Database - sys_blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sys_blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sys_blog`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` varchar(128) NOT NULL COMMENT '文章表ID',
  `title` varchar(128) NOT NULL COMMENT '文章标题',
  `cover` varchar(128) DEFAULT NULL COMMENT '文章封面图',
  `content` longtext NOT NULL COMMENT '文章内容',
  `createDate` datetime NOT NULL COMMENT '文章编辑日期',
  `userID` varchar(128) NOT NULL COMMENT '用户表ID',
  `typeID` varchar(128) NOT NULL COMMENT '类别表ID',
  `looks` bigint(20) DEFAULT NULL COMMENT '文章被查看了多少次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`cover`,`content`,`createDate`,`userID`,`typeID`,`looks`) values ('111111','文章标题','','这是内容','2018-05-09 10:17:17','666666','1',NULL),('222222','文章标题','','这是内容','2018-05-09 10:17:41','666666','1',NULL);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` varchar(128) NOT NULL COMMENT '评论表ID',
  `articleID` varchar(128) NOT NULL COMMENT '被评论文章ID',
  `userID` varchar(128) NOT NULL COMMENT '评论用户ID',
  `content` longtext NOT NULL COMMENT '评论内容',
  `createDate` datetime NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`),
  KEY `articleID` (`articleID`),
  KEY `userID` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`articleID`,`userID`,`content`,`createDate`) values ('111111','111111','666666','文章不错','2018-05-09 10:18:13');

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `id` varchar(128) NOT NULL COMMENT '文章类型表ID',
  `typeName` varchar(50) NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `type` */

insert  into `type`(`id`,`typeName`) values ('1','前端'),('2','Java'),('3','Linux'),('4','Python');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(128) NOT NULL COMMENT '用户表ID',
  `nickname` varchar(50) NOT NULL COMMENT '用户昵称',
  `email` varchar(128) NOT NULL COMMENT '用户邮箱',
  `head` varchar(128) DEFAULT NULL COMMENT '用户头像',
  `gender` varchar(5) DEFAULT NULL COMMENT '用户性别',
  `explain` varchar(128) DEFAULT NULL COMMENT '用户个性签名',
  `password` varchar(128) NOT NULL COMMENT '用户密码',
  `state` varchar(5) NOT NULL COMMENT '用户账户状态',
  `createDate` datetime NOT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`nickname`,`email`,`head`,`gender`,`explain`,`password`,`state`,`createDate`) values ('666666','Alex','isidea@outlook.com','','男','山有木兮木有枝','666666','1','2018-05-09 10:16:06');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
