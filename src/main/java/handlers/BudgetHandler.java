package handlers;

import beans.Budget;
import beans.InternationalCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IBudgetService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

@Controller
@RequestMapping("/Budget")
public class BudgetHandler {

    @Autowired
    private IBudgetService budgetService;

    @RequestMapping("/Generate")
    public void generateBudget(HttpServletRequest request, HttpServletResponse response) {
        try {
            //System.out.println(budgetService);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            Integer totalBudget = Integer.valueOf(request.getParameter("total"));
            String[] items = request.getParameterValues("items");
            //System.out.println(totalBudget);

            //以时间为标志，标志预算对象
            long sessionID = new Date().getTime();
            response.addCookie(new Cookie("sessionID",Long.toString(sessionID)));

            Budget budget=new Budget();

            for (String item : items) {
                //System.out.println(item);
                Double number = Double.valueOf(request.getParameter(item + "-number"))*10000;
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
                } else if (item.contains("confernece")) {
                    budget.setConferences(budgetService.doConference(number));
                } else if (item.contains("international")) {
                    budget.setInternationalCommunications(budgetService.doInternationalCommunication(number));
                } else if (item.contains("property")) {
                    budget.setProperties(budgetService.doProperty(number));
                } else if (item.contains("labour")) {
                    budget.setLabour(budgetService.doLabour(number));
                } else if (item.contains("consulatation")) {
                    budget.setConsultations(budgetService.doConsultation(number));
                } else if (item.contains("others")) {
                    budget.setOthers(budgetService.doOthers(number));
                } else if (item.contains("indirect")) {
                    budget.setIndirects(budgetService.doIndirect(number));
                }
            }
            System.out.println(budget);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
