package com.efremov.SpringBoot.RESTSecurityApp.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LikeDTO {
    private long userId;
    private long postId;

    @JsonCreator
    public LikeDTO(long userId, long postId) {

        this.userId = userId;
        this.postId = postId;
    }
}