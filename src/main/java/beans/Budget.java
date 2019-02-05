package beans;

import java.util.Arrays;
import java.util.List;

/**
 * 经费报表对象，内含各种经费的集合
 */
public class Budget {
    private List<Equipment> equipments;
    private List<Material> materials;
    private List<TestAndProcess> testAndProcesses;
    private List<Power> powers;
    private List<Travel> travels;
    private List<Conference> conferences;
    private List<InternationalCommunication> internationalCommunications;
    private List<Property> properties;
    private List<Labour> labour;
    private List<Consultation> consultations;
    private List<Others> others;

    @Override
    public String toString() {
        return "Budget{" +
                "equipments=" + equipments +
                ", materials=" + materials +
                ", testAndProcesses=" + testAndProcesses +
                ", powers=" + powers +
                ", travels=" + travels +
                ", conferences=" + conferences +
                ", internationalCommunications=" + internationalCommunications +
                ", properties=" + properties +
                ", labour=" + labour +
                ", consultations=" + consultations +
                ", others=" + others +
                ", indirects=" + indirects +
                '}';
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<TestAndProcess> getTestAndProcesses() {
        return testAndProcesses;
    }

    public void setTestAndProcesses(List<TestAndProcess> testAndProcesses) {
        this.testAndProcesses = testAndProcesses;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

    public List<InternationalCommunication> getInternationalCommunications() {
        return internationalCommunications;
    }

    public void setInternationalCommunications(List<InternationalCommunication> internationalCommunications) {
        this.internationalCommunications = internationalCommunications;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Labour> getLabour() {
        return labour;
    }

    public void setLabour(List<Labour> labour) {
        this.labour = labour;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public List<Others> getOthers() {
        return others;
    }

    public void setOthers(List<Others> others) {
        this.others = others;
    }

    public List<Indirect> getIndirects() {
        return indirects;
    }

    public void setIndirects(List<Indirect> indirects) {
        this.indirects = indirects;
    }

    private List<Indirect> indirects;

}
