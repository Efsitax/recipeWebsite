package com.Kadir.recipeWebsite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe_photos")
public class RecipePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_photo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @Lob
    private byte[] photo;
}
