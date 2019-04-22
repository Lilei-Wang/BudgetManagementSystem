import org.junit.Test;
import util.SalaryCalculator;

import java.util.ArrayList;

public class GongjijinTest {
    @Test
    public void gongjijinTest(){
        ArrayList<Double> list=new ArrayList<Double>();
        for(int i=1;i<10;i++)
            list.add((double)(i*1000));
        for (Double salary : list) {
            System.out.println(SalaryCalculator.getOrgCost(salary));
        }
        System.out.println(SalaryCalculator.getOrgCost(100000));
    }

}
