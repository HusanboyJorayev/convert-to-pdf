package org.example.demo_insta_app.user;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestPdf {
    private final GeneratePdfFiles generatePdfFiles;


    @GetMapping(value = "/load", produces = MediaType.APPLICATION_PDF_VALUE)
    public void generatedFiles(HttpServletResponse response) {

        try {
            this.generatePdfFiles.generatePdfFiles(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/getPdfFile", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> createPdf() {
        try {
            byte[] pdfBytes = generatePdfFiles.createPdf();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
