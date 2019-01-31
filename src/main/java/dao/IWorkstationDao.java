package dao;

import beans.Workstation;

import java.util.List;

public interface IWorkstationDao {
    void insertWorkstation(Workstation workstation);

    Workstation selectBySpec(String spec);

    void updateWorkstation(Workstation workstation);
}
