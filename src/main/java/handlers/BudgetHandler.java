package handlers;

import beans.*;
import dao.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.IBudgetService;
import service.ICheckService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Budget")
public class BudgetHandler {

    @Autowired
    private IBudgetService budgetService;

    @RequestMapping("/Generate")
    public void generateBudget(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
            Cookie cookie = new Cookie("sessionID", Long.toString(sessionID));
            cookie.setMaxAge(Integer.MAX_VALUE);
            cookie.setComment("会话鉴别，age=int_max，除非重新生成预算，否则长时间保持会话");
            response.addCookie(cookie);

            Budget budget = new Budget();
            Integer actualBudget = 0;

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
                        int experts = expertSum(budget.getConferences());
                        budget.setConsultations(budgetService.doConsultation(experts));
                    } else if (item.contains("international")) {
                        budget.setInternationalCommunications(budgetService.doInternationalCommunication(number));
                        budget.getRequirement().setInternational(number);
                    } else if (item.contains("property")) {
                        budget.setProperties(budgetService.doProperty(number));
                        budget.getRequirement().setProperty(number);
                    } else if (item.contains("labour")) {
                        budget.setLabour(budgetService.doLabour(number));
                        budget.getRequirement().setLabour(number);
                    } /*else if (item.contains("consulatation")) {
                        budget.setConsultations(budgetService.doConsultation(number));
                    } */ else if (item.contains("others")) {
                        budget.setOthers(budgetService.doOthers(number));
                        budget.getRequirement().setOthers(number);
                    }
                }
            }

            //间接生成的经费，咨询费、间接费
            //budget.setConsultations(budgetService.doConsultation(budget.getConferences()));
            //budget.setOthers(budgetService.doOthers(totalBudget.doubleValue()*10000-actualBudget.doubleValue()));
            budget.setIndirects(budget.computeIndirect());

            System.out.println(budget);


            /*String filePath = this.getClass().getClassLoader().getResource("..").getPath() +
                    File.separator + "budgets" +
                    File.separator + sessionID;*/
            String filePath = getFilePath(Long.toString(sessionID));
            serializeBudget(budget, filePath);

            response.setHeader("content-disposition", "attachment;filename=Budget" + sessionID + ".csv");
            System.out.println("ContextPath: " + request.getContextPath());
            System.out.println(session.getServletContext().getRealPath(""));
            budgetToOutputStream(budget, response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int expertSum(Map<Conference, Pair> conferences) {
        int experts = 0;
        for (Conference conference : conferences.keySet()) {
            experts += conference.getExperts() * conferences.get(conference).getDays();
        }
        return experts;
    }

    /**
     * 下载最新报表
     *
     * @param request
     * @param response
     * @param session
     */
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

    @RequestMapping("/Modify/Equip")
    public void modifyEquip(Integer mode, Equipment equipment, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/Modify/Equip");

        if (nums < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);
        assert budget != null;
        Map<Equipment, Integer> equipments = budget.getEquipments();
        //是否符合规则
        if (!checkService.checkEquipment(equipment)) return;
        //删除
        if (curd.equals(1)) {
            equipments.remove(equipment);
        } else if (curd.equals(2))//增改
        {
            equipments.remove(equipment);
            equipments.put(equipment, nums);
            /*for (Equipment item : equipments.keySet()) {
                if(item.equals(equipment))
                {
                    item.setPrice(equipment.getPrice());
                    equipments.put(item,nums);
                    break;
                }
            }*/
        } else
            equipments.put(equipment, nums);
        serializeBudget(budget, getFilePath(sessionID));
        /*if (mode.equals(0))//修改预算
        {
            if (nums < 0) return;
            String sessionID = getSessionID(request.getCookies());
            Budget budget = retrieveBudget(sessionID);

            assert budget != null;

            Map<Equipment, Integer> items = budget.getEquipments();
            Equipment mod=equipmentDao.selectById(id);
            if(mod!=null)
                items.put(mod,nums);
            try {
                double sum=0.0;
                for (Equipment equipment : items.keySet()) {
                    sum+=(equipment.getPrice()*items.get(equipment));
                }
                response.getWriter().print(sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            serializeBudget(budget, getFilePath(sessionID));
        } else//修改规则
        {
            Equipment equipment = new Equipment();
            equipment.setId(id);
            equipment.setName(name);
            equipment.setPrice(price);
            if (curd.equals(0))//增
            {
                equipmentDao.insertEquipment(equipment);
            } else if (curd.equals(1))//删
            {
                equipmentDao.deleteEquipment(equipment);
            } else//改
            {
                equipmentDao.updateEquipment(equipment);
            }
        }*/
    }


    @Autowired
    private IMaterialDao materialDao;

    /**
     * 修改预算中的材料费以及规则里的材料费
     *
     * @param mode
     * @param id
     * @param name
     * @param price
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Material")
    public void modifyMaterial(Integer mode, Integer id, String name, Double price, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/Modify/Material");

        if (mode.equals(0))//修改预算
        {
            if (nums < 0) return;
            String sessionID = getSessionID(request.getCookies());
            Budget budget = retrieveBudget(sessionID);

            assert budget != null;

            Map<Material, Integer> items = null;
            items = budget.getMaterials();
            Material mod = materialDao.selectById(id);
            if (mod != null)
                items.put(mod, nums);
            try {
                double sum = 0.0;
                for (Material material : items.keySet()) {
                    sum += (material.getPrice() * items.get(material));
                }
                response.getWriter().print(sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            serializeBudget(budget, getFilePath(sessionID));
        } else//修改规则
        {
            Material item = new Material();
            item.setId(id);
            item.setName(name);
            item.setPrice(price);
            if (curd.equals(0))//增
            {
                materialDao.insertMaterial(item);
            } else if (curd.equals(1))//删
            {
                materialDao.deleteMaterial(item);
            } else//改
            {
                materialDao.updateMaterial(item);
            }
        }
    }


    @Autowired
    private IInternationalCommunicationDao internationalCommunicationDao;

    /**
     * 修改预算中的国际交流合作费、规则中的国际交流合作费
     *
     * @param mode
     * @param id
     * @param name
     * @param price
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/International")
    public void modifyInternational(Integer mode, Integer id, String name, Double price, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/Modify/International");

        if (mode.equals(0))//修改预算
        {
            if (nums < 0) return;
            String sessionID = getSessionID(request.getCookies());
            Budget budget = retrieveBudget(sessionID);

            assert budget != null;

            Map<InternationalCommunication, Integer> items = null;
            items = budget.getInternationalCommunications();
            InternationalCommunication mod = internationalCommunicationDao.selectById(id);
            if (mod != null)
                items.put(mod, nums);
            serializeBudget(budget, getFilePath(sessionID));
        } else//修改规则
        {
            InternationalCommunication item = new InternationalCommunication();
            item.setId(id);
            item.setName(name);
            item.setPrice(price);
            if (curd.equals(0))//增
            {
                internationalCommunicationDao.insertInternational(item);
            } else if (curd.equals(1))//删
            {
                internationalCommunicationDao.deleteInternational(item);
            } else//改
            {
                internationalCommunicationDao.updateInternational(item);
            }
        }
    }


    @Autowired
    private IPropertyDao propertyDao;
    /**
     *
     * @param mode
     * @param property
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Property")
    public void modifyProperty(Integer mode, Property property, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/Modify/Property");

        /*if (mode.equals(0))//修改预算
        {*/
            if (nums < 0) return;
            String sessionID = getSessionID(request.getCookies());
            Budget budget = retrieveBudget(sessionID);

            assert budget != null;

            Map<Property, Integer> items = null;
            items = budget.getProperties();
            if(!checkService.checkProperty(property)) return;
            if(curd.equals(0)){
                items.put(property,nums);
            }else if(curd.equals(1)){
                items.remove(property);
            }else{
                items.remove(property);
                items.put(property,nums);
            }
            serializeBudget(budget, getFilePath(sessionID));
        /*} *//*else//修改规则
        {
            Property item = new Property();
            item.setId(id);
            item.setName(name);
            item.setPrice(price);
            if (curd.equals(0))//增
            {
                propertyDao.insertProperty(item);
            } else if (curd.equals(1))//删
            {
                propertyDao.deleteProperty(item);
            } else//改
            {
                propertyDao.updateProperty(item);
            }
        }*/
    }


    @Autowired
    private ILabourDao labourDao;

    /**
     * 修改预算中的劳务费、规则中的劳务费
     *
     * @param mode
     * @param id
     * @param name
     * @param price
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Labour")
    public void modifyLabour(Integer mode, Integer id, String name, Double price, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/Modify/Labour");

        if (mode.equals(0))//修改预算
        {
            if (nums < 0) return;
            String sessionID = getSessionID(request.getCookies());
            Budget budget = retrieveBudget(sessionID);

            assert budget != null;

            Map<Labour, Integer> items = null;
            items = budget.getLabour();
            Labour mod = labourDao.selectById(id);
            if (mod != null)
                items.put(mod, nums);
            serializeBudget(budget, getFilePath(sessionID));
        } else//修改规则
        {
            Labour item = new Labour();
            item.setId(id);
            item.setName(name);
            item.setPrice(price);
            if (curd.equals(0))//增
            {
                labourDao.insertLabour(item);
            } else if (curd.equals(1))//删
            {
                labourDao.deleteLabour(item);
            } else//改
            {
                labourDao.updateLabour(item);
            }
        }
    }


    @Autowired
    private IConferenceDao conferenceDao;

    /**
     * @param mode
     * @param conference
     * @param pair
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Conference")
    public void modifyConference(Integer mode, Conference conference, Pair pair, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/Modify/Conference");

        if (mode.equals(0))//修改预算
        {
            if (pair == null || pair.getDays() < 0 || pair.getPeople() < 0) return;
            String sessionID = getSessionID(request.getCookies());
            Budget budget = retrieveBudget(sessionID);

            assert budget != null;

            Map<Conference, Pair> items = budget.getConferences();
            Conference mod = conferenceDao.selectById(conference.getId());
            if (mod != null)
                items.put(mod, pair);
            int experts = 0;
            for (Conference item : items.keySet()) {
                experts += item.getExperts() * items.get(item).getDays();
            }
            budget.setConsultations(budgetService.doConsultation(experts));
            try {
                double sum = 0.0;
                Map<Conference, Pair> conferencesMap = budget.getConferences();
                Map<Consultation, Integer> consultationsMap = budget.getConsultations();
                Consultation consultation = null;
                for (Consultation item : consultationsMap.keySet()) {
                    consultation = item;
                }
                for (Conference item : conferencesMap.keySet()) {
                    sum += (consultation.getPrice() * item.getExperts()
                            + item.getPrice() * conferencesMap.get(item).getPeople()) * conferencesMap.get(item).getDays();
                }
                response.getWriter().print(sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            serializeBudget(budget, getFilePath(sessionID));
        } else//修改规则
        {
            Conference item = conference;
            if (curd.equals(0))//增
            {
                conferenceDao.insertConference(item);
            } else if (curd.equals(1))//删
            {
                conferenceDao.deleteConference(item);
            } else//改
            {
                conferenceDao.updateConference(item);
            }
        }
    }


    @Autowired
    private IConsultationDao consultationDao;

    /**
     * 修改预算中的劳务费、规则中的劳务费
     *
     * @param mode
     * @param id
     * @param name
     * @param price
     * @param nums
     * @param curd
     * @param request
     * @param response
     */
    @RequestMapping("/Modify/Consultation")
    public void modifyConsultation(Integer mode, Integer id, String name, Double price, Integer nums, Integer curd, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/Modify/Consultation");

        if (mode.equals(0))//修改预算
        {
            //咨询费的预算不需要单独更改
            /*if (nums < 0) return;
            String sessionID = getSessionID(request.getCookies());
            Budget budget = retrieveBudget(sessionID);

            assert budget != null;

            Map<Consultation, Integer> items = null;
            items = budget.getConsultations();
            for (Consultation item : items.keySet()) {
                if (item.getId().equals(id)) {
                    items.put(item, nums);
                    break;
                }
            }
            serializeBudget(budget, getFilePath(sessionID));*/
        } else//修改规则
        {
            Consultation item = new Consultation();
            item.setId(id);
            item.setName(name);
            item.setPrice(price);
            if (curd.equals(0))//增
            {
                consultationDao.insertConsultation(item);
            } else if (curd.equals(1))//删
            {
                consultationDao.deleteConsultation(item);
            } else//改
            {
                consultationDao.updateConsultation(item);
            }
        }
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
    public void modifyTravel(Integer mode, Travel travel, Pair pair, Integer curd, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/Modify/Travel");

        if (pair == null || pair.getDays() < 0 || pair.getPeople() < 0) return;
        String sessionID = getSessionID(request.getCookies());
        Budget budget = retrieveBudget(sessionID);

        assert budget != null;

        Map<Travel, Pair> items = budget.getTravels();
        checkService.checkTravel(travel);
        if (curd.equals(0)) {
            items.put(travel, pair);
        } else if (curd.equals(1)) {
            items.remove(travel);
        } else {
            items.remove(travel);
            items.put(travel, pair);
        }

        serializeBudget(budget, getFilePath(sessionID));

         /*else//修改规则
        {
            if (curd.equals(0))//增
            {
                travelDao.insertTravel(travel);
            } else if (curd.equals(1))//删
            {
                travelDao.deleteTravel(travel);
            } else//改
            {
                travelDao.updateTravel(travel);
            }
        }*/
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
        return new Budget();
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

    /**
     * 将Budget对象以csv文件的形式写入输出流
     *
     * @param budget
     * @param printWriter
     */
    private static void budgetToOutputStream(Budget budget, Writer printWriter) {
        BufferedWriter writer = new BufferedWriter(printWriter);
        try {
            writer.write("设备费,名称,单价,数量,小计");
            writer.newLine();
            Map<Item, Integer> items = (Map) budget.getEquipments();
            char comma = ',';
            for (Item item : items.keySet()) {
                writer.write(comma + item.getName()
                        + comma + item.getPrice()
                        + comma + items.get(item)
                        + comma + item.computeUnitPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("材料费,名称,单价,数量,小计");
            writer.newLine();
            items = (Map) budget.getMaterials();
            for (Item item : items.keySet()) {
                writer.write(comma + item.getName()
                        + comma + item.getPrice()
                        + comma + items.get(item)
                        + comma + item.computeUnitPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("测试化验加工费,名称,单价,数量,小计");
            writer.newLine();
            items = (Map) budget.getTestAndProcesses();
            for (Item item : items.keySet()) {
                writer.write(comma + item.getName()
                        + comma + item.getPrice()
                        + comma + items.get(item)
                        + comma + item.computeUnitPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("燃料动力费,名称,单价,数量,小计");
            writer.newLine();
            items = (Map) budget.getPowers();
            for (Item item : items.keySet()) {
                writer.write(comma + item.getName()
                        + comma + item.getPrice()
                        + comma + items.get(item)
                        + comma + item.computeUnitPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("差旅费,目的地,往返价格,伙食费,交通费,住宿费,人数,天数,小计");
            writer.newLine();
            Map<Travel, Pair> travels = budget.getTravels();
            for (Item item : travels.keySet()) {
                if (item instanceof Travel) {
                    writer.write(comma + ((Travel) item).getDest()
                            + comma + item.getPrice()
                            + comma + ((Travel) item).getFood()
                            + comma + ((Travel) item).getTraffic()
                            + comma + ((Travel) item).getAccommodation()
                            + comma + travels.get(item).getPeople()
                            + comma + travels.get(item).getDays()
                            + comma + ((Travel) item).cost(travels.get(item)));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("会议费,会议内容,费用标准,参会人数,会议次数,小计");
            writer.newLine();
            Map<Conference, Pair> conferences = budget.getConferences();
            for (Item item : conferences.keySet()) {
                if (item instanceof Conference) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + conferences.get(item).getPeople()
                            + comma + conferences.get(item).getDays()
                            + comma + item.getPrice() * conferences.get(item).getPeople() * conferences.get(item).getDays());
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("国际合作交流费,会议内容,费用标准,会议次数,小计");
            writer.newLine();
            items = (Map) budget.getInternationalCommunications();
            for (Item item : items.keySet()) {
                if (item instanceof InternationalCommunication) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("出版/文献/信息传播/知识产权事务费,费用名称,费用标准,数量,小计");
            writer.newLine();
            items = (Map) budget.getProperties();
            for (Item item : items.keySet()) {
                if (item instanceof Property) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("劳务费,费用名称,费用标准,数量,小计");
            writer.newLine();
            items = (Map) budget.getLabour();
            for (Item item : items.keySet()) {
                if (item instanceof Labour) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            if (budget.getConferences() != null && budget.getConferences().size() > 0 && budget.getConsultations() != null) {
                writer.write("咨询费,工作内容,人员类型,费用标准,每次人数,次数,小计");
                writer.newLine();
                Map<Consultation, Integer> consultations = budget.getConsultations();
                Consultation consultation = null;
                for (Consultation item : consultations.keySet()) {
                    consultation = item;
                }
                conferences = budget.getConferences();
                for (Item item : conferences.keySet()) {
                    if (item instanceof Conference) {
                        writer.write(comma + item.getName()
                                + comma + consultation.getName()
                                + comma + consultation.getPrice()
                                + comma + ((Conference) item).getExperts()
                                + comma + conferences.get(item).getDays()
                                + comma + ((Conference) item).getExperts() * consultation.getPrice() * conferences.get(item).getDays());
                        writer.newLine();
                    }
                }
            }
            writer.newLine();

            writer.write("其他费用,费用名称,费用标准,数量,小计");
            writer.newLine();
            items = (Map) budget.getOthers();
            for (Item item : items.keySet()) {
                if (item instanceof Others) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("间接费用,费用名称,费用标准,数量,小计");
            writer.newLine();
            items = (Map) budget.getIndirects();
            for (Item item : items.keySet()) {
                if (item instanceof Indirect) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }


            writer.flush();
            System.out.println("CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * GBK格式，解决csv文件中文乱码
     *
     * @param budget
     * @param outputStream
     */
    public static void budgetToOutputStream(Budget budget, OutputStream outputStream) {
        System.out.println("GBK");
        try {
            budgetToOutputStream(budget, new PrintWriter(new OutputStreamWriter(outputStream, "GBK")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
