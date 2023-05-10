package com.Kadir.recipeWebsite.Models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_role_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @EmbeddedId
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @EmbeddedId
    private Role role;
}
