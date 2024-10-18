package com.efremov.SpringBoot.RESTSecurityApp.controllers;

import com.efremov.SpringBoot.RESTSecurityApp.DTO.PostResponseDTO;
import com.efremov.SpringBoot.RESTSecurityApp.services.PostServiceClient;
import com.efremov.SpringBoot.RESTSecurityApp.util.UserErrorPermission;
import grpc.PostServiceProto.GetAllPostResponse;
import grpc.PostServiceProto.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostServiceClient postServiceClient;

    @Autowired
    public PostController(PostServiceClient postServiceClient) {
        this.postServiceClient = postServiceClient;
    }

    @PostMapping
    public PostResponseDTO createPost(@RequestParam String title, @RequestParam String content) {
        return convertToPostDTO(postServiceClient.createPost(title, content));
    }

    @GetMapping("/{id}")
    public PostResponseDTO getByIdPost(@PathVariable long id) {
        return convertToPostDTO(postServiceClient.getByIdPost(id));
    }

    @GetMapping
    public List<PostResponseDTO> getAllPost(@RequestParam long startId, @RequestParam long endId) {
        return convertToAllPostDTO(postServiceClient.getAllPost(startId, endId));
    }

    @PutMapping("/{id}")
    public PostResponseDTO updatePost(@PathVariable long id, @RequestBody PostResponseDTO request) throws UserErrorPermission {
        return convertToPostDTO(postServiceClient.updatePost(id, request.getTitle(), request.getContent()));
    }

    @DeleteMapping("/{id}")
    public PostResponseDTO deletePost(@PathVariable long id) throws UserErrorPermission {
        return convertToPostDTO(postServiceClient.deletePost(id));
    }


    private PostResponseDTO convertToPostDTO(PostResponse post) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(post.getId());
        postResponseDTO.setTitle(post.getTitle());
        postResponseDTO.setContent(post.getContent());
        postResponseDTO.setIdAuthor(post.getIdAuthor());
        return postResponseDTO;
    }

    private List<PostResponseDTO> convertToAllPostDTO(GetAllPostResponse posts) {
        List<PostResponseDTO> postResponseDTOList = new ArrayList<>();
        for (PostResponse post : posts.getPostsList()) {
            PostResponseDTO postResponseDTO = convertToPostDTO(post);
            postResponseDTOList.add(postResponseDTO);
        }
        return postResponseDTOList;
    }

}