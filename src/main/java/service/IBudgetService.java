package service;

import beans.*;

public interface IBudgetService {


    Equipment[] doEquipment(Double number);

    Material[] doMaterial(Double number);

    TestAndProcess[] doTestAndProcess(Double number);

    Power[] doPower(Double number);

    Travel[] doTravel(Double number);

    Conference[] doConference(Double number);

    InternationalCommunication[] doInternationalCommunication(Double number);

    Property[] doProperty(Double number);

    Labour[] doLabour(Double number);

    Consultation[] doConsultation(Double number);

    Others[] doOthers(Double number);

    Indirect[] doIndirect(Double number);
}
