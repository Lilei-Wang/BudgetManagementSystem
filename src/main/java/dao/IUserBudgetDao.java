package dao;

import beans.UserBudget;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserBudgetDao {
    void insertUserBudget(@Param("userid") Integer userid, @Param("budgetid") Long budgetid);

    List<UserBudget> selectBudgetByUserid(@Param("userid") Integer userid);

    void deleteUserBudget(@Param("userid") Integer userid, @Param("budgetid") Long budgetId);

    void updateBudgetName(@Param("budgetid") Long id,@Param("budgetname") String budgetName);

    UserBudget selectBudgetByBudgetid(@Param("budgetid") Long budgetId);
}
