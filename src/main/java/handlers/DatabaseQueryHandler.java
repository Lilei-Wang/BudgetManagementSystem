package handlers;

import beans.Budget;
import beans.Consultation;
import beans.Equipment;
import beans.Labour;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.IConsultationDao;
import dao.IEquipmentDao;
import dao.ILabourDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Database/Query")
public class DatabaseQueryHandler {

    @Autowired
    private IEquipmentDao equipmentDao;
    @RequestMapping("/Equipment")
    public void queryEquipment(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Equipment> equipments = equipmentDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Equipment item : equipments) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("nums",0);
                list.add(obj);
            }
            object.put("data",list);
            writer.write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private ILabourDao labourDao;
    @RequestMapping("/Labour")
    public void queryLabour(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Labour> labours = labourDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Labour item : labours) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("people",0);
                obj.put("months",0);
                obj.put("nums",0);
                list.add(obj);
            }
            object.put("data",list);
            writer.write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private IConsultationDao consultationDao;
    @RequestMapping("/Consultation")
    public void queryConsultation(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Consultation> consultations = consultationDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Consultation item : consultations) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("nums",0);
                list.add(obj);
            }
            object.put("data",list);
            writer.write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
