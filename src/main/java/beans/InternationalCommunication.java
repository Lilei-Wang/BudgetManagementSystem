package beans;

/**
 * 国际交流合作费
 */
public class InternationalCommunication extends Item {
    private double food;
    private double accommodation;
    private double traffic;
    private int people;
    private int days;

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(double accommodation) {
        this.accommodation = accommodation;
    }

    public double getTraffic() {
        return traffic;
    }

    public void setTraffic(double traffic) {
        this.traffic = traffic;
    }

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

    /**
     * 人数*机票+人数*天数*（各种补贴）
     * @return
     */
    @Override
    public double computeUnitPrice() {
        return getPeople()*getPrice()+getPeople()*getDays()*(getFood()+getAccommodation()+getTraffic());
    }

    @Override
    public String toString() {
        return "InternationalCommunication{} " + super.toString();
    }
}
