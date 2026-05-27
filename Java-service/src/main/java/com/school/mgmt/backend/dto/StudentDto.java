package com.school.mgmt.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Integer id;

    private String name;

    private String email;

    private Boolean systemAccess;

    private String phone;

    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant dob;

    @JsonProperty("class")
    private String studentClass;

    private String section;

    private String roll;

    private String fatherName;

    private String fatherPhone;

    private String motherName;

    private String motherPhone;

    private String guardianName;

    private String guardianPhone;

    private String relationOfGuardian;

    private String currentAddress;

    private String permanentAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant admissionDate;

    private String reporterName;
}