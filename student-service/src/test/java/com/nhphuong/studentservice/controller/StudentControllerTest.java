package com.nhphuong.studentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhphuong.studentservice.StudentServiceApplication;
import com.nhphuong.studentservice.model.enumeration.Gender;
import com.nhphuong.studentservice.model.enumeration.StudentStatus;
import com.nhphuong.studentservice.repository.StudentRepository;
import com.nhphuong.studentservice.service.inteface.StudentService;
import com.nhphuong.studentservice.viewmodel.student.StudentPostVm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@ContextConfiguration(classes = StudentServiceApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @MockitoBean
    private StudentRepository studentRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testListStudents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/backoffice/students"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateStudent() throws Exception {
        StudentPostVm studentPostVm = new StudentPostVm("Test Name",
                LocalDateTime.now().toString(),
                Gender.MALE,
                "Address number 1",
                "Parent Name",
                "Parent phone",
                StudentStatus.INACTIVE);

        String content = objectMapper.writeValueAsString(studentPostVm);

        mockMvc.perform(MockMvcRequestBuilders.post("/backoffice/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/backoffice/students/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateStudent() throws Exception {
        StudentPostVm studentPostVm = new StudentPostVm("Test Name",
                LocalDateTime.now().toString(),
                Gender.MALE,
                "Address number 1",
                "Parent Name",
                "Parent phone",
                StudentStatus.INACTIVE);

        String content = objectMapper.writeValueAsString(studentPostVm);
        mockMvc.perform(MockMvcRequestBuilders.put("/backoffice/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isNoContent());
    }
}
