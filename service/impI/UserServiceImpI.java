package com.zr0817.news0817.service.impI;


import com.zr0817.news0817.bean.User;
import com.zr0817.news0817.dao.UserDao;
import com.zr0817.news0817.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpI implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUsers(String username,String password){
        return userDao.findByUsernameAndPassword(username,password);
    }
}
