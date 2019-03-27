import beans.Pair;
import beans.Travel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonTest {
    @Test
    public void json()
    {
        Map<Travel, Pair> map=new HashMap<>();
        map.put(new Travel(){{
            setDest("xxx");
            setPrice(100);setFood(10);setTraffic(50);setAccommodation(80);
        }},new Pair(1,1));
        map.put(new Travel(){{
            setDest("yyy");
            setPrice(200);setFood(20);setTraffic(90);setAccommodation(99);
        }},new Pair(1,2));
        for (Map.Entry<Travel, Pair> travelPairEntry : map.entrySet()) {
            
        }
        System.out.println(JSON.toJSONString(map.entrySet()));
    }
}
