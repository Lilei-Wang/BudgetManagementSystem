package beans;

public class UserBudget {
    private Integer id;
    private Integer userid;
    private Long budgetid;
    private String budgetname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Long getBudgetid() {
        return budgetid;
    }

    public void setBudgetid(Long budgetid) {
        this.budgetid = budgetid;
    }

    public String getBudgetname() {
        return budgetname;
    }

    public void setBudgetname(String budgetname) {
        this.budgetname = budgetname;
    }
}
