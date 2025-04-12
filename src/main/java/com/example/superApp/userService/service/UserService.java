package com.example.superApp.userService.service;



import com.example.superApp.userService.entity.User;
import com.example.superApp.userService.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final IUserRepository repo;

    public UserService(IUserRepository repo) {
        this.repo = repo;
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}