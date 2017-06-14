/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : xiaoxuan

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-06-14 19:23:56
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
INSERT INTO `banji` VALUES ('0c908e3c-47d4-4975-839f-c35da0aae181', '幼儿园大班8');
INSERT INTO `banji` VALUES ('2bda75d0-e9be-4e02-9509-5b168d21f271', '幼儿园大班9');
INSERT INTO `banji` VALUES ('2c3a4b53-d94c-448d-87c6-da1af1708f9c', '幼儿园大班4');
INSERT INTO `banji` VALUES ('3dc3b6ff-40ff-4b84-9370-7f97a8c2d886', '幼儿园大班3');
INSERT INTO `banji` VALUES ('42378636-7755-4667-bddf-b55f18f5e3cd', '幼儿园大班2');
INSERT INTO `banji` VALUES ('75b632d4-2742-428a-9a91-2f677bfc6b43', '幼儿园大班1');
INSERT INTO `banji` VALUES ('809ae5a6-e8be-4235-9fed-b4be96aae8d4', '幼儿园大班7');
INSERT INTO `banji` VALUES ('a7610fac-5c87-43e4-bef1-dc6f39c6ec8f', '幼儿园大班10');
INSERT INTO `banji` VALUES ('e998a71d-ddeb-40c1-9bcb-528d18fb314c', '幼儿园大班6');
INSERT INTO `banji` VALUES ('f0cbbf0f-bd5d-49e2-8c99-ca788e3df1cb', '幼儿园大班5');

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
INSERT INTO `homework` VALUES ('8ab27b4f-7e53-4e3d-b1c0-361df0fda963', '语文作业', '抄写满江红', '1504919349', '070b28c3-dce0-4ad2-85e1-f5a708833ecd');
INSERT INTO `homework` VALUES ('c8b4a71a-e997-456d-b23a-b3f6e90a5015', '语文作业', '抄写出师表', '1504919349', '3ee9b47b-4a24-4a40-bf3b-537e73a36596');
INSERT INTO `homework` VALUES ('c92c384a-c879-484d-9bb1-ceaa7744de83', '语文作业', '抄写欢乐颂', '1504919349', '3ee9b47b-4a24-4a40-bf3b-537e73a36596');

-- ----------------------------
-- Table structure for homeworkdata
-- ----------------------------
DROP TABLE IF EXISTS `homeworkdata`;
CREATE TABLE `homeworkdata` (
  `id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `fileid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homeworkdata
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
INSERT INTO `kecheng` VALUES ('048de9b1-2266-455c-add9-fb8c621298c3', '语文10', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('070b28c3-dce0-4ad2-85e1-f5a708833ecd', '语文12', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('0b2844bd-fdb8-41f4-a3d6-3a5c9e1f5efc', '语文8', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('312baa3e-0b24-4b5a-8d86-52d2bf898841', '语文2', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('3ee9b47b-4a24-4a40-bf3b-537e73a36596', '语文11', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('4840bb00-be87-425c-b437-dfa26319e152', '语文7', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('62e94782-06a7-48ce-9422-12278261a6f4', '语文6', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('6bbdf042-603e-413d-95fa-ad56ec6d3d1e', '语文5', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('90611373-5a51-4b44-9dd4-2782beaf39a6', '语文9', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('97422792-bf5a-4006-95aa-f76d9cedf528', '语文4', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('df35a319-30a9-4306-a77b-3c589077f2be', '语文3', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');
INSERT INTO `kecheng` VALUES ('ff11329b-c7db-4f5c-9784-915ca2c8e199', '语文1', 'A0000004', '0c908e3c-47d4-4975-839f-c35da0aae181', '50');

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

-- ----------------------------
-- Table structure for stukecheng
-- ----------------------------
DROP TABLE IF EXISTS `stukecheng`;
CREATE TABLE `stukecheng` (
  `id` varchar(128) NOT NULL,
  `stuid` varchar(128) NOT NULL,
  `kechengid` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stukecheng
-- ----------------------------
INSERT INTO `stukecheng` VALUES ('0efa81bb-b27a-470a-9029-8a735c527957', 'A2013121004', '0b2844bd-fdb8-41f4-a3d6-3a5c9e1f5efc');
INSERT INTO `stukecheng` VALUES ('3387db7b-ee3a-4c0f-92f0-b43c752e0127', 'A2013121003', '3ee9b47b-4a24-4a40-bf3b-537e73a36596');
INSERT INTO `stukecheng` VALUES ('3e2d85d0-9e2f-4550-b9b5-3ae1cbbd7194', 'A2013121004', '3ee9b47b-4a24-4a40-bf3b-537e73a36596');
INSERT INTO `stukecheng` VALUES ('3e2d85d0-9e2f-4550-b9b5-3ae1cbbd7195', 'A2013121004', '070b28c3-dce0-4ad2-85e1-f5a708833ecd');
INSERT INTO `stukecheng` VALUES ('41737c79-3e4e-4626-a5f8-a31aad840d9a', 'A2013121003', '90611373-5a51-4b44-9dd4-2782beaf39a6');
INSERT INTO `stukecheng` VALUES ('9b827381-92b9-476e-b37d-d43cd8d5eec6', 'A2013121004', '4840bb00-be87-425c-b437-dfa26319e152');
INSERT INTO `stukecheng` VALUES ('c9388e79-1c42-4df6-8616-61ebf9c6ed15', 'A2013121004', '62e94782-06a7-48ce-9422-12278261a6f4');

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
  `likepoint` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `stu-pro` (`stuid`),
  KEY `pro-work` (`workid`),
  CONSTRAINT `pro-work` FOREIGN KEY (`workid`) REFERENCES `homework` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu-pro` FOREIGN KEY (`stuid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stupro
-- ----------------------------
INSERT INTO `stupro` VALUES ('dd44ac08-7ee4-4622-908b-154b7afa502c', 'A2013121004', 'c8b4a71a-e997-456d-b23a-b3f6e90a5015', '\\homework\\A2013121004\\c8b4a71a-e997-456d-b23a-b3f6e90a5015\\19dapp-debug.apk', '1496307712', '0', '0');

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
INSERT INTO `teacher` VALUES ('A0000001', '测试员1', '7C6A180B36896A0A8C02787EEAFB0E4C', null, null);
INSERT INTO `teacher` VALUES ('A0000002', '测试员2', '6CB75F652A9B52798EB6CF2201057C73', null, null);
INSERT INTO `teacher` VALUES ('A0000003', '测试员3', '819B0643D6B89DC9B579FDFC9094F28E', null, null);
INSERT INTO `teacher` VALUES ('A0000004', '测试员4', '34CC93ECE0BA9E3F6F235D4AF979B16C', null, null);
INSERT INTO `teacher` VALUES ('A0000005', '测试员5', 'DB0EDD04AAAC4506F7EDAB03AC855D56', null, null);
INSERT INTO `teacher` VALUES ('A0000006', '测试员6', '218DD27AEBECCECAE69AD8408D9A36BF', null, null);
INSERT INTO `teacher` VALUES ('A0000007', '测试员7', '00CDB7BB942CF6B290CEB97D6ACA64A3', null, null);
INSERT INTO `teacher` VALUES ('A0000008', '测试员8', 'B25EF06BE3B6948C0BC431DA46C2C738', null, null);
INSERT INTO `teacher` VALUES ('A0000009', '测试员9', '5D69DD95AC183C9643780ED7027D128A', null, null);
INSERT INTO `teacher` VALUES ('A0000010', '测试员10', '87E897E3B54A405DA144968B2CA19B45', null, null);
INSERT INTO `teacher` VALUES ('A0000011', '测试员11', '1E5C2776CF544E213C3D279C40719643', null, null);

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
INSERT INTO `user` VALUES ('A2013121001', '刘壮实1', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121002', '刘壮实2', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121003', '刘壮实3', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121004', '刘壮实4', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121005', '刘壮实5', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121006', '刘壮实6', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121007', '刘壮实7', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121008', '刘壮实8', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121009', '刘壮实9', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121010', '刘壮实10', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121011', '刘壮实11', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121012', '刘壮实12', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121013', '刘壮实13', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121014', '刘壮实14', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121015', '刘壮实15', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121016', '刘壮实16', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121017', '刘壮实17', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121018', '刘壮实18', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
INSERT INTO `user` VALUES ('A2013121019', '刘壮实19', 'E10ADC3949BA59ABBE56E057F20F883E', '0c908e3c-47d4-4975-839f-c35da0aae181');
