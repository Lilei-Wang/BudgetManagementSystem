package beans;

/**
 * 间接费用，由直接费用计算生成，不对应数据库
 */
public class Indirect  extends Item{
    @Override
    public String toString() {
        return "Indirect{} " + super.toString();
    }
}
