package beans;

/**
 * 差旅费
 */
public class Travel extends Item{

    private String dest;
    private double food;
    private double traffic;
    private double accommodation;

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
}
