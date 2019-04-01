package handlers;

import beans.*;


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
    @RequestMapping("/Indirect")
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
            req_sofar+=req;sum_sofar+=sum;

            sub=new JSONObject();
            req=budget.getRequirement().getTravel();
            sum=detailService.sumTravel(budget.getTravels());
            sub.put("req",req);
            sub.put("sum",sum);
            sub.put("diff",req-sum);
            object.put("travel",sub);
            req_sofar+=req;sum_sofar+=sum;

            object.put("req",req_sofar);
            object.put("sum",sum_sofar);
            object.put("diff",req_sofar-sum_sofar);

            writer.write(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/Equipment")
    public void updateEquipment(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            String sessionID = BudgetHandler.getSessionID(request.getCookies());
            Budget budget = BudgetHandler.retrieveBudget(sessionID);
            Map<Equipment, Integer> equipments = budget.getEquipments();
            List<JSONObject> list=new LinkedList<>();
            for (Equipment item : equipments.keySet()) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("nums",equipments.get(item));
                list.add(obj);
            }
            object.put("equipments",list);
            writer.write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/Travel")
    public void updateTravel(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            String sessionID = BudgetHandler.getSessionID(request.getCookies());
            Budget budget = BudgetHandler.retrieveBudget(sessionID);
            Map<Travel, Pair> travels = budget.getTravels();
            List<JSONObject> list=new LinkedList<>();
            for (Travel item : travels.keySet()) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("food",item.getFood());
                obj.put("traffic",item.getTraffic());
                obj.put("accommodation",item.getAccommodation());
                obj.put("people",travels.get(item).getPeople());
                obj.put("days",travels.get(item).getDays());
                list.add(obj);
            }
            object.put("travels",list);
            writer.write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/Power")
    public void powerDetail(HttpServletRequest request,HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            String sessionID = BudgetHandler.getSessionID(request.getCookies());
            Budget budget = BudgetHandler.retrieveBudget(sessionID);
            Map<Power, Integer> powers = budget.getPowers();
            List<JSONObject> list=new LinkedList<>();
            for (Power item : powers.keySet()) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("nums",powers.get(item));
                list.add(obj);
            }
            object.put("powers",list);
            writer.write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/Property")
    public void propertyDetail(HttpServletRequest request,HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            String sessionID = BudgetHandler.getSessionID(request.getCookies());
            Budget budget = BudgetHandler.retrieveBudget(sessionID);
            Map<Property, Integer> propertyIntegerMap = budget.getProperties();
            List<JSONObject> list=new LinkedList<>();
            for (Property item : propertyIntegerMap.keySet()) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("nums",propertyIntegerMap.get(item));
                list.add(obj);
            }
            object.put("data",list);
            writer.write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
