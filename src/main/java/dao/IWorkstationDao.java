package dao;

import beans.Workstation;

import java.util.List;
@Deprecated
public interface IWorkstationDao {
    void insertWorkstation(Workstation workstation);

    Workstation selectBySpec(String spec);

    void updateWorkstation(Workstation workstation);
}
