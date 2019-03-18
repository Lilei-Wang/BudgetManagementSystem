package dao;

import beans.Property;

import java.util.List;

public interface IPropertyDao {
    List<Property> selectAll();
    void insertProperty(Property property);
    void updateProperty(Property property);
    void deleteProperty(Property property);
}
