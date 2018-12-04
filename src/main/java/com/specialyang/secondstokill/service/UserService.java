package com.specialyang.secondstokill.service;

import com.specialyang.secondstokill.dao.UserDAO;
import com.specialyang.secondstokill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Fan Yang in 2018/12/4 1:59 PM.
 */
@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

//    @Transactional
    public boolean tx() {
        User u1 = new User();
        u1.setId((long) 2);
        u1.setUsername("haha");
        userDAO.insert(u1);

        User u2 = new User();
        u2.setId((long) 1);
        u2.setUsername("11111");
        userDAO.insert(u2);

        return true;
    }
}
