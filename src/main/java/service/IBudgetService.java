package service;

import beans.*;

public interface IBudgetService {


    Equipment[] doEquipment(Integer number);

    Material[] doMaterial(Integer number);

    TestAndProcess[] doTestAndProcess(Integer number);

    Power[] doPower(Integer number);

    Travel[] doTravel(Integer number);

    Conference[] doConference(Integer number);

    InternationalCommunication[] doInternationalCommunication(Integer number);

    Property[] doProperty(Integer number);

    Labour[] doLabour(Integer number);

    Consultation[] doConsultation(Integer number);

    Others[] doOthers(Integer number);

    Indirect[] doIndirect(Integer number);
}
