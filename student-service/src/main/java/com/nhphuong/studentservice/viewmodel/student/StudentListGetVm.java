package com.nhphuong.studentservice.viewmodel.student;

import java.util.List;

public record StudentListGetVm(
        List<StudentVm> students,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLast) {
}

