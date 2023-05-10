package com.Kadir.recipeWebsite.Services;

import com.Kadir.recipeWebsite.Models.User;
import com.Kadir.recipeWebsite.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService implements IBaseService<User>{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User add(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById() {
        return null;
    }

    @Override
    public User update(User entity, Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
