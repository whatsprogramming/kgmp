package com.nhphuong.studentservice.viewmodel.student;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nhphuong.studentservice.model.enumeration.Gender;
import com.nhphuong.studentservice.model.enumeration.StudentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record StudentPostVm (
    @NotBlank String fullName,
    @NotBlank String dob,
    Gender gender,
    String address,
    String parentName,
    String parentPhone,
    StudentStatus studentStatus
){
}
