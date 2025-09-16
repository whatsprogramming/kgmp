package com.nhphuong.studentservice.service;

import com.nhphuong.commonmodule.exception.NotFoundException;
import com.nhphuong.studentservice.mapper.StudentMapper;
import com.nhphuong.studentservice.model.Student;
import com.nhphuong.studentservice.repository.StudentRepository;
import com.nhphuong.studentservice.service.inteface.StudentService;
import com.nhphuong.studentservice.utils.Constant;
import com.nhphuong.studentservice.viewmodel.student.StudentGetDetailVm;
import com.nhphuong.studentservice.viewmodel.student.StudentListGetVm;
import com.nhphuong.studentservice.viewmodel.student.StudentPostVm;
import com.nhphuong.studentservice.viewmodel.student.StudentVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class DefaultStudentService implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public DefaultStudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentListGetVm getStudentPagination(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Student> page = this.studentRepository.findAll(pageable);
        List<Student> content = page.getContent();
        List<StudentVm> students = content.stream().map(studentMapper::studentToStudentVm).toList();

        return new StudentListGetVm(students,
                page.getNumber(),
                page.getSize(),
                (int) page.getTotalElements(),
                page.getTotalPages(),
                page.isLast());
    }

    @Override
    public StudentGetDetailVm createStudent(StudentPostVm studentPostVm) {
        Student student = buildStudentFromVm(studentPostVm);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.studentToStudentGetDetailVm(savedStudent);
    }

    @Override
    public void delete(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(String id, StudentPostVm studentPostVm) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.ErrorCode.STUDENT_NOT_FOUND));
        studentMapper.updateEntityFromVm(studentPostVm, student);

        return studentRepository.save(student);
    }

    private Student buildStudentFromVm(StudentPostVm studentPostVm) {

        LocalDateTime dateTime = LocalDateTime.parse(studentPostVm.dob(), DateTimeFormatter.ISO_DATE_TIME);

        return Student.builder()
                .fullName(studentPostVm.fullName())
                .dob(dateTime)
                .gender(studentPostVm.gender())
                .address(studentPostVm.address())
                .parentName(studentPostVm.parentName())
                .parentPhone(studentPostVm.parentPhone())
                .status(studentPostVm.studentStatus())
                .build();
    }


}
