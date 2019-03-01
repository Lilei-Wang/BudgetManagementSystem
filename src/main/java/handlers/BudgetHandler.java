package handlers;

import beans.*;
import dao.IEquipmentDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.IBudgetService;

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
        PrintWriter writer = null;
        try {
            //System.out.println(budgetService);
            response.setContentType("text/html;charset=UTF-8");
            writer = response.getWriter();
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
                    } else if (item.contains("material")) {
                        budget.setMaterials(budgetService.doMaterial(number));
                    } else if (item.contains("test")) {
                        budget.setTestAndProcesses(budgetService.doTestAndProcess(number));
                    } else if (item.contains("power")) {
                        budget.setPowers(budgetService.doPower(number));
                    } else if (item.contains("travel")) {
                        budget.setTravels(budgetService.doTravel(number));
                    } else if (item.contains("conference")) {
                        budget.setConferences(budgetService.doConference(number));
                        budget.setConsultations(budgetService.doConsultation(budget.getConferences()));
                    } else if (item.contains("international")) {
                        budget.setInternationalCommunications(budgetService.doInternationalCommunication(number));
                    } else if (item.contains("property")) {
                        budget.setProperties(budgetService.doProperty(number));
                    } else if (item.contains("labour")) {
                        budget.setLabour(budgetService.doLabour(number));
                    } /*else if (item.contains("consulatation")) {
                        budget.setConsultations(budgetService.doConsultation(number));
                    } */ else if (item.contains("others")) {
                        budget.setOthers(budgetService.doOthers(number));
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
            budgetToOutputStream(budget, writer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert writer != null;
            writer.close();
        }
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
        String sessionID = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sessionID")) {
                sessionID = cookie.getValue();
                break;
            }
        }
        if (sessionID == null) return;
        String filepath = getFilePath(sessionID);
        PrintWriter writer = null;
        try {
            Budget budget = retrieveBudget(sessionID);
            writer = response.getWriter();
            response.setHeader("content-disposition", "attachment;filename=Budget" + sessionID + ".csv");
            assert budget != null;
            budgetToOutputStream(budget, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert writer != null;
            writer.close();
        }
    }

    @RequestMapping("/Detail")
    public ModelAndView budgetDetailHandler(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/budgetDetail.jsp");
        modelAndView.addObject("budget", retrieveBudget(getSessionID(request.getCookies())));
        return modelAndView;
    }


    @Autowired
    private IEquipmentDao equipmentDao;

    @RequestMapping("/Modify")
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
    }


    /**
     * 从Cookies中获取sessionID
     *
     * @param cookies
     * @return
     */
    private String getSessionID(Cookie[] cookies) {
        String sessionID = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sessionID")) {
                sessionID = cookie.getValue();
                break;
            }
        }
        return sessionID;
    }


    /**
     * 根据sessionID获取Budget对象
     *
     * @param sessionID
     * @return
     */
    private Budget retrieveBudget(String sessionID) {
        String filepath = getFilePath(sessionID);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filepath));
            return (Budget) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert inputStream != null;
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
    private String getFilePath(String sessionID) {
        String filePath = this.getClass().getClassLoader().getResource("..").getPath() +
                File.separator + "budgets" +
                File.separator + sessionID;
        System.out.println("Budget File Path:" + filePath);
        return filePath;
    }


    private void serializeBudget(Budget budget, String filePath) {
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
    private void budgetToOutputStream(Budget budget, Writer printWriter) {
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
                        + comma + item.getPrice() * items.get(item));
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
                        + comma + item.getPrice() * items.get(item));
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
                        + comma + item.getPrice() * items.get(item));
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
                        + comma + item.getPrice() * items.get(item));
                writer.newLine();
            }
            writer.newLine();

            writer.write("差旅费,目的地,往返价格,伙食费,交通费,住宿费,小计");
            writer.newLine();
            items = (Map) budget.getTravels();
            for (Item item : items.keySet()) {
                if (item instanceof Travel) {
                    writer.write(comma + ((Travel) item).getDest()
                            + comma + item.getPrice()
                            + comma + ((Travel) item).getFood()
                            + comma + ((Travel) item).getTraffic()
                            + comma + ((Travel) item).getAccommodation()
                            + comma + item.computeUnitPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.write("会议费,会议内容,费用标准,会议次数,小计");
            writer.newLine();
            items = (Map) budget.getConferences();
            for (Item item : items.keySet()) {
                if (item instanceof Conference) {
                    writer.write(comma + item.getName()
                            + comma + item.getPrice()
                            + comma + items.get(item)
                            + comma + item.getPrice() * items.get(item));
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
                            + comma + item.getPrice() * items.get(item));
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
                            + comma + item.getPrice() * items.get(item));
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
                            + comma + item.getPrice() * items.get(item));
                    writer.newLine();
                }
            }
            writer.newLine();

            if (budget.getConferences() != null && budget.getConferences().size() > 0) {
                writer.write("咨询费,工作内容,人员类型,费用标准,每次人数,次数,小计");
                writer.newLine();
                Map<Consultation, Integer> consultations = budget.getConsultations();
                Consultation consultation = null;
                for (Consultation item : consultations.keySet()) {
                    consultation = item;
                }
                items = (Map) budget.getConferences();
                for (Item item : items.keySet()) {
                    if (item instanceof Conference) {
                        writer.write(comma + item.getName()
                                + comma + consultation.getName()
                                + comma + item.getPrice()
                                + comma + ((Conference) item).getExperts()
                                + comma + items.get(item)
                                + comma + ((Conference) item).getExperts() * consultation.getPrice() * items.get(item));
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
                            + comma + item.getPrice() * items.get(item));
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
                            + comma + item.getPrice() * items.get(item));
                    writer.newLine();
                }
            }


            writer.flush();
            System.out.println("CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
