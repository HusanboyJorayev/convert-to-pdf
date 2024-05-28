package org.example.demo_insta_app.generator;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pdf_generator")
public class Generator {

    private final GeneratorServiceImpl generatorService;
    private final GenerateSecondService generateSecondService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated PDF", content = {
                    @Content(mediaType = "application/pdf")}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/generate-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> generatePdf(@RequestParam("phone") String phone,
                                              @RequestParam("email") String email,
                                              @RequestParam("account_number") String accountNumber,
                                              @RequestParam("routing_number") String routingNumber,
                                              @RequestParam(value = "podFile", required = false) MultipartFile podFile,
                                              @RequestParam(value = "bolFile", required = false) MultipartFile bolFile,
                                              @RequestParam(value = "rateConfirmationFile", required = false) MultipartFile rateConfirmationFile) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            String fileName = dateFormat.format(new Date());
            byte[] pdfBytes = generatorService.createPdf(phone, email, accountNumber, routingNumber, podFile, bolFile, rateConfirmationFile);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated PDF", content = {
                    @Content(mediaType = "application/pdf")}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/generate-pdf_second", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> generatePdfSecond(@RequestParam("phone") String phone,
                                                    @RequestParam("email") String email,
                                                    @RequestParam("account_number") String accountNumber,
                                                    @RequestParam("routing_number") String routingNumber,
                                                    @RequestParam(value = "podFile", required = false) MultipartFile podFile,
                                                    @RequestParam(value = "bolFile", required = false) MultipartFile bolFile,
                                                    @RequestParam(value = "rateConfirmationFile", required = false) MultipartFile rateConfirmationFile) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fileName = dateFormat.format(new Date());
            byte[] pdfBytes = generateSecondService.createPdf(phone, email, accountNumber, routingNumber, podFile, bolFile, rateConfirmationFile);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfBytes.length).body(pdfBytes);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
