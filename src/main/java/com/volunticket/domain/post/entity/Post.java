package com.volunticket.domain.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
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
    private int currentParticipants;

    @Column(nullable = false)
    private int maxParticipants; // 모집 인원

    @Column(nullable = false)
    private LocalDate recruitmentPeriod; // 모집 기간

    @Column(nullable = false)
    private LocalDate createdDate; // 작성일
}
