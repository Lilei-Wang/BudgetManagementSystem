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
    private Map<Equipment,Integer> equipments;
    private Map<Material,Integer> materials;
    private Map<TestAndProcess,Integer> testAndProcesses;
    private Map<Power,Integer> powers;
    private Map<Travel,Integer> travels;
    private Map<Conference,Integer> conferences;
    private Map<InternationalCommunication,Integer> internationalCommunications;
    private Map<Property,Integer> properties;
    private Map<Labour,Integer> labour;
    private Map<Consultation,Integer> consultations;
    private Map<Others,Integer> others;
    private Map<Indirect,Integer> indirects;

    public Map<Indirect, Integer> getIndirects() {
        return indirects;
    }

    public void setIndirects(Map<Indirect, Integer> indirects) {
        this.indirects = indirects;
    }

    public Map<Equipment, Integer> getEquipments() {
        return equipments;
    }

    public void setEquipments(Map<Equipment, Integer> equipments) {
        this.equipments = equipments;
    }

    public Map<Material, Integer> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<Material, Integer> materials) {
        this.materials = materials;
    }

    public Map<TestAndProcess, Integer> getTestAndProcesses() {
        return testAndProcesses;
    }

    public void setTestAndProcesses(Map<TestAndProcess, Integer> testAndProcesses) {
        this.testAndProcesses = testAndProcesses;
    }

    public Map<Power, Integer> getPowers() {
        return powers;
    }

    public void setPowers(Map<Power, Integer> powers) {
        this.powers = powers;
    }

    public Map<Travel, Integer> getTravels() {
        return travels;
    }

    public void setTravels(Map<Travel, Integer> travels) {
        this.travels = travels;
    }

    public Map<Conference, Integer> getConferences() {
        return conferences;
    }

    public void setConferences(Map<Conference, Integer> conferences) {
        this.conferences = conferences;
    }

    public Map<InternationalCommunication, Integer> getInternationalCommunications() {
        return internationalCommunications;
    }

    public void setInternationalCommunications(Map<InternationalCommunication, Integer> internationalCommunications) {
        this.internationalCommunications = internationalCommunications;
    }

    public Map<Property, Integer> getProperties() {
        return properties;
    }

    public void setProperties(Map<Property, Integer> properties) {
        this.properties = properties;
    }

    public Map<Labour, Integer> getLabour() {
        return labour;
    }

    public void setLabour(Map<Labour, Integer> labour) {
        this.labour = labour;
    }

    public Map<Consultation, Integer> getConsultations() {
        return consultations;
    }

    public void setConsultations(Map<Consultation, Integer> consultations) {
        this.consultations = consultations;
    }

    public Map<Others, Integer> getOthers() {
        return others;
    }

    public void setOthers(Map<Others, Integer> others) {
        this.others = others;
    }

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
    public Map<Indirect,Integer> computeIndirect() {
        double direct = 0.0;
        if (materials != null)
            for (Material material : materials.keySet()) {
                direct += material.computeUnitPrice() * materials.get(material);
            }
        if (testAndProcesses != null)
            for (TestAndProcess testAndProcess : testAndProcesses.keySet()) {
                direct += testAndProcess.computeUnitPrice() * testAndProcesses.get(testAndProcess);
            }
        if (powers != null)
            for (Power power : powers.keySet()) {
                direct += power.computeUnitPrice() * powers.get(power);
            }
        if (travels != null)
            for (Travel travel : travels.keySet()) {
                direct += travel.computeUnitPrice() * travels.get(travel);
            }
        if (conferences != null)
            for (Conference conference : conferences.keySet()) {
                direct += conference.computeUnitPrice()*conferences.get(conference);
            }
        if (internationalCommunications != null)
            for (InternationalCommunication internationalCommunication : internationalCommunications.keySet()) {
                direct += internationalCommunication.computeUnitPrice()*internationalCommunications.get(internationalCommunication);
            }
        if (properties != null)
            for (Property property : properties.keySet()) {
                direct += property.computeUnitPrice()*properties.get(property);
            }
        if (labour != null)
            for (Labour labour1 : labour.keySet()) {
                direct += labour1.computeUnitPrice()*labour.get(labour1);
            }
        if (others != null)
            for (Others other : others.keySet()) {
                direct += other.computeUnitPrice()*others.get(other);
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
        Map<Indirect,Integer> map=new HashMap<>();
        map.put(result,1);
        return map;
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
}
