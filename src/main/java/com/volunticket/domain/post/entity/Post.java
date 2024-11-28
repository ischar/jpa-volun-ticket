package com.volunticket.domain.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder=true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostType type;

    @Column(nullable = false)
    private String title;

    private String image;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String author;

    @Column(nullable = false)
    private Integer currentParticipants;

    @Column(nullable = false)
    private Integer maxParticipants; // 모집 인원

    @Column(nullable = true)
    private LocalDate recruitmentPeriod; // 모집 기간

    @CreatedDate // Spring Data JPA에서 자동으로 생성 시간 설정
    private LocalDate createdDate; // 작성일
}
