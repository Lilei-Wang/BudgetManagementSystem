package dao;

import org.apache.ibatis.annotations.Param;

public interface IUserBudgetDao {
    void insertUserBudget(@Param("userid") Integer userid, @Param("budgetid") Long budgetid);
}
