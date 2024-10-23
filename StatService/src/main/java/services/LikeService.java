package services;

import DTO.LikeDTO;
import jakarta.transaction.Transactional;
import models.Like;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import repositories.LikeRepository;

@Component
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @KafkaListener(topics = "likeTopic")
    public void addLike(LikeDTO likeDTO) {
        likeRepository.save(convertLikeDTOToLike(likeDTO));
    }

    private Like convertLikeDTOToLike(LikeDTO likeDTO) {
        Like like = new Like();
        like.setUserId(likeDTO.getUserId());
        like.setPostId(likeDTO.getPostId());
        return like;
    }
}
