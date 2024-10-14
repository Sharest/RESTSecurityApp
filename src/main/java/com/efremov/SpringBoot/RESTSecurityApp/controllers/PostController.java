package com.efremov.SpringBoot.RESTSecurityApp.controllers;

import com.efremov.SpringBoot.RESTSecurityApp.services.PostServiceClient;
import com.efremov.SpringBoot.RESTSecurityApp.util.UserErrorPermission;
import grpc.PostServiceProto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostServiceClient postServiceClient;

    @Autowired
    public PostController(PostServiceClient postServiceClient) {
        this.postServiceClient = postServiceClient;
    }
    @PostMapping
    public PostResponse createPost(@RequestBody PostResponse request) {
        return postServiceClient.createPost(request.getTitle(), request.getContent());
    }
    @GetMapping("/{id}")
    public PostResponse getByIdPost(@PathVariable long id) {
        return postServiceClient.getByIdPost(id);
    }
    @GetMapping
    public GetAllPostResponse getAllPost(@RequestParam long startId, @RequestParam long endId) {
        return postServiceClient.getAllPost(startId, endId);
    }
    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable long id, @RequestBody PostResponse request) throws UserErrorPermission {
        return postServiceClient.updatePost(id, request.getTitle(), request.getContent());
    }
    @DeleteMapping("/{id}")
    public PostResponse deletePost(@PathVariable long id) throws UserErrorPermission {
        return postServiceClient.deletePost(id);
    }

}