package beans;

/**
 * 会议费
 */
public class Conference extends Item {
    private int experts;

    public int getExperts() {
        return experts;
    }

    public void setExperts(int experts) {
        this.experts = experts;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "experts=" + experts +
                "} " + super.toString();
    }
}
