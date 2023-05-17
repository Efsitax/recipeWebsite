package com.Kadir.recipeWebsite.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest {
    private String title;
    private String description;
    private String content;
    private MultipartFile[] photos;
    private String[] ingredients;
}
