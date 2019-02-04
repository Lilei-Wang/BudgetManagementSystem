package beans;

/**
 * 其他费用，没有数据库表，由实际预算与期望预算的差值构成
 */
public class Others {
    private Integer id;
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Others{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
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
}
