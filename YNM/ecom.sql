/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50016
Source Host           : localhost:3306
Source Database       : ecom

Target Server Type    : MYSQL
Target Server Version : 50016
File Encoding         : 65001

Date: 2014-06-02 10:04:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ordersid` bigint(10) NOT NULL auto_increment,
  `ordersstatus` varchar(10) default NULL,
  `userid` bigint(10) NOT NULL,
  `productid` bigint(10) NOT NULL,
  PRIMARY KEY  (`ordersid`),
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
  `productid` bigint(10) NOT NULL auto_increment,
  `productpic` varchar(20) default NULL,
  `productbinfo` varchar(50) default NULL,
  `productprice` varchar(20) default NULL,
  `productsum` varchar(10) default NULL,
  `showtime` date default NULL,
  `deletetime` date default NULL,
  `userid` bigint(10) default NULL,
  `productname` varchar(20) default NULL,
  `producttypeid` bigint(10) default NULL,
  PRIMARY KEY  (`productid`),
  KEY `Refuser1` (`userid`),
  KEY `Refproducttype4` (`producttypeid`),
  CONSTRAINT `Refproducttype4` FOREIGN KEY (`producttypeid`) REFERENCES `producttype` (`producttypeid`),
  CONSTRAINT `Refuser1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('9', '123', '123', '123', '123', '2014-05-14', '2014-05-24', '1', '麦当劳', '2');
INSERT INTO `product` VALUES ('10', '242', '133322', '22', '22', '2014-05-24', '2014-06-05', '1', 'kfc', '3');
INSERT INTO `product` VALUES ('13', '1', '1', '1', '1', '2014-04-29', '2014-05-07', '1', '小驴', '4');
INSERT INTO `product` VALUES ('14', 'ww', 'aaa', '11', '34', '2014-05-29', '2014-06-03', '1', 'ww', '3');

-- ----------------------------
-- Table structure for `productspecial`
-- ----------------------------
DROP TABLE IF EXISTS `productspecial`;
CREATE TABLE `productspecial` (
  `productspid` bigint(10) NOT NULL auto_increment,
  `productid` bigint(10) NOT NULL,
  `productdetail` varchar(50) default NULL,
  `productphos` varchar(20) default NULL,
  PRIMARY KEY  (`productspid`),
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
  `producttypeid` bigint(10) NOT NULL auto_increment,
  `producttypename` varchar(10) default NULL,
  PRIMARY KEY  (`producttypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES ('1', '女装');
INSERT INTO `producttype` VALUES ('2', '男装');
INSERT INTO `producttype` VALUES ('3', '鞋/箱包');
INSERT INTO `producttype` VALUES ('4', '化妆品');
INSERT INTO `producttype` VALUES ('5', '食品');
INSERT INTO `producttype` VALUES ('6', '玩具');
INSERT INTO `producttype` VALUES ('7', '图书音像');
INSERT INTO `producttype` VALUES ('8', '农副产品');
INSERT INTO `producttype` VALUES ('9', '数码产品');
INSERT INTO `producttype` VALUES ('10', '其他');

-- ----------------------------
-- Table structure for `purchaseinfo`
-- ----------------------------
DROP TABLE IF EXISTS `purchaseinfo`;
CREATE TABLE `purchaseinfo` (
  `pinfoid` bigint(10) NOT NULL auto_increment,
  `producttypeid` bigint(10) NOT NULL,
  `userid` bigint(10) NOT NULL,
  `phead` varchar(20) default NULL,
  `pdetail` varchar(50) default NULL,
  PRIMARY KEY  (`pinfoid`),
  KEY `Refuser10` (`userid`),
  KEY `producttypeid` (`producttypeid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchaseinfo
-- ----------------------------
INSERT INTO `purchaseinfo` VALUES ('2', '1', '1', '求购女装', '求购女装一件');
INSERT INTO `purchaseinfo` VALUES ('4', '2', '3', '求购男装', '男装一件');

-- ----------------------------
-- Table structure for `shoppingcart`
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `cartid` bigint(10) NOT NULL auto_increment,
  `userid` bigint(10) NOT NULL,
  `productid` bigint(10) NOT NULL,
  `createtime` datetime default NULL,
  PRIMARY KEY  (`cartid`),
  KEY `Refproduct13` (`productid`),
  KEY `tuserid` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('2', '1', '1', '2014-05-20 23:34:48');

-- ----------------------------
-- Table structure for `store`
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `storeid` bigint(10) NOT NULL auto_increment,
  `producttypeid` bigint(10) NOT NULL,
  `userid` bigint(10) NOT NULL,
  PRIMARY KEY  (`storeid`),
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
  `userid` bigint(10) NOT NULL auto_increment,
  `userrole` varchar(10) default NULL,
  `password` varchar(10) NOT NULL,
  `username` varchar(10) NOT NULL,
  `userphotourl` varchar(20) default NULL,
  `telephone` varchar(20) default NULL,
  `email` varchar(20) default NULL,
  `position` varchar(50) default NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '卖家', '123', '吕昕', '123', '123', '123', '123');
INSERT INTO `user` VALUES ('2', '卖家', '123', '123', '123', '123', '123', '123');
INSERT INTO `user` VALUES ('3', '买家', '123', 'xx', '12', '123', '1234', '123');

-- ----------------------------
-- View structure for `viewproduct`
-- ----------------------------
DROP VIEW IF EXISTS `viewproduct`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewproduct` AS select `product`.`productid` AS `productid`,`product`.`productpic` AS `productpic`,`product`.`productbinfo` AS `productbinfo`,`product`.`productprice` AS `productprice`,`product`.`productsum` AS `productsum`,`product`.`showtime` AS `showtime`,`product`.`deletetime` AS `deletetime`,`product`.`userid` AS `userid`,`product`.`productname` AS `productname`,`product`.`producttypeid` AS `producttypeid`,`producttype`.`producttypename` AS `producttypename`,`user`.`username` AS `username` from ((`product` join `producttype`) join `user`) where ((`product`.`userid` = `user`.`userid`) and (`product`.`producttypeid` = `producttype`.`producttypeid`));

-- ----------------------------
-- View structure for `viewpurchase`
-- ----------------------------
DROP VIEW IF EXISTS `viewpurchase`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewpurchase` AS select `purchaseinfo`.`pinfoid` AS `pinfoid`,`purchaseinfo`.`producttypeid` AS `producttypeid`,`purchaseinfo`.`userid` AS `userid`,`purchaseinfo`.`phead` AS `phead`,`purchaseinfo`.`pdetail` AS `pdetail`,`producttype`.`producttypename` AS `producttypename`,`user`.`username` AS `username`,`user`.`telephone` AS `telephone`,`user`.`position` AS `position` from ((`purchaseinfo` join `producttype`) join `user`) where ((`purchaseinfo`.`userid` = `user`.`userid`) and (`purchaseinfo`.`producttypeid` = `producttype`.`producttypeid`));
