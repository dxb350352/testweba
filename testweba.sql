/*
Navicat MySQL Data Transfer

Source Server         : 192.168.130.201
Source Server Version : 50723
Source Host           : 192.168.130.201:3306
Source Database       : testweba

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-26 16:24:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '12', '24', '1');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `customer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'nnn', '2', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `userAge` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `member` varchar(255) DEFAULT NULL,
  `userAddress` varchar(255) DEFAULT NULL,
  `reg_time` datetime DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1', '1', '1', '1', '2018-10-15 00:00:00', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `password` varchar(256) DEFAULT NULL COMMENT '登录密码',
  `name` varchar(256) DEFAULT NULL COMMENT '用户真实姓名',
  `id_card_num` varchar(256) DEFAULT NULL COMMENT '用户身份证号',
  `state` char(1) DEFAULT '0' COMMENT '用户状态：0:正常状态,1：用户被锁定',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `id_card_num` (`id_card_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('4', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超哥', '133333333333333333', '0');
INSERT INTO `user_info` VALUES ('5', 'test', 'ed0290f05224a188160858124a5f5077', '超哥哥', '166666666666666666', '0');

-- ----------------------------
-- Table structure for user_two
-- ----------------------------
DROP TABLE IF EXISTS `user_two`;
CREATE TABLE `user_two` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_two
-- ----------------------------
