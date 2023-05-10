package com.Kadir.recipeWebsite.Models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredient_id;
    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
    @Column(name = "ingredient", length = 255)
    private String ingredient;
}
