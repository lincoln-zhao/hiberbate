/*
MySQL Data Transfer
Source Host: localhost
Source Database: graduation
Target Host: localhost
Target Database: graduation
Date: 2017/5/22 23:34:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
CREATE TABLE `t_admin` (
  `admin_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
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

-- ----------------------------
-- Table structure for t_borrowbook
-- ----------------------------
CREATE TABLE `t_borrowbook` (
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `book_id` varchar(10) NOT NULL COMMENT '图书ID',
  `borrow_out_time` date NOT NULL COMMENT '借书时间',
  PRIMARY KEY (`user_id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_borrowbookhistory
-- ----------------------------
CREATE TABLE `t_borrowbookhistory` (
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `book_id` varchar(10) NOT NULL COMMENT '图书ID',
  `start_date` date NOT NULL COMMENT '借书时间',
  `end_date` date NOT NULL COMMENT '还书时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '注释'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_classification
-- ----------------------------
CREATE TABLE `t_classification` (
  `classification_id` int(5) NOT NULL AUTO_INCREMENT,
  `classification_name` varchar(20) NOT NULL,
  PRIMARY KEY (`classification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USER_NAME` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(10) NOT NULL COMMENT '密码',
  `SEX` varchar(5) DEFAULT NULL COMMENT '性别',
  `PHONE` varchar(11) DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '123');
INSERT INTO `t_book` VALUES ('a1', '啊啊', '强哥', '文学', 'B03', '0', '2017-05-07', 'main.f.png');
INSERT INTO `t_book` VALUES ('a2', '中文书学', '不认识', '小说', 'A15', '0', '2017-05-08', 'main.g.png');
INSERT INTO `t_book` VALUES ('a3', 'asd', 'mack', '技术', 'C26', '1', '2017-05-09', 'main.f.png');
INSERT INTO `t_book` VALUES ('a4', '不知道', '作者1', '技术', '', '0', '2017-05-11', 'main.h.png');
INSERT INTO `t_book` VALUES ('a5', '变成', '李刚', '小说', 'G99', '5', '2017-05-15', 'main.g.png');
INSERT INTO `t_book` VALUES ('a6', 'yes', '小明', '技术', 'H81', '6', '2017-05-16', 'main.f.png');
INSERT INTO `t_book` VALUES ('a7', '美容针', '幺蛾子姐', '文学', 'U34', '0', '2017-05-17', 'main.h.png');
INSERT INTO `t_book` VALUES ('a8', '菜根谭', '呵呵哒', '文学', 'Y55', '10', '2017-05-19', 'main.g.png');
INSERT INTO `t_borrowbook` VALUES ('1', 'a5', '2017-05-20');
INSERT INTO `t_borrowbook` VALUES ('2', 'a8', '2017-05-22');
INSERT INTO `t_borrowbookhistory` VALUES ('1', 'a1', '2017-05-01', '2017-05-09', null);
INSERT INTO `t_borrowbookhistory` VALUES ('1', 'a2', '2017-04-10', '2017-04-26', null);
INSERT INTO `t_borrowbookhistory` VALUES ('1', 'a3', '2017-05-04', '2017-05-22', null);
INSERT INTO `t_borrowbookhistory` VALUES ('1', 'a8', '2017-05-08', '2017-05-22', null);
INSERT INTO `t_borrowbookhistory` VALUES ('2', 'a6', '2017-05-22', '2017-05-22', null);
INSERT INTO `t_classification` VALUES ('1', '文学');
INSERT INTO `t_classification` VALUES ('2', '小说');
INSERT INTO `t_classification` VALUES ('3', '技术');
INSERT INTO `t_classification` VALUES ('4', '教育');
INSERT INTO `t_classification` VALUES ('5', '生活');
INSERT INTO `t_classification` VALUES ('6', '文艺');
INSERT INTO `t_classification` VALUES ('7', '青春');
INSERT INTO `t_classification` VALUES ('8', '职场');
INSERT INTO `t_classification` VALUES ('9', '历史');
INSERT INTO `t_classification` VALUES ('10', '军事');
INSERT INTO `t_classification` VALUES ('12', '法法');
INSERT INTO `t_user` VALUES ('1', 'lincoln', '123', '女', '777');
INSERT INTO `t_user` VALUES ('2', 'mary', '123', '男', '888');
