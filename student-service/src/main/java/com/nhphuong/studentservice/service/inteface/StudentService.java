package com.nhphuong.studentservice.service.inteface;

import com.nhphuong.studentservice.model.Student;
import com.nhphuong.studentservice.viewmodel.student.StudentGetDetailVm;
import com.nhphuong.studentservice.viewmodel.student.StudentListGetVm;
import com.nhphuong.studentservice.viewmodel.student.StudentPostVm;
import jakarta.validation.Valid;

public interface StudentService {

    StudentListGetVm getStudentPagination(Integer pageNo, Integer pageSize);

    StudentGetDetailVm createStudent(@Valid StudentPostVm studentPostVm);

    void delete(String id);

    Student updateStudent(String id, @Valid StudentPostVm studentPostVm);
}
