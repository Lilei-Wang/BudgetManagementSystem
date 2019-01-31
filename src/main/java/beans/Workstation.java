package beans;

/**
 * 工作站
 */
public class Workstation {
    private Integer id;
    private String spec;
    private double price;
    private String img;

    public Workstation() {
    }

    public Workstation(String spec, double price, String img) {
        this.spec = spec;
        this.price = price;
        this.img = img;
    }

    @Override
    public String toString() {
        return "Workstation{" +
                "id=" + id +
                ", spec='" + spec + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
