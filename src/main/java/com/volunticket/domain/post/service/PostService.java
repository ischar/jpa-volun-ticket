package com.volunticket.domain.post.service;

import com.volunticket.domain.post.entity.Post;
import com.volunticket.domain.post.entity.PostType;
import com.volunticket.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final S3Service s3Service;

    @Transactional
    public void createPost(String title, String content, String email, PostType type, MultipartFile image) throws IOException {

        String imageUrl = null;

        if (image != null && !image.isEmpty()) {
            imageUrl = s3Service.uploadFile(image);
        }

        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(email)
                .type(type)
                .image(imageUrl)
                .build();

        postRepository.save(post);
    }

}
