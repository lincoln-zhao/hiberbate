/*
MySQL Data Transfer
Source Host: localhost
Source Database: graduation
Target Host: localhost
Target Database: graduation
Date: 2017/5/7 22:11:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `USER_ID` int(5) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `SEX` varchar(2) DEFAULT NULL,
  `PHONE` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'lincoln', '123', '1', '11');
