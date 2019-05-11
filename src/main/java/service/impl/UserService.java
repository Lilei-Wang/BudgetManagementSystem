package service.impl;

import beans.User;
import dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IUserService;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public boolean checkUser(User user) {
        if(user==null) return false;
        User exist=userDao.selectUserByName(user.getName());
        if(exist==null || !exist.getPassword().equals(user.getPassword())) return false;
        user.setId(exist.getId());
        return true;
    }

    @Override
    public boolean existUser(User user) {
        User exist = userDao.selectUserByName(user.getName());
        return exist!=null;
    }

    @Override
    public void addUser(User user) {
        if(user==null) return ;
        userDao.insertUser(user);
    }
}
