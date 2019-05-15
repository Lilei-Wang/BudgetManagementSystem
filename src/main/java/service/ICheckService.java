package service;

import beans.*;

public interface ICheckService {
    final int isValid=0;
    int checkEquipment(Equipment equipment);
    int checkMaterial(Material material);
    boolean checkTravel(Travel travel);
    boolean checkIndirect(Indirect indirect);

    boolean checkProperty(Property property);

    String getMessage(int status);

}
