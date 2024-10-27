package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean isUsernameAvailable(String username) {
        // Logic kiểm tra username
        return true;
    }

    public int createUser(User user) {
        // Logic tạo user
        return 1;
    }
}