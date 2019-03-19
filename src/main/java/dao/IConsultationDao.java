package dao;

import beans.Consultation;

import java.util.List;

public interface IConsultationDao {
    List<Consultation> selectAll();
    void insertConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
}
