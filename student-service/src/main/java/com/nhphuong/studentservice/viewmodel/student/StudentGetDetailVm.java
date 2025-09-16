package com.nhphuong.studentservice.viewmodel.student;

import com.nhphuong.studentservice.model.Student;

public record StudentGetDetailVm(String id, String fullName) {
//    public static StudentGetDetailVm fromModel(Student student) {
//        return new StudentGetDetailVm(student.getId(), student.getFullName());
//    }
}
