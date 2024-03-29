package beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Pair implements Serializable {
    @JSONField(name = "people")
    private int people;
    @JSONField(name = "days")
    private int days;

    public Pair() {
    }

    public Pair(int people, int days) {
        this.people = people;
        this.days = days;
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

    @Override
    public String toString() {
        return "Pair{" +
                "people=" + people +
                ", days=" + days +
                '}';
    }
}
