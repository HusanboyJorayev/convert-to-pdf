package org.example.demo_insta_app.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pdf_generator")
public class Generator {

    private final GeneratorServiceImpl generatorService;

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(TestUser user) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            String fileName = dateFormat.format(new Date());
            byte[] pdfBytes = generatorService.createPdf(user);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
