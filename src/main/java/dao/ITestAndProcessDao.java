package dao;

import beans.TestAndProcess;

import java.util.List;

public interface ITestAndProcessDao {
    List<TestAndProcess> selectAll();

    void insertTestAndProcess(TestAndProcess testAndProcess);

    void deleteTestAndProcess(TestAndProcess testAndProcess);
}
