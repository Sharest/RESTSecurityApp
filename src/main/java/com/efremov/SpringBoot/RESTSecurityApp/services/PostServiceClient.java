package com.efremov.SpringBoot.RESTSecurityApp.services;

import com.efremov.SpringBoot.RESTSecurityApp.util.UserErrorPermission;
import grpc.PostServiceProto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class PostServiceClient {
    private final grpc.PostServerGrpc.PostServerBlockingStub postServerBlockingStub;
    private final UserService userService;


    public PostServiceClient(UserService userService) {
        this.userService = userService;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

        postServerBlockingStub = grpc.PostServerGrpc.newBlockingStub(channel);
    }

    public PostResponse createPost(String title, String content) {
        Long myId = userService.getMe().getId();
        CreatePostRequest request = CreatePostRequest.newBuilder()
                .setTitle(title)
                .setContent(content)
                .setIdAuthor(myId)
                .build();
        return postServerBlockingStub.createPost(request);
    }

    public PostResponse getByIdPost(long id) {
        GetByIdPostRequest request = GetByIdPostRequest.newBuilder()
                .setId(id)
                .build();
        return postServerBlockingStub.getByIdPost(request);
    }


    public GetAllPostResponse getAllPost(long startId, long endId) {
        GetAllPostRequest request = GetAllPostRequest.newBuilder()
                .setStartId(startId)
                .setEndId(endId)
                .build();
        return postServerBlockingStub.getAllPost(request);
    }

    public PostResponse updatePost(long id, String title, String content) throws UserErrorPermission {
        Long myId = userService.getMe().getId();
        if (id != myId) {
            throw new UserErrorPermission("You don't have permission");
        } else {
            UpdatePostRequest request = UpdatePostRequest.newBuilder()
                    .setId(id)
                    .setTitle(title)
                    .setContent(content)
                    .build();
            return postServerBlockingStub.updatePost(request);
        }
    }


    public PostResponse deletePost(long id) throws UserErrorPermission {
        Long myId = userService.getMe().getId();
        if (id != myId) {
            throw new UserErrorPermission("You don't have permission");

        } else {
            DeletePostRequest request = DeletePostRequest.newBuilder()
                    .setId(id)
                    .build();
            return postServerBlockingStub.deletePost(request);
        }
    }

}
