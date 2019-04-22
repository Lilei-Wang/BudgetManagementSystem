package beans;

/**
 * 会议费
 */
public class Conference extends Item {
    @Deprecated
    private int experts;

    private int people;
    private int days;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getExperts() {
        return experts;
    }

    public void setExperts(int experts) {
        this.experts = experts;
    }

    @Override
    public double computeUnitPrice() {
        return getPrice()*getPeople()*getDays();
    }

    @Override
    public String toString() {
        return "Conference{" +
                "experts=" + experts +
                ", people=" + people +
                ", days=" + days +
                "} " + super.toString();
    }

    @Deprecated
    public double cost(Pair pair, Consultation consultation)
    {
        if(pair==null || pair.getDays()<=0 || pair.getPeople()<=0 || (consultation==null&&getExperts()!=0))
            return 0.0;
        double expert=(consultation==null)?0.0:consultation.getPrice();
        return pair.getDays()*(getExperts()*expert+getPrice()*pair.getPeople());
    }
}
