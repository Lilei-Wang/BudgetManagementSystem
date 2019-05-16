package service;

import beans.*;

import java.util.Map;

public interface IDetailService {

    double sumEquipment(Map<Equipment, Integer> equipments);

    double sumTravel(Map<Travel, Integer> travels);

    double sumLabour(Map<Labour, Integer> labour);

    double sumProperty(Map<Property, Integer> properties);

    double sumConference(Map<Conference, Integer> conferences);

    double sumConsultation(Map<Consultation, Integer> consultations);

    double sumMaterial(Map<Material, Integer> materials);

    double sumInternational(Map<InternationalCommunication, Integer> internationalCommunications);

    double sumTestAndProcess(Map<TestAndProcess, Integer> testAndProcesses);
}
