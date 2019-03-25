package dao;

import beans.Travel;

import java.util.List;

public interface ITravelDao {
    List<Travel> selectAll();

    Travel selectById(Integer id);

    void insertTravel(Travel travel);

    void deleteTravel(Travel travel);

    void updateTravel(Travel travel);
}
