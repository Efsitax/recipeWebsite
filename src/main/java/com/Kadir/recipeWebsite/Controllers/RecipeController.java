package com.Kadir.recipeWebsite.Controllers;

import com.Kadir.recipeWebsite.Dto.RecipeRequest;
import com.Kadir.recipeWebsite.Models.Ingredient;
import com.Kadir.recipeWebsite.Models.Recipe;
import com.Kadir.recipeWebsite.Models.RecipePhoto;
import com.Kadir.recipeWebsite.Models.User;
import com.Kadir.recipeWebsite.Repositories.IngredientRepository;
import com.Kadir.recipeWebsite.Repositories.RecipePhotoRepository;
import com.Kadir.recipeWebsite.Repositories.RecipeRepository;
import com.Kadir.recipeWebsite.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final RecipePhotoRepository recipePhotoRepository;
    private final IngredientRepository ingredientRepository;
    @PostMapping("/add")
    public ResponseEntity<?> addRecipe(@ModelAttribute RecipeRequest recipeRequest) throws IOException {

        User user = userRepository.findUserById(LoginController.UserId);
        Recipe recipe = Recipe.builder()
                .user(user)
                .title(recipeRequest.getTitle())
                .description(recipeRequest.getDescription())
                .content(recipeRequest.getContent()).build();
        Recipe saved = recipeRepository.save(recipe);
        for(String str: recipeRequest.getIngredients()){
            Ingredient ingredient = Ingredient.builder()
                    .recipe(saved)
                    .ingredient(str).build();
            ingredientRepository.save(ingredient);
        }
        for(MultipartFile file: recipeRequest.getPhotos()){
            RecipePhoto photo = RecipePhoto.builder()
                    .recipe(saved)
                    .photo(file.getBytes())
                    .build();
            recipePhotoRepository.save(photo);
        }
        return ResponseEntity.ok("Recipe saved.");
    }
}
