package com.nhphuong.studentservice.model.enumeration;

import lombok.Getter;

@Getter
public enum StudentStatus {
    ACTIVE("active"),
    INACTIVE("inactive");
    private final String value;
    StudentStatus(String value) { this.value = value; }
}
