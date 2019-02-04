package beans;

import java.util.Arrays;

/**
 * 经费报表对象，内含各种经费的集合
 */
public class Budget {
    private Equipment[] equipments;
    private Material[] materials;
    private TestAndProcess[] testAndProcesses;
    private Power[] powers;
    private Travel[] travels;
    private Conference[] conferences;
    private InternationalCommunication[] internationalCommunications;
    private Property[] properties;
    private Labour[] labour;
    private Consultation[] consultations;
    private Others[] others;
    private Indirect[] indirects;

    @Override
    public String toString() {
        return "Budget{" +
                "equipments=" + Arrays.toString(equipments) +
                ", materials=" + Arrays.toString(materials) +
                ", testAndProcesses=" + Arrays.toString(testAndProcesses) +
                ", powers=" + Arrays.toString(powers) +
                ", travels=" + Arrays.toString(travels) +
                ", conferences=" + Arrays.toString(conferences) +
                ", internationalCommunications=" + Arrays.toString(internationalCommunications) +
                ", properties=" + Arrays.toString(properties) +
                ", labour=" + Arrays.toString(labour) +
                ", consultations=" + Arrays.toString(consultations) +
                ", others=" + Arrays.toString(others) +
                ", indirects=" + Arrays.toString(indirects) +
                '}';
    }

    public Indirect[] getIndirects() {
        return indirects;
    }

    public void setIndirects(Indirect[] indirects) {
        this.indirects = indirects;
    }

    public Others[] getOthers() {
        return others;
    }

    public void setOthers(Others[] others) {
        this.others = others;
    }

    public Equipment[] getEquipments() {
        return equipments;
    }

    public void setEquipments(Equipment[] equipments) {
        this.equipments = equipments;
    }

    public Material[] getMaterials() {
        return materials;
    }

    public void setMaterials(Material[] materials) {
        this.materials = materials;
    }

    public TestAndProcess[] getTestAndProcesses() {
        return testAndProcesses;
    }

    public void setTestAndProcesses(TestAndProcess[] testAndProcesses) {
        this.testAndProcesses = testAndProcesses;
    }

    public Power[] getPowers() {
        return powers;
    }

    public void setPowers(Power[] powers) {
        this.powers = powers;
    }

    public Travel[] getTravels() {
        return travels;
    }

    public void setTravels(Travel[] travels) {
        this.travels = travels;
    }

    public Conference[] getConferences() {
        return conferences;
    }

    public void setConferences(Conference[] conferences) {
        this.conferences = conferences;
    }

    public InternationalCommunication[] getInternationalCommunications() {
        return internationalCommunications;
    }

    public void setInternationalCommunications(InternationalCommunication[] internationalCommunications) {
        this.internationalCommunications = internationalCommunications;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setProperties(Property[] properties) {
        this.properties = properties;
    }

    public Labour[] getLabour() {
        return labour;
    }

    public void setLabour(Labour[] labour) {
        this.labour = labour;
    }

    public Consultation[] getConsultations() {
        return consultations;
    }

    public void setConsultations(Consultation[] consultations) {
        this.consultations = consultations;
    }
}
