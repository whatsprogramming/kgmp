package com.nhphuong.postservice.viewmodel.post;

import java.util.List;

public record PostListGetVm(
        List<PostVm> posts,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLast
) {
}
