package service;

import beans.*;

import java.util.Map;

public interface IBudgetService {


    Map<Equipment,Integer> doEquipment(Double number);

    Map<Material,Integer> doMaterial(Double number);

    Map<TestAndProcess,Integer> doTestAndProcess(Double number);

    Map<Power,Integer> doPower(Double number);

    Map<Travel, Pair> doTravel(Double number);

    Map<Conference,Integer> doConference(Double number);

    Map<InternationalCommunication,Integer> doInternationalCommunication(Double number);

    Map<Property,Integer> doProperty(Double number);

    Map<Labour,Integer> doLabour(Double number);

    @Deprecated
    Map<Consultation,Integer> doConsultation(Double number);


    Map<Consultation,Integer> doConsultation(Map<Conference,Integer> conferences);


    Map<Others,Integer> doOthers(Double number);

    Map<Indirect,Integer> doIndirect(Double number);

    void refreshBudget(Budget budget);
}
