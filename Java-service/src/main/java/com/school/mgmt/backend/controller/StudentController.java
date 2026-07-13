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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final AuthService authService;
    private final StudentService studentService;
    private final PdfService pdfService;

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        Map<String, String> responseObject = new HashMap<>();
        responseObject.put("message", "Successfully tested!");
        responseObject.put("status", "success");
        return ResponseEntity.ok(responseObject);
    }

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
