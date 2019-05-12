package service.impl;

import dao.IUserBudgetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IUserBudgetService;

import java.util.List;

@Service
public class UserBudgetService implements IUserBudgetService {
    @Autowired
    private IUserBudgetDao userBudgetDao;
    @Override
    public void addUserBudget(Integer userid, Long budgetid) {
        if(userid==null || budgetid==null) return ;
        userBudgetDao.insertUserBudget(userid,budgetid);
        System.out.println("add User-Budget");
    }

    @Override
    public List<Long> getBudgetByUserid(Integer userid) {
        List<Long> budgets=userBudgetDao.selectBudgetByUserid(userid);
        return budgets;
    }

    @Override
    public void deleteUserBudget(Integer userid, Long budgetId) {
        userBudgetDao.deleteUserBudget(userid,budgetId);
    }
}
