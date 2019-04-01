package service.impl;

import beans.Equipment;
import beans.Labour;
import beans.Pair;
import beans.Travel;
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
    public double sumTravel(Map<Travel, Pair> travels) {
        double sum=0.0;
        for (Travel travel : travels.keySet()) {
            sum+=travel.cost(travels.get(travel));
        }
        return sum;
    }

    @Override
    public double sumLabour(Map<Labour, Integer> labours) {
        double sum=0.0;
        for (Labour labour : labours.keySet()) {
            sum+=(labour.getPrice()*labours.get(labour));
        }
        return sum;
    }
}
