package com.Kadir.recipeWebsite.Models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe_photos")
public class RecipePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipe_photo_id;

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @Lob
    private byte[] photo;
}
