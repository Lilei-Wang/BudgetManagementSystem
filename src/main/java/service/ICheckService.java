package service;

import beans.Equipment;
import beans.Indirect;
import beans.Material;
import beans.Travel;

public interface ICheckService {
    boolean checkEquipment(Equipment equipment);
    boolean checkMaterial(Material material);
    boolean checkTravel(Travel travel);
    boolean checkIndirect(Indirect indirect);
}
