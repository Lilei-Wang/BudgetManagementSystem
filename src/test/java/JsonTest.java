import beans.Pair;
import beans.Travel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JsonTest {
    @Test
    public void json()
    {
        Map<Travel, Pair> map=new HashMap<>();
        map.put(new Travel(){{
            setPrice(100);setFood(10);setTraffic(50);setAccommodation(80);
        }},new Pair(1,1));
        map.put(new Travel(){{
            setPrice(200);setFood(20);setTraffic(90);setAccommodation(99);
        }},new Pair(1,2));
        for (Map.Entry<Travel, Pair> travelPairEntry : map.entrySet()) {
            
        }
        System.out.println(JSON.toJSONString(map.entrySet()));
    }


    @Test
    public void urlTest(){
        URLConnection urlConnection=null;
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL("http://salarycalculator.sinaapp.com/calculate?city=beijing&origin_salary=8000&base_3j=8000&base_gjj=8000&is_gjj=true&is_exgjj=false&factor_exgjj=0.08");
            urlConnection = url.openConnection();
            urlConnection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder builder=new StringBuilder();
            String line=null;
            while((line=bufferedReader.readLine())!=null)
            {
                builder.append(line);
            }
            System.out.println(builder.toString());
            JSONObject object = JSON.parseObject(builder.toString());
            System.out.println(object.getDouble("org_allpay"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
