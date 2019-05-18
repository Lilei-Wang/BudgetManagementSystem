package beans;

import java.io.Serializable;

public class Requirement implements Serializable {
    private double total;
    private double equip,material,conference,consultation,indirect,international,labour,others,power,property,test,travel;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getEquip() {
        return equip;
    }

    public void setEquip(double equip) {
        this.equip = equip;
    }

    public double getMaterial() {
        return material;
    }

    public void setMaterial(double material) {
        this.material = material;
    }

    public double getConference() {
        return conference;
    }

    public void setConference(double conference) {
        this.conference = conference;
    }

    public double getConsultation() {
        return consultation;
    }

    public void setConsultation(double consultation) {
        this.consultation = consultation;
    }

    public double getIndirect() {
        return indirect;
    }

    public void setIndirect(double indirect) {
        this.indirect = indirect;
    }

    public double getInternational() {
        return international;
    }

    public void setInternational(double international) {
        this.international = international;
    }

    public double getLabour() {
        return labour;
    }

    public void setLabour(double labour) {
        this.labour = labour;
    }

    public double getOthers() {
        return others;
    }

    public void setOthers(double others) {
        this.others = others;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getProperty() {
        return property;
    }

    public void setProperty(double property) {
        this.property = property;
    }

    public double getTest() {
        return test;
    }

    public void setTest(double test) {
        this.test = test;
    }

    public double getTravel() {
        return travel;
    }

    public void setTravel(double travel) {
        this.travel = travel;
    }
}
