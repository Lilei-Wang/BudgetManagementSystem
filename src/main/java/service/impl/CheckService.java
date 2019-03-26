package service.impl;

import beans.Equipment;
import beans.Indirect;
import beans.Material;
import beans.Travel;
import org.springframework.stereotype.Service;
import service.ICheckService;
@Service
public class CheckService implements ICheckService {
    @Override
    public boolean checkEquipment(Equipment equipment) {
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
}
