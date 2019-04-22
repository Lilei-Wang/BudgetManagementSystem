package beans;

import util.SalaryCalculator;

/**
 * 劳务费
 */
public class Labour extends Item{
    private int priority;
    private int people;
    private int months;

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    @Override
    public String toString() {
        return "Labour{" +
                "priority=" + priority +
                ", people=" + people +
                ", months=" + months +
                "} " + super.toString();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public double computeUnitPrice() {
        double base=0;
        if(getName().equals("专职")){
            base = SalaryCalculator.getOrgCost(getPrice());
        }
        else
            base=getPrice();
        return base*getPeople()*getMonths();
    }
}
