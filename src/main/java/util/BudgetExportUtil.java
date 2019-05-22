package util;

import beans.*;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IDetailService;
import service.impl.BudgetService;
import service.impl.DetailService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class BudgetExportUtil {
    private  static IDetailService detailService=new DetailService();
    public static void toWord(Budget budget, OutputStream outputStream) throws IOException {
        XWPFDocument document = new XWPFDocument();

        //设备费
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("一、设备费");

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
            line.append(equipment.getType())
                    .append(num).append("台,每台")
                    .append(price).append("元，共计")
                    .append(num * price).append("元");
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("用途，")
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
            line.append("用途：，购置")
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
        paragraph=document.createParagraph();
        run=paragraph.createRun();
        run.addTab();
        run.setText("测试化验加工费：万元");


        //燃料动力费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("四、燃料动力费");
        paragraph=document.createParagraph();
        run=paragraph.createRun();
        run.addTab();
        run.setText("燃料动力费：万元");


        //差旅费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("五、差旅费");

        paragraph=document.createParagraph();
        run=paragraph.createRun();
        run.addTab();
        run.setText("为顺利完成本课题实施方案中的工作内容，课题拟对上海、南宁、桂林、北海等地相关单位进行调研和技术交流。具体考察调研单位包括：上海市交通委员会、广西省交通运输厅、桂林运营公司、钦州运营公司等。");

        paragraph=document.createParagraph();
        run=paragraph.createRun();
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
            line.append("北京-"+item.getName()).append("，")
                    .append(item.getDays()*item.getPeople()*travels.get(item)).append("人天，共计")
                    .append(item.computeUnitPrice()*travels.get(item)).append("元");
            run.setText(line.toString());

            line = new StringBuilder();
            line.append("本课题需要前往"+item.getName()+"相关单位进行调研和技术交流，" +
                    "行程为 "+travels.get(item)+" 次 "+item.getPeople()+" 人 "+item.getDays()+" 天。" +
                    "乘坐飞机经济舱（北京-"+item.getName()+"飞机均价约 "+item.getPrice()+" 元），" +
                    "住宿标准为 "+item.getAccommodation()+" 元/天，" +
                    "伙食与交通费包干每天 "+(item.getFood()+item.getTraffic())+" 元。" +
                    "总计用 "+item.getPeople()+" 人 ×（"+item.getPrice()+" 元+500 元 × "+(item.getDays()-1)+
                    " 天+"+(item.getFood()+item.getTraffic())+" 元 ×"+item.getDays()+" 天）× "+travels.get(item)+" 次" +
                    "="+item.computeUnitPrice()*travels.get(item)+" 元");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }



        //会议费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("六、会议费");

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("本课题列支会议费 " +detailService.sumConference(budget.getConferences())+"元。");

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
            line.append("牵头单位将组织和协调各课题组召开"+item.getName()+"，【会议任务】，" +
                    "共 "+conferences.get(item)+" 次，" +
                    "会期 "+item.getDays()+" 天，" +
                    "预计参会 "+item.getPeople()+" 人，" +
                    "所需费用小计："
                    +item.getPrice()+" 元/人次× "+item.getPeople()+" 人× "+item.getDays()+" 天 × "+conferences.get(item)+
                    " 次= "+item.computeUnitPrice()*conferences.get(item)+" 元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }


        //国际交流合作费
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("七、国际交流合作费");

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("本课题列支国际交流合作费 " +detailService.sumInternational(budget.getInternationalCommunications())+"元。");

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
            line.append(item.getName()+"：【时间】，【地点】，" +
                    "课题组预计 "+item.getPeople()+" 人参会，" +
                    "注册费、往返机票每人约为 "+item.getPrice()+" 元（国航经济舱往返打折）。" +
                    "每次住宿费："+item.getAccommodation()+" 元/人天，" +
                    "伙食费："+item.getFood()+" 元/人天，" +
                    "公杂费："+item.getTraffic()+" 元/人天。" +
                    "出访时间："+item.getDays()+" 天，汇率：1 美元=6.9 元人民币。" +
                    "合计：("+item.getPrice()+" + ( "+item.getAccommodation()+" + "+item.getFood()+" + "+item.getTraffic()+" )× "+item.getPeople()+" × "+item.getDays()+" × "+internationalCommunications.get(item)+" )" +
                    "= "+item.computeUnitPrice()*internationalCommunications.get(item)+" 元；");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }


        //知识产权
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("八、出版/文献/信息传播/知识产权事务费");

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addTab();
        run.setText("本课题列支出版/文献/信息传播/知识产权事务费 " +detailService.sumProperty(budget.getProperties())+"元。");


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
            line.append(item.getName()+" "+properties.get(item)+" 项，" +
                    "单价 "+item.getPrice()+" 元，"+
                    "其中专利申请费 4605 元（参考国家知识产权局公告（第 75 号）专利费收费标准，发明专利申请费 950 元，发明专利申请审查费 2500 元，专利登记、印刷、印花费 255 元，发明专利年费 900 元），" +
                    "共计 "+item.getPrice()*properties.get(item)+" 元。");
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.addTab();
            run.setText(line.toString());
        }



        document.write(outputStream);
    }
}
