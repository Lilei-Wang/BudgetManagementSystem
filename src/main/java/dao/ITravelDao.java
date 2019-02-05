package dao;

import beans.Travel;

import java.util.List;

public interface ITravelDao {
    List<Travel> selectAll();
}
