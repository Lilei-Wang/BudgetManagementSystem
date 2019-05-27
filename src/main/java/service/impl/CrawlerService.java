package service.impl;

import beans.Equipment;
import beans.Workstation;
import dao.IEquipmentDao;
import dao.IWorkstationDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import service.ICrawlerService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class CrawlerService implements ICrawlerService {

    @Autowired
    @Deprecated
    private IWorkstationDao workstationDao;

    @Autowired
    private IEquipmentDao equipmentDao;

    public void setEquipmentDao(IEquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    @Deprecated
    public void setWorkstationDao(IWorkstationDao workstationDao) {
        this.workstationDao = workstationDao;
    }

    /**
     * 爬取热门工作站
     */
    @Override
    public void ForWorkstation() {
        String url = "http://top.zol.com.cn/compositor/51/workstation.html";
        List<Equipment> equipments = new LinkedList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements rankList = document.getElementsByClass("rank-list").first().getElementsByClass("rank-list__item");
            int index = 0;
            for (Element rankItem : rankList) {
                if (rankItem.className().equals("rank-list__item clearfix")) {
                    String name = rankItem.getElementsByClass("rank-list__cell cell-3").first()
                            .getElementsByClass("rank__name").first()
                            .getElementsByTag("a").first()
                            .text().trim();
                    String params = rankItem.getElementsByClass("rank-list__cell cell-3").first()
                            .getElementsByClass("_j_pro_info_count").first()
                            .getElementsByTag("a").get(1).attributes().get("href").trim();
                    String priceStr = rankItem.getElementsByClass("rank-list__cell cell-4").first()
                            .getElementsByClass("rank__price").first()
                            .text().trim();
                    double price = 0;
                    if (priceStr.endsWith("万")) {
                        priceStr = priceStr.substring(1, priceStr.length() - 1);
                        price = Double.valueOf(priceStr) * 10000.00;
                    } else {
                        priceStr = priceStr.substring(1);
                        price = Double.valueOf(priceStr);
                    }
                    String comment = getDescription(params);
                    if (price < 100000.0) {
                        Equipment equipment = new Equipment();
                        equipment.setName(name);
                        equipment.setPrice(price);
                        equipment.setComment(comment);
                        equipment.setType("工作站");
                        equipments.add(equipment);
                        index++;
                    }
                    //爬取前十
                    if (index > 10)
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Equipment equipment : equipments) {
            //System.out.println("before");
            Equipment exist = equipmentDao.selectByName(equipment.getName());
            //System.out.println("after");
            if (exist != null) {
                equipmentDao.updateEquipment(equipment);
            } else
                equipmentDao.insertEquipment(equipment);
        }

        System.out.println("爬取");
    }

    private String getDescription(String params) {
        String url = "http:" + params;
        StringBuilder builder = new StringBuilder();
        try {
            Document document = Jsoup.connect(url).get();
            Element paramsList = document.getElementsByClass("detailed-parameters").first();
            builder.append(paramsList.text().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void MysqlConnection(){
        List<Equipment> equipmentList = equipmentDao.selectAll();
        System.out.println("scheduled...........");
    }

}
