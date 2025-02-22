package com.StdMngmnt.service;

import com.StdMngmnt.entity.User;
import com.StdMngmnt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;  // ✅ Inject BCryptPasswordEncoder

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ Hash password before saving
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
