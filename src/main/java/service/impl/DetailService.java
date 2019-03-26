package service.impl;

import beans.Equipment;
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
}