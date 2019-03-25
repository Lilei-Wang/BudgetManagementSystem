package dao;

import beans.InternationalCommunication;

import java.util.List;

public interface IInternationalCommunicationDao {
    List<InternationalCommunication> selectAll();
    void insertInternational(InternationalCommunication internationalCommunication);
    void updateInternational(InternationalCommunication internationalCommunication);
    void deleteInternational(InternationalCommunication internationalCommunication);

    InternationalCommunication selectById(Integer id);
}
