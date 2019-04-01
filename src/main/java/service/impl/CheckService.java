package service.impl;

import beans.*;
import org.springframework.stereotype.Service;
import service.ICheckService;
@Service
public class CheckService implements ICheckService {
    @Override
    public boolean checkEquipment(Equipment equipment) {
        if(equipment==null
                || equipment.getName()==null) return false;
        return true;
    }

    @Override
    public boolean checkMaterial(Material material) {
        return true;
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
}
