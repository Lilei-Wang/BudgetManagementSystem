package dao;

import beans.Conference;

import java.util.List;

public interface IConferenceDao {
    List<Conference> selectAll();
    void insertConference(Conference conference);
    void updateConference(Conference conference);
    void deleteConference(Conference conference);
}
