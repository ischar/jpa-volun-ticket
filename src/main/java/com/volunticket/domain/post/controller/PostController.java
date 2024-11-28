package com.volunticket.domain.post.controller;

import com.volunticket.domain.post.entity.PostType;
import com.volunticket.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("create")
    public ResponseEntity<String> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("user") String username,
            @RequestParam("category") PostType category,
            @RequestParam("maxParticipants") Integer maxParticipants,
            @RequestParam("recruitmentPeriod") String recruitmentPeriod,
            @RequestParam(value = "images", required = false) MultipartFile image
    ) throws IOException {
        postService.createPost(title, content, username, category, maxParticipants, recruitmentPeriod, image);
        return ResponseEntity.ok("success!");
    }
}
