package beans;

/**
 * 会议费
 */
public class Conference extends Item {
    private int experts;
    private int people;

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
        return getPrice()*getPeople();
    }

    @Override
    public String toString() {
        return "Conference{" +
                "experts=" + experts +
                ", people=" + people +
                "} " + super.toString();
    }
    public double cost(Pair pair,Consultation consultation)
    {
        if(pair==null || pair.getDays()<=0 || pair.getPeople()<=0 || (consultation==null&&getExperts()!=0))
            return 0.0;
        double expert=(consultation==null)?0.0:consultation.getPrice();
        return pair.getDays()*(getExperts()*expert+getPrice()*pair.getPeople());
    }
}
