package service.impl;

import beans.*;
import org.springframework.stereotype.Service;
import service.ICheckService;

@Service
public class CheckService implements ICheckService {
    @Override
    public int checkEquipment(Equipment equipment) {
        if (equipment == null || equipment.getName() == null) {
            return 101;
        }
        if(equipment.computeUnitPrice()>=100000){
            return 102;
        }
        return isValid;
    }

    @Override
    public int checkMaterial(Material material) {
        if (material == null || material.getName() == null) {
            return 201;
        }
        /*if (material.computeUnitPrice() >= 10000) {
            return 202;
        }*/
        return isValid;
    }

    @Override
    public boolean checkTravel(Travel travel) {
        return true;
    }

    @Override
    public boolean checkIndirect(Indirect indirect) {
        return true;
    }

    @Override
    public boolean checkProperty(Property property) {
        return true;
    }

    @Override
    public String getMessage(int status) {
        switch (status){
            case 101:
                return "不能为空";
            case 102:
                return "设备费需要小于100000";
            case 201:
                return "不能为空";
            case 202:
                return "材料费需要小于1000";
        }
        return "";
    }
}
