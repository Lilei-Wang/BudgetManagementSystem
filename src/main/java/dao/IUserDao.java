package dao;

import beans.User;

public interface IUserDao {
    User selectUserByName(String name);
}
