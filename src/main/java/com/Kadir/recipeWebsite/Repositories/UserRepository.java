package com.Kadir.recipeWebsite.Repositories;

import com.Kadir.recipeWebsite.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findUserById(Long id);
    User findByUsername(String username);
}
