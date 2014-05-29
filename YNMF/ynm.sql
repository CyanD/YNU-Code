/*
Navicat MySQL Data Transfer

Source Server         : ccb
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : ynm

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-05-08 10:44:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `rol_id` bigint(30) DEFAULT NULL,
  `com_id` bigint(30) NOT NULL,
  `userName` varchar(30) NOT NULL,
  `password` varchar(64) NOT NULL,
  `cardType` enum('护照','军官证','身份证') NOT NULL DEFAULT '身份证',
  `IDNum` varchar(20) NOT NULL,
  `failureDate` date DEFAULT NULL,
  `customerStatus` enum('注销','正常') NOT NULL DEFAULT '正常',
  `email` varchar(20) DEFAULT NULL,
  `cellphone` varchar(11) DEFAULT NULL,
  `tel` varchar(13) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `zipCode` varchar(10) DEFAULT NULL,
  `sex` enum('女','男') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `educationalBackground` enum('博士','研究生','本科','大专') NOT NULL,
  `occupation` varchar(20) NOT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `customerLev` varchar(255) DEFAULT NULL,
  `loyalty` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_1` (`com_id`),
  KEY `FK_Relationship_2` (`rol_id`),
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`com_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK_Relationship_2` FOREIGN KEY (`rol_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '1', '1', 'super', '123456', '身份证', '12112', '2013-11-11', '正常', '9999', '1', '1', null, null, '男', '2013-12-07', '本科', '1', '1', '高级', '');
INSERT INTO `user` VALUES ('4', '2', '2', 'tester', '123456', '身份证', '1223232323213', '2014-01-31', '正常', '23123@qq.com', '13444444444', '5999999', 'XXXXXX', '343333', '女', '2014-01-15', '研究生', '职员', '11', '高级', '11');
