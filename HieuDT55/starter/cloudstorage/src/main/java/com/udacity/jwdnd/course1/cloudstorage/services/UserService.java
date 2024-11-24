package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public SuperUser register(SuperUser superUser) throws Exception {
        SuperUser existingUser = userMapper.findByUsername(superUser.getUsername());
        if (existingUser != null) {
            throw new Exception("Username already exists. Please choose a different username.");
        }
        String encodedPassword = passwordEncoder.encode(superUser.getPassword());
        superUser.setPassword(encodedPassword);
        superUser.setEnabled(true);
        superUser.setRole("USER");
        try {
            System.out.println(superUser);
            userMapper.insertUser(superUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return superUser;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SuperUser superUser = userMapper.findByUsername(username);
        if (superUser == null) {
            throw new UsernameNotFoundException("Username already exists. Please choose a different username.");
        }
        return superUser;
    }

    public SuperUser findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public SuperUser findUserById(int userId) {
        return userMapper.findOne(userId);
    }

    public boolean checkPassword(SuperUser user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

}