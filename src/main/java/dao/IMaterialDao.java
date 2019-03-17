package dao;

import beans.Material;

import java.util.List;

public interface IMaterialDao {
    List<Material> selectAll();
    void insertMaterial(Material material);
    void deleteMaterial(Material material);
    void updateMaterial(Material material);
}
