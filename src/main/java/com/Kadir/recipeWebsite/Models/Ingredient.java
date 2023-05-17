package com.Kadir.recipeWebsite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ingredient_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
    @Column(name = "ingredient", length = 255)
    private String ingredient;
}
