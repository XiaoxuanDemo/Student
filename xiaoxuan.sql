/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : xiaoxuan

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-24 09:07:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for banji
-- ----------------------------
DROP TABLE IF EXISTS `banji`;
CREATE TABLE `banji` (
  `classid` varchar(128) NOT NULL,
  `classname` varchar(128) NOT NULL,
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banji
-- ----------------------------

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `id` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `content` varchar(128) NOT NULL,
  `lasttime` varchar(128) NOT NULL,
  `kechengid` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kecheng-zuoye` (`kechengid`),
  CONSTRAINT `kecheng-zuoye` FOREIGN KEY (`kechengid`) REFERENCES `kecheng` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------

-- ----------------------------
-- Table structure for kecheng
-- ----------------------------
DROP TABLE IF EXISTS `kecheng`;
CREATE TABLE `kecheng` (
  `id` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `teahcerid` varchar(128) NOT NULL,
  `classid` varchar(128) NOT NULL,
  `stunum` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher-kechengid` (`teahcerid`),
  KEY `banji-kecheng` (`classid`),
  CONSTRAINT `banji-kecheng` FOREIGN KEY (`classid`) REFERENCES `banji` (`classid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher-kechengid` FOREIGN KEY (`teahcerid`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kecheng
-- ----------------------------

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` varchar(128) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('admin', 'admin', 'admin');

-- ----------------------------
-- Table structure for stupro
-- ----------------------------
DROP TABLE IF EXISTS `stupro`;
CREATE TABLE `stupro` (
  `id` varchar(128) NOT NULL,
  `stuid` varchar(128) NOT NULL,
  `workid` varchar(128) NOT NULL,
  `filepath` varchar(128) NOT NULL,
  `createtime` varchar(128) NOT NULL,
  `score` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `stu-pro` (`stuid`),
  KEY `pro-work` (`workid`),
  CONSTRAINT `pro-work` FOREIGN KEY (`workid`) REFERENCES `homework` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu-pro` FOREIGN KEY (`stuid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stupro
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(128) NOT NULL,
  `teachername` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `telphone` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(128) NOT NULL,
  `stunam` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `classid` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `banji-xuesheng` (`classid`),
  CONSTRAINT `banji-xuesheng` FOREIGN KEY (`classid`) REFERENCES `banji` (`classid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
