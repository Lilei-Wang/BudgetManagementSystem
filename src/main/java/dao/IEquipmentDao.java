package dao;

import beans.Equipment;

public interface IEquipmentDao {
    Equipment selectByName(String name);

    void updateEquipment(Equipment equipment);

    void insertEquipment(Equipment equipment);
}
