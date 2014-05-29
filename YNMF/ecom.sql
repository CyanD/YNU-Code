/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : ecom

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2014-05-21 10:09:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ordersid` bigint(30) NOT NULL,
  `ordersstatus` varchar(10) DEFAULT NULL,
  `userid` bigint(30) NOT NULL,
  `productid` bigint(30) NOT NULL,
  PRIMARY KEY (`ordersid`),
  KEY `Refuser7` (`userid`),
  KEY `Refproduct8` (`productid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '1', '1', '1');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productid` bigint(30) NOT NULL,
  `productpic` varchar(20) DEFAULT NULL,
  `productbinfo` varchar(50) DEFAULT NULL,
  `productprice` varchar(20) DEFAULT NULL,
  `productsum` varchar(10) DEFAULT NULL,
  `showtime` datetime DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `userid` bigint(30) NOT NULL,
  `productname` varchar(20) DEFAULT NULL,
  `producttypeid` bigint(30) NOT NULL,
  PRIMARY KEY (`productid`),
  KEY `Refuser1` (`userid`),
  KEY `Refproducttype4` (`producttypeid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '1', '1', '1', '1', '2014-05-20 23:35:29', '2014-05-20 23:35:33', '1', '1', '1');

-- ----------------------------
-- Table structure for `productspecial`
-- ----------------------------
DROP TABLE IF EXISTS `productspecial`;
CREATE TABLE `productspecial` (
  `productspid` bigint(30) NOT NULL,
  `productid` bigint(30) NOT NULL,
  `productdetail` varchar(50) DEFAULT NULL,
  `productphos` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`productspid`),
  KEY `Refproduct18` (`productid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productspecial
-- ----------------------------
INSERT INTO `productspecial` VALUES ('1', '1', '1', '1');

-- ----------------------------
-- Table structure for `producttype`
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype` (
  `producttypeid` bigint(30) NOT NULL,
  `producttypename` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`producttypeid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `purchaseinfo`
-- ----------------------------
DROP TABLE IF EXISTS `purchaseinfo`;
CREATE TABLE `purchaseinfo` (
  `producttypeid` bigint(30) NOT NULL,
  `userid` bigint(30) NOT NULL,
  `phead` varchar(20) DEFAULT NULL,
  `pdetail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`producttypeid`,`userid`),
  KEY `Refuser10` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchaseinfo
-- ----------------------------
INSERT INTO `purchaseinfo` VALUES ('1', '1', '1', '1');

-- ----------------------------
-- Table structure for `shoppingcart`
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `userid` bigint(30) NOT NULL,
  `productid` bigint(30) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`userid`,`productid`),
  KEY `Refproduct13` (`productid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('1', '1', '2014-05-20 23:34:48');

-- ----------------------------
-- Table structure for `store`
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `storeid` bigint(30) NOT NULL,
  `producttypeid` bigint(30) NOT NULL,
  `userid` bigint(30) NOT NULL,
  PRIMARY KEY (`storeid`),
  KEY `Refproducttype16` (`producttypeid`),
  KEY `Refuser17` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` bigint(30) NOT NULL,
  `userrole` char(1) DEFAULT NULL,
  `password` varchar(10) NOT NULL,
  `username` varchar(10) NOT NULL,
  `userphotourl` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1', '1', '1', '1', '1', '1');
