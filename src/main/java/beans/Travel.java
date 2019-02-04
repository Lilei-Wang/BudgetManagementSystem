package beans;

/**
 * 差旅费
 */
public class Travel {
    private Integer id;
    private String dest;
    private double price;
    private double food;
    private double traffic;
    private double accommodation;

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", dest='" + dest + '\'' +
                ", price=" + price +
                ", food=" + food +
                ", traffic=" + traffic +
                ", accommodation=" + accommodation +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getTraffic() {
        return traffic;
    }

    public void setTraffic(double traffic) {
        this.traffic = traffic;
    }

    public double getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(double accommodation) {
        this.accommodation = accommodation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;
}
