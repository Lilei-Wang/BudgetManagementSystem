package service;

import java.util.List;

/**
 * 维护用户与预算文件的关系
 */
public interface IUserBudgetService {
    void addUserBudget(Integer userid, Long id);

    List<Long> getBudgetByUserid(Integer userid);
}
