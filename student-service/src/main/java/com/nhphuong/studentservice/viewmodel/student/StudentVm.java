package com.nhphuong.studentservice.viewmodel.student;

import com.nhphuong.studentservice.model.Student;
import com.nhphuong.studentservice.model.enumeration.Gender;
import com.nhphuong.studentservice.model.enumeration.StudentStatus;

import java.time.LocalDateTime;

public record StudentVm(
        String id,
        String fullName,
        LocalDateTime dob,
        Gender gender,
        String address,
        String parentName,
        String parentPhone,
        StudentStatus status

) {
//    public static StudentVm fromModel(Student model) {
//        return new StudentVm(model.getId(), model.getFullName(), model.getDob(), model.getGender(), model.getAddress(), model.getParentName(), model.getParentPhone(), model.getStatus());
//    }
}
