package service.impl;

import beans.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import service.IBudgetService;
@Service
public class BudgetService implements IBudgetService {

    @Override
    public Equipment[] doEquipment(Integer number) {
        return new Equipment[0];
    }

    @Override
    public Material[] doMaterial(Integer number) {
        return new Material[0];
    }

    @Override
    public TestAndProcess[] doTestAndProcess(Integer number) {
        return new TestAndProcess[0];
    }

    @Override
    public Power[] doPower(Integer number) {
        return new Power[0];
    }

    @Override
    public Travel[] doTravel(Integer number) {
        return new Travel[0];
    }

    @Override
    public Conference[] doConference(Integer number) {
        return new Conference[0];
    }

    @Override
    public InternationalCommunication[] doInternationalCommunication(Integer number) {
        return new InternationalCommunication[0];
    }

    @Override
    public Property[] doProperty(Integer number) {
        return new Property[0];
    }

    @Override
    public Labour[] doLabour(Integer number) {
        return new Labour[0];
    }

    @Override
    public Consultation[] doConsultation(Integer number) {
        return new Consultation[0];
    }

    @Override
    public Others[] doOthers(Integer number) {
        return new Others[0];
    }

    @Override
    public Indirect[] doIndirect(Integer number) {
        return new Indirect[0];
    }
}
