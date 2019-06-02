package handlers;

import beans.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.*;
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
                obj.put("type",item.getType());
                obj.put("comment",item.getComment());
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
    private IMaterialDao materialDao;
    @RequestMapping("/Material")
    public void queryMaterial(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Material> materials = materialDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Material item : materials) {
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
    private ITestAndProcessDao testAndProcessDao;
    @RequestMapping("/TestAndProcess")
    public void queryTestAndProcess(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<TestAndProcess> testAndProcesses = testAndProcessDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (TestAndProcess item : testAndProcesses) {
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
    private IPowerDao powerDao;
    @RequestMapping("/Power")
    public void queryPower(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Power> powers = powerDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Power item : powers) {
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



    @Autowired
    private ITravelDao travelDao;
    @RequestMapping("/Travel")
    public void queryTravel(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Travel> travels = travelDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Travel item : travels) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("food",item.getFood());
                obj.put("traffic",item.getTraffic());
                obj.put("accommodation",item.getAccommodation());
                obj.put("people",item.getPeople());
                obj.put("days",item.getDays());
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
    private IConferenceDao conferenceDao;
    @RequestMapping("/Conference")
    public void queryConference(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Conference> conferences = conferenceDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Conference item : conferences) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("people",item.getPeople());
                obj.put("days",item.getDays());
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
    private IPropertyDao propertyDao;
    @RequestMapping("/Property")
    public void queryProperty(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Property> properties = propertyDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Property item : properties) {
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
    private IInternationalCommunicationDao internationalCommunicationDao;
    @RequestMapping("/International")
    public void queryInternational(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<InternationalCommunication> internationalCommunications = internationalCommunicationDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (InternationalCommunication item : internationalCommunications) {
                JSONObject obj=new JSONObject();
                obj.put("id",item.getId());
                obj.put("name",item.getName());
                obj.put("price",item.getPrice());
                obj.put("food",item.getFood());
                obj.put("traffic",item.getTraffic());
                obj.put("accommodation",item.getAccommodation());
                obj.put("people",item.getPeople());
                obj.put("days",item.getDays());
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
    private IOthersDao othersDao;
    @RequestMapping("/Others")
    public void queryOthers(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object=new JSONObject();
            List<Others> others = othersDao.selectAll();
            List<JSONObject> list=new LinkedList<>();
            for (Others item : others) {
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
