package dao;

import beans.Equipment;

import java.util.List;

public interface IEquipmentDao {
    Equipment selectByName(String name);

    void updateEquipment(Equipment equipment);

    void insertEquipment(Equipment equipment);

    void deleteEquipment(Equipment equipment);

    List<Equipment> selectAll();

    Equipment selectById(Integer id);
}
