package dao;

import beans.Labour;

import java.util.List;

public interface ILabourDao {
    List<Labour> selectAll();
}
