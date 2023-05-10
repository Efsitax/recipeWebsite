package com.Kadir.recipeWebsite.Repositories;

import com.Kadir.recipeWebsite.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
