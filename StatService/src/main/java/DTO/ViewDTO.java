package DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ViewDTO {
    private long userId;
    private long postId;

    public ViewDTO(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
