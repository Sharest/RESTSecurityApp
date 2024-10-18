package com.efremov.SpringBoot.PostService.repositories;

import com.efremov.SpringBoot.PostService.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByIdBetween(Long startId, Long endId);
}
