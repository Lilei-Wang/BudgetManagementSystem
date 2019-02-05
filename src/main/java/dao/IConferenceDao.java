package dao;

import beans.Conference;

import java.util.List;

public interface IConferenceDao {
    List<Conference> selectAll();
}
