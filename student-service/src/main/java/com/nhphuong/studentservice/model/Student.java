package com.nhphuong.studentservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhphuong.studentservice.model.enumeration.Gender;
import com.nhphuong.studentservice.model.enumeration.StudentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    private String parentName;
    private String parentPhone;
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
