package service;

import beans.Equipment;
import beans.Pair;
import beans.Travel;

import java.util.Map;

public interface IDetailService {

    double sumEquipment(Map<Equipment, Integer> equipments);

    double sumTravel(Map<Travel, Pair> travels);
}
