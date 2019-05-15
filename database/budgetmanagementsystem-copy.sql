CREATE TABLE budgetmanagementsystem.conference
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称（会议内容）',
    price double COMMENT '会议标准费用',
    comment text,
    experts int(11) COMMENT '需要专家人数'
);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts) VALUES (1, '项目启动会', 200, null, 3);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts) VALUES (2, '中期', 200, null, 3);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts) VALUES (3, '预验收', 200, null, 3);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts) VALUES (4, '验收', 200, null, 2);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts) VALUES (5, '学术会议', 5000, null, 3);
CREATE TABLE budgetmanagementsystem.consultation
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '专家信息（职称等）',
    price double COMMENT '咨询费',
    comment text
);
INSERT INTO budgetmanagementsystem.consultation (id, name, price, comment) VALUES (1, '专家', 1000, null);
CREATE TABLE budgetmanagementsystem.equipment
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '型号规格',
    price double,
    type varchar(50) COMMENT '子类别',
    img varchar(100) COMMENT '图片url',
    comment text COMMENT '备注'
);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (1, '戴尔Precision T5820(Xeon W-2123/64GB/512GB+4TB/P2000)', 23700, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (2, 'UltraLAB P490(14664-MAX)', 60000, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (3, 'Wiseteam CP265 C33128-SAEK12', 149900, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (4, '凌炫Pt8040（E221T-ECEP6）', 499900, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (5, '惠普战99（4RV70PA）', 7999, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (6, '联想ThinkStation P900(Xeon E5-2630 v3/512GB/K2200)', 41300, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (7, '戴尔Precision T7920塔式系列(P7920T-S4110NLCN01)', 24300, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (8, 'UltraLAB Alpha720(425256-SB28TD)', 275000, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (9, 'UltraLAB H490(14664-M5TCX)', 47000, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (10, 'UltraLAB EX620(23496-M528TC)', 140000, null, null, null);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (11, '戴尔Precision 7530系列(Promo-M7530I78750-Online1)', 18600, null, null, null);
CREATE TABLE budgetmanagementsystem.internationalcommunication
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100),
    price double,
    comment text
);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment) VALUES (1, '澳大利亚', 39000, null);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment) VALUES (2, '北卡莱罗纳', 21000, null);
CREATE TABLE budgetmanagementsystem.labour
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名目（本硕博专）',
    price double COMMENT '单价（人月）',
    priority int(11) COMMENT '优先级',
    comment text
);
INSERT INTO budgetmanagementsystem.labour (id, name, price, priority, comment) VALUES (1, '博士生', 2000, 2, null);
INSERT INTO budgetmanagementsystem.labour (id, name, price, priority, comment) VALUES (2, '硕士生', 1000, 3, null);
INSERT INTO budgetmanagementsystem.labour (id, name, price, priority, comment) VALUES (3, '专职', 11536, 1, null);
CREATE TABLE budgetmanagementsystem.material
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称/规格',
    price double COMMENT '单价',
    type varchar(50) COMMENT '类型',
    img varchar(100) COMMENT '图片链接',
    comment text COMMENT '备注'
);
INSERT INTO budgetmanagementsystem.material (id, name, price, type, img, comment) VALUES (1, '硬盘', 2000, null, null, null);
INSERT INTO budgetmanagementsystem.material (id, name, price, type, img, comment) VALUES (2, '加密移动硬盘', 3000, null, null, null);
CREATE TABLE budgetmanagementsystem.power
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100),
    price double,
    comment text
);
CREATE TABLE budgetmanagementsystem.property
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称名目',
    price double,
    comment text
);
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (1, '著作权', 2000, null);
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (2, '知识产权', 5000, null);
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (3, '广西省电子地图数据', 10000, null);
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (4, '广西省货车 UGC 数据', 10000, null);
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (5, '广西省物流 UGC 数据', 10000, null);
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (6, '论文发表费', 4000, null);
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (7, '广西省交通流量视频数据', 10000, null);
CREATE TABLE budgetmanagementsystem.testandprocess
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称、内容',
    price double COMMENT '单价',
    comment text COMMENT '备注'
);
INSERT INTO budgetmanagementsystem.testandprocess (id, name, price, comment) VALUES (1, '测试化验加工费', 150000, null);
CREATE TABLE budgetmanagementsystem.travel
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dest varchar(50) COMMENT '目的地',
    price double COMMENT '往返价格',
    food double COMMENT '伙食标准',
    traffic double COMMENT '交通标准',
    accommodation double COMMENT '住宿标准',
    comment text COMMENT '备注'
);
INSERT INTO budgetmanagementsystem.travel (id, name, price, food, traffic, accommodation, comment) VALUES (1, '上海', 2000, 100, 80, 500, null);
INSERT INTO budgetmanagementsystem.travel (id, name, price, food, traffic, accommodation, comment) VALUES (2, '南宁', 2000, 100, 80, 350, null);
INSERT INTO budgetmanagementsystem.travel (id, name, price, food, traffic, accommodation, comment) VALUES (3, '桂林', 2000, 100, 80, 350, null);
INSERT INTO budgetmanagementsystem.travel (id, name, price, food, traffic, accommodation, comment) VALUES (4, '北海', 2000, 100, 80, 350, null);
CREATE TABLE budgetmanagementsystem.workstation
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    spec varchar(100) COMMENT '型号规格',
    price double COMMENT '单价（万）',
    img varchar(100) COMMENT '图片url'
);
CREATE UNIQUE INDEX workstation_spec_uindex ON budgetmanagementsystem.workstation (spec);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (35, '戴尔Precision T5820(Xeon W-2123/64GB/512GB+4TB/P2000)', 23700, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (36, 'UltraLAB P490(14664-MAX)', 60000, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (37, 'HP Z4 G4(1JP11AV-SC001)', 17000, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (38, 'Wiseteam CP265 C33128-SAEK12', 149900, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (39, '凌炫Pt8040（E221T-ECEP6）', 499900, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (40, '联想ThinkStation P520c(Xeon W-2123/16GB/256GB+1TB/P2000)', 14400, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (41, '戴尔Precision T7920塔式系列(P7920T-S4110NLCN01)', 24300, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (42, 'UltraLAB Alpha720(425256-SB28TD)', 275000, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (43, 'UltraLAB H490(14664-M5TCX)', 47000, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (44, 'UltraLAB EX620(23496-M528TC)', 140000, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (45, '戴尔Precision 7530系列(Promo-M7530I78750-Online1)', 18600, null);
INSERT INTO budgetmanagementsystem.workstation (id, spec, price, img) VALUES (46, '戴尔Precision 5530系列(Promo-M5530I58300-Online1)', 9999, null);