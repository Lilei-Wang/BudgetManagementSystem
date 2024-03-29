package service;

import beans.*;

import java.util.Map;

public interface IBudgetService {


    Map<Equipment,Integer> doEquipment(Double number);

    Map<Material,Integer> doMaterial(Double number);

    Map<TestAndProcess,Integer> doTestAndProcess(Double number);

    Map<Power,Integer> doPower(Double number);

    Map<Travel, Integer> doTravel(Double number);

    //Map<Conference, Pair> doConference(Double number);
    Map<Conference, Integer> doConference(Double number);

    Map<InternationalCommunication,Integer> doInternationalCommunication(Double number);

    Map<Property,Integer> doProperty(Double number);

    Map<Labour,Integer> doLabour(Double number);

    Map<Consultation,Integer> doConsultation(Double number);

    Map<Consultation,Integer> doConsultation(Map<Conference,Integer> conferences);
    Map<Consultation,Integer> doConsultation(int n);


    Map<Others,Integer> doOthers(Double number);

    Map<Indirect,Integer> doIndirect(Double number);

    void refreshBudget(Budget budget);
}
