package com.school.mgmt.backend.controller;

import com.school.mgmt.backend.dto.StudentDto;
import com.school.mgmt.backend.service.AuthService;
import com.school.mgmt.backend.service.PdfService;
import com.school.mgmt.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final AuthService authService;
    private final StudentService studentService;
    private final PdfService pdfService;

    @GetMapping("/login")
    public String login() {

        return authService.login();
    }

    @GetMapping("/students")
    public String students() {

        return studentService.getStudents();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<byte[]> getStudent(@PathVariable("id") Integer id) {
        StudentDto student = studentService.getStudentById(id);
        byte[] pdf = pdfService.generateStudentPdf(student);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=student-report.pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Object requestObject) {

        return studentService.createStudent(requestObject);
    }
}
