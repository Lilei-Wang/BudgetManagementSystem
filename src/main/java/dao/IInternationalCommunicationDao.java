package dao;

import beans.InternationalCommunication;

import java.util.List;

public interface IInternationalCommunicationDao {
    List<InternationalCommunication> selectAll();
}
