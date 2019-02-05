package dao;

import beans.Equipment;

import java.util.List;

public interface IEquipmentDao {
    Equipment selectByName(String name);

    void updateEquipment(Equipment equipment);

    void insertEquipment(Equipment equipment);

    List<Equipment> selectAll();
}
