package dao;

import beans.Power;

import java.util.List;

public interface IPowerDao {
    List<Power> selectAll();

    void insertPower(Power power);

    void deletePower(Power power);
}
