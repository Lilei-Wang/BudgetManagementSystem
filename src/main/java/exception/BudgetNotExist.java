package exception;

/**
 * 预算数据不存在の错误
 */
public class BudgetNotExist extends Exception {
    public BudgetNotExist() {
        super();
    }

    public BudgetNotExist(String message) {
        super(message);
    }
}
