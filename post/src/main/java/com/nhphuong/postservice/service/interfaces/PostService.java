package com.nhphuong.postservice.service.interfaces;

import com.nhphuong.postservice.model.Post;
import com.nhphuong.postservice.viewmodel.post.PostListGetVm;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    PostListGetVm getPostsPagination(Integer pageNo, Integer pageSize);
}
