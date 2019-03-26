package service;

import beans.Equipment;

import java.util.Map;

public interface IDetailService {

    double sumEquipment(Map<Equipment, Integer> equipments);
}
