package com.nhphuong.postservice.viewmodel.post;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nhphuong.postservice.model.Post;

import java.util.List;

public record PostVm(String id,
                     String title,
                     String content,
                     List<String> tags) {
    public static PostVm fromModel(Post post) {
        return new PostVm(post.getId(), post.getTitle(), post.getContent(), post.getTags());
    }

}
