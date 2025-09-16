package com.nhphuong.postservice.controller;

import com.nhphuong.postservice.repository.PostRepository;
import com.nhphuong.postservice.service.interfaces.PostService;
import com.nhphuong.postservice.viewmodel.post.PostListGetVm;
import com.nhphuong.postservice.viewmodel.post.PostVm;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class PostController {

    private PostRepository postRepository;
    private PostService postService;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<PostListGetVm> getPosts(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(postService.getPostsPagination(pageNo, pageSize));
    }


}
