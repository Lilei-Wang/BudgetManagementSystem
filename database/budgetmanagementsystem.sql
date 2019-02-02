CREATE TABLE budgetmanagementsystem.conference
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称（会议内容）',
    price double COMMENT '会议标准费用',
    comment text,
    experts int(11) COMMENT '需要专家人数'
);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts) VALUES (1, '项目启动会', 200, null, 3);
CREATE TABLE budgetmanagementsystem.consultation
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '专家信息（职称等）',
    price double COMMENT '咨询费',
    comment text
);
CREATE TABLE budgetmanagementsystem.equipment
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '型号规格',
    price double,
    type varchar(50) COMMENT '子类别',
    img varchar(100) COMMENT '图片url',
    comment text COMMENT '备注'
);
CREATE TABLE budgetmanagementsystem.internationalcommunication
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100),
    price double,
    comment text
);
CREATE TABLE budgetmanagementsystem.labour
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名目（本硕博专）',
    price double COMMENT '单价（人月）',
    priority int(11) COMMENT '优先级',
    comment text
);
CREATE TABLE budgetmanagementsystem.material
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称/规格',
    price double COMMENT '单价',
    type varchar(50) COMMENT '类型',
    img varchar(100) COMMENT '图片链接',
    comment text COMMENT '备注'
);
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
    price double
);
CREATE TABLE budgetmanagementsystem.testandprocess
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称、内容',
    price double COMMENT '单价',
    comment text COMMENT '备注'
);
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