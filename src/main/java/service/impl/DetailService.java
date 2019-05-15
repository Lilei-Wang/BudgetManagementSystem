package service.impl;

import beans.*;
import org.springframework.stereotype.Service;
import service.IDetailService;

import java.util.Map;

@Service
public class DetailService implements IDetailService {
    @Override
    public double sumEquipment(Map<Equipment, Integer> equipments) {
        double sum=0.0;
        for (Equipment equipment : equipments.keySet()) {
            sum+=equipment.getPrice()*equipments.get(equipment);
        }
        return sum;
    }

    @Override
    public double sumTravel(Map<Travel, Integer> travels) {
        double sum=0.0;
        for (Travel travel : travels.keySet()) {
            sum+=travel.computeUnitPrice()*travels.get(travel);
        }
        return sum;
    }

    @Override
    public double sumLabour(Map<Labour, Integer> labours) {
        double sum=0.0;
        for (Labour labour : labours.keySet()) {
            sum+=(labour.computeUnitPrice()*labours.get(labour));
        }
        return sum;
    }

    @Override
    public double sumProperty(Map<Property, Integer> properties) {
        double sum=0.0;
        for (Property item : properties.keySet()) {
            sum+=(item.getPrice()*properties.get(item));
        }
        return sum;
    }

    @Override
    public double sumConference(Map<Conference, Integer> conferences) {
        double sum=0.0;
        for (Conference item : conferences.keySet()) {
            sum+=(item.computeUnitPrice()*conferences.get(item));
        }
        return sum;
    }

    @Override
    public double sumConsultation(Map<Consultation, Integer> consultations) {
        double sum=0.0;
        for (Consultation item : consultations.keySet()) {
            sum+=(item.computeUnitPrice()*consultations.get(item));
        }
        return sum;
    }

    @Override
    public double sumMaterial(Map<Material, Integer> materials) {
        double sum=0.0;
        for (Material item : materials.keySet()) {
            sum+=(item.computeUnitPrice()*materials.get(item));
        }
        return sum;
    }

    @Override
    public double sumInternational(Map<InternationalCommunication, Integer> internationalCommunications) {
        double sum=0.0;
        for (InternationalCommunication item : internationalCommunications.keySet()) {
            sum+=(item.computeUnitPrice()*internationalCommunications.get(item));
        }
        return sum;
    }
}
