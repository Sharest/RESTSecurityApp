package com.efremov.SpringBoot.RESTSecurityApp.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;
    private Long idAuthor;

}
