package dao;

import beans.Consultation;

import java.util.List;

public interface IConsultationDao {
    List<Consultation> selectAll();
}
