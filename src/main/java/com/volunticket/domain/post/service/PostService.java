package com.volunticket.domain.post.service;

import com.volunticket.domain.post.entity.Post;
import com.volunticket.domain.post.entity.PostType;
import com.volunticket.domain.post.repository.PostRepository;
import com.volunticket.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final S3Service s3Service;
    private final DateUtil dateUtil;

    @Transactional
    public void createPost(String title, String content, String email, PostType type, Integer maxParticipants, String recruitmentPeriod, MultipartFile image) throws IOException {

        String imageUrl = null;

        if (image != null && !image.isEmpty()) {
            imageUrl = s3Service.uploadFile(image);
        }

        LocalDate period = dateUtil.convertDate(recruitmentPeriod);
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(email)
                .type(type)
                .image(imageUrl)
                .maxParticipants(maxParticipants)
                .recruitmentPeriod(period)
                .currentParticipants(0)
                .build();

        postRepository.save(post);
    }

    public Optional<List<Post>> getPosts(String type, String email) {
        PostType postType = PostType.valueOf(type.toUpperCase());
        Optional<List<Post>> postList = postRepository.findByTypeAndAuthor(postType, email);
        return postList;
    }
}
