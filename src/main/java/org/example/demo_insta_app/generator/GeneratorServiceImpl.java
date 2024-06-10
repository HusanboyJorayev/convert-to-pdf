package org.example.demo_insta_app.generator;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl {

    public byte[] createPdf(String phone, String email, String accountNumber, String routingNumber,
                            MultipartFile podFile, MultipartFile bolFile, MultipartFile rateConfirmationFile) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            pdfDoc.addNewPage();
            document.add(new Paragraph("\n"));
            // todo images
            String imagePath = "C:\\newFolder\\invoice_app\\logo.png";
            ImageData imageData = ImageDataFactory.create(imagePath);
            com.itextpdf.layout.element.Image image = new Image(imageData);
            float width = pdfDoc.getDefaultPageSize().getWidth() / 4;
            float height = pdfDoc.getDefaultPageSize().getHeight() / 4;
            image.setFixedPosition(width + 180f, height + 400);
            image.setOpacity(2f);
            document.add(image);

            // TODO  BORDER 1
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


            // todo from
            float[] twoR = {250f};
            Table twoT = new Table(twoR);
            twoT.addCell("FROM").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setFontColor(ColorConstants.WHITE);
            document.add(twoT.setBackgroundColor(ColorConstants.BLACK));
            document.add(bt);


            // todo beyond factoring
            float ff = 250f;
            float[] fr = {ff};
            Table ft = new Table(fr);
            ft.addCell("Beyond Factoring LLC").setBorder(Border.NO_BORDER).setFontSize(10);
            document.add(ft);


            // todo factoring information
            float f1 = 250f;
            float[] f1r = {f1};
            Table f1t = new Table(f1r);
            f1t.addCell("325,Sentry ParkWay,Building 5,Suite 200\n"
                            + "Blue Bell,PA 19422,USA\n" + "Phone:" + phone + "\nEmail:" + email)
                    .setBorder(Border.NO_BORDER).setFontSize(10);
            document.add(new Paragraph("\n"));
            document.add(f1t);

            // todo on behalf of
            float[] twoR1 = {250f, 20f, 250f};
            Table twoT1 = new Table(twoR1);
            twoT1.addCell("ON BEHALF OF").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold().setFontColor(ColorConstants.WHITE);
            twoT1.addCell("").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold();
            twoT1.addCell("PAYMENT DETAILS").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold().setFontColor(ColorConstants.WHITE);

            document.add(new Paragraph("\n"));
            document.add(twoT1.setBackgroundColor(ColorConstants.BLACK));
            document.add(bt2);

            // todo cargo prime llc
            float[] twoR2 = {250f, 20f, 250f};
            Table twoT2 = new Table(twoR2);
            twoT2.addCell("Cargo prime LLC").setFontSize(12).setBorder(Border.NO_BORDER);
            twoT2.addCell("").setFontSize(12).setBorder(Border.NO_BORDER);
            twoT2.addCell("Chase Bank, N.A").setFontSize(12).setBorder(Border.NO_BORDER);


            twoT2.addCell("9800A mcKnight Road Suite 310\nPittsburgh PA\nPhone:" + phone + "\nFax:12345" + "\nEmail:" + email)
                    .setFontSize(10).setBorder(Border.NO_BORDER);
            twoT2.addCell("").setFontSize(12).setBorder(Border.NO_BORDER);
            twoT2.addCell("Account number   " + accountNumber + "\nRouting number   " + routingNumber)
                    .setFontSize(12).setBorder(Border.NO_BORDER);
            document.add(twoT2);
            document.add(new Paragraph("\n"));


            // todo bill to
            float[] twoR3 = {250f, 20f, 250f};
            Table twoT3 = new Table(twoR3);
            twoT3.addCell("BILL TO").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold().setFontColor(ColorConstants.WHITE);
            twoT3.addCell("").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold();
            twoT3.addCell("INVOICE INFO").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold().setFontColor(ColorConstants.WHITE);
            document.add(twoT3.setBackgroundColor(ColorConstants.BLACK));
            document.add(bt2);

            // todo amx logistics
            float[] twoR4 = {250f, 20f, 250f};
            Table twoT4 = new Table(twoR4);
            twoT4.addCell("AMX LOGISTICS").setFontSize(12).setBold().setBorder(Border.NO_BORDER);
            twoT4.addCell("").setFontSize(12).setBold().setBorder(Border.NO_BORDER);
            twoT4.addCell("").setFontSize(12).setBold().setBorder(Border.NO_BORDER);
            document.add(twoT4.setBorder(Border.NO_BORDER));


            // todo amx logistics information
            float[] twoR5 = {250f, 20f, 250f};
            Table twoT5 = new Table(twoR5);
            String po_box = "12345";
            LocalDate now = LocalDate.now();
            LocalDate due_date = now.plusMonths(1);
            int invoice_total = 1234;

            twoT5.addCell("Po Box " + po_box + "\nAshford,Al 36574" + "\nEmail:" + email + "\nPhone:" + phone).setFontSize(12).setBorder(Border.NO_BORDER);
            twoT5.addCell("").setFontSize(12).setBorder(Border.NO_BORDER);
            twoT5.addCell("Invoice date  " + now + "\nDue date   " + due_date + "\nInvoice total    US$" + invoice_total).setFontSize(12).setBorder(Border.NO_BORDER);
            document.add(twoT5.setBorder(Border.NO_BORDER));


            // todo load
            float[] twoR6 = {250f, 20f, 250f};
            int load = 1234;
            Table twoT6 = new Table(twoR6);
            twoT6.addCell("LOAD # " + load).setFontSize(12).setFontColor(ColorConstants.WHITE).setBorder(Border.NO_BORDER)
                    .setBold();
            document.add(new Paragraph("\n"));
            document.add(twoT6.setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLACK));
            document.add(bt2);
            document.add(new Paragraph("\n"));


            // todo pick up
            float[] twoR8 = {250f, 20f, 250f};
            Table twoT8 = new Table(twoR8);
            twoT8.addCell("Pickup").setFontSize(12).setFontColor(ColorConstants.GREEN).setBorder(Border.NO_BORDER);
            twoT8.addCell("").setFontSize(12).setFontColor(ColorConstants.WHITE).setBorder(Border.NO_BORDER);
            twoT8.addCell("Drop location").setFontSize(12).setFontColor(ColorConstants.GREEN).setBorder(Border.NO_BORDER)
                    .setBold();
            document.add(twoT8);


            // todo pick up information
            float[] twoR7 = {250f, 20f, 250f};
            Table twoT7 = new Table(twoR7);
            twoT7.addCell("Pamlico Air - VA" + "\n93 Industrial Dr,Louisa,VA 23093" + "\n" + LocalDateTime.now())
                    .setFontSize(12).setBorder(Border.NO_BORDER);
            twoT7.addCell("\n" + "->").setFontSize(12).setFontColor(ColorConstants.BLACK).setBorder(Border.NO_BORDER);
            twoT7.addCell("Microsoft CY-S17" + "\n7700 Commerce Cir,Cheyenne,WY 82007" + "\n" + LocalDateTime.now()).setFontSize(12).setBorder(Border.NO_BORDER);
            document.add(twoT7.setBorder(Border.NO_BORDER));


            // TODO ADD POD FILE
            PdfDocument pod = new PdfDocument(new PdfReader(podFile.getInputStream()));
            int podPages = pod.getNumberOfPages();
            for (int i = 1; i <= podPages; i++) {
                PdfPage pdfPage = pod.getPage(i).copyTo(pdfDoc);
                pdfDoc.addPage(pdfPage);
            }
            pod.close();


            // TODO ADD BOL FILE
            PdfDocument bol = new PdfDocument(new PdfReader(bolFile.getInputStream()));
            int bolPages = bol.getNumberOfPages();
            for (int i = 1; i <= bolPages; i++) {
                PdfPage pdfPage = bol.getPage(i).copyTo(pdfDoc);
                pdfDoc.addPage(pdfPage);
            }
            bol.close();


            // TODO ADD RATE CONFIRMATION FILE
            if (!rateConfirmationFile.isEmpty() && rateConfirmationFile != null) {
                PdfReader reader = new PdfReader(rateConfirmationFile.getInputStream());
                PdfDocument rate = new PdfDocument(reader);
                int ratePages = rate.getNumberOfPages();
                for (int i = 1; i <= ratePages; i++) {
                    PdfPage ratePage = rate.getPage(i).copyTo(pdfDoc);
                    pdfDoc.addPage(ratePage);
                }
                rate.close();
            }


            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while creating PDF", e);
        }
    }



 /*   // todo page 4
    private static void page4(Document document) {
        float[] pageR4 = {250f};
        DateFormat dateFormat = new SimpleDateFormat("ddMM");
        String date = dateFormat.format(new Date());
        Table pageT4 = new Table(pageR4);
        pageT4.addCell("INVOICE# LoadNumber_" + date).setFontColor(ColorConstants.BLUE)
                .setBackgroundColor(ColorConstants.WHITE).setFontSize(12).setBorder(Border.NO_BORDER)
                .setBold();
        document.add(pageT4.setBorder(Border.NO_BORDER));
        document.add(new Paragraph("\n"));

        for (int i = 0; i < 10; i++) {
            document.add(new Paragraph("\n"));
        }
        float[] pr4 = {200f, 200f, 200f};
        Table pt4 = new Table(pr4);
        pt4.addCell("").setBorder(Border.NO_BORDER);
        pt4.addCell("POD FILE").setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
        pt4.addCell("").setBorder(Border.NO_BORDER);
        document.add(pt4);
    }


    // todo page 3
    private static void page3(Document document) {
        float[] page3R = {250f};
        DateFormat dateFormat = new SimpleDateFormat("ddMM");
        String date = dateFormat.format(new Date());
        Table page3T = new Table(page3R);
        page3T.addCell("INVOICE# LoadNumber_" + date).setFontColor(ColorConstants.BLUE)
                .setBackgroundColor(ColorConstants.WHITE).setFontSize(12).setBorder(Border.NO_BORDER)
                .setBold();
        document.add(page3T.setBorder(Border.NO_BORDER));
        document.add(new Paragraph("\n"));

        for (int i = 0; i < 10; i++) {
            document.add(new Paragraph("\n"));
        }
        float[] p3 = {200f, 200f, 200f};
        Table pt3 = new Table(p3);
        pt3.addCell("").setBorder(Border.NO_BORDER);
        pt3.addCell("BOL FILE").setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
        pt3.addCell("").setBorder(Border.NO_BORDER);
        document.add(pt3);
    }


    // todo page 2
    private static void page2(Document document) {
        float[] page1R = {250f};
        DateFormat dateFormat = new SimpleDateFormat("ddMM");
        String date = dateFormat.format(new Date());
        Table page1T = new Table(page1R);
        page1T.addCell("INVOICE# LoadNumber_" + date).setFontColor(ColorConstants.BLUE)
                .setBackgroundColor(ColorConstants.WHITE).setFontSize(12).setBorder(Border.NO_BORDER)
                .setBold();
        document.add(page1T.setBorder(Border.NO_BORDER));
        document.add(new Paragraph("\n"));

        for (int i = 0; i < 10; i++) {
            document.add(new Paragraph("\n"));
        }
        float[] r3 = {200f, 200f, 200f};
        Table t1 = new Table(r3);
        t1.addCell("").setBorder(Border.NO_BORDER);
        t1.addCell("RATE CONFIRMATION FILE").setBold().setBorder(Border.NO_BORDER);
        t1.addCell("").setBorder(Border.NO_BORDER);
        document.add(t1);
    }*/
}
