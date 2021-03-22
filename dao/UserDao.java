package com.zr0817.news0817.dao;

import com.zr0817.news0817.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
