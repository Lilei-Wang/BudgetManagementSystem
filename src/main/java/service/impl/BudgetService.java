package service.impl;

import beans.*;
import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import service.IBudgetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetService implements IBudgetService {
    @Autowired
    private IEquipmentDao equipmentDao;
    private IMaterialDao materialDao;
    private IConferenceDao conferenceDao;
    private IConsultationDao consultationDao;
    private IInternationalCommunicationDao internationalCommunicationDao;
    private ILabourDao labourDao;
    private IPowerDao powerDao;
    private IPropertyDao propertyDao;
    private ITestAndProcessDao testAndProcessDao;
    private ITravelDao travelDao;

    public void setEquipmentDao(IEquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    /**
     * 合成设备费
     * @param number
     * @return
     */
    @Override
    public Equipment[] doEquipment(Double number) {
        Equipment[] equipments=equipmentDao.selectAll();
        List<Equipment> temp=new ArrayList<>();

        double sum=0.00;
        while(sum<number){
            temp.add(equipments[0]);
            sum+=equipments[0].getPrice();
        }
        Equipment[] result=new Equipment[temp.size()];
        temp.toArray(result);
        return result;
    }

    /**
     * 合成材料费
     * @param number
     * @return
     */
    @Override
    public Material[] doMaterial(Double number) {
        List<Object> objects=new ArrayList<>();
        return new Material[0];
    }

    /**
     * 合成测试化验加工费
     * @param number
     * @return
     */
    @Override
    public TestAndProcess[] doTestAndProcess(Double number) {
        return new TestAndProcess[0];
    }

    /**
     * 合成燃料动力费
     * @param number
     * @return
     */
    @Override
    public Power[] doPower(Double number) {
        return new Power[0];
    }

    /**
     * 合成差旅费
     * @param number
     * @return
     */
    @Override
    public Travel[] doTravel(Double number) {
        return new Travel[0];
    }

    /**
     * 合成会议费
     * @param number
     * @return
     */
    @Override
    public Conference[] doConference(Double number) {
        return new Conference[0];
    }

    /**
     * 合成国际交流合作费
     * @param number
     * @return
     */
    @Override
    public InternationalCommunication[] doInternationalCommunication(Double number) {
        return new InternationalCommunication[0];
    }

    /**
     * 合成知识产权费
     * @param number
     * @return
     */
    @Override
    public Property[] doProperty(Double number) {
        return new Property[0];
    }

    /**
     * 合成劳务费
     * @param number
     * @return
     */
    @Override
    public Labour[] doLabour(Double number) {
        return new Labour[0];
    }

    /**
     * 合成咨询费
     * @param number
     * @return
     */
    @Override
    public Consultation[] doConsultation(Double number) {
        return new Consultation[0];
    }

    /**
     * 合成其他费用（补差价）
     * @param number
     * @return
     */
    @Override
    public Others[] doOthers(Double number) {
        return new Others[0];
    }

    /**
     * 合成间接费用（由直接费用计算）
     * @param number
     * @return
     */
    @Override
    public Indirect[] doIndirect(Double number) {
        return new Indirect[0];
    }

    /*private List<Object> generateList()*/
}
