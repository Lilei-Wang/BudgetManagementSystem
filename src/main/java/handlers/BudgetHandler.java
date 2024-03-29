package handlers;

import beans.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.IBudgetService;
import service.ICheckService;
import service.IUserBudgetService;
import util.BudgetExportUtil;
import util.CookieUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/Budget")
public class BudgetHandler {

    @Autowired
    private IBudgetService budgetService;

    @Autowired
    private IUserBudgetService userBudgetService;

    @RequestMapping("/Generate")
    public String generateBudget(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //PrintWriter writer = null;
        try {
            //System.out.println(budgetService);
            response.setContentType("text/html;charset=UTF-8");
            //writer = response.getWriter();
            Integer totalBudget = Integer.valueOf(request.getParameter("total"));
            String[] items = request.getParameterValues("items");
            //System.out.println(totalBudget);

            //以时间为标志，标志预算对象，用以鉴别不同用户
            long sessionID = new Date().getTime();
            /*Cookie cookie = new Cookie("sessionID", Long.toString(sessionID));
            cookie.setPath(request.getContextPath());
            cookie.setComment("会话鉴别，age=int_max，除非重新生成预算，否则长时间保持会话");
            response.addCookie(cookie);*/

            Budget budget = new Budget();
            budget.setId(sessionID);
            Integer actualBudget = 0;
            budget.getRequirement().setTotal(totalBudget*10000);

            if (items != null && items.length != 0) {
                for (String item : items) {
                    //System.out.println(item);
                    Double number = Double.valueOf(request.getParameter(item + "-number")) * 10000;
                    actualBudget += number.intValue();
                    if (item.contains("equipment")) {
                        budget.setEquipments(budgetService.doEquipment(number));
                        budget.getRequirement().setEquip(number);
                    } else if (item.contains("material")) {
                        budget.setMaterials(budgetService.doMaterial(number));
                        budget.getRequirement().setMaterial(number);
                    } else if (item.contains("test")) {
                        budget.setTestAndProcesses(budgetService.doTestAndProcess(number));
                        budget.getRequirement().setTest(number);
                    } else if (item.contains("power")) {
                        budget.setPowers(budgetService.doPower(number));
                        budget.getRequirement().setPower(number);
                    } else if (item.contains("travel")) {
                        budget.setTravels(budgetService.doTravel(number));
                        budget.getRequirement().setTravel(number);
                    } else if (item.contains("conference")) {
                        budget.setConferences(budgetService.doConference(number));
                        budget.getRequirement().setConference(number);
                        /*int experts = expertSum(budget.getConferences());
                        budget.setConsultations(budgetService.doConsultation(experts));*/
                    } else if (item.contains("international")) {
                        budget.setInternationalCommunications(budgetService.doInternationalCommunication(number));
                        budget.getRequirement().setInternational(number);
                    } else if (item.contains("property")) {
                        budget.setProperties(budgetService.doProperty(number));
                        budget.getRequirement().setProperty(number);
                    } else if (item.contains("labour")) {
                        budget.setLabour(budgetService.doLabour(number));
                        budget.getRequirement().setLabour(number);
                    } else if (item.contains("consultation")) {
                        budget.setConsultations(budgetService.doConsultation(number));
                        budget.getRequirement().setConsultation(number);
                    } else if (item.contains("others")) {
                        budget.setOthers(budgetService.doOthers(number));
                        budget.getRequirement().setOthers(number);
                    }
                }
            }

            //间接生成的经费，咨询费、间接费
            //budget.setConsultations(budgetService.doConsultation(budget.getConferences()));
            //budget.setOthers(budgetService.doOthers(totalBudget.doubleValue()*10000-actualBudget.doubleValue()));
            budget.setIndirects(budget.computeIndirect());

            //System.out.println(budget);


            /*String filePath = this.getClass().getClassLoader().getResource("..").getPath() +
                    File.separator + "budgets" +
                    File.separator + sessionID;*/
            String filePath = getFilePath(Long.toString(sessionID));
            serializeBudget(budget, filePath);
            Cookie useridCookie = CookieUtil.getCookieByName(request.getCookies(), "userid");
            Integer userid = Integer.valueOf(useridCookie.getValue());
            System.out.println("get userid " + userid);
            String budgetName = request.getParameter("name");
            userBudgetService.addUserBudget(userid, budget.getId());
            userBudgetService.changeBudgetName(budget.getId(),budgetName);

            //response.setHeader("content-disposition", "attachment;filename=Budget" + sessionID + ".csv");
            System.out.println("ContextPath: " + request.getContextPath());
            System.out.println(session.getServletContext().getRealPath(""));
            //budgetToOutputStream(budget, response.getOutputStream());
            //response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/history.jsp";
    }

    private int expertSum(Map<Conference, Pair> conferences) {
        int experts = 0;
        for (Conference conference : conferences.keySet()) {
            experts += conference.getExperts() * conferences.get(conference).getDays();
        }
        return experts;
    }

/*    *//**
     * 下载最新报表
     *
     * @param request
     * @param response
     * @param session
     *//*
    @RequestMapping("/Download")
    public void downloadBudgetHandler(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        //response.setContentType("application/octet-stream");
        response.setContentType("text/html;charset=UTF-8");
        String sessionID = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sessionID")) {
                sessionID = cookie.getValue();
                break;
            }
        }
        if (sessionID == null) return;
        String filepath = getFilePath(sessionID);
        try {
            Budget budget = retrieveBudget(sessionID);
            //writer = response.getWriter();
            response.setHeader("content-disposition", "attachment;filename=Budget" + sessionID + ".csv");
            assert budget != null;
            //budgetToOutputStream(budget, writer);
            budgetToOutputStream(budget, response.getOutputStream());
            System.out.println("Download...............");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }*/



    @RequestMapping("/Download/csv/{budgetid}")
    public void downloadCsv(@PathVariable("budgetid") Long budgetid,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //response.setContentType("application/octet-stream");
        response.setContentType("text/html;charset=UTF-8");
        String sessionID = budgetid.toString();
        try {
            Budget budget = retrieveBudget(sessionID);
            //writer = response.getWriter();
            response.setHeader("content-disposition", "attachment;filename=Budget" + sessionID + ".csv");
            assert budget != null;
            //budgetToOutputStream(budget, writer);
            //budgetToOutputStream(budget, response.getOutputStream());
            BudgetExportUtil.toCsv(budget,response.getOutputStream());
            System.out.println("Download...............");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载word说明
     * @param budgetid
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping("/Download/word/{budgetid}")
    public void downloadWord(@PathVariable("budgetid") Long budgetid,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //response.setContentType("application/octet-stream");
        response.setContentType("text/html;charset=UTF-8");
        String sessionID = budgetid.toString();
        try {
            Budget budget = retrieveBudget(sessionID);
            //writer = response.getWriter();
            response.setHeader("content-disposition", "attachment;filename=Budget" + sessionID + ".docx");
            assert budget != null;
            //budgetToOutputStream(budget, writer);
            //budgetToOutputStream(budget, response.getOutputStream());
            BudgetExportUtil.toWord(budget,response.getOutputStream());
            System.out.println("Download word...............");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }



    @RequestMapping("/Detail")
    public ModelAndView budgetDetailHandler(HttpServletRequest request) {
        String sessionID = getSessionID(request.getCookies());
        if (sessionID == null) return new ModelAndView("/");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/budgetDetail.jsp");
        modelAndView.addObject("budget", retrieveBudget(sessionID));
        return modelAndView;
    }

    @RequestMapping("/Detail/{budgetId}")
    public ModelAndView budgetDetailById(@PathVariable("budgetId") Long budgetId, HttpServletRequest request, HttpServletResponse response) {
        if (budgetId == null) return new ModelAndView("/");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/budgetDetail.jsp");
        modelAndView.addObject("budget", retrieveBudget(budgetId.toString()));
        Cookie sessionID = CookieUtil.getCookieByName(request.getCookies(), "sessionID");
        if (sessionID == null) {
            sessionID = new Cookie("sessionID", budgetId.toString());
        } else {
            sessionID.setValue(budgetId.toString());
        }
        sessionID.setPath("/");
        response.addCookie(sessionID);
        return modelAndView;
    }


    @RequestMapping("/Setting/{budgetId}")
    public ModelAndView budgetSettingById(@PathVariable("budgetId") Long budgetId, HttpServletRequest request, HttpServletResponse response) {
        if (budgetId == null) return new ModelAndView("/");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/budgetSetting.jsp");
        modelAndView.addObject("budget", retrieveBudget(budgetId.toString()));
        Cookie sessionID = CookieUtil.getCookieByName(request.getCookies(), "sessionID");
        if (sessionID == null) {
            sessionID = new Cookie("sessionID", budgetId.toString());
        } else {
            sessionID.setValue(budgetId.toString());
        }
        sessionID.setPath("/");
        response.addCookie(sessionID);
        UserBudget userBudget=userBudgetService.getBudgetByBudgetid(budgetId);
        modelAndView.addObject("name",userBudget.getBudgetname());
        return modelAndView;
    }

    @RequestMapping("/Setting.do")
    public void doBudgetSettingById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String sessionID = getSessionID(request.getCookies());
            if (sessionID == null) return;

            Integer totalBudget = Integer.valueOf(request.getParameter("total"));
            String[] items = request.getParameterValues("items");

            Budget budget = retrieveBudget(sessionID);
            if(budget==null) return ;
            budget.getRequirement().setTotal(totalBudget);

            if (items != null && items.length != 0) {
                for (String item : items) {
                    Double number = Double.valueOf(request.getParameter(item + "-number"));
                    if (item.contains("equipment")) {
                        budget.getRequirement().setEquip(number);
                    } else if (item.contains("material")) {
                        budget.getRequirement().setMaterial(number);
                    } else if (item.contains("test")) {
                        budget.getRequirement().setTest(number);
                    } else if (item.contains("power")) {
                        budget.getRequirement().setPower(number);
                    } else if (item.contains("travel")) {
                        budget.getRequirement().setTravel(number);
                    } else if (item.contains("conference")) {
                        budget.getRequirement().setConference(number);
                    } else if (item.contains("international")) {
                        budget.getRequirement().setInternational(number);
                    } else if (item.contains("property")) {
                        budget.getRequirement().setProperty(number);
                    } else if (item.contains("labour")) {
                        budget.getRequirement().setLabour(number);
                    } else if (item.contains("consultation")) {
                        budget.getRequirement().setConsultation(number);
                    } else if (item.contains("others")) {
                        budget.getRequirement().setOthers(number);
                    }
                }
            }
            serializeBudget(budget, getFilePath(sessionID));
            String budgetName = request.getParameter("name");
            userBudgetService.changeBudgetName(budget.getId(),budgetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath()+"/Budget/HistoryPage");
    }



    @RequestMapping("/Delete/{budgetId}")
    public void deleteBudgetById(@PathVariable("budgetId") Long budgetId, HttpServletRequest request, HttpServletResponse response) {
        if (budgetId == null) return;
        Cookie useridCookie = CookieUtil.getCookieByName(request.getCookies(), "userid");
        Integer userid = Integer.valueOf(useridCookie.getValue());
        //删除数据库记录
        userBudgetService.deleteUserBudget(userid, budgetId);
        //删除文件
        String filePath = getFilePath(budgetId.toString());
        File budgetFile = new File(filePath);
        if (budgetFile.delete()) {
            System.out.println("成功删除文件");
        }
        //删除budget相关cookie，防止删除后重新进入详情页面
        String sessionID = getSessionID(request.getCookies());
        if (sessionID!=null && sessionID.equals(budgetId.toString())) {
            Cookie cookie = new Cookie("sessionID", null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    @RequestMapping("/HistoryPage")
    public ModelAndView historyPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/history.jsp");
        return modelAndView;
    }

    @RequestMapping("/HistoryList")
    public void historyList(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject object = new JSONObject();
            //String sessionID = BudgetHandler.getSessionID(request.getCookies());
            Cookie useridCookie = CookieUtil.getCookieByName(request.getCookies(), "userid");
            Integer userid = Integer.valueOf(useridCookie.getValue());

            List<UserBudget> budgetList = userBudgetService.getBudgetByUserid(userid);
            List<JSONObject> list = new LinkedList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (UserBudget budget : budgetList) {
                JSONObject obj = new JSONObject();
                obj.put("id", budget.getBudgetid());
                Date date = new Date(budget.getBudgetid());
                obj.put("date", format.format(date));
                obj.put("name",budget.getBudgetname());
                list.add(obj);
            }
            object.put("data", list);
            writer.write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /*@RequestMapping("/Modify")
    @ResponseBody
    public void modifyBudget(String type, Integer id, Integer nums, HttpServletRequest request, HttpServletResponse response) {
        if (nums < 0) return;
        System.out.println("Modify");
        System.out.println(type);
        System.out.println(id);
        System.out.println(nums);
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        assert budget != null;

        Map<Item, Integer> items = null;
        if (type.contains("equip")) {
            items = (Map) budget.getEquipments();
        } else if (type.contains("material")) {
            items = (Map) budget.getMaterials();
        } else if (type.contains("test")) {
            items = (Map) budget.getTestAndProcesses();
        } else if (type.contains("power")) {
            items = (Map) budget.getPowers();
        } else if (type.contains("travel")) {
            items = (Map) budget.getTravels();
        } else if (type.contains("conference")) {
            items = (Map) budget.getConferences();
        } else if (type.contains("international")) {
            items = (Map) budget.getInternationalCommunications();
        } else if (type.contains("property")) {
            items = (Map) budget.getProperties();
        } else if (type.contains("labour")) {
            items = (Map) budget.getLabour();
        } else if (type.contains("consultation")) {
            items = (Map) budget.getConsultations();
        } else if (type.contains("other")) {
            items = (Map) budget.getOthers();
        } else if (type.contains("indirect")) {
            items = (Map) budget.getIndirects();
        }

        for (Item item : items.keySet()) {
            if (item.getId().equals(id)) {
                items.put(item, nums);
                break;
            }
        }


        serializeBudget(budget, getFilePath(sessionID));
    }*/


    @Autowired
    private IEquipmentDao equipmentDao;

    @Autowired
    private ICheckService checkService;

    @RequestMapping("/Modify/Equipment")
    public void modifyEquip(Integer mode, Equipment equipment, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Equipment");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);
        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Equipment, Integer> equipments = budget.getEquipments();
        //删除
        if (curd.equals(1)) {
            equipments.remove(equipment);
        } else {
            //是否符合规则
            int status = 0;
            if ((status = checkService.checkEquipment(equipment)) != checkService.isValid) {
                response.getWriter().write(checkService.getMessage(status));
                response.setStatus(444);
                return;
            }
            if (curd.equals(2))//改
            {
                Set<Equipment> keySet = equipments.keySet();
                //更新key
                for (Equipment item : keySet) {
                    if(item.equals(equipment)){
                        item.setPrice(equipment.getPrice());
                        item.setType(equipment.getType());
                        item.setComment(equipment.getComment());
                    }
                }
                //更新value
                equipments.put(equipment, nums);
            } else if(curd.equals(0))
            {
                equipments.put(equipment, nums);
                equipmentDao.insertEquipment(equipment);
            }
            else if(curd.equals(3)){
                Equipment add = equipmentDao.selectByName(equipment.getName());
                equipments.remove(add);
                equipments.put(add,nums);
                System.out.println("add form database:"+add.getComment());
            }else if(curd.equals(4)){
                equipmentDao.deleteEquipment(equipment);
                System.out.println("delete form database:"+equipment.getName());
            }
        }
        serializeBudget(budget, getFilePath(sessionID));

    }


    @Autowired
    private IMaterialDao materialDao;

    /**
     * 修改预算中的材料费
     *
     * @param mode
     * @param material
     * @param nums
     * @param curd
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/Modify/Material")
    public void modifyMaterial(Integer mode, Material material, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Material");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);
        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Material, Integer> items = null;
        items = budget.getMaterials();
        //删除
        if (curd.equals(1)) {
            items.remove(material);
        } else {
            //是否符合规则
            int status = 0;
            if ((status = checkService.checkMaterial(material)) != checkService.isValid) {
                response.setStatus(444);
                response.getWriter().write(checkService.getMessage(status));
                return;
            }
            if (curd.equals(2))//增改
            {
                items.remove(material);
                items.put(material, nums);
            } else if(curd.equals(0))
            {
                items.put(material, nums);
                materialDao.insertMaterial(material);
            }else if(curd.equals(3))
            {
                items.put(material, nums);
            }else if(curd.equals(4))
            {
                materialDao.deleteMaterial(material);
            }
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }


    @Autowired
    private ITestAndProcessDao testAndProcessDao;
    @RequestMapping("/Modify/TestAndProcess")
    public void modifyTestAndProcess(Integer mode, TestAndProcess testAndProcess, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/TestAndProcess");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);
        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<TestAndProcess, Integer> items = null;
        items = budget.getTestAndProcesses();
        //删除
        if (curd.equals(1)) {
            items.remove(testAndProcess);
        } else {
            if (curd.equals(2))//增改
            {
                items.remove(testAndProcess);
                items.put(testAndProcess, nums);
            } else if(curd.equals(3))
                items.put(testAndProcess, nums);
            else if(curd.equals(0)){
                items.put(testAndProcess,nums);
                testAndProcessDao.insertTestAndProcess(testAndProcess);
            }else if(curd.equals(4)){
                testAndProcessDao.deleteTestAndProcess(testAndProcess);
            }
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }


    @Autowired
    private IPowerDao powerDao;
    @RequestMapping("/Modify/Power")
    public void modifyPower(Integer mode, Power power, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Power");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);
        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Power, Integer> items = null;
        items = budget.getPowers();
        //删除
        if (curd.equals(1)) {
            items.remove(power);
        } else {
            if (curd.equals(2))//增改
            {
                items.remove(power);
                items.put(power, nums);
            } else if(curd.equals(3))
                items.put(power, nums);
            else if(curd.equals(0)){
                items.put(power,nums);
                powerDao.insertPower(power);
            }else if(curd.equals(4)){
                powerDao.deletePower(power);
            }
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }



    @Autowired
    private IInternationalCommunicationDao internationalCommunicationDao;

    /**
     * 修改预算中的国际交流合作费
     *
     * @param mode
     * @param internationalCommunication
     * @param nums
     * @param curd
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/Modify/International")
    public void modifyInternational(Integer mode, InternationalCommunication internationalCommunication, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/International");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<InternationalCommunication, Integer> items = null;
        items = budget.getInternationalCommunications();
        //删除
        if (curd.equals(1)) {
            items.remove(internationalCommunication);
        } else {
            if (curd.equals(2))//增改
            {
                items.remove(internationalCommunication);
                items.put(internationalCommunication, nums);
            } else if(curd.equals(0))
            {
                items.put(internationalCommunication, nums);
                internationalCommunicationDao.insertInternational(internationalCommunication);
            }else if(curd.equals(3))
            {
                items.put(internationalCommunication, nums);
            }else if(curd.equals(4))
            {
                internationalCommunicationDao.deleteInternational(internationalCommunication);
            }
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }


    @Autowired
    private IPropertyDao propertyDao;

    /**
     * @param mode
     * @param property
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Property")
    public void modifyProperty(Integer mode, Property property, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Property");

        /*if (mode.equals(0))//修改预算
        {*/
        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Property, Integer> items = null;
        items = budget.getProperties();
        if (!checkService.checkProperty(property)) return;
        if (curd.equals(0)) {
            items.put(property, nums);
            propertyDao.insertProperty(property);
        } else if (curd.equals(1)) {
            items.remove(property);
        } else if(curd.equals(2)){
            items.remove(property);
            items.put(property, nums);
        }else if(curd.equals(3)){
            items.put(property,nums);
        }else if(curd.equals(4)){
            propertyDao.deleteProperty(property);
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }


    @Autowired
    private ILabourDao labourDao;

    /**
     * 修改预算中的劳务费、规则中的劳务费
     *
     * @param mode
     * @param labour
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Labour")
    public void modifyLabour(Integer mode, Labour labour, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Labour");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Labour, Integer> items = null;
        items = budget.getLabour();
        if (curd.equals(0)) {
            items.put(labour, nums);
        } else if (curd.equals(1)) {
            items.remove(labour);
        } else {
            items.remove(labour);
            items.put(labour, nums);
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }


    @Autowired
    private IConferenceDao conferenceDao;

    /**
     * @param mode
     * @param conference
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Conference")
    public void modifyConference(Integer mode, Conference conference, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Conference");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Conference, Integer> items = budget.getConferences();
        if (curd.equals(0)) {
            items.put(conference, nums);
            conferenceDao.insertConference(conference);
        } else if (curd.equals(1)) {
            items.remove(conference);
        } else if(curd.equals(2)){
            items.remove(conference);
            items.put(conference, nums);
        }else if(curd.equals(3)){
            items.put(conference,nums);
        }else if(curd.equals(4)){
            conferenceDao.deleteConference(conference);
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }


    @Autowired
    private IConsultationDao consultationDao;

    /**
     * 修改预算中的劳务费、规则中的劳务费
     *
     * @param mode
     * @param consultation
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Consultation")
    public void modifyConsultation(Integer mode, Consultation consultation, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Consultation");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Consultation, Integer> items = null;
        items = budget.getConsultations();
        if (curd.equals(0)) {
            items.put(consultation, nums);
        } else if (curd.equals(1)) {
            items.remove(consultation);
        } else {
            items.remove(consultation);
            items.put(consultation, nums);
        }
        afterUpdate(budget);

        serializeBudget(budget, getFilePath(sessionID));
    }


    @Autowired
    private ITravelDao travelDao;

    /**
     * @param mode
     * @param travel
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Travel")
    public void modifyTravel(Integer mode, Travel travel, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Travel");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Travel, Integer> items = budget.getTravels();
        checkService.checkTravel(travel);
        if (curd.equals(0)) {
            items.put(travel, nums);
            travelDao.insertTravel(travel);
        } else if (curd.equals(1)) {
            items.remove(travel);
        } else if(curd.equals(2)){
            items.remove(travel);
            items.put(travel, nums);
        }else if(curd.equals(3)){
            items.put(travel, nums);
        }else if(curd.equals(4)){
            travelDao.deleteTravel(travel);
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }



    @RequestMapping("/Modify/Indirect")
    public void modifyIndirect(Integer mode, Indirect indirect, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Indirect");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Indirect, Integer> items = null;
        items = budget.getIndirects();
        if (curd.equals(0)) {
            items.put(indirect, nums);
        } else if (curd.equals(1)) {
            items.remove(indirect);
        } else {
            items.remove(indirect);
            items.put(indirect, nums);
        }
        serializeBudget(budget, getFilePath(sessionID));
    }



    @Autowired
    private IOthersDao othersDao;
    @RequestMapping("/Modify/Others")
    public void modifyOthers(Integer mode, Others others, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/Modify/Others");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        if (budget == null) {
            response.sendError(444, "budget not exists");
            return;
        }

        Map<Others, Integer> items = null;
        items = budget.getOthers();
        if (curd.equals(0)) {
            items.put(others, nums);
            othersDao.insertOthers(others);
        } else if (curd.equals(1)) {
            items.remove(others);
        } else if(curd.equals(2)){
            items.remove(others);
            items.put(others, nums);
        }else if(curd.equals(3)){
            items.put(others, nums);
        }else if(curd.equals(4)){
            othersDao.deleteOthers(others);
        }
        afterUpdate(budget);
        serializeBudget(budget, getFilePath(sessionID));
    }



    /**
     * 预算修改的善后工作
     *
     * @param budget
     */
    private void afterUpdate(Budget budget) {
        budget.setIndirects(budget.computeIndirect());
    }

    /**
     * 从Cookies中获取sessionID
     *
     * @param cookies
     * @return
     */
    public static String getSessionID(Cookie[] cookies) {
        String sessionID = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sessionID")) {
                sessionID = cookie.getValue();
                break;
            }
        }
        System.out.println("获取到的sessionID：" + sessionID);
        return sessionID;
    }


    /**
     * 根据sessionID获取Budget对象
     *
     * @param sessionID
     * @return
     */
    public static Budget retrieveBudget(String sessionID) {
        String filepath = getFilePath(sessionID);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filepath));
            return (Budget) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据sessionID生成文件路径
     *
     * @param sessionID
     * @return
     */
    public static String getFilePath(String sessionID) {
        String filePath = BudgetHandler.class.getClassLoader().getResource("..").getPath() +
                File.separator + "budgets" +
                File.separator + sessionID;
        System.out.println("Budget File Path:" + filePath);
        return filePath;
    }


    public static void serializeBudget(Budget budget, String filePath) {
        File budgetFile = new File(filePath);
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(budgetFile));
            outputStream.writeObject(budget);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
