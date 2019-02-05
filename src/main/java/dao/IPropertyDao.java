package dao;

import beans.Property;

import java.util.List;

public interface IPropertyDao {
    List<Property> selectAll();
}
