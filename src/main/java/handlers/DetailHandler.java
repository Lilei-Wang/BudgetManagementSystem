package handlers;

import beans.Budget;
import beans.Indirect;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IDetailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Budget/Detail")
public class DetailHandler {
    @RequestMapping("Indirect")
    public void showIndirect(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            String sessionID = BudgetHandler.getSessionID(request.getCookies());
            Budget budget = BudgetHandler.retrieveBudget(sessionID);
            Map<Indirect, Integer> indirects = budget.getIndirects();
            List<JSONObject> list=new LinkedList<>();
            for (Indirect indirect : indirects.keySet()) {
                JSONObject obj=new JSONObject();
                obj.put("id",indirect.getId());
                obj.put("name",indirect.getName());
                obj.put("price",indirect.getPrice());
                obj.put("nums",indirects.get(indirect));
                list.add(obj);
            }
            object.put("indirects",list);
            writer.write(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private IDetailService detailService;
    @RequestMapping("/Stats")
    public void getStats(HttpServletRequest request,HttpServletResponse response)
    {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            String sessionID = BudgetHandler.getSessionID(request.getCookies());
            Budget budget = BudgetHandler.retrieveBudget(sessionID);

            double req_sofar=0.0,sum_sofar=0.0,req,sum;
            JSONObject sub=new JSONObject();
            req=budget.getRequirement().getEquip();
            sum=detailService.sumEquipment(budget.getEquipments());
            sub.put("req",req);
            sub.put("sum",sum);
            sub.put("diff",req-sum);
            object.put("equipment",sub);


            object.put("req",req_sofar);
            object.put("sum",sum_sofar);
            object.put("diff",req_sofar-sum_sofar);

            writer.write(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
