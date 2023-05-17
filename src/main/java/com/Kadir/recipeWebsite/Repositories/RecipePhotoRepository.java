package com.Kadir.recipeWebsite.Repositories;

import com.Kadir.recipeWebsite.Models.RecipePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipePhotoRepository extends JpaRepository<RecipePhoto,Long> {
}
