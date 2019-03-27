package beans;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 差旅费
 */
public class Travel extends Item {

    @JSONField(name = "dest")
    private String dest;
    @JSONField(name = "food")
    private double food;
    @JSONField(name = "traffic")
    private double traffic;
    @JSONField(name = "accommodation")
    private double accommodation;

    @Override
    public double computeUnitPrice() {
        return getPrice() + getFood() + getAccommodation() + getTraffic();
    }

    @Override
    public String getName() {
        return getDest();
    }

    @Override
    public void setName(String name) {
        setDest(name);
    }

    @Override
    public String toString() {
        return "Travel{" +
                "dest='" + dest + '\'' +
                ", food=" + food +
                ", traffic=" + traffic +
                ", accommodation=" + accommodation +
                "} " + super.toString();
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
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

    /**
     * 计算n人m天的旅行需要多少钱
     *
     * @param pair
     * @return
     */
    public double cost(Pair pair) {
        if(pair==null || pair.getPeople()<=0 || pair.getDays()<=0) return 0.0;
        return pair.getPeople() *
                (getPrice()
                        + (getTraffic() + getFood()) * pair.getDays()
                        + getAccommodation() * (pair.getDays() - 1));
    }
}
