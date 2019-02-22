package beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 经费报表对象，内含各种经费的集合
 */
public class Budget implements Serializable {
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

    public Map<Item, Integer> listToMap(List<Item> items) {
        Map<Item, Integer> map = new HashMap<>();
        if (items == null || items.size() == 0) return map;
        for (Item item : items) {
            int val = 1;
            if (map.containsKey(item)) {
                val = map.get(item) + 1;
            }
            map.put(item, val);
        }
        return map;
    }

    /**
     * 计算间接经费
     *
     * @return
     */
    public List<Indirect> computeIndirect() {
        double direct = 0.0;
        if (materials != null)
            for (Material material : materials) {
                direct += material.computeUnitPrice();
            }
        if (testAndProcesses != null)
            for (TestAndProcess testAndProcess : testAndProcesses) {
                direct += testAndProcess.computeUnitPrice();
            }
        if (powers != null)
            for (Power power : powers) {
                direct += power.computeUnitPrice();
            }
        if (travels != null)
            for (Travel travel : travels) {
                direct += travel.computeUnitPrice();
            }
        if (conferences != null)
            for (Conference conference : conferences) {
                direct += conference.computeUnitPrice();
            }
        if (internationalCommunications != null)
            for (InternationalCommunication internationalCommunication : internationalCommunications) {
                direct += internationalCommunication.computeUnitPrice();
            }
        if (properties != null)
            for (Property property : properties) {
                direct += property.computeUnitPrice();
            }
        if (labour != null)
            for (Labour labour1 : labour) {
                direct += labour1.computeUnitPrice();
            }
        if (others != null)
            for (Others other : others) {
                direct += other.computeUnitPrice();
            }

        double indirect = 0.0;
        if (direct <= 5000000) {
            indirect = direct * 0.2;
        } else if (direct <= 10000000) {
            indirect = 5000000 * 0.2 + (direct - 5000000) * 0.15;
        } else {
            indirect = 5000000 * 0.2 + 5000000 * 0.15 + (direct - 10000000) * 0.13;
        }
        Indirect result = new Indirect();
        result.setPrice(indirect);
        result.setName("间接费用");
        return Arrays.asList(result);
    }

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
