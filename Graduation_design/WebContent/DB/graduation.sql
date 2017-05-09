/*
MySQL Data Transfer
Source Host: localhost
Source Database: graduation
Target Host: localhost
Target Database: graduation
Date: 2017/5/9 23:13:01
*/

SET FOREIGN_KEY_CHECKS=0;
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
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_borrowbookhistory
-- ----------------------------
CREATE TABLE `t_borrowbookhistory` (
  `user_id` int(10) NOT NULL,
  `book_id` varchar(10) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(10) NOT NULL,
  `SEX` varchar(5) DEFAULT NULL,
  `PHONE` varchar(11) DEFAULT NULL,
  `book_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_book` VALUES ('a1', '啊啊', '强哥', '文学', 'B03', '0');
INSERT INTO `t_book` VALUES ('a2', '中文书', '不认识', '小说', 'A15', '0');
INSERT INTO `t_book` VALUES ('a3', 'asd', 'mack', '技术', 'C26', '0');
INSERT INTO `t_borrowbookhistory` VALUES ('1', 'a1', '2017-05-01', '2017-05-09', null);
INSERT INTO `t_borrowbookhistory` VALUES ('1', 'a2', '2017-04-10', '2017-04-26', null);
INSERT INTO `t_user` VALUES ('1', 'lincoln', '123', '男', '888', 'a3');
