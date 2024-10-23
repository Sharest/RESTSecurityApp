package com.efremov.SpringBoot.RESTSecurityApp.services;

import com.efremov.SpringBoot.RESTSecurityApp.DTO.ViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewService {
    private final KafkaTemplate<String, ViewDTO> kafkaTemplate;
    private final UserService userService;

    @Autowired
    public ViewService(KafkaTemplate<String, ViewDTO> kafkaTemplate, UserService userService) {
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
    }


    public void addView(long postId) {
        long userId = getUserId(userService.getMe().getId());
        ViewDTO view = new ViewDTO(userId, postId);

        kafkaTemplate.send("viewTopic", "view", view);
    }

    private long getUserId(Long userId) {
        ViewDTO like = new ViewDTO();
        like.setUserId(userId);
        return like.getUserId();
    }
}
