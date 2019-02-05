package service;

import beans.*;

import java.util.List;

public interface IBudgetService {


    List<Equipment> doEquipment(Double number);

    List<Material> doMaterial(Double number);

    List<TestAndProcess> doTestAndProcess(Double number);

    List<Power> doPower(Double number);

    List<Travel> doTravel(Double number);

    List<Conference> doConference(Double number);

    List<InternationalCommunication> doInternationalCommunication(Double number);

    List<Property> doProperty(Double number);

    List<Labour> doLabour(Double number);

    List<Consultation> doConsultation(Double number);

    List<Others> doOthers(Double number);

    List<Indirect> doIndirect(Double number);
}
