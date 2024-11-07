
package com.udacity.jwdnd.course1.cloudstorage.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    // Sử dụng Constructor Injection và thêm @Lazy vào PasswordEncoder
    @Autowired
    public UserService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Các phương thức sử dụng `passwordEncoder`
}