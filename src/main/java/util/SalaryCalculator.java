package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * 五险一金计算器（简易版）
 * 没有考虑社保汇缴基数和公积金汇缴基数的变动，两者均采用税前工资
 */
public class SalaryCalculator {
    private static double yanglao=0.19;
    private static double yiliao=0.10;
    private static double shiye=0.008;
    private static double gongjijin=0.12;
    private static double gongshang=0.004;
    private static double shengyu=0.008;

    /**
     * 根据税前工资，计算用人成本
     * @param preTaxSalary
     * @return
     */
    public static double getOrgCost(double preTaxSalary){
        return preTaxSalary+getWuxianyijin(preTaxSalary);
    }

    /**
     * 根据税前工资，计算企业应缴纳的五险一金
     * @param preTaxSalary
     * @return
     */
    public static double getWuxianyijin(double preTaxSalary){
        double shebaoBase=getShebaoBase(preTaxSalary);
        double gongjijinBase=getGongjijinBase(preTaxSalary);
        return shebaoBase*getShebaoPercentage()+gongjijinBase*getGongjijinPercentage();
    }

    private static double getGongjijinPercentage() {
        return gongjijin;
    }

    private static double getShebaoPercentage() {
        return yanglao+yiliao+shiye+gongshang+shengyu;
    }

    private static double getGongjijinBase(double preTaxSalary) {
        return (preTaxSalary>25401)?25401:((preTaxSalary<2273)?2273:preTaxSalary);
    }

    private static double getShebaoBase(double preTaxSalary) {
        return (preTaxSalary>25401)?25401:((preTaxSalary<5080)?5080:preTaxSalary);
    }

    private static double getAllPercentage() {
        return yanglao+yiliao+shiye+gongjijin+gongshang+shengyu;
    }

    public static double getYanglao() {
        return yanglao;
    }

    public static void setYanglao(double yanglao) {
        SalaryCalculator.yanglao = yanglao;
    }

    public static double getYiliao() {
        return yiliao;
    }

    public static void setYiliao(double yiliao) {
        SalaryCalculator.yiliao = yiliao;
    }

    public static double getShiye() {
        return shiye;
    }

    public static void setShiye(double shiye) {
        SalaryCalculator.shiye = shiye;
    }

    public static double getGongjijin() {
        return gongjijin;
    }

    public static void setGongjijin(double gongjijin) {
        SalaryCalculator.gongjijin = gongjijin;
    }

    public static double getGongshang() {
        return gongshang;
    }

    public static void setGongshang(double gongshang) {
        SalaryCalculator.gongshang = gongshang;
    }

    public static double getShengyu() {
        return shengyu;
    }

    public static void setShengyu(double shengyu) {
        SalaryCalculator.shengyu = shengyu;
    }
}
