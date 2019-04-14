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
        User exist=userDao.selectUserByName(user.getName());
        if(exist==null) return false;
        user.setId(exist.getId());
        return true;
    }
}
