package service;

import beans.*;

public interface ICheckService {
    boolean checkEquipment(Equipment equipment);
    boolean checkMaterial(Material material);
    boolean checkTravel(Travel travel);
    boolean checkIndirect(Indirect indirect);

    boolean checkProperty(Property property);
}
