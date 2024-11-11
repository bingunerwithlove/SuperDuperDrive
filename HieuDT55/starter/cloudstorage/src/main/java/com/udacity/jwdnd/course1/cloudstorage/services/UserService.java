package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public SuperUser register(SuperUser superUser) throws Exception {
        String encodedPassword = passwordEncoder.encode(superUser.getPassword());
        superUser.setPassword(encodedPassword);
        superUser.setEnabled(true);
        superUser.setRole("USER");
        try {
            userMapper.insertUser(superUser);
        } catch (Exception e) {
            throw e;
        }
        return superUser;
    }

}