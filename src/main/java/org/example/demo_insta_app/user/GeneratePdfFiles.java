package org.example.demo_insta_app.user;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GeneratePdfFiles {


    public void generatePdfFiles(HttpServletResponse response) {
        File file = new File("generate.pdf");
        try {
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());


            com.itextpdf.kernel.pdf.PdfWriter pdfWriter = new PdfWriter(file.getName());
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            com.itextpdf.layout.Document document = new Document(pdfDocument);

            pdfDocument.addNewPage();

            Table bt = new Table(new float[]{250f});
            bt.setBorder(new SolidBorder(1f));

            // TODO  BORDER 2
            Table bt2 = new Table(new float[]{250f, 20f, 250f});
            bt2.setBorder(new SolidBorder(1f));
            bt2.setBorder(Border.NO_BORDER);
            bt2.setBorder(new SolidBorder(1f));


            // todo invoice
            float[] oneR = {250f};
            DateFormat dateFormat = new SimpleDateFormat("ddMM");
            String date = dateFormat.format(new Date());
            Table oneT = new Table(oneR);
            oneT.addCell("INVOICE# LoadNumber_" + date).setFontColor(ColorConstants.BLUE)
                    .setBackgroundColor(ColorConstants.WHITE).setFontSize(12).setBorder(Border.NO_BORDER)
                    .setBold();
            document.add(oneT.setBorder(Border.NO_BORDER));
            document.add(new Paragraph("\n"));

            Table t = new Table(oneR);
            t.addCell("HELLO WORLD").setBold().setBorder(Border.NO_BORDER).setFontColor(ColorConstants.GREEN)
                    .setFontSize(15f);
            t.addCell("HELLO JAVA").setBold().setBorder(Border.NO_BORDER).setFontColor(ColorConstants.GREEN)
                    .setFontSize(15f);
            t.addCell("HELLO python").setBold().setBorder(Border.NO_BORDER).setFontColor(ColorConstants.GREEN)
                    .setFontSize(15f);

            document.add(t);
            com.itextpdf.text.Document doc=new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(doc,response.getOutputStream());

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] createPdf() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            pdfDoc.addNewPage();
            document.add(new Paragraph("content"));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

