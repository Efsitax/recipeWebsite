package com.Kadir.recipeWebsite.Models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipe_id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name = "title", length = 255)
    private String title;
    @Lob
    @Column(name = "description", columnDefinition = "CLOB")
    private String description;
    @Lob
    @Column(name = "content", columnDefinition = "CLOB")
    private String content;

    @Temporal(TemporalType.DATE)
    private Date date;

}