/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50617
Source Host           : 127.0.0.1:3306
Source Database       : oasystem

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-05-25 21:09:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `daly`
-- ----------------------------
DROP TABLE IF EXISTS `daly`;
CREATE TABLE `daly` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daly
-- ----------------------------
INSERT INTO `daly` VALUES ('14', '2', '2016年5月20日', '测试', '测试');
INSERT INTO `daly` VALUES ('15', '1', '2016年5月20日', '测试', '测试2');
INSERT INTO `daly` VALUES ('16', '1', '2016年5月21日', '我', '我');
INSERT INTO `daly` VALUES ('17', '2', '2016年5月22日', '我我', '我我');

-- ----------------------------
-- Table structure for `date`
-- ----------------------------
DROP TABLE IF EXISTS `date`;
CREATE TABLE `date` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of date
-- ----------------------------
INSERT INTO `date` VALUES ('17', '2', '2016/05/20', '测试1', '测试1');
INSERT INTO `date` VALUES ('18', '2', '2016/05/19', '测试2', '测试2');
INSERT INTO `date` VALUES ('23', '1', '2016/05/21', '我', '我');

-- ----------------------------
-- Table structure for `leave_i`
-- ----------------------------
DROP TABLE IF EXISTS `leave_i`;
CREATE TABLE `leave_i` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `topic` varchar(20) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `begin_time` varchar(50) DEFAULT NULL,
  `end_time` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_i
-- ----------------------------
INSERT INTO `leave_i` VALUES ('17', '1', '测试', '测试', '2016年5月20日', '2016年5月21日', '2016-05-20 15:47:38', 'wait', 'jishu');
INSERT INTO `leave_i` VALUES ('19', '1', '我', '我', '2016年5月20日', '2016年5月20日', '2016-05-20 16:58:16', 'wait', 'jishu');

-- ----------------------------
-- Table structure for `money`
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `topic` varchar(20) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of money
-- ----------------------------
INSERT INTO `money` VALUES ('6', '1', '测试', '测试', '2016-05-20 15:48:22', 'wait', 'jishu');
INSERT INTO `money` VALUES ('7', '1', '打车', '车费20元', '2016-05-20 16:19:15', 'wait', 'jishu');
INSERT INTO `money` VALUES ('12', '1', '测试', '测试', '2016-05-20 16:55:29', 'wait', 'jishu');
INSERT INTO `money` VALUES ('13', '1', '', '', '2016-05-20 17:38:12', 'wait', 'jishu');
INSERT INTO `money` VALUES ('14', '1', '', '', '2016-05-20 18:55:23', 'access', 'jishu');
INSERT INTO `money` VALUES ('15', '1', '33', '33', '2016-05-20 20:06:34', 'refuse', 'jishu');
INSERT INTO `money` VALUES ('29', '1', '', '', '2016-05-21 10:55:15', 'wait', 'jishu');
INSERT INTO `money` VALUES ('30', '1', '', '', '2016-05-21 10:55:17', 'refuse', 'jishu');
INSERT INTO `money` VALUES ('31', '1', '测试', '测试', '2016-05-21 13:54:11', 'refuse', 'jishu');
INSERT INTO `money` VALUES ('32', '1', '测试1', '测试1', '2016-05-21 15:13:13', 'wait', 'jishu');
INSERT INTO `money` VALUES ('33', '1', '测试', '测试', '2016-05-21 17:38:25', 'wait', 'jishu');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `issuer` varchar(20) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `content` text,
  `title` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('22', '刘刚', '2016-05-20 12:50:14', '公司股票代码123456\n欢迎大家积极购买', '公司股票代码');
INSERT INTO `news` VALUES ('23', '刘刚', '2016-05-20 12:54:58', '测试1', '测试1');
INSERT INTO `news` VALUES ('26', '刘刚', '2016-05-21 08:49:18', '测试', '测试');
INSERT INTO `news` VALUES ('33', '刘刚', '2016-05-21 16:23:17', '测试2', '测试2');
INSERT INTO `news` VALUES ('34', '刘刚', '2016-05-21 17:24:55', '我', '我');
INSERT INTO `news` VALUES ('35', '刘刚', '2016-05-21 17:36:59', '测试', '测试');
INSERT INTO `news` VALUES ('36', '刘刚', '2016-05-21 17:37:00', '测试', '测试');
INSERT INTO `news` VALUES ('37', '刘刚', '2016-05-21 17:37:02', '测试', '测试');
INSERT INTO `news` VALUES ('38', '刘刚', '2016-05-21 17:37:03', '测试', '测试');

-- ----------------------------
-- Table structure for `note`
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `content` text,
  `create_time` varchar(20) DEFAULT NULL,
  `change_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('16', '2', '体育用品艺术家', '2016-05-20 13:14:19', '2016-05-20 13:14:19');
INSERT INTO `note` VALUES ('17', '1', '测试', '2016-05-20 15:46:48', '2016-05-20 15:46:48');
INSERT INTO `note` VALUES ('18', '2', '距离哭唧唧考虑图咯啦咯啦咯啦', '2016-05-20 17:41:17', '2016-05-20 17:41:17');
INSERT INTO `note` VALUES ('19', '1', '测试', '2016-05-21 17:06:19', '2016-05-21 17:06:19');
INSERT INTO `note` VALUES ('20', '1', '我', '2016-05-21 17:22:50', '2016-05-21 17:22:50');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `issuer` varchar(20) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `content` text,
  `title` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('76', '刘刚', '2016-05-20 12:52:13', '测试1', '测试1');
INSERT INTO `notice` VALUES ('100', '刘刚', '2016-05-21 17:33:33', '', '');

-- ----------------------------
-- Table structure for `sign`
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('1', '1', '山东省烟台市芝罘区学院路靠近烟台师范学院10号楼学生公寓', '技术员', '2016-03-29 22:47:58');
INSERT INTO `sign` VALUES ('13', '1', '山东省烟台市芝罘区交通路靠近鲁东大学(北区)住宅3号楼', '技术员', '2016-05-13 17:49:21');
INSERT INTO `sign` VALUES ('14', '1', '山东省烟台市芝罘区红旗西路靠近逸夫实验楼', '技术员', '2016-05-20 15:47:00');
INSERT INTO `sign` VALUES ('15', '1', '山东省烟台市芝罘区交通路靠近鲁东大学(北区)住宅3号楼', 'jishu', '2016-05-20 18:56:25');
INSERT INTO `sign` VALUES ('16', '1', '山东省烟台市芝罘区博学大道靠近鲁东大学(北区)住宅3号楼', 'jishu', '2016-05-20 20:27:25');
INSERT INTO `sign` VALUES ('17', '1', '山东省烟台市芝罘区交通路靠近鲁东大学(北区)住宅3号楼', 'jishu', '2016-05-20 20:29:10');
INSERT INTO `sign` VALUES ('18', '1', '山东省烟台市芝罘区交通路靠近鲁东大学(北区)住宅3号楼', 'jishu', '2016-05-21 08:53:10');
INSERT INTO `sign` VALUES ('19', '1', '山东省烟台市芝罘区交通路靠近鲁东大学(北区)住宅3号楼', 'jishu', '2016-05-21 14:20:09');
INSERT INTO `sign` VALUES ('20', '1', '山东省烟台市芝罘区交通路靠近鲁东大学(北区)住宅3号楼', 'jishu', '2016-05-21 15:13:24');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '李庆圣', '1', '1', 'jishu', '员工');
INSERT INTO `user` VALUES ('2', '王五', '2', '2', 'jishu', '经理');
INSERT INTO `user` VALUES ('3', '刘刚', '3', '3', '主管', '管理员');
INSERT INTO `user` VALUES ('4', '张三', '4', '13333333333', 'test', '员工');
INSERT INTO `user` VALUES ('5', '李四', '5', '13255555555', '主管', '管理员');
INSERT INTO `user` VALUES ('6', '刘明', '4', '4', 'test', '员工');

-- ----------------------------
-- Table structure for `x`
-- ----------------------------
DROP TABLE IF EXISTS `x`;
CREATE TABLE `x` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of x
-- ----------------------------
INSERT INTO `x` VALUES ('3', null);
INSERT INTO `x` VALUES ('4', '3');
INSERT INTO `x` VALUES ('5', 'ksfd');
