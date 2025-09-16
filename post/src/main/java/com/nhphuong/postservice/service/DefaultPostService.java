package com.nhphuong.postservice.service;

import com.nhphuong.postservice.model.Post;
import com.nhphuong.postservice.repository.PostRepository;
import com.nhphuong.postservice.service.interfaces.PostService;
import com.nhphuong.postservice.viewmodel.post.PostListGetVm;
import com.nhphuong.postservice.viewmodel.post.PostVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultPostService implements PostService {

    private PostRepository postRepository;

    public DefaultPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostListGetVm getPostsPagination(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> page = this.postRepository.findAll(pageable);
        List<Post> content = page.getContent();
        List<PostVm> postVmList = content.stream()
                .map(PostVm::fromModel)
                .toList();

        return new PostListGetVm(postVmList,
                page.getNumber(),
                page.getSize(),
                (int) page.getTotalElements(),
                page.getTotalPages(),
                page.isLast());
    }
}
