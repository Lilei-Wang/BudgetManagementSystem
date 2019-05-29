CREATE TABLE budgetmanagementsystem.budgets
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userid int(11) DEFAULT '0' COMMENT '用户id',
    filename varchar(50) COMMENT '预算文件名称'
);
CREATE TABLE budgetmanagementsystem.conference
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称（会议内容）',
    price double COMMENT '会议标准费用',
    comment text,
    experts int(11) COMMENT '需要专家人数',
    people int(11) DEFAULT '0' COMMENT '参会人数',
    days int(11) DEFAULT '0'
);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts, people, days) VALUES (1, '项目启动会', 700, null, 3, 0, 0);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts, people, days) VALUES (2, '中期', 700, null, 3, 0, 0);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts, people, days) VALUES (3, '预验收', 700, null, 3, 0, 0);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts, people, days) VALUES (4, '验收', 700, null, 2, 0, 0);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts, people, days) VALUES (5, '学术会议', 5000, null, 0, 0, 0);
INSERT INTO budgetmanagementsystem.conference (id, name, price, comment, experts, people, days) VALUES (9, '技术交流会', 700, null, 0, 0, 0);
CREATE TABLE budgetmanagementsystem.consultation
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '专家信息（职称等）',
    price double COMMENT '咨询费',
    comment text
);
INSERT INTO budgetmanagementsystem.consultation (id, name, price, comment) VALUES (1, '其他专家', 1000, null);
INSERT INTO budgetmanagementsystem.consultation (id, name, price, comment) VALUES (6, '中级专家', 1500, null);
INSERT INTO budgetmanagementsystem.consultation (id, name, price, comment) VALUES (7, '高级专家', 2400, null);
CREATE TABLE budgetmanagementsystem.equipment
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '型号规格',
    price double,
    type varchar(50) COMMENT '子类别',
    img varchar(100) COMMENT '图片url',
    comment text COMMENT '备注'
);
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (104, '存储服务器', 98000, '存储服务器', '', 'Intel Xeon E5-2600 v4处理器，2颗，48核，96线程；128G内存；80T硬盘');
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (105, '计算服务器', 30000, '计算服务器', null, 'Intel Xeon Skylake-W-2133处理器；32F内存；256GNVMe固态，2T硬盘');
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (106, '深度学习工作站', 20000, '深度学习工作站', null, '英特尔（Intel）2.5G十四核Xeon处理器，64G ddr4 2133内存，英伟达（Nvidia）Quadro 16G显卡，Radeon Pro Vega 64 图形处理器，2T 机械硬盘');
INSERT INTO budgetmanagementsystem.equipment (id, name, price, type, img, comment) VALUES (107, '移动工作站', 20000, '移动工作站', null, '第九代 Intel Core i5 处理器，16GB 2666MHz DDR4 内存，2TB 固态硬盘，Radeon Pro 580X 图形处理器 (配备 8GB GDDR5 显存)');
CREATE TABLE budgetmanagementsystem.internationalcommunication
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100),
    price double,
    comment text,
    accommodation double,
    food double,
    traffic double COMMENT '市内交通（公杂费）'
);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (1, '澳大利亚', 24000, null, 1100, 380, 310);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (2, '北卡莱罗纳', 21000, null, null, null, null);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (4, '东京', 0, null, 1200, 630, 310);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (5, '莫斯科', 0, null, 2000, 310, 280);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (6, '法国巴黎', 0, null, 1200, 460, 300);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (7, '美国华盛顿', 0, null, 1400, 380, 310);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (8, '美国纽约', 0, null, 1700, 380, 310);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (10, '东京', 0, null, 1200, 630, 310);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (11, '法国巴黎', 0, null, 1200, 460, 300);
INSERT INTO budgetmanagementsystem.internationalcommunication (id, name, price, comment, accommodation, food, traffic) VALUES (18, 'sample', 0, null, 0, 0, 0);
CREATE TABLE budgetmanagementsystem.labour
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名目（本硕博专）',
    price double COMMENT '单价（人月）',
    priority int(11) COMMENT '优先级',
    comment text
);
INSERT INTO budgetmanagementsystem.labour (id, name, price, priority, comment) VALUES (1, '博士生', 2200, 2, null);
INSERT INTO budgetmanagementsystem.labour (id, name, price, priority, comment) VALUES (2, '硕士生', 1760, 3, null);
INSERT INTO budgetmanagementsystem.labour (id, name, price, priority, comment) VALUES (3, '专职', 8000, 1, null);
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
INSERT INTO budgetmanagementsystem.material (id, name, price, type, img, comment) VALUES (16, '服务器内存', 4000, null, null, null);
CREATE TABLE budgetmanagementsystem.others
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100),
    price double,
    comment text
);
INSERT INTO budgetmanagementsystem.others (id, name, price, comment) VALUES (1, '审计费', 20000, null);
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
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (11, 'sample', 0, null);
INSERT INTO budgetmanagementsystem.property (id, name, price, comment) VALUES (12, 'sample', 0, null);
CREATE TABLE budgetmanagementsystem.ruleEquipment
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    min_val int(11) DEFAULT '0',
    max_val int(11) DEFAULT '100000'
);
CREATE TABLE budgetmanagementsystem.testandprocess
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100) COMMENT '名称、内容',
    price double COMMENT '单价',
    comment text COMMENT '备注'
);
INSERT INTO budgetmanagementsystem.testandprocess (id, name, price, comment) VALUES (1, '测试化验加工费', 150000, null);
INSERT INTO budgetmanagementsystem.testandprocess (id, name, price, comment) VALUES (2, '数据清洗服务', 50000, null);
INSERT INTO budgetmanagementsystem.testandprocess (id, name, price, comment) VALUES (3, '云服务器租用', 100000, null);
CREATE TABLE budgetmanagementsystem.travel
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50) COMMENT '目的地',
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
INSERT INTO budgetmanagementsystem.travel (id, name, price, food, traffic, accommodation, comment) VALUES (7, '天津', 2000, 100, 80, 380, null);
INSERT INTO budgetmanagementsystem.travel (id, name, price, food, traffic, accommodation, comment) VALUES (8, '深圳', 2000, 100, 80, 450, null);
INSERT INTO budgetmanagementsystem.travel (id, name, price, food, traffic, accommodation, comment) VALUES (9, '重庆', 2000, 100, 80, 370, null);
INSERT INTO budgetmanagementsystem.travel (id, name, price, food, traffic, accommodation, comment) VALUES (10, '西安', 2000, 100, 80, 350, null);
CREATE TABLE budgetmanagementsystem.user
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50) DEFAULT 'username' COMMENT '用户名',
    password varchar(20) DEFAULT 'password' COMMENT '密码'
);
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (1, '1', '1');
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (2, '2', '2');
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (3, '222', '222');
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (4, '333', '333');
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (5, 'zhangsan', 'zhangsan');
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (6, '444', '444');
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (7, 'xxx', 'xxx');
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (9, '44444', '44444');
INSERT INTO budgetmanagementsystem.user (id, name, password) VALUES (10, 'aaa', 'aaa');
CREATE TABLE budgetmanagementsystem.user_budget
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userid int(11) NOT NULL,
    budgetid mediumtext NOT NULL,
    budgetname varchar(100)
);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (13, 1, '1557585969465', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (15, 4, '1557653424704', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (16, 4, '1557658609291', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (23, 5, '1557681943936', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (24, 5, '1557681978320', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (26, 3, '1557682461267', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (27, 3, '1557682473376', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (29, 9, '1557717529246', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (46, 10, '1557738195161', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (47, 10, '1557743667619', null);
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (120, 7, '1558802034440', 'new');
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (122, 1, '1558966651041', 'HP Z8 G4(Z3Z16AV-SC001)');
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (123, 7, '1558969830804', '预算');
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (126, 7, '1558979419134', 'HP Z8 G4(Z3Z16AV-SC001)');
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (127, 7, '1559005935872', '基于群体智能的城市交通数据开放共享技术研发');
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (128, 1, '1559127952477', 'new');
INSERT INTO budgetmanagementsystem.user_budget (id, userid, budgetid, budgetname) VALUES (131, 6, '1559132140099', '预算');
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