package service;

import beans.UserBudget;

import java.util.List;

/**
 * 维护用户与预算文件的关系
 */
public interface IUserBudgetService {
    void addUserBudget(Integer userid, Long id);

    List<UserBudget> getBudgetByUserid(Integer userid);

    void deleteUserBudget(Integer userid, Long budgetId);

    void changeBudgetName(Long id, String budgetName);
}
