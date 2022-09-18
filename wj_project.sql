/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80028
Source Host           : localhost:3306
Source Database       : wj_project

Target Server Type    : MYSQL
Target Server Version : 80028
File Encoding         : 65001

Date: 2022-09-18 15:23:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wj_exchange
-- ----------------------------
DROP TABLE IF EXISTS `wj_exchange`;
CREATE TABLE `wj_exchange` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收款人',
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收款方式',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收款人电话',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `deal_time` datetime DEFAULT NULL COMMENT '处理时间',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '处理状态',
  `money` int NOT NULL COMMENT '打款金额',
  `start_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发件邮箱',
  `end_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目的地邮箱',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收款码',
  `gift_card_id` int NOT NULL COMMENT '礼品卡id',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of wj_exchange
-- ----------------------------
INSERT INTO `wj_exchange` VALUES ('1', 'string', 'string', 'string', null, null, '0', '0', 'string', 'string', 'string', '0', '0', '0');
INSERT INTO `wj_exchange` VALUES ('5', 'string', 'string', 'string', null, null, '0', '0', 'string', 'string', 'string', '0', '38', '38');

-- ----------------------------
-- Table structure for wj_gift_card
-- ----------------------------
DROP TABLE IF EXISTS `wj_gift_card`;
CREATE TABLE `wj_gift_card` (
  `gift_card_id` int NOT NULL AUTO_INCREMENT COMMENT '礼品卡id',
  `gift_card_type` int DEFAULT NULL COMMENT '礼品卡类型(1、星巴克 2、亚马逊 3、VISA 4、香草VISA 5、其它)',
  `area` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地区',
  `cardcode` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '卡密或卡号',
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '编号',
  `cvv` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'cvv',
  `createtime` datetime DEFAULT NULL COMMENT '日期',
  `facevalue` int DEFAULT NULL COMMENT '面值',
  `exchange_id` int DEFAULT NULL COMMENT '兑换id',
  PRIMARY KEY (`gift_card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Records of wj_gift_card
-- ----------------------------
INSERT INTO `wj_gift_card` VALUES ('1', '0', 'string', 'string', 'string', 'string', '2022-09-16 00:00:00', '0', '1');
INSERT INTO `wj_gift_card` VALUES ('5', '0', 'string', 'string', 'string', 'string', '2022-09-18 00:00:00', '0', '5');

-- ----------------------------
-- Table structure for wj_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `wj_questionnaire`;
CREATE TABLE `wj_questionnaire` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `money` int NOT NULL COMMENT '金额',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '连接',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of wj_questionnaire
-- ----------------------------
INSERT INTO `wj_questionnaire` VALUES ('1', '问卷调查', '5', '前20名', 'http://dfsaf.com', '2022-09-15 11:52:24');

-- ----------------------------
-- Table structure for wj_user
-- ----------------------------
DROP TABLE IF EXISTS `wj_user`;
CREATE TABLE `wj_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名称(用户名)',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `is_account_non_expired` tinyint NOT NULL COMMENT '帐户是否过期(1-未过期，0-已过期)',
  `is_account_non_locked` tinyint NOT NULL COMMENT '帐户是否被锁定(1-未过期，0-已过期)',
  `is_credentials_non_expired` tinyint NOT NULL COMMENT '密码是否过期(1-未过期，0-已过期)',
  `is_enabled` tinyint NOT NULL COMMENT '帐户是否可用(1-可用，0-禁用)',
  `gender` tinyint DEFAULT NULL COMMENT '性别(0-男，1-女)',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'https://manong-authority.oss-cn-guangzhou.aliyuncs.com/avatar/default-avatar.gif' COMMENT '用户头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint DEFAULT '0' COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of wj_user
-- ----------------------------
INSERT INTO `wj_user` VALUES ('36', 'test', '123', '1', '1', '1', '1', '1', '1234', 'test@gmail.com', 'https://manong-authority.oss-cn-guangzhou.aliyuncs.com/avatar/default-avatar.gif', null, '2022-09-16 10:28:46', '0');
INSERT INTO `wj_user` VALUES ('38', 'admin', '$2a$10$/rXsMftXEpMZ2pDPdPji4eZ4jvEjZSQnnW.t5vvZ7uIbxPG1/ROkK', '1', '1', '1', '1', null, '4324', null, 'https://manong-authority.oss-cn-guangzhou.aliyuncs.com/avatar/default-avatar.gif', '2022-09-15 14:48:47', '2022-09-15 14:48:47', '0');
INSERT INTO `wj_user` VALUES ('40', 'string', 'string', '1', '1', '1', '1', '1', '1', 'adj@qdd.com', 'https://manong-authority.oss-cn-guangzhou.aliyuncs.com/avatar/default-avatar.gif', '2022-09-16 10:29:32', '2022-09-16 10:31:46', '1');
