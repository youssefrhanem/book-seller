package com.rhanem.bookseller.service;


import com.rhanem.bookseller.model.Role;
import com.rhanem.bookseller.model.User;
import com.rhanem.bookseller.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IUserServiceImpl implements IUserService{

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional // Transactional Required when executing update - delete query
    public void makeAdmin(String username) {
        userRepository.updateUserRole(username,Role.ADMIN);
    }
}
