/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : automate

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-12-02 10:09:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for basis_substance_type
-- ----------------------------
DROP TABLE IF EXISTS `basis_substance_type`;
CREATE TABLE `basis_substance_type` (
  `ID` varchar(64) NOT NULL,
  `TYPE_NAME` varchar(64) DEFAULT NULL,
  `DESCN` varchar(128) DEFAULT NULL,
  `STATUS` varchar(64) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MODIFY_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DISP_INX` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basis_substance_type
-- ----------------------------
INSERT INTO `basis_substance_type` VALUES ('5b959b67-91b1-11e5-bf87-10604b6fd31b', '环境配置', '环境配置', 'T', '2015-11-23 15:11:06', '2015-11-23 15:11:06', '1');
INSERT INTO `basis_substance_type` VALUES ('6fe2b956-91b1-11e5-bf87-10604b6fd31b', '节点配置', '节点配置', 'T', '2015-11-23 15:11:40', '2015-11-23 15:11:40', '3');
INSERT INTO `basis_substance_type` VALUES ('74ef1aed-91b1-11e5-bf87-10604b6fd31b', '存储配置', '存储配置', 'T', '2015-11-23 15:11:49', '2015-11-23 15:11:49', '4');
