package com.nhphuong.studentservice.controller;

import com.nhphuong.commonmodule.viewmodel.ErrorVm;
import com.nhphuong.studentservice.model.Student;
import com.nhphuong.studentservice.service.inteface.StudentService;
import com.nhphuong.studentservice.viewmodel.student.StudentGetDetailVm;
import com.nhphuong.studentservice.viewmodel.student.StudentListGetVm;
import com.nhphuong.studentservice.viewmodel.student.StudentPostVm;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/backoffice/students")
    public ResponseEntity<StudentListGetVm> getStudents(@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return ResponseEntity.ok(this.studentService.getStudentPagination(pageNo, pageSize));
    }

    @PostMapping("/backoffice/students")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(schema = @Schema(implementation = StudentGetDetailVm.class))),
        @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ErrorVm.class)))
    })
    public ResponseEntity<StudentGetDetailVm> createStudent(@Valid @RequestBody StudentPostVm studentPostVm) {
        StudentGetDetailVm studentDetailVm = studentService.createStudent(studentPostVm);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDetailVm);
    }

    @PutMapping("/backoffice/students/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not found",
                content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                content =  @Content(schema = @Schema(implementation = ErrorVm.class)))
    })
    public ResponseEntity<Void> updateStudent(@PathVariable String id, @Valid @RequestBody StudentPostVm  studentPostVm) {
        studentService.updateStudent(id, studentPostVm);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/backoffice/students/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not found",
            content = @Content(schema = @Schema(implementation = ErrorVm.class)))
    })
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
