package com.Kadir.recipeWebsite.Repositories;

import com.Kadir.recipeWebsite.Models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
}
