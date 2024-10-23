package DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LikeDTO {
    private long userId;
    private long postId;

    public LikeDTO(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
