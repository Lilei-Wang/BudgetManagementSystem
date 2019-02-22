package service.impl;

import beans.*;
import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IBudgetService;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class BudgetService implements IBudgetService {
    @Autowired
    private IEquipmentDao equipmentDao;
    @Autowired
    private IMaterialDao materialDao;
    @Autowired
    private IConferenceDao conferenceDao;
    @Autowired
    private IConsultationDao consultationDao;
    @Autowired
    private IInternationalCommunicationDao internationalCommunicationDao;
    @Autowired
    private ILabourDao labourDao;
    @Autowired
    private IPowerDao powerDao;
    @Autowired
    private IPropertyDao propertyDao;
    @Autowired
    private ITestAndProcessDao testAndProcessDao;
    @Autowired
    private ITravelDao travelDao;

    public IEquipmentDao getEquipmentDao() {
        return equipmentDao;
    }

    public IMaterialDao getMaterialDao() {
        return materialDao;
    }

    public IConferenceDao getConferenceDao() {
        return conferenceDao;
    }

    public IConsultationDao getConsultationDao() {
        return consultationDao;
    }

    public IInternationalCommunicationDao getInternationalCommunicationDao() {
        return internationalCommunicationDao;
    }

    public ILabourDao getLabourDao() {
        return labourDao;
    }

    public IPowerDao getPowerDao() {
        return powerDao;
    }

    public IPropertyDao getPropertyDao() {
        return propertyDao;
    }

    public ITestAndProcessDao getTestAndProcessDao() {
        return testAndProcessDao;
    }

    public ITravelDao getTravelDao() {
        return travelDao;
    }

    public void setMaterialDao(IMaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    public void setConferenceDao(IConferenceDao conferenceDao) {
        this.conferenceDao = conferenceDao;
    }

    public void setConsultationDao(IConsultationDao consultationDao) {
        this.consultationDao = consultationDao;
    }

    public void setInternationalCommunicationDao(IInternationalCommunicationDao internationalCommunicationDao) {
        this.internationalCommunicationDao = internationalCommunicationDao;
    }

    public void setLabourDao(ILabourDao labourDao) {
        this.labourDao = labourDao;
    }

    public void setPowerDao(IPowerDao powerDao) {
        this.powerDao = powerDao;
    }

    public void setPropertyDao(IPropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public void setTestAndProcessDao(ITestAndProcessDao testAndProcessDao) {
        this.testAndProcessDao = testAndProcessDao;
    }

    public void setTravelDao(ITravelDao travelDao) {
        this.travelDao = travelDao;
    }

    public void setEquipmentDao(IEquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    /**
     * 合成设备费
     *
     * @param number
     * @return
     */
    @Override
    public List<Equipment> doEquipment(Double number) {
        List<Equipment> equipments = equipmentDao.selectAll();
        List<Equipment> result = generateList((List) equipments, number);
        return result;
    }

    /**
     * 合成材料费
     *
     * @param number
     * @return
     */
    @Override
    public List<Material> doMaterial(Double number) {
        List<Material> materials = materialDao.selectAll();
        List<Material> result = generateList((List) materials, number);
        return result;
    }

    /**
     * 合成测试化验加工费
     *
     * @param number
     * @return
     */
    @Override
    public List<TestAndProcess> doTestAndProcess(Double number) {
        List<TestAndProcess> testAndProcesses = testAndProcessDao.selectAll();
        List<TestAndProcess> result = generateList((List) testAndProcesses, number);
        return result;
    }

    /**
     * 合成燃料动力费
     *
     * @param number
     * @return
     */
    @Override
    public List<Power> doPower(Double number) {
        List<Power> powers = powerDao.selectAll();
        List<Power> result = generateList((List) powers, number);
        return result;
    }

    /**
     * 合成差旅费
     *
     * @param number
     * @return
     */
    @Override
    public List<Travel> doTravel(Double number) {
        List<Travel> travels = travelDao.selectAll();
        List<Travel> result = generateList((List) travels, number);
        return result;
    }

    /**
     * 合成会议费
     *
     * @param number
     * @return
     */
    @Override
    public List<Conference> doConference(Double number) {
        List<Conference> conferences = conferenceDao.selectAll();
        List<Conference> result = generateList((List) conferences, number);
        return result;
    }

    /**
     * 合成国际交流合作费
     *
     * @param number
     * @return
     */
    @Override
    public List<InternationalCommunication> doInternationalCommunication(Double number) {
        List<InternationalCommunication> internationalCommunications = internationalCommunicationDao.selectAll();
        List<InternationalCommunication> result = generateList((List) internationalCommunications, number);
        return result;
    }

    /**
     * 合成知识产权费
     *
     * @param number
     * @return
     */
    @Override
    public List<Property> doProperty(Double number) {
        List<Property> properties = propertyDao.selectAll();
        List<Property> result = generateList((List) properties, number);
        return result;
    }

    /**
     * 合成劳务费
     *
     * @param number
     * @return
     */
    @Override
    public List<Labour> doLabour(Double number) {
        List<Labour> labour = labourDao.selectAll();
        List<Labour> result = generateList((List) labour, number);
        return result;
    }

    /**
     * 合成咨询费
     *
     * @param number
     * @return
     */
    @Override
    @Deprecated
    public List<Consultation> doConsultation(Double number) {
        List<Consultation> consultations = consultationDao.selectAll();
        List<Consultation> result = generateList((List) consultations, number);
        return result;
    }

    /**
     * 根据会议费合成咨询费
     * @param conferences
     * @return
     */
    @Override
    public List<Consultation> doConsultation(List<Conference> conferences) {
        List<Consultation> consultations = consultationDao.selectAll();
        Consultation consultation=null;
        try
        {
            consultation=consultations.get(0);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        int experts=0;
        for (Conference conference : conferences) {
            experts+=conference.getExperts();
        }
        List<Consultation> result=new ArrayList<>();
        while(experts!=0)
        {
            experts--;
            result.add(consultation);
        }
        return result;
    }

    /**
     * 合成其他费用（补差价）
     *
     * @param number
     * @return
     */
    @Override
    public List<Others> doOthers(Double number) {
        List<Others> others = new ArrayList<>();
        Others item = new Others();
        item.setName("其他费用");
        item.setPrice(number);
        others.add(item);

        return others;
    }

    /**
     * 合成间接费用（由直接费用计算）
     *
     * @param number
     * @return
     */
    @Override
    public List<Indirect> doIndirect(Double number) {
        List<Indirect> indirects = new ArrayList<>();
        Indirect item = new Indirect();
        item.setName("间接费用");
        item.setPrice(number);
        indirects.add(item);

        return indirects;
    }

    /**
     * @param
     * @param number
     * @return
     */
    private List generateList(List list, Double number) {
        List<Item> results = new ArrayList<>();
        try {
            List<Item> items = (List<Item>) list;
            Item item = items.get(0);
            double sum = 0.0;

            /*
            根据不同类型的预算计算单价，简单的就是getPrice，复杂的比如差旅费就要计算每个小项
            还有会议费和咨询费一起计算
            */
            double price = item.computeUnitPrice();
            /*if(item instanceof Travel)
                price=item.getPrice()+((Travel) item).getFood()+
                        ((Travel) item).getTraffic()+((Travel) item).getAccommodation();
            else if(item instanceof Conference)
            {
                Consultation consultation = consultationDao.selectAll().get(0);
                price=item.getPrice()+((Conference) item).getExperts()*consultation.getPrice();
            }
            else if(item instanceof )
            else
                price=item.getPrice();*/


            while (sum+price < number) {
                results.add(item);
                sum += price;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (List) results;
    }
}
