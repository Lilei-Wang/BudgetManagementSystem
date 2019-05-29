package dao;

import beans.Others;

import java.util.List;

public interface IOthersDao {
    List<Others> selectAll();

    void insertOthers(Others others);

    void deleteOthers(Others others);
}
