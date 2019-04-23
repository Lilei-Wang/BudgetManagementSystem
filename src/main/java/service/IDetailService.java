package service;

import beans.*;

import java.util.Map;

public interface IDetailService {

    double sumEquipment(Map<Equipment, Integer> equipments);

    double sumTravel(Map<Travel, Pair> travels);

    double sumLabour(Map<Labour, Integer> labour);

    double sumProperty(Map<Property, Integer> properties);

    double sumConference(Map<Conference, Integer> conferences);
}
