package beans;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 差旅费
 */
public class Travel extends Item {

    private double food;
    private double traffic;
    private double accommodation;
    private int people;
    private int days;

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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
    @Deprecated
    public double cost(Pair pair) {
        if(pair==null || pair.getPeople()<=0 || pair.getDays()<=0) return 0.0;
        return pair.getPeople() *
                (getPrice()
                        + (getTraffic() + getFood()) * pair.getDays()
                        + getAccommodation() * (pair.getDays() - 1));
    }

    @Override
    public double computeUnitPrice() {
        return getPeople()*(getPrice() + (getTraffic()+getFood())*getDays() +getAccommodation()*(getDays()-1));
    }
}
