package com.school.mgmt.backend.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.school.mgmt.backend.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final TemplateEngine templateEngine;

    public byte[] generateStudentPdf(StudentDto student) {
        try {
            Context context = new Context();
            context.setVariable("student", student);

            String html = templateEngine.process(
                    "student_report",
                    context
            );

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();
            PdfRendererBuilder builder =
                    new PdfRendererBuilder();

            builder.withHtmlContent(html, null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
