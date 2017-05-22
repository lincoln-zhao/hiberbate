/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.31-log : Database - graduation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`graduation` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `graduation`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `admin_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_admin` */

insert  into `t_admin`(`admin_id`,`admin_name`,`password`) values (1,'admin','123');

/*Table structure for table `t_book` */

DROP TABLE IF EXISTS `t_book`;

CREATE TABLE `t_book` (
  `book_id` varchar(10) NOT NULL COMMENT '图书编号',
  `book_name` varchar(50) NOT NULL COMMENT '图书名',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `classification` varchar(10) NOT NULL COMMENT '种类',
  `position` varchar(10) NOT NULL COMMENT '存放位置',
  `number` int(10) NOT NULL DEFAULT '0' COMMENT '借阅次数',
  `add_date` date NOT NULL COMMENT '上架时间',
  `picture` varchar(50) NOT NULL COMMENT '封面图片',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_book` */

insert  into `t_book`(`book_id`,`book_name`,`author`,`classification`,`position`,`number`,`add_date`,`picture`) values ('a1','啊啊','强哥','文学','B03',0,'2017-05-07','main.f.png'),('a2','中文书学','不认识','小说','A15',0,'2017-05-08','main.g.png'),('a3','asd','mack','技术','C26',1,'2017-05-09','main.f.png'),('a4','不知道','作者1','技术','',0,'2017-05-11','main.h.png'),('a5','变成','李刚','小说','G99',5,'2017-05-15','main.g.png'),('a6','yes','小明','技术','H81',5,'2017-05-16','main.f.png'),('a7','美容针','幺蛾子姐','文学','U34',0,'2017-05-17','main.h.png'),('a8','菜根谭','呵呵哒','文学','Y55',10,'2017-05-19','main.g.png');

/*Table structure for table `t_borrowbook` */

DROP TABLE IF EXISTS `t_borrowbook`;

CREATE TABLE `t_borrowbook` (
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `book_id` varchar(10) NOT NULL COMMENT '图书ID',
  `borrow_out_time` date NOT NULL COMMENT '借书时间',
  PRIMARY KEY (`user_id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_borrowbook` */

insert  into `t_borrowbook`(`user_id`,`book_id`,`borrow_out_time`) values (1,'a5','2017-05-20');

/*Table structure for table `t_borrowbookhistory` */

DROP TABLE IF EXISTS `t_borrowbookhistory`;

CREATE TABLE `t_borrowbookhistory` (
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `book_id` varchar(10) NOT NULL COMMENT '图书ID',
  `start_date` date NOT NULL COMMENT '借书时间',
  `end_date` date NOT NULL COMMENT '还书时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '注释'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_borrowbookhistory` */

insert  into `t_borrowbookhistory`(`user_id`,`book_id`,`start_date`,`end_date`,`remarks`) values (1,'a1','2017-05-01','2017-05-09',NULL),(1,'a2','2017-04-10','2017-04-26',NULL),(1,'a3','2017-05-04','2017-05-22',NULL),(1,'a8','2017-05-08','2017-05-22',NULL);

/*Table structure for table `t_news` */

DROP TABLE IF EXISTS `t_news`;

CREATE TABLE `t_news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `add_date` date NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_news` */

insert  into `t_news`(`news_id`,`title`,`content`,`add_date`) values (1,'新闻1','这是内容，，，，啊啊啊','2017-05-22'),(2,'news2','aaa ','2017-05-22'),(3,'新闻3','无法无法发哦发哦地方','2017-05-22'),(4,'啊新闻4','额发感染而GV是DVD是否','2017-05-22'),(5,'哈哈新闻5','3333房他','2017-05-22'),(6,'6新闻地方','无法万恶反而','2017-05-22'),(7,'7涣发大号不认同还让他','二个人个个人个','2017-05-22');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USER_NAME` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(10) NOT NULL COMMENT '密码',
  `SEX` varchar(5) DEFAULT NULL COMMENT '性别',
  `PHONE` varchar(11) DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`USER_ID`,`USER_NAME`,`PASSWORD`,`SEX`,`PHONE`) values (1,'lincoln','123','女','777'),(2,'mary','123','女','889');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
