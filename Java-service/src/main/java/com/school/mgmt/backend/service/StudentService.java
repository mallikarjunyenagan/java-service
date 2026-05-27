package com.school.mgmt.backend.service;

import com.school.mgmt.backend.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final RestClient restClient;
    private final AuthService authService;

    public String getStudents() {
        return restClient.get()
                .uri("/api/v1/students")
                .retrieve()
                .body(String.class);
    }
    public StudentDto getStudentById(Integer id) {
        return restClient.get()
                .uri("/api/v1/students/"+id)
                .retrieve()
                .body(StudentDto.class);
    }
    public String createStudent(Object request) {

        return restClient.post()
                .uri("/api/v1/students")
                .body(request)
                .retrieve()
                .body(String.class);
    }
}
