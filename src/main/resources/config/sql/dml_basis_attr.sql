/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : automate

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-12-02 10:09:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for basis_attr
-- ----------------------------
DROP TABLE IF EXISTS `basis_attr`;
CREATE TABLE `basis_attr` (
  `ID` varchar(64) NOT NULL,
  `ATTR_NAME` varchar(64) DEFAULT NULL,
  `ATTR_COLUMN` varchar(64) DEFAULT NULL,
  `ATTR_TYPE` varchar(64) DEFAULT NULL,
  `REQUIRED` varchar(64) DEFAULT NULL,
  `BASIS_SUBSTANCE_TYPE_ID` varchar(64) DEFAULT NULL,
  `DESCN` varchar(128) DEFAULT NULL,
  `STATUS` varchar(64) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MODIFY_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DISP_INX` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basis_attr
-- ----------------------------
INSERT INTO `basis_attr` VALUES ('004d339e-94b5-11e5-bf87-10604b6fd31b', 'GLUSTERFS_CINDER', 'GLUSTERFS_CINDER', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:14:48', '2015-11-27 11:14:48', '12');
INSERT INTO `basis_attr` VALUES ('02b51ec9-924e-11e5-bf87-10604b6fd31b', '逻辑单元IQN', 'LJDYIQN', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:52:29', '2015-11-24 09:52:29', '7');
INSERT INTO `basis_attr` VALUES ('081988a2-94b5-11e5-bf87-10604b6fd31b', 'OCFS2_CINDER', 'OCFS2_CINDER', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:15:01', '2015-11-27 11:15:01', '13');
INSERT INTO `basis_attr` VALUES ('0c171836-94d8-11e5-bf87-10604b6fd31b', '存储网段_止', 'CCWD_Z', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:25:40', '2015-11-27 15:25:40', '22');
INSERT INTO `basis_attr` VALUES ('0e8377f8-94b5-11e5-bf87-10604b6fd31b', 'CEPH_CINDER', 'CEPH_CINDER', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:15:12', '2015-11-27 11:15:12', '14');
INSERT INTO `basis_attr` VALUES ('1481ed3d-94b5-11e5-bf87-10604b6fd31b', 'GLUSTERFS_GLANCE', 'GLUSTERFS_GLANCE', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:15:22', '2015-11-27 11:15:22', '15');
INSERT INTO `basis_attr` VALUES ('189ea439-94d8-11e5-bf87-10604b6fd31b', '调度策略', 'DDCL', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:26:01', '2015-11-27 15:26:01', '23');
INSERT INTO `basis_attr` VALUES ('1a529cee-94b5-11e5-bf87-10604b6fd31b', 'OCFS2_GLANCE', 'OCFS2_GLANCE', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:15:32', '2015-11-27 11:15:32', '16');
INSERT INTO `basis_attr` VALUES ('1b8d8c4d-94db-11e5-bf87-10604b6fd31b', '是否已挂载', 'SFYGZ', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:47:35', '2015-11-27 15:47:35', '11');
INSERT INTO `basis_attr` VALUES ('1d9de6fb-924e-11e5-bf87-10604b6fd31b', '逻辑单元地址', 'LJDYDZ', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:53:14', '2015-11-24 09:53:14', '8');
INSERT INTO `basis_attr` VALUES ('2241a42a-94b5-11e5-bf87-10604b6fd31b', 'CEPH_GLANCE', 'CEPH_GLANCE', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:15:45', '2015-11-27 11:15:45', '17');
INSERT INTO `basis_attr` VALUES ('27aa6faf-924e-11e5-bf87-10604b6fd31b', '环境配置ID', 'HJPZID', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:53:31', '2015-11-24 09:53:31', '9');
INSERT INTO `basis_attr` VALUES ('2c676f75-94b5-11e5-bf87-10604b6fd31b', 'LOCALDISK_NOVA', 'LOCALDISK_NOVA', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:16:02', '2015-11-27 11:16:02', '18');
INSERT INTO `basis_attr` VALUES ('30483505-94d8-11e5-bf87-10604b6fd31b', 'CPU虚拟化比例', 'CPUXNHBL', 'DOUBLE', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:26:41', '2015-11-27 15:26:41', '24');
INSERT INTO `basis_attr` VALUES ('39441eb8-924d-11e5-bf87-10604b6fd31b', '方案类型', 'FALX', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:46:51', '2015-11-24 09:46:51', '1');
INSERT INTO `basis_attr` VALUES ('396dac54-94d8-11e5-bf87-10604b6fd31b', 'RAM虚拟化比例', 'RAMXNHBL', 'DOUBLE', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:26:56', '2015-11-27 15:26:56', '25');
INSERT INTO `basis_attr` VALUES ('4464f0c2-924d-11e5-bf87-10604b6fd31b', '使用网络', 'SYWL', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:47:10', '2015-11-24 09:47:10', '2');
INSERT INTO `basis_attr` VALUES ('50c80e0a-924d-11e5-bf87-10604b6fd31b', '服务主机', 'FWZJ', 'TEXT', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:47:30', '2015-11-24 09:47:30', '3');
INSERT INTO `basis_attr` VALUES ('58e4f713-94db-11e5-bf87-10604b6fd31b', 'Nova共享磁盘ID', 'NOVA_GXCPID', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:49:18', '2015-11-27 15:49:18', '12');
INSERT INTO `basis_attr` VALUES ('61e175b3-94d7-11e5-bf87-10604b6fd31b', '管理网段_始', 'GLWD_S', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:20:55', '2015-11-27 15:20:55', '19');
INSERT INTO `basis_attr` VALUES ('63cd8b15-94db-11e5-bf87-10604b6fd31b', 'Glance共享磁盘ID', 'GLANCE_GXCPID', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:49:36', '2015-11-27 15:49:36', '13');
INSERT INTO `basis_attr` VALUES ('6e480112-94db-11e5-bf87-10604b6fd31b', 'Cinder共享磁盘ID', 'CINDER_GXCPID', 'VARCHAR', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:49:54', '2015-11-27 15:49:54', '14');
INSERT INTO `basis_attr` VALUES ('6f96d89f-94d7-11e5-bf87-10604b6fd31b', '管理网段_止', 'GLWD_Z', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:21:18', '2015-11-27 15:21:18', '20');
INSERT INTO `basis_attr` VALUES ('737a5c8a-91b2-11e5-bf87-10604b6fd31b', '节点角色', 'JDJS', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:18:56', '2015-11-23 15:18:56', '1');
INSERT INTO `basis_attr` VALUES ('78e4de6d-91b2-11e5-bf87-10604b6fd31b', '虚拟化类型', 'XNHLX', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:19:05', '2015-11-23 15:19:05', '2');
INSERT INTO `basis_attr` VALUES ('8cd692e9-91b2-11e5-bf87-10604b6fd31b', '主机名', 'ZJM', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:19:38', '2015-11-23 15:19:38', '3');
INSERT INTO `basis_attr` VALUES ('8f123292-91b1-11e5-bf87-10604b6fd31b', '环境名称', 'HJMC', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:12:33', '2015-11-23 15:12:33', '1');
INSERT INTO `basis_attr` VALUES ('919a85a3-91b2-11e5-bf87-10604b6fd31b', '管理网卡', 'GLWK', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:19:46', '2015-11-23 15:19:46', '4');
INSERT INTO `basis_attr` VALUES ('97ca0aad-91b1-11e5-bf87-10604b6fd31b', '是否为HA', 'SFWHA', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:12:47', '2015-11-23 15:12:47', '2');
INSERT INTO `basis_attr` VALUES ('99f7b7ac-94d7-11e5-bf87-10604b6fd31b', '存储网段_始', 'CCWD_S', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:22:29', '2015-11-27 15:22:29', '21');
INSERT INTO `basis_attr` VALUES ('9e5dccc0-91b2-11e5-bf87-10604b6fd31b', '管理网络IP', 'GLWLIP', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:20:08', '2015-11-23 15:20:08', '5');
INSERT INTO `basis_attr` VALUES ('a487321f-924d-11e5-bf87-10604b6fd31b', 'glance逻辑单元', 'GLANCE_LJDY', 'INT', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:49:51', '2015-11-24 09:49:51', '4');
INSERT INTO `basis_attr` VALUES ('a6b717c7-94b4-11e5-bf87-10604b6fd31b', '虚拟化类型_VM', 'XNHLX_VM', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:12:18', '2015-11-27 11:12:18', '7');
INSERT INTO `basis_attr` VALUES ('a8f71984-94d9-11e5-bf87-10604b6fd31b', 'Nova存储', 'NOVA_CC', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:37:13', '2015-11-27 15:37:13', '15');
INSERT INTO `basis_attr` VALUES ('ad73ff8f-924c-11e5-bf87-10604b6fd31b', '环境配置ID', 'HJPZID', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:42:56', '2015-11-24 09:42:56', '14');
INSERT INTO `basis_attr` VALUES ('af591f48-924d-11e5-bf87-10604b6fd31b', 'nova逻辑单元', 'NOVA_LJDY', 'INT', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:50:09', '2015-11-24 09:50:09', '5');
INSERT INTO `basis_attr` VALUES ('b68b7818-94d9-11e5-bf87-10604b6fd31b', 'VMWareHost', 'VM_HOST', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:37:36', '2015-11-27 15:37:36', '16');
INSERT INTO `basis_attr` VALUES ('b6b92596-91b2-11e5-bf87-10604b6fd31b', '存储网卡', 'CCWK', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:20:49', '2015-11-23 15:20:49', '6');
INSERT INTO `basis_attr` VALUES ('bd8d13ec-91b2-11e5-bf87-10604b6fd31b', '存储网络IP', 'CCWLIP', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:21:00', '2015-11-23 15:21:00', '7');
INSERT INTO `basis_attr` VALUES ('c06e6a03-924d-11e5-bf87-10604b6fd31b', 'cinder逻辑单元', 'CINDER_LJDY', 'INT', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-24 09:50:38', '2015-11-24 09:50:38', '6');
INSERT INTO `basis_attr` VALUES ('c1383de7-94d9-11e5-bf87-10604b6fd31b', 'VMWareUser', 'VM_USER', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:37:54', '2015-11-27 15:37:54', '17');
INSERT INTO `basis_attr` VALUES ('c53e6116-91b2-11e5-bf87-10604b6fd31b', '存储网络掩码', 'CCWLYM', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:21:13', '2015-11-23 15:21:13', '8');
INSERT INTO `basis_attr` VALUES ('cb2966bf-94d9-11e5-bf87-10604b6fd31b', 'VMWarePassword', 'VM_PASSWORD', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:38:10', '2015-11-27 15:38:10', '18');
INSERT INTO `basis_attr` VALUES ('cca151be-94b4-11e5-bf87-10604b6fd31b', '虚拟化类型_IRONIC', 'XNHLX_IRONIC', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:13:21', '2015-11-27 11:13:21', '8');
INSERT INTO `basis_attr` VALUES ('ced73780-91b2-11e5-bf87-10604b6fd31b', '业务网卡', 'YWWK', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:21:29', '2015-11-23 15:21:29', '9');
INSERT INTO `basis_attr` VALUES ('d49d135a-91b2-11e5-bf87-10604b6fd31b', '外部网卡', 'WBWK', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:21:39', '2015-11-23 15:21:39', '10');
INSERT INTO `basis_attr` VALUES ('db0313b9-91b1-11e5-bf87-10604b6fd31b', '虚拟化类型_KVM', 'XNHLX_KVM', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:14:40', '2015-11-23 15:14:40', '5');
INSERT INTO `basis_attr` VALUES ('dd01f619-94da-11e5-bf87-10604b6fd31b', '数据份数', 'SJFS', 'INT', 'T', '74ef1aed-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:45:50', '2015-11-27 15:45:50', '10');
INSERT INTO `basis_attr` VALUES ('e22bb739-94d9-11e5-bf87-10604b6fd31b', 'VMWareCluster', 'VM_CLUSTER', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 15:38:49', '2015-11-27 15:38:49', '19');
INSERT INTO `basis_attr` VALUES ('e33e9bd2-91b1-11e5-bf87-10604b6fd31b', '虚拟化类型_HYPERV', 'XNHLX_HYPERV', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:14:54', '2015-11-23 15:14:54', '6');
INSERT INTO `basis_attr` VALUES ('eb94f1d8-94b4-11e5-bf87-10604b6fd31b', 'GLUSTERFS_NOVA', 'GLUSTERFS_NOVA', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:14:13', '2015-11-27 11:14:13', '9');
INSERT INTO `basis_attr` VALUES ('ebca609b-91b2-11e5-bf87-10604b6fd31b', 'IPMI地址', 'IPMIDZ', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:22:18', '2015-11-23 15:22:18', '11');
INSERT INTO `basis_attr` VALUES ('f15fa923-94b4-11e5-bf87-10604b6fd31b', 'OCFS2_NOVA', 'OCFS2_NOVA', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:14:23', '2015-11-27 11:14:23', '10');
INSERT INTO `basis_attr` VALUES ('f3058c47-91b2-11e5-bf87-10604b6fd31b', 'IPMI用户', 'IPMIYH', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:22:30', '2015-11-23 15:22:30', '12');
INSERT INTO `basis_attr` VALUES ('f959af22-94b4-11e5-bf87-10604b6fd31b', 'CEPH_NOVA', 'CEPH_NOVA', 'VARCHAR', 'T', '5b959b67-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-27 11:14:36', '2015-11-27 11:14:36', '11');
INSERT INTO `basis_attr` VALUES ('f9d744c6-91b2-11e5-bf87-10604b6fd31b', 'IPMI密码', 'IPMIMM', 'VARCHAR', 'T', '6fe2b956-91b1-11e5-bf87-10604b6fd31b', null, null, '2015-11-23 15:22:41', '2015-11-23 15:22:41', '13');
