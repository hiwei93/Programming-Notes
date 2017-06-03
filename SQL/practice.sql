/*
用于练习SQL的数据库结构及数据
Source Database       : practice

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-06-03 20:03:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '‘课程ID’',
  `c_name` varchar(100) NOT NULL COMMENT '‘课程名称’',
  `c_hours` int(11) NOT NULL COMMENT '‘课时’',
  `c_credit` smallint(6) NOT NULL COMMENT '''学分''',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '建筑的文化理解（A）', '16', '1');
INSERT INTO `course` VALUES ('2', '建筑的文化理解（B）', '16', '1');
INSERT INTO `course` VALUES ('3', '建筑发展趋向', '24', '1');
INSERT INTO `course` VALUES ('4', '建筑节能导论', '32', '2');
INSERT INTO `course` VALUES ('5', '建筑技术概论', '16', '1');
INSERT INTO `course` VALUES ('6', '中国古代建筑史纲', '32', '2');
INSERT INTO `course` VALUES ('7', '空间形体表达基础', '32', '2');
INSERT INTO `course` VALUES ('8', '建筑声环境', '16', '1');
INSERT INTO `course` VALUES ('9', '城市规划原理', '32', '2');
INSERT INTO `course` VALUES ('10', '建筑数学', '32', '2');
INSERT INTO `course` VALUES ('11', '生态建筑设计策略导论', '16', '1');
INSERT INTO `course` VALUES ('12', '建筑构造', '48', '2');
INSERT INTO `course` VALUES ('13', '人居科学基础', '16', '1');
INSERT INTO `course` VALUES ('14', '古建测绘实习', '16', '1');
INSERT INTO `course` VALUES ('15', '乡土建筑学', '16', '1');
INSERT INTO `course` VALUES ('16', '公共雕塑艺术', '32', '2');
INSERT INTO `course` VALUES ('17', '建筑细部设计', '32', '2');
INSERT INTO `course` VALUES ('18', '风景园林学导论', '16', '1');
INSERT INTO `course` VALUES ('19', '科研思维方法', '32', '2');
INSERT INTO `course` VALUES ('20', '机器人工程基础及应用', '32', '2');
INSERT INTO `course` VALUES ('21', '科技商务', '32', '2');
INSERT INTO `course` VALUES ('22', '产品设计与开发', '32', '2');
INSERT INTO `course` VALUES ('23', '生物材料工程与器件', '32', '2');
INSERT INTO `course` VALUES ('24', '绿色制造与可持续发展', '16', '1');
INSERT INTO `course` VALUES ('25', '表面工程基础', '32', '2');
INSERT INTO `course` VALUES ('26', '工程生物学基础', '32', '2');
INSERT INTO `course` VALUES ('27', '有限元分析', '32', '2');
INSERT INTO `course` VALUES ('28', '机电控制系统实践', '32', '2');
INSERT INTO `course` VALUES ('29', '工程材料', '48', '3');
INSERT INTO `course` VALUES ('30', '工程制图基础', '48', '3');
INSERT INTO `course` VALUES ('31', '机械设计基础', '48', '3');
INSERT INTO `course` VALUES ('32', '机械工程导论', '48', '3');
INSERT INTO `course` VALUES ('33', '控制工程基础', '48', '3');
INSERT INTO `course` VALUES ('34', '制造工程基础', '48', '3');
INSERT INTO `course` VALUES ('35', '微纳米工程材料', '32', '2');
INSERT INTO `course` VALUES ('36', '制造工程信息技术', '48', '3');
INSERT INTO `course` VALUES ('37', '设计与制造基础实践', '160', '4');
INSERT INTO `course` VALUES ('38', '制造工程基础', '54', '3');
INSERT INTO `course` VALUES ('39', '设计与制造', '80', '4');
INSERT INTO `course` VALUES ('40', '微纳米工程材料', '48', '3');
INSERT INTO `course` VALUES ('41', '特种加工工艺', '32', '2');
INSERT INTO `course` VALUES ('42', '信号处理', '64', '3');
INSERT INTO `course` VALUES ('43', '材料加工工艺', '48', '3');
INSERT INTO `course` VALUES ('44', '制造系统', '32', '2');
INSERT INTO `course` VALUES ('45', '工业产品造型设计', '32', '2');
INSERT INTO `course` VALUES ('46', '机器人技术与应用', '32', '2');
INSERT INTO `course` VALUES ('47', '生产系统规划与设计', '32', '2');
INSERT INTO `course` VALUES ('48', '微纳制造导论', '32', '2');
INSERT INTO `course` VALUES ('49', '机械材料学', '48', '3');
INSERT INTO `course` VALUES ('50', '计算机辅助设计技术基础', '48', '3');
INSERT INTO `course` VALUES ('51', '软件工程', '96', '3');
INSERT INTO `course` VALUES ('52', '人工智能导论', '32', '2');
INSERT INTO `course` VALUES ('53', '面向计算机科学的离散数学', '48', '3');
INSERT INTO `course` VALUES ('54', '数据结构', '64', '4');
INSERT INTO `course` VALUES ('55', '计算机组成与系统结构', '64', '4');
INSERT INTO `course` VALUES ('56', '计算机网络', '48', '3');
INSERT INTO `course` VALUES ('57', '下一代互联网', '32', '2');
INSERT INTO `course` VALUES ('58', '计算基因组分析', '48', '3');
INSERT INTO `course` VALUES ('59', 'C++语言程序设计', '64', '3');
INSERT INTO `course` VALUES ('60', '操作系统', '48', '3');
INSERT INTO `course` VALUES ('61', '语言程序设计', '48', '3');
INSERT INTO `course` VALUES ('62', '办公自动化软件应用', '40', '2');
INSERT INTO `course` VALUES ('63', '并行计算基础', '56', '2');
INSERT INTO `course` VALUES ('64', '网页设计与制作', '48', '2');
INSERT INTO `course` VALUES ('65', '嵌入式系统设计与应用', '32', '2');
INSERT INTO `course` VALUES ('66', '数据挖掘：方法与应用', '48', '3');
INSERT INTO `course` VALUES ('67', '初等数论', '32', '2');
INSERT INTO `course` VALUES ('68', '计算机文化基础', '32', '2');
INSERT INTO `course` VALUES ('69', '数据库技术及应用', '48', '3');
INSERT INTO `course` VALUES ('70', '计算机程序设计基础', '48', '3');
INSERT INTO `course` VALUES ('71', '人工智能导论', '32', '2');
INSERT INTO `course` VALUES ('72', '信号处理原理', '48', '3');
INSERT INTO `course` VALUES ('73', '编译原理', '64', '2');
INSERT INTO `course` VALUES ('74', '数字逻辑实验', '32', '1');
INSERT INTO `course` VALUES ('75', '系统分析与控制（A）', '48', '3');
INSERT INTO `course` VALUES ('76', '系统分析与控制（B）', '32', '2');
INSERT INTO `course` VALUES ('77', '信息检索', '32', '2');
INSERT INTO `course` VALUES ('78', '模式识别', '32', '2');
INSERT INTO `course` VALUES ('79', '现代控制技术', '36', '2');

-- ----------------------------
-- Table structure for curricula_variable
-- ----------------------------
DROP TABLE IF EXISTS `curricula_variable`;
CREATE TABLE `curricula_variable` (
  `cv_fk_sid` int(11) NOT NULL COMMENT '''选课学生id''',
  `cv_fk_cid` int(11) NOT NULL COMMENT '''选课课程''',
  `grade` int(11) DEFAULT NULL COMMENT '‘成绩’',
  PRIMARY KEY (`cv_fk_sid`,`cv_fk_cid`),
  KEY `cv_fk_cid` (`cv_fk_cid`),
  CONSTRAINT `curricula_variable_ibfk_1` FOREIGN KEY (`cv_fk_sid`) REFERENCES `student` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `curricula_variable_ibfk_2` FOREIGN KEY (`cv_fk_cid`) REFERENCES `course` (`c_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of curricula_variable
-- ----------------------------
INSERT INTO `curricula_variable` VALUES ('1', '78', '88');
INSERT INTO `curricula_variable` VALUES ('2', '51', '75');
INSERT INTO `curricula_variable` VALUES ('2', '75', '67');
INSERT INTO `curricula_variable` VALUES ('2', '78', '86');
INSERT INTO `curricula_variable` VALUES ('3', '6', '84');
INSERT INTO `curricula_variable` VALUES ('3', '17', '65');
INSERT INTO `curricula_variable` VALUES ('3', '40', '94');
INSERT INTO `curricula_variable` VALUES ('4', '15', null);
INSERT INTO `curricula_variable` VALUES ('4', '17', '85');
INSERT INTO `curricula_variable` VALUES ('4', '37', '54');
INSERT INTO `curricula_variable` VALUES ('4', '78', '74');
INSERT INTO `curricula_variable` VALUES ('5', '4', '66');
INSERT INTO `curricula_variable` VALUES ('5', '6', '80');
INSERT INTO `curricula_variable` VALUES ('5', '11', '67');
INSERT INTO `curricula_variable` VALUES ('5', '17', '96');
INSERT INTO `curricula_variable` VALUES ('5', '22', '56');
INSERT INTO `curricula_variable` VALUES ('5', '56', '92');
INSERT INTO `curricula_variable` VALUES ('5', '65', null);
INSERT INTO `curricula_variable` VALUES ('6', '4', '70');
INSERT INTO `curricula_variable` VALUES ('6', '11', '78');
INSERT INTO `curricula_variable` VALUES ('6', '46', '94');
INSERT INTO `curricula_variable` VALUES ('6', '55', '73');
INSERT INTO `curricula_variable` VALUES ('6', '76', '60');
INSERT INTO `curricula_variable` VALUES ('6', '78', '77');
INSERT INTO `curricula_variable` VALUES ('7', '6', '65');
INSERT INTO `curricula_variable` VALUES ('7', '17', '73');
INSERT INTO `curricula_variable` VALUES ('7', '44', '89');
INSERT INTO `curricula_variable` VALUES ('7', '55', null);
INSERT INTO `curricula_variable` VALUES ('7', '60', '88');
INSERT INTO `curricula_variable` VALUES ('7', '76', '76');
INSERT INTO `curricula_variable` VALUES ('8', '4', '95');
INSERT INTO `curricula_variable` VALUES ('8', '14', '84');
INSERT INTO `curricula_variable` VALUES ('8', '76', '70');
INSERT INTO `curricula_variable` VALUES ('8', '78', '88');
INSERT INTO `curricula_variable` VALUES ('9', '6', '56');
INSERT INTO `curricula_variable` VALUES ('9', '76', '77');
INSERT INTO `curricula_variable` VALUES ('10', '6', '75');
INSERT INTO `curricula_variable` VALUES ('10', '37', '62');
INSERT INTO `curricula_variable` VALUES ('10', '49', '53');
INSERT INTO `curricula_variable` VALUES ('10', '60', '54');
INSERT INTO `curricula_variable` VALUES ('10', '76', '90');
INSERT INTO `curricula_variable` VALUES ('11', '22', '83');
INSERT INTO `curricula_variable` VALUES ('11', '53', '59');
INSERT INTO `curricula_variable` VALUES ('11', '65', '77');
INSERT INTO `curricula_variable` VALUES ('12', '15', '83');
INSERT INTO `curricula_variable` VALUES ('12', '55', '94');
INSERT INTO `curricula_variable` VALUES ('12', '74', '93');
INSERT INTO `curricula_variable` VALUES ('13', '11', '77');
INSERT INTO `curricula_variable` VALUES ('13', '53', '66');
INSERT INTO `curricula_variable` VALUES ('13', '61', '87');
INSERT INTO `curricula_variable` VALUES ('13', '76', '80');
INSERT INTO `curricula_variable` VALUES ('14', '67', '75');
INSERT INTO `curricula_variable` VALUES ('15', '10', '74');
INSERT INTO `curricula_variable` VALUES ('15', '11', '84');
INSERT INTO `curricula_variable` VALUES ('15', '17', '65');
INSERT INTO `curricula_variable` VALUES ('15', '22', '78');
INSERT INTO `curricula_variable` VALUES ('15', '49', '78');
INSERT INTO `curricula_variable` VALUES ('15', '56', '64');
INSERT INTO `curricula_variable` VALUES ('15', '60', '78');
INSERT INTO `curricula_variable` VALUES ('15', '63', '76');
INSERT INTO `curricula_variable` VALUES ('16', '43', '83');
INSERT INTO `curricula_variable` VALUES ('22', '76', '67');
INSERT INTO `curricula_variable` VALUES ('23', '49', '73');
INSERT INTO `curricula_variable` VALUES ('24', '48', '82');
INSERT INTO `curricula_variable` VALUES ('24', '49', '79');
INSERT INTO `curricula_variable` VALUES ('24', '76', '84');
INSERT INTO `curricula_variable` VALUES ('25', '9', '65');
INSERT INTO `curricula_variable` VALUES ('25', '10', '84');
INSERT INTO `curricula_variable` VALUES ('25', '38', '76');
INSERT INTO `curricula_variable` VALUES ('25', '53', '44');
INSERT INTO `curricula_variable` VALUES ('25', '55', '85');
INSERT INTO `curricula_variable` VALUES ('25', '65', '99');
INSERT INTO `curricula_variable` VALUES ('32', '6', '90');
INSERT INTO `curricula_variable` VALUES ('32', '14', '76');
INSERT INTO `curricula_variable` VALUES ('32', '17', '77');
INSERT INTO `curricula_variable` VALUES ('32', '37', '60');
INSERT INTO `curricula_variable` VALUES ('32', '60', '87');
INSERT INTO `curricula_variable` VALUES ('33', '14', '67');
INSERT INTO `curricula_variable` VALUES ('33', '15', '64');
INSERT INTO `curricula_variable` VALUES ('33', '16', '78');
INSERT INTO `curricula_variable` VALUES ('33', '38', '75');
INSERT INTO `curricula_variable` VALUES ('33', '49', '68');
INSERT INTO `curricula_variable` VALUES ('33', '56', '82');
INSERT INTO `curricula_variable` VALUES ('33', '74', '99');
INSERT INTO `curricula_variable` VALUES ('34', '9', '78');
INSERT INTO `curricula_variable` VALUES ('34', '22', '85');
INSERT INTO `curricula_variable` VALUES ('34', '75', '80');
INSERT INTO `curricula_variable` VALUES ('35', '9', '87');
INSERT INTO `curricula_variable` VALUES ('35', '10', '68');
INSERT INTO `curricula_variable` VALUES ('35', '33', '89');
INSERT INTO `curricula_variable` VALUES ('35', '65', '80');
INSERT INTO `curricula_variable` VALUES ('41', '49', '89');
INSERT INTO `curricula_variable` VALUES ('42', '65', '55');
INSERT INTO `curricula_variable` VALUES ('43', '22', '74');
INSERT INTO `curricula_variable` VALUES ('43', '45', '56');
INSERT INTO `curricula_variable` VALUES ('43', '56', null);
INSERT INTO `curricula_variable` VALUES ('43', '59', '83');
INSERT INTO `curricula_variable` VALUES ('43', '64', '92');
INSERT INTO `curricula_variable` VALUES ('44', '9', '75');
INSERT INTO `curricula_variable` VALUES ('44', '71', '85');
INSERT INTO `curricula_variable` VALUES ('45', '6', null);
INSERT INTO `curricula_variable` VALUES ('45', '22', '66');
INSERT INTO `curricula_variable` VALUES ('45', '32', '80');
INSERT INTO `curricula_variable` VALUES ('45', '49', '63');
INSERT INTO `curricula_variable` VALUES ('45', '73', '84');
INSERT INTO `curricula_variable` VALUES ('46', '59', '80');
INSERT INTO `curricula_variable` VALUES ('50', '55', '75');
INSERT INTO `curricula_variable` VALUES ('50', '56', '84');
INSERT INTO `curricula_variable` VALUES ('50', '60', '67');
INSERT INTO `curricula_variable` VALUES ('50', '62', '85');
INSERT INTO `curricula_variable` VALUES ('51', '11', '75');
INSERT INTO `curricula_variable` VALUES ('51', '32', '88');
INSERT INTO `curricula_variable` VALUES ('52', '10', '88');
INSERT INTO `curricula_variable` VALUES ('52', '11', '86');
INSERT INTO `curricula_variable` VALUES ('52', '65', '77');
INSERT INTO `curricula_variable` VALUES ('53', '7', '84');
INSERT INTO `curricula_variable` VALUES ('53', '11', '89');
INSERT INTO `curricula_variable` VALUES ('53', '14', null);
INSERT INTO `curricula_variable` VALUES ('53', '17', '77');
INSERT INTO `curricula_variable` VALUES ('53', '28', '63');
INSERT INTO `curricula_variable` VALUES ('53', '49', null);
INSERT INTO `curricula_variable` VALUES ('54', '14', '87');
INSERT INTO `curricula_variable` VALUES ('54', '20', null);
INSERT INTO `curricula_variable` VALUES ('54', '28', '80');
INSERT INTO `curricula_variable` VALUES ('55', '22', '65');
INSERT INTO `curricula_variable` VALUES ('56', '45', '69');
INSERT INTO `curricula_variable` VALUES ('56', '49', '74');
INSERT INTO `curricula_variable` VALUES ('61', '14', '69');
INSERT INTO `curricula_variable` VALUES ('61', '32', '66');
INSERT INTO `curricula_variable` VALUES ('63', '56', '67');
INSERT INTO `curricula_variable` VALUES ('63', '59', '88');
INSERT INTO `curricula_variable` VALUES ('64', '21', '60');
INSERT INTO `curricula_variable` VALUES ('65', '9', '91');
INSERT INTO `curricula_variable` VALUES ('66', '11', '99');
INSERT INTO `curricula_variable` VALUES ('66', '28', '97');
INSERT INTO `curricula_variable` VALUES ('66', '45', '91');
INSERT INTO `curricula_variable` VALUES ('66', '59', '81');
INSERT INTO `curricula_variable` VALUES ('66', '71', '55');
INSERT INTO `curricula_variable` VALUES ('69', '28', '84');
INSERT INTO `curricula_variable` VALUES ('70', '9', '92');
INSERT INTO `curricula_variable` VALUES ('70', '11', '94');
INSERT INTO `curricula_variable` VALUES ('70', '20', '64');
INSERT INTO `curricula_variable` VALUES ('70', '59', '82');
INSERT INTO `curricula_variable` VALUES ('70', '71', '66');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `d_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '''学院ID''',
  `d_name` varchar(100) NOT NULL COMMENT '‘学院名称’',
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '信息管理学院');
INSERT INTO `department` VALUES ('2', '通信学院');
INSERT INTO `department` VALUES ('3', '经济管理学院');
INSERT INTO `department` VALUES ('4', '理学院');
INSERT INTO `department` VALUES ('5', '机械与电力学院');
INSERT INTO `department` VALUES ('6', '计算机学院');
INSERT INTO `department` VALUES ('7', '外国语学院');
INSERT INTO `department` VALUES ('8', '建筑学院');
INSERT INTO `department` VALUES ('9', '公共管理学院');
INSERT INTO `department` VALUES ('10', '政治教育学院');
INSERT INTO `department` VALUES ('11', '医学院');
INSERT INTO `department` VALUES ('12', '人文学院');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '‘学生ID’',
  `s_name` varchar(100) NOT NULL COMMENT '‘学生名称’',
  `s_sex` varchar(255) NOT NULL COMMENT '‘学生性别’',
  `s_age` int(11) NOT NULL COMMENT '‘学生年龄’',
  `s_department` int(11) NOT NULL COMMENT '‘所在学院’',
  PRIMARY KEY (`s_id`),
  KEY `s_department` (`s_department`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`s_department`) REFERENCES `department` (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '王蔚', '女', '22', '3');
INSERT INTO `student` VALUES ('2', '王安', '女', '22', '3');
INSERT INTO `student` VALUES ('3', '李泽源', '男', '23', '8');
INSERT INTO `student` VALUES ('4', '林艳', '女', '22', '1');
INSERT INTO `student` VALUES ('5', '殷畅', '女', '22', '5');
INSERT INTO `student` VALUES ('6', '权洪林', '女', '22', '1');
INSERT INTO `student` VALUES ('7', '董佳', '女', '22', '2');
INSERT INTO `student` VALUES ('8', '陈棋颖', '女', '19', '6');
INSERT INTO `student` VALUES ('9', '马尚芳', '女', '22', '1');
INSERT INTO `student` VALUES ('10', '葛子欣', '女', '22', '4');
INSERT INTO `student` VALUES ('11', '李万青', '女', '22', '7');
INSERT INTO `student` VALUES ('12', '岳媛', '女', '20', '3');
INSERT INTO `student` VALUES ('13', '汤小朋', '女', '22', '9');
INSERT INTO `student` VALUES ('14', '杨菁筠', '女', '22', '1');
INSERT INTO `student` VALUES ('15', '张可佳', '女', '22', '10');
INSERT INTO `student` VALUES ('16', '蔡倩瑜', '女', '19', '6');
INSERT INTO `student` VALUES ('17', '张伟', '男', '22', '11');
INSERT INTO `student` VALUES ('18', '王春鸣', '女', '22', '1');
INSERT INTO `student` VALUES ('19', '鲁丹', '女', '22', '8');
INSERT INTO `student` VALUES ('20', '常竞', '女', '22', '5');
INSERT INTO `student` VALUES ('21', '陈良华', '男', '20', '6');
INSERT INTO `student` VALUES ('22', '孟子钰', '女', '22', '4');
INSERT INTO `student` VALUES ('23', '芮安妮', '女', '22', '7');
INSERT INTO `student` VALUES ('24', '杨雪莉', '女', '22', '2');
INSERT INTO `student` VALUES ('25', '诸萌', '女', '22', '1');
INSERT INTO `student` VALUES ('26', '罗政', '男', '21', '9');
INSERT INTO `student` VALUES ('27', '陈燕', '女', '22', '6');
INSERT INTO `student` VALUES ('28', '吴瑶', '女', '22', '3');
INSERT INTO `student` VALUES ('29', '王维', '女', '22', '2');
INSERT INTO `student` VALUES ('30', '麻瑞瑶', '女', '22', '1');
INSERT INTO `student` VALUES ('31', '谭卉宁', '女', '23', '1');
INSERT INTO `student` VALUES ('32', '周梦鑫', '女', '22', '1');
INSERT INTO `student` VALUES ('33', '吴禹非', '男', '20', '1');
INSERT INTO `student` VALUES ('34', '岳永杰', '男', '22', '3');
INSERT INTO `student` VALUES ('35', '郑婉滢', '女', '22', '1');
INSERT INTO `student` VALUES ('36', '王环', '女', '23', '9');
INSERT INTO `student` VALUES ('37', '程昊', '男', '22', '10');
INSERT INTO `student` VALUES ('38', '朴一君', '男', '22', '2');
INSERT INTO `student` VALUES ('39', '董晨', '男', '22', '4');
INSERT INTO `student` VALUES ('40', '陈玉立', '男', '22', '7');
INSERT INTO `student` VALUES ('41', '韩莹', '女', '22', '4');
INSERT INTO `student` VALUES ('42', '张亚丽', '女', '22', '6');
INSERT INTO `student` VALUES ('43', '郑瀚潇', '男', '22', '3');
INSERT INTO `student` VALUES ('44', '王婧璇', '女', '22', '1');
INSERT INTO `student` VALUES ('45', '孙雨晨', '男', '21', '1');
INSERT INTO `student` VALUES ('46', '唐凯城', '男', '21', '11');
INSERT INTO `student` VALUES ('47', '石晗', '女', '22', '6');
INSERT INTO `student` VALUES ('48', '田甜', '女', '22', '8');
INSERT INTO `student` VALUES ('49', '赵旭彤', '女', '22', '9');
INSERT INTO `student` VALUES ('50', '刘联博', '男', '21', '1');
INSERT INTO `student` VALUES ('51', '胡亚楠', '女', '22', '4');
INSERT INTO `student` VALUES ('52', '钟泽昊', '男', '22', '6');
INSERT INTO `student` VALUES ('53', '张露雨', '女', '22', '7');
INSERT INTO `student` VALUES ('54', '张增慧', '女', '22', '4');
INSERT INTO `student` VALUES ('55', '江帆', '女', '23', '5');
INSERT INTO `student` VALUES ('56', '李晨希', '男', '22', '1');
INSERT INTO `student` VALUES ('57', '杨菁', '女', '22', '1');
INSERT INTO `student` VALUES ('58', '李晨曦', '女', '22', '3');
INSERT INTO `student` VALUES ('59', '张勇', '男', '23', '1');
INSERT INTO `student` VALUES ('60', '李萍', '女', '22', '1');
INSERT INTO `student` VALUES ('61', '张文', '女', '22', '1');
INSERT INTO `student` VALUES ('62', '贾冬雪', '女', '22', '8');
INSERT INTO `student` VALUES ('63', '王振', '男', '22', '10');
INSERT INTO `student` VALUES ('64', '董宁', '男', '22', '5');
INSERT INTO `student` VALUES ('65', '赵阳', '男', '22', '7');
INSERT INTO `student` VALUES ('66', '都宇成', '男', '22', '12');
INSERT INTO `student` VALUES ('67', '任大为', '男', '22', '6');
INSERT INTO `student` VALUES ('68', '徐文清', '女', '23', '9');
INSERT INTO `student` VALUES ('69', '李涵', '女', '22', '10');
INSERT INTO `student` VALUES ('70', '孟繁琪', '男', '22', '1');
