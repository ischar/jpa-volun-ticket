package com.volunticket.domain.post.repository;

import com.volunticket.domain.post.entity.Post;
import com.volunticket.domain.post.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findByType(PostType type);
}
