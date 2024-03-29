package beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Objects;

/**
 * 预算基类，包括所有预算都用到的id、name、price、comment等属性
 * 继承之后每个类的toString方法要修改
 */
public class Item implements Serializable {
    @JSONField(name = "id")
    private Integer id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "price")
    private double price;
    private String comment;


    /**
     * 此方法返回每一类预算的价格，不等同于getPrice，由子类选择实现，因为存在差旅费等多个价格加算的预算
     * 一般就是getPrice
     * @return
     */
    public double computeUnitPrice()
    {
        return getPrice();
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                '}';
    }

    /**
     * 在集合中初步判断两个对象是否可能相同
     * @return
     */
    @Override
    public int hashCode() {
        return (name==null)?0:name.hashCode();
    }

    /**
     * 在集合中，两个对象相同的定义：同一个类，所有字段都一样
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if(this==obj) return true;
        if(getClass()!=obj.getClass()) return false;
        Item other=(Item)obj;
        return other.getName().equals(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
