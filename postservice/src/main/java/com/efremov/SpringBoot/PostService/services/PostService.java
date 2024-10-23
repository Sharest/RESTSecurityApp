package com.efremov.SpringBoot.PostService.services;

import com.efremov.SpringBoot.PostService.models.Post;
import com.efremov.SpringBoot.PostService.repositories.PostRepository;
import grpc.PostServerGrpc;
import grpc.PostServiceProto.*;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@GrpcService
@Transactional
public class PostService extends PostServerGrpc.PostServerImplBase {

    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(CreatePostRequest request, StreamObserver<PostResponse> responseObserver) {
        Long id = postRepository.save(new Post(request.getTitle(), request.getContent(), request.getIdAuthor())).getId();

        PostResponse post = PostResponse.newBuilder().setId(id).setTitle(request.getTitle()).setContent(request.getContent()).setIdAuthor(request.getIdAuthor()).build();
        responseObserver.onNext(post);
        responseObserver.onCompleted();
    }

    @Override
    public void getByIdPost(GetByIdPostRequest request, StreamObserver<PostResponse> responseObserver) {
        Optional<Post> post = postRepository.findById(request.getId());

        PostResponse postR = PostResponse.newBuilder().setId(post.get().getId()).setTitle(post.get().getTitle()).setContent(post.get().getContent()).setIdAuthor(post.get().getIdauthor()).build();
        responseObserver.onNext(postR);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllPost(GetAllPostRequest request, StreamObserver<GetAllPostResponse> responseObserver) {
        long startId = request.getStartId();
        long endId = request.getEndId();
        List<Post> posts = postRepository.findByIdBetween(startId, endId);

        GetAllPostResponse.Builder getAllPostResponseBuilder = GetAllPostResponse.newBuilder();
        for(Post post : posts) {
            getAllPostResponseBuilder.addPosts(PostResponse.newBuilder()
                    .setId(post.getId())
                    .setTitle(post.getTitle())
                    .setContent(post.getContent())
                    .setIdAuthor(post.getIdauthor())
                    .build()
            );
        }
        responseObserver.onNext(getAllPostResponseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void updatePost(UpdatePostRequest request, StreamObserver<PostResponse> responseObserver) {
        Long idAuthor = postRepository.findById(request.getId()).get().getIdauthor();
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setIdauthor(idAuthor);
        post.setId(request.getId());
        postRepository.save(post);

        PostResponse postR = PostResponse.newBuilder().setId(request.getId()).setTitle(request.getTitle()).setContent(request.getContent()).setIdAuthor(idAuthor).build();
        responseObserver.onNext(postR);
        responseObserver.onCompleted();
    }

    @Override
    public void deletePost(DeletePostRequest request, StreamObserver<PostResponse> responseObserver) {
        Optional<Post> post = postRepository.findById(request.getId());
        postRepository.deleteById(post.orElseThrow().getId());

        PostResponse postR = PostResponse.newBuilder().setId(post.get().getId()).setTitle(post.get().getTitle()).setContent(post.get().getContent()).setIdAuthor(post.get().getIdauthor()).build();
        responseObserver.onNext(postR);
        responseObserver.onCompleted();

    }
}
