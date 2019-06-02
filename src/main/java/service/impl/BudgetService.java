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
        Map<Equipment, Integer> map = new HashMap<>();
        List<Equipment> equipments = equipmentDao.selectAll();
        if (equipments != null && equipments.size() > 0) {
            equipments.sort((o1, o2) -> {
                double diff = o1.getPrice() - o2.getPrice();
                return (int) diff;
            });
            Equipment equipment = null;
            for (Equipment item : equipments) {
                if(item.computeUnitPrice()!=0)
                    equipment=item;
            }
            if(equipment==null) return map;
            double sum = 0.0;
            int i = 0;
            while (sum < number) {
                sum += equipment.getPrice();
                i++;
            }
            map.put(equipment, i);
        }
        return map;
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
        Map<Material,Integer> result=new HashMap<>();
        if(materials!=null && materials.size()>0){
            Material material = null;
            for (Material item : materials) {
                if(item.computeUnitPrice()>0)
                    material=item;
            }
            if(material==null) return result;
            int i=0;
            while (i*material.getPrice()<number){
                i++;
            }
            result.put(material,i);
        }
        return result;
    }

    /**
     * 合成测试化验加工费
     *
     * @param number
     * @return
     */
    @Override
    public Map<TestAndProcess, Integer> doTestAndProcess(Double number) {
        Map<TestAndProcess,Integer> result=new HashMap<>();
        TestAndProcess testAndProcess = new TestAndProcess();
        testAndProcess.setId(0);
        testAndProcess.setPrice(number);
        testAndProcess.setName("测试化验加工费");
        result.put(testAndProcess,1);
        return result;
    }

    /**
     * 合成燃料动力费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Power, Integer> doPower(Double number) {
        Map<Power,Integer> result=new HashMap<>();
        Power power = new Power();
        power.setId(1);
        power.setPrice(number);
        power.setName("燃料动力费");
        result.put(power,1);
        return result;
    }

    /**
     * 合成差旅费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Travel, Integer> doTravel(Double number) {
        Map<Travel, Integer> result = new HashMap<>();
        List<Travel> travels = travelDao.selectAll();
        if (travels != null && travels.size() != 0) {
            Travel travel = null;
            for (Travel item : travels) {
                item.setPeople(1);
                item.setDays(1);
                if(item.computeUnitPrice()>0)
                    travel=item;
            }
            if(travel==null) return result;
            int i=1;
            travel.setDays(1);
            travel.setPeople(1);
            while(travel.computeUnitPrice()<number){
                i++;
                travel.setPeople(i);
            }
            result.put(travel,1);

        }
        return result;
    }

    /////////////////////////////////////////////////////////待解决

    /**
     * 合成会议费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Conference, Integer> doConference(Double number) {
        Map<Conference, Integer> map = new HashMap<>();
        List<Conference> conferences = conferenceDao.selectAll();
        if(conferences!=null && conferences.size()>0){
            Conference conference = null;
            for (Conference item : conferences) {
                if(item.getPrice()>0)
                    conference=item;
            }
            if(conference==null) return map;
            conference.setDays(1);
            conference.setPeople(1);
            int i=1;
            while(conference.computeUnitPrice()<number){
                conference.setPeople(++i);
            }
            map.put(conference,1);
        }
        return map;
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
        Map<InternationalCommunication,Integer> result=new HashMap<>();
        if(internationalCommunications==null || internationalCommunications.size()==0) return result;
        InternationalCommunication internationalCommunication = null;
        for (InternationalCommunication item : internationalCommunications) {
            item.setDays(1);
            item.setPeople(1);
            if(item.computeUnitPrice()>0)
                internationalCommunication=item;
        }
        if(internationalCommunication==null) return result;
        internationalCommunication.setDays(1);
        internationalCommunication.setPeople(1);
        while(internationalCommunication.computeUnitPrice()<number){
            internationalCommunication.setDays(internationalCommunication.getDays()+1);
        }
        result.put(internationalCommunication,1);
        return result;
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
        Map<Property,Integer> result=new HashMap<>();
        if(properties!=null && properties.size()>0){
            Property property =null;
            for (Property item : properties) {
                if(item.getPrice()>0)
                    property=item;
            }
            if(property==null) return result;
            int i=0;
            while(i*property.getPrice()<number){
                i++;
            }
            result.put(property,i);
        }
        return result;
    }

    /**
     * 合成劳务费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Labour, Integer> doLabour(Double number) {
        List<Labour> list = labourDao.selectAll();
        Map<Labour, Integer> result = new HashMap<>();
        try {
            Labour labour = null;
            for (Labour item : list) {
                if(item.getPrice()>0)
                    labour=item;
            }
            if(labour==null) return result;
            labour.setPeople(1);
            labour.setMonths(1);
            while (labour.computeUnitPrice() < number) {
                labour.setMonths(labour.getMonths() + 1);
            }
            result.put(labour, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 合成咨询费
     *
     * @param number
     * @return
     */
    @Override
    public Map<Consultation, Integer> doConsultation(Double number) {
        List<Consultation> consultations = consultationDao.selectAll();
        Map<Consultation, Integer> result = new HashMap<>();
        if(consultations==null || consultations.size()==0) return result;
        try {
            Consultation consultation = null;

            //取一个价格非0的元素，避免死循环
            for (Consultation item : consultations) {
                if (item.getPrice() > 0) {
                    consultation = item;
                    break;
                }
            }
            if(consultation==null) return result;

            result.put(consultation,0);
            double sum=0.0;
            while(sum<number){
                int i=result.get(consultation);
                result.put(consultation,++i);
                sum+=consultation.getPrice();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    @Override
    public Map<Consultation, Integer> doConsultation(int n) {
        List<Consultation> consultations = consultationDao.selectAll();
        Map<Consultation, Integer> map = new HashMap<>();
        if (consultations != null && consultations.size() > 0) {
            Consultation consultation = consultations.get(0);
            map.put(consultation, n);
        }
        return map;
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
        item.setId(0);
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
