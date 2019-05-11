package service;

import beans.User;

public interface IUserService {

    boolean checkUser(User user);

    boolean existUser(User user);

    void addUser(User user);
}
