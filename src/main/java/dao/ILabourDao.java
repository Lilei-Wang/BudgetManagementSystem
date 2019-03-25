package dao;

import beans.Labour;

import java.util.List;

public interface ILabourDao {
    List<Labour> selectAll();
    void insertLabour(Labour labour);
    void updateLabour(Labour labour);
    void deleteLabour(Labour labour);

    Labour selectById(Integer id);
}
