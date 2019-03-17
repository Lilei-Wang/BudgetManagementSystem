package service.impl;

import beans.*;
import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IBudgetService;

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
    public Map<Equipment, Integer> doEquipment(Double number) {
        List<Equipment> equipments = equipmentDao.selectAll();
        return generateMap((List) equipments, number);
    }

    /**
     * 合成材料费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Material, Integer> doMaterial(Double number) {
        List<Material> materials = materialDao.selectAll();
        return generateMap((List) materials, number);
    }

    /**
     * 合成测试化验加工费
     *
     * @param number
     * @return
     */
    @Override
    public Map<TestAndProcess, Integer> doTestAndProcess(Double number) {
        List<TestAndProcess> testAndProcesses = testAndProcessDao.selectAll();
        return generateMap((List) testAndProcesses, number);
    }

    /**
     * 合成燃料动力费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Power, Integer> doPower(Double number) {
        List<Power> powers = powerDao.selectAll();
        return generateMap((List) powers, number);
    }

    /**
     * 合成差旅费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Travel, Integer> doTravel(Double number) {
        List<Travel> travels = travelDao.selectAll();
        return generateMap((List) travels, number);
    }

    /**
     * 合成会议费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Conference, Integer> doConference(Double number) {
        List<Conference> conferences = conferenceDao.selectAll();
        return generateMap((List) conferences, number);
    }

    /**
     * 合成国际交流合作费
     *
     * @param number
     * @return
     */
    @Override
    public Map<InternationalCommunication, Integer> doInternationalCommunication(Double number) {
        List<InternationalCommunication> internationalCommunications = internationalCommunicationDao.selectAll();
        return generateMap((List) internationalCommunications, number);
    }

    /**
     * 合成知识产权费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Property, Integer> doProperty(Double number) {
        List<Property> properties = propertyDao.selectAll();
        return generateMap((List) properties, number);
    }

    /**
     * 合成劳务费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Labour, Integer> doLabour(Double number) {
        List<Labour> labour = labourDao.selectAll();
        return generateMap((List) labour, number);
    }

    /**
     * 合成咨询费
     *
     * @param number
     * @return
     */
    @Override
    @Deprecated
    public Map<Consultation, Integer> doConsultation(Double number) {
        List<Consultation> consultations = consultationDao.selectAll();
        return generateMap((List) consultations, number);
    }

    /**
     * 根据会议费合成咨询费
     *
     * @param conferences
     * @return
     */
    @Override
    public Map<Consultation, Integer> doConsultation(Map<Conference, Integer> conferences) {
        List<Consultation> consultations = consultationDao.selectAll();
        Consultation consultation = null;
        try {
            consultation = consultations.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int experts = 0;
        for (Conference conference : conferences.keySet()) {
            experts += conference.getExperts() * conferences.get(conference);
        }
        Map<Consultation, Integer> result = new HashMap<>();
        result.put(consultation, experts);
        return result;
    }

    /**
     * 合成其他费用（补差价）
     *
     * @param number
     * @return
     */
    @Override
    public Map<Others, Integer> doOthers(Double number) {
        Map<Others, Integer> others = new HashMap<>();
        Others item = new Others();
        item.setName("其他费用");
        item.setPrice(number);
        others.put(item, 1);

        return others;
    }

    /**
     * 合成间接费用（由直接费用计算）
     *
     * @param number
     * @return
     */
    @Override
    public Map<Indirect, Integer> doIndirect(Double number) {
        Map<Indirect, Integer> indirects = new HashMap<>();
        Indirect item = new Indirect();
        item.setName("间接费用");
        item.setPrice(number);
        indirects.put(item, 1);

        return indirects;
    }

    /**
     * 刷新Bedget对象，与数据库最新数据一致，包括条目的增删改
     *
     * @param budget
     */
    @Override
    public void refreshBudget(Budget budget) {
        ;
    }

    /**
     * @param
     * @param number
     * @return
     */
    private Map generateMap(List list, Double number) {
        Map<Item, Integer> results = new HashMap<>();
        if (list != null && list.size() != 0) {
            try {
                List<Item> items = (List<Item>) list;
                for (Item item : items) {
                    results.put(item, 0);
                }
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

                int nums = 0;
                while (sum + price <= number) {
                    nums++;
                    sum += price;
                }
                results.put(item, nums);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (Map) results;
    }
}
