package dao;

import beans.Material;

import java.util.List;

public interface IMaterialDao {
    List<Material> selectAll();
}
