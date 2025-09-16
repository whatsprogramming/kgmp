package com.nhphuong.studentservice.mapper;

import com.nhphuong.studentservice.model.Student;
import com.nhphuong.studentservice.viewmodel.student.StudentGetDetailVm;
import com.nhphuong.studentservice.viewmodel.student.StudentPostVm;
import com.nhphuong.studentservice.viewmodel.student.StudentVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentGetDetailVm studentToStudentGetDetailVm(Student student);
    StudentVm studentToStudentVm(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studentStatus", target = "status")
    void updateEntityFromVm(StudentPostVm studentVm, @MappingTarget Student student);
}
