package com.efremov.SpringBoot.RESTSecurityApp.services;

import com.efremov.SpringBoot.RESTSecurityApp.DTO.LikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final KafkaTemplate<String, LikeDTO> kafkaTemplate;
    private final UserService userService;

    @Autowired
    public LikeService(KafkaTemplate<String, LikeDTO> kafkaTemplate, UserService userService) {
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
    }

    public void addLike(long postId) {
        long userId = getUserId(userService.getMe().getId());
        LikeDTO like = new LikeDTO(userId, postId);
        kafkaTemplate.send("likeTopic", "like", like);
    }

    private long getUserId(Long userId) {
        LikeDTO like = new LikeDTO();
        like.setUserId(userId);
        return like.getUserId();
    }
}
