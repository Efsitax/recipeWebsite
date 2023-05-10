package com.Kadir.recipeWebsite.Repositories;

import com.Kadir.recipeWebsite.Models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> findByUserUserId(Long id);
}
