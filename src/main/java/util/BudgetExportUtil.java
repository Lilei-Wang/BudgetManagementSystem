package util;

import beans.*;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IDetailService;
import service.impl.BudgetService;
import service.impl.DetailService;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BudgetExportUtil {
    private static IDetailService detailService = new DetailService();

    public static void toWord(Budget budget, OutputStream outputStream) throws IOException {
        XWPFDocument document = new XWPFDocument();

        //设备费
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("一、设备费");
        run.setFontSize(18);
        run.setBold(true);

        Map<Equipment, Integer> equipments = budget.getEquipments();
        int i = 0, total_num = 0;
        double total_price = 0.0;
        for (Equipment equipment : equipments.keySet()) {
            i++;
            int num = equipments.get(equipment);
            double price = equipment.getPrice();
            total_num += num;
            total_price += price * num;

            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append(equipment.getName())
                    .append(num).append("台,每台")
                    .append(price).append("元，共计")
                    .append(num * price).append("元");
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("【用途说明，例如“针对测试过程中产生的图形数据的存储和分析需要”】，")
                    .append(num).append("台，主要配置如下：").append(equipment.getComment())
                    .append("。工作站单价约").append(price).append("元，共需").append(num * price).append("元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("北京航空航天大学拟购置" + equipments.size() + "类设备" + total_num + "台（套），总价格" + total_price + "元。");


        //材料费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("二、材料费");
        run.setFontSize(18);
        run.setBold(true);

        Map<Material, Integer> materials = budget.getMaterials();
        i = 0;
        for (Material item : materials.keySet()) {
            i++;
            int num = materials.get(item);
            double price = item.getPrice();

            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append(item.getName())
                    .append(num).append("台,每台")
                    .append(price).append("元，共计")
                    .append(num * price).append("元");
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("用途：【自行填写】，购置")
                    .append(num).append("台，")
                    .append("单价").append(price).append("元，共需").append(num * price).append("元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }


        //测试化验加工费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("三、测试化验加工费");
        run.setFontSize(18);
        run.setBold(true);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("测试化验加工费："+detailService.sumTestAndProcess(budget.getTestAndProcesses())+"万元。【具体用途自行填写】");

        Map<TestAndProcess, Integer> testAndProcesses = budget.getTestAndProcesses();
        i = 0;
        for (TestAndProcess item : testAndProcesses.keySet()) {
            i++;
            int num = testAndProcesses.get(item);
            double price = item.getPrice();

            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append(item.getName())
                    .append(num).append("次,每次")
                    .append(price).append("元，共计")
                    .append(num * price).append("元");
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("用途：【自行填写】，购买")
                    .append(num).append("服务，")
                    .append("单价").append(price).append("元，共需").append(num * price).append("元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }


        //燃料动力费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("四、燃料动力费");
        run.setFontSize(18);
        run.setBold(true);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("燃料动力费："+detailService.sumPower(budget.getPowers())+"万元。【具体用途自行填写】");

        Map<Power, Integer> powers = budget.getPowers();
        i = 0;
        for (Power item : powers.keySet()) {
            i++;
            int num = powers.get(item);
            double price = item.getPrice();

            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append(item.getName())
                    .append(num).append("次,每次")
                    .append(price).append("元，共计")
                    .append(num * price).append("元");
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("用途：【自行填写】，燃料动力费")
                    .append(num).append("次，")
                    .append("单价").append(price).append("元，共需").append(num * price).append("元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }




        //差旅费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("五、差旅费");
        run.setFontSize(18);
        run.setBold(true);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("为顺利完成本课题实施方案中的工作内容，课题拟对【出差地点】等地相关单位进行调研和技术交流。具体考察调研单位包括：【上海市交通委员会、广西省交通运输厅、桂林运营公司、钦州运营公司等】。");

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("北京航空航天大学人员按照《中央和国家机关差旅费管理办法》（财行[2013]531号）、《关于调整中央和国家机关差旅住宿费标准等有关问题的通知》(财行[2015]497 号)、《北京航空航天大学差旅费管理办法》（北航财字[2016]32 号）进行计算。");

        Map<Travel, Integer> travels = budget.getTravels();
        i = 0;
        for (Travel item : travels.keySet()) {
            i++;

            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append("北京-" + item.getName()).append("，")
                    .append(item.getDays() * item.getPeople() * travels.get(item)).append("人天，共计")
                    .append(item.computeUnitPrice() * travels.get(item)).append("元");
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("本课题需要前往" + item.getName() + "相关单位进行调研和技术交流，" +
                    "行程为 " + travels.get(item) + " 次 " + item.getPeople() + " 人 " + item.getDays() + " 天。" +
                    "乘坐飞机经济舱（北京-" + item.getName() + "飞机均价约 " + item.getPrice() + " 元），" +
                    "住宿标准为 " + item.getAccommodation() + " 元/天，" +
                    "伙食与交通费包干每天 " + (item.getFood() + item.getTraffic()) + " 元。" +
                    "总计用 " + item.getPeople() + " 人 ×（" + item.getPrice() + " 元+500 元 × " + (item.getDays() - 1) +
                    " 天+" + (item.getFood() + item.getTraffic()) + " 元 ×" + item.getDays() + " 天）× " + travels.get(item) + " 次" +
                    "=" + item.computeUnitPrice() * travels.get(item) + " 元");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }


        //会议费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("六、会议费");
        run.setFontSize(18);
        run.setBold(true);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("本课题列支会议费 " + detailService.sumConference(budget.getConferences()) + "元。");

        Map<Conference, Integer> conferences = budget.getConferences();
        i = 0;
        for (Conference item : conferences.keySet()) {
            i++;
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append(item.getName());
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("牵头单位将组织和协调各课题组召开" + item.getName() + "，【会议任务】，" +
                    "共 " + conferences.get(item) + " 次，" +
                    "会期 " + item.getDays() + " 天，" +
                    "预计参会 " + item.getPeople() + " 人，" +
                    "所需费用小计："
                    + item.getPrice() + " 元/人次× " + item.getPeople() + " 人× " + item.getDays() + " 天 × " + conferences.get(item) +
                    " 次= " + item.computeUnitPrice() * conferences.get(item) + " 元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("【会议任务举例】 牵头单位将组织和协调各课题组召开项目启动会，共同商讨各课题的具体目标、流程、关键节点及研究路线；" +
                "牵头单位每年组织和协调各课题组召开一次项目及课题中期检查会，检查各课题进度，讨论遇到的问题，内部研讨，寻找解决问题的方法；" +
                "为了做好项目的结题验收工作，牵头单位将组织各课题承担单位自查自纠，总结课题财务执行情况和撰写课题自评价报告，邀请专家进行项目预验收；" +
                "项目牵头单位将根据项目进度要求，按时完成项目结题验收工作，并组织召开项目结题验收；" +
                "在课题实施期间，要与相关领域其他学者进行研讨，预计受邀参加国内学术会议；");


        //国际交流合作费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("七、国际交流合作费");
        run.setFontSize(18);
        run.setBold(true);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("本课题列支国际交流合作费 " + detailService.sumInternational(budget.getInternationalCommunications()) + "元。");

        Map<InternationalCommunication, Integer> internationalCommunications = budget.getInternationalCommunications();
        i = 0;
        for (InternationalCommunication item : internationalCommunications.keySet()) {
            i++;
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append(item.getName());
            run.setText(line.toString());

            line = new StringBuilder();
            line.append(item.getName() + "：【时间】，【地点】，" +
                    "课题组预计 " + item.getPeople() + " 人参会，" +
                    "注册费、往返机票每人约为 " + item.getPrice() + " 元（国航经济舱往返打折）。" +
                    "每次住宿费：" + item.getAccommodation() + " 元/人天，" +
                    "伙食费：" + item.getFood() + " 元/人天，" +
                    "公杂费：" + item.getTraffic() + " 元/人天。" +
                    "出访时间：" + item.getDays() + " 天，汇率：1 美元=6.9 元人民币。" +
                    "合计：(" + item.getPrice() + " + ( " + item.getAccommodation() + " + " + item.getFood() + " + " + item.getTraffic() + " )× " + item.getPeople() + " × " + item.getDays() + " × " + internationalCommunications.get(item) + " )" +
                    "= " + item.computeUnitPrice() * internationalCommunications.get(item) + " 元；");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }


        //知识产权
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("八、出版/文献/信息传播/知识产权事务费");
        run.setFontSize(18);
        run.setBold(true);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("本课题列支出版/文献/信息传播/知识产权事务费 " + detailService.sumProperty(budget.getProperties()) + "元。");


        Map<Property, Integer> properties = budget.getProperties();
        i = 0;
        for (Property item : properties.keySet()) {
            i++;

            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）").append(item.getName());
            run.setText(line.toString());

            line = new StringBuilder();
            line.append(item.getName() + " " + properties.get(item) + " 项，" +
                    "单价 " + item.getPrice() + " 元，" +
                    "其中专利申请费 4605 元（参考国家知识产权局公告（第 75 号）专利费收费标准，发明专利申请费 950 元，发明专利申请审查费 2500 元，专利登记、印刷、印花费 255 元，发明专利年费 900 元），" +
                    "共计 " + item.getPrice() * properties.get(item) + " 元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }

        //劳务费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("九、劳务费");
        run.setFontSize(18);
        run.setBold(true);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("本课题列支劳务费 " + detailService.sumLabour(budget.getLabour()) + "元。");

        Map<Labour, Integer> labours = budget.getLabour();
        i = 0;
        for (Labour item : labours.keySet()) {
            i++;

            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）").append(item.getName());
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("投入" + item.getName() + " " + item.getPeople() + " 名，" +
                    "按照每人每月 " + item.getPrice() + " 元，" +
                    "共计 " + item.getMonths() + " 个月，" +
                    "劳务费小计 " + item.computeUnitPrice() * labours.get(item) + " 元；");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("【参考】其中博士研究生主要任务包括突破面向流式处理任务的高效动态调度技术、面向时空大数据的计算资源动态分配技术、多系统跨平台环境下的领域知识动态获取与隐性规律挖掘技术、跨系统环境下协同服务技术、跨域时空数据隐私保护技术、分层可扩展的大数据弹性计算框架、基于边缘计算的源用分离与自适应请求相应框架和复杂异构时空数据的统一访问模型等 8 项关键技术与理论。硕士研究生主要完成相关支撑技术的技术攻关和和算法原型的设计与实现。专职研发人员 3 人包括 1 名系统架构师、1 名技术开发工程师、1 名测试工程师");
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("【参考】技术人员工资每月按 8000 元计算，养老保险单位 1600(19%) 元/月，失业保险 80(0.8%)/月，工伤 32(0.4%)/月，生育险 64 (0.8%)元/月，医疗保险 800(10%) 元/月，住房公积金 960(12%) 元/月，保险费用共计 3536 元/月，每人月成本 8000+3536=11536 元。");



        //咨询费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("十、咨询费");
        run.setFontSize(18);
        run.setBold(true);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("本课题列支咨询费 " + detailService.sumConsultation(budget.getConsultations()) + "元。");

        Map<Consultation, Integer> consultations = budget.getConsultations();
        i = 0;
        for (Conference item : conferences.keySet()) {
            i++;
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append(item.getName());
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("【具体内容请自行替换】课题启动会议，邀请专家对课题总体技术方案进行咨询、评审，咨询专家 3人，会期 1 天，标准1000元/人/天，此项专家咨询费小计：1000 元/人×2 次×3 人=0.6 万元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }


        //其他费用
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("十一、其他费用");
        run.setFontSize(18);
        run.setBold(true);

        Map<Others, Integer> others = budget.getOthers();
        i = 0;
        for (Others item : others.keySet()) {
            i++;
            int num = others.get(item);
            double price = item.getPrice();

            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();

            StringBuilder line = new StringBuilder();
            line.append("（").append(i).append("）");
            line.append(item.getName())
                    .append(num).append("次,每次")
                    .append(price).append("元，共计")
                    .append(num * price).append("元");
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("用途：【自行填写】，购买")
                    .append(num).append("服务，")
                    .append("单价").append(price).append("元，共需").append(num * price).append("元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }


        //间接费用
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("十二、间接费用");
        run.setFontSize(18);
        run.setBold(true);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        double indirectSum = detailService.sumIndirect(budget.getIndirects());
        run.setText("本课题列支间接费用 " + indirectSum + "元。");

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("参照北京航空航天大学国家科技重大专项经费管理办法（试行），间接费用是指承担课题任务的单位在组织课题任务的过程中发生的无法在直接费用中列支的相关费用。主要包括承担课题任务的单位为课题研究提供的现有仪器设备及房屋，水、电、气、暖消耗，有关管理费用的补助支出，以及绩效支出等。其中绩效支出是指承担课题任务的单位为提高科研工作的绩效安排的相关支出。");

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("间接费用实行总额控制，在与课题牵头单位信用等级挂钩基础上，按照不超过课题经费中直接费用扣除设备购置费后的一定比例核定，根据最新《北京市科技计划项目（课题）经费管理办法》和间接经费计算方法，" +
                "本课题扣除设备购置费后 "+detailService.sumEquipment(budget.getEquipments())+" 元，" +
                "间接经费根据上述管理办法计算为 "+indirectSum+" 元，其中绩效费用约 "+indirectSum*0.2+" 万元，用于承担单位人员绩效支出。");



        document.write(outputStream);
    }

    public static void toCsv(Budget budget,OutputStream outputStream){
        System.out.println("GBK");
        try {
            toCsv(budget, new PrintWriter(new OutputStreamWriter(outputStream, "GBK")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void toCsv(Budget budget, PrintWriter printWriter){
        BufferedWriter writer = new BufferedWriter(printWriter);
        try {
            writer.write("设备费,名称,规格型号,产地,数量,计量单位,单价,小计");
            writer.newLine();
            Map<Item, Integer> items = (Map) budget.getEquipments();
            char comma = ',';
            for (Item item : items.keySet()) {
                writer.write(comma + item.getName()
                        + comma + item.getComment()
                        + comma + "中国"
                        + comma + items.get(item)
                        + comma + "台(套)"
                        + comma + item.getPrice()
                        + comma + item.computeUnitPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("材料费,支出内容,单位,单价,数量,用途,小计");
            writer.newLine();
            items = (Map) budget.getMaterials();
            for (Item item : items.keySet()) {
                writer.write(comma + item.getName()
                        + comma + "套(个)"
                        + comma + item.getPrice()
                        + comma + items.get(item)
                        + comma + item.getComment()
                        + comma + item.computeUnitPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("测试化验加工费,名称,单价,数量,小计");
            writer.newLine();
            items = (Map) budget.getTestAndProcesses();
            for (Item item : items.keySet()) {
                writer.write(comma + item.getName()
                        + comma + item.getPrice()
                        + comma + items.get(item)
                        + comma + item.computeUnitPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("燃料动力费,名称,单价,数量,小计");
            writer.newLine();
            items = (Map) budget.getPowers();
            for (Item item : items.keySet()) {
                writer.write(comma + item.getName()
                        + comma + item.getPrice()
                        + comma + items.get(item)
                        + comma + item.computeUnitPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("差旅费,出差城市,交通方式,往返价格,伙食标准补助,交通标准补助,住宿标准,人数,天数,次数,小计");
            writer.newLine();
            Map<Travel, Integer> travels = budget.getTravels();
            for (Item item : travels.keySet()) {
                if (item instanceof Travel) {
                    writer.write(comma + ((Travel) item).getName()
                            + comma + "飞机"
                            + comma + item.getPrice()
                            + comma + ((Travel) item).getFood()
                            + comma + ((Travel) item).getTraffic()
                            + comma + ((Travel) item).getAccommodation()
                            + comma + ((Travel) item).getPeople()
                            + comma + ((Travel) item).getDays()
                            + comma + travels.get(item)
                            + comma + item.computeUnitPrice() * travels.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("会议费,会议内容,费用标准,会期,参会人数,会议次数,小计");
            writer.newLine();
            Map<Conference, Integer> conferences = budget.getConferences();
            for (Item item : conferences.keySet()) {
                if (item instanceof Conference) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + ((Conference) item).getDays()
                            + comma + ((Conference) item).getPeople()
                            + comma + conferences.get(item)
                            + comma + item.computeUnitPrice() * conferences.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("国际合作交流费,会议内容,市际交通（往返交通）,住宿补贴,伙食补贴,公杂费,人数,天数,次数,小计");
            writer.newLine();
            Map<InternationalCommunication, Integer> internationalCommunications = budget.getInternationalCommunications();
            for (InternationalCommunication item : internationalCommunications.keySet()) {
                writer.write(comma + item.getName()
                        + comma + item.getPrice()
                        + comma + item.getAccommodation()
                        + comma + item.getFood()
                        + comma + item.getTraffic()
                        + comma + item.getPeople()
                        + comma + item.getDays()
                        + comma + internationalCommunications.get(item)
                        + comma + item.computeUnitPrice() * internationalCommunications.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("出版/文献/信息传播/知识产权事务费,出版/文献/信息传播/知识产权事务费内容,单位,单价,数量,小计");
            writer.newLine();
            items = (Map) budget.getProperties();
            for (Item item : items.keySet()) {
                if (item instanceof Property) {
                    writer.write(comma + item.getName()
                            + comma + "篇(个)"
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("劳务费,费用名称,费用标准,数量,小计(五险一金)");
            writer.newLine();
            items = (Map) budget.getLabour();
            for (Item item : items.keySet()) {
                if (item instanceof Labour) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            if (budget.getConferences() != null && budget.getConferences().size() > 0 && budget.getConsultations() != null) {
                writer.write("咨询费,人员类型,费用标准,人数,小计");
                writer.newLine();
                Map<Consultation, Integer> consultations = budget.getConsultations();
                for (Consultation consultation : consultations.keySet()) {
                    writer.write(comma + consultation.getName()
                            + comma + consultation.getPrice()
                            + comma + consultations.get(consultation)
                            + comma + consultation.getPrice() * consultations.get(consultation));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("其他费用,费用名称,费用标准,数量,小计");
            writer.newLine();
            items = (Map) budget.getOthers();
            for (Item item : items.keySet()) {
                if (item instanceof Others) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("间接费用,费用名称,费用标准,数量,小计");
            writer.newLine();
            items = (Map) budget.getIndirects();
            for (Item item : items.keySet()) {
                if (item instanceof Indirect) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }


            writer.flush();
            System.out.println("CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
