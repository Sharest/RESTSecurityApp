package com.efremov.SpringBoot.PostService.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Long idauthor;

    public Post(String title, String content, Long idauthor) {
        this.title = title;
        this.content = content;
        this.idauthor = idauthor;
    }
}
