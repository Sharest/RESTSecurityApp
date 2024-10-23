package com.efremov.SpringBoot.RESTSecurityApp.controllers;

import com.efremov.SpringBoot.RESTSecurityApp.DTO.LikeDTO;
import com.efremov.SpringBoot.RESTSecurityApp.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public void addLike(@RequestBody long postId) {
        likeService.addLike(postId);
    }
}
