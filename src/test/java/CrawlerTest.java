import beans.Equipment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CrawlerTest {
    @Test
    public void test01() {
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
                    System.out.println("params url: " + params);
                    String priceStr = rankItem.getElementsByClass("rank-list__cell cell-4").first()
                            .getElementsByClass("rank__price").first()
                            .text().trim();
                    String comment = getDescription(params);
                    System.out.println("description: " + comment);
                    double price = 0;
                    if (priceStr.endsWith("万")) {
                        priceStr = priceStr.substring(1, priceStr.length() - 1);
                        price = Double.valueOf(priceStr) * 10000.00;
                    } else {
                        priceStr = priceStr.substring(1);
                        price = Double.valueOf(priceStr);
                    }
                    Equipment equipment = new Equipment();
                    equipment.setName(name);
                    equipment.setPrice(price);
                    equipments.add(equipment);
                    index++;
                    //爬取前十
                    if (index > 10)
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            /*builder.append("CPU型号：").append(paramsList.getElementById("newPmVal_2").text());
            builder.append("，CPU数量：").append(paramsList.getElementById("newPmVal_5").text());
            builder.append("，CPU核心：").append(paramsList.getElementById("newPmVal_10").text());
            builder.append("；内存大小：").append(paramsList.getElementById("newPmVal_15").text());
            builder.append("；显卡类型：").append(paramsList.getElementById("newPmVal_23").text());
            builder.append("，显存容量：").append(paramsList.getElementById("newPmVal_24").text());
            builder.append("；硬盘容量：").append(paramsList.getElementById("newPmVal_20").text());
            builder.append("；硬盘描述：").append(paramsList.getElementById("newPmVal_21").text());*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
