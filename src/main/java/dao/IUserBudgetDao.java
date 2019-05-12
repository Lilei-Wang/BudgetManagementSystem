package dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserBudgetDao {
    void insertUserBudget(@Param("userid") Integer userid, @Param("budgetid") Long budgetid);

    List<Long> selectBudgetByUserid(@Param("userid") Integer userid);

    void deleteUserBudget(@Param("userid") Integer userid, @Param("budgetid") Long budgetId);
}
