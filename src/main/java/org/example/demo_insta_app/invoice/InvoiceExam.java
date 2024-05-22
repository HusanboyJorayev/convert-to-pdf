package org.example.demo_insta_app.invoice;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.time.LocalDate;

public class InvoiceExam {
    public static void main(String[] args) {
        File file = new File("invoice2.pdf");
        try {
            PdfWriter pdfWriter = new PdfWriter(file.getName());

            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            Document document = new Document(pdfDocument);

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
            Table oneT = new Table(oneR);
            oneT.addCell("INVOICE# LoadNumber_" + LocalDate.now())
                    .setBackgroundColor(ColorConstants.WHITE).setFontSize(12).setBorder(Border.NO_BORDER)
                    .setBold();
            document.add(oneT);


            // todo from
            float[] twoR = {250f};
            Table twoT = new Table(twoR);
            twoT.addCell("FROM").setBold().setFontSize(12).setBorder(Border.NO_BORDER);
            document.add(twoT);
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
            String phone = "1234567";
            String email = "email@email.com";
            f1t.addCell("325,Sentry ParkWay,Building 5,Suite 200\n")
                    .addCell("Blue Bell,PA 19422,USA\n")
                    .addCell("Phone:" + phone)
                    .addCell("Email:" + email)
                    .setBorder(Border.NO_BORDER).setFontSize(10);
            document.add(new Paragraph("\n"));
            document.add(f1t);

            // todo on behalf of
            float[] twoR1 = {250f, 20f, 250f};
            Table twoT1 = new Table(twoR1);
            twoT1.addCell("ON BEHALF OF").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold();
            twoT1.addCell("").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold();
            twoT1.addCell("PAYMENT DETAILS").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold();

            document.add(new Paragraph("\n"));
            document.add(twoT1);
            document.add(bt2);

            // todo cargo prime llc
            float[] twoR2 = {250f, 20f, 250f};
            Table twoT2 = new Table(twoR2);
            twoT2.addCell("Cargo prime LLC").setFontSize(12).setBorder(Border.NO_BORDER);
            twoT2.addCell("").setFontSize(12).setBorder(Border.NO_BORDER);
            twoT2.addCell("Chase Bank, N.A").setFontSize(12).setBorder(Border.NO_BORDER);

            String account_number = "123 345 567";
            String routing_number = "123 345 567";

            twoT2.addCell("9800A mcKnight Road Suite 310\nPittsburgh PA\nPhone:" + phone + "\nFax:12345" + "\nEmail:" + email)
                    .setFontSize(10).setBorder(Border.NO_BORDER);
            twoT2.addCell("").setFontSize(12).setBorder(Border.NO_BORDER);
            twoT2.addCell("Account number   " + account_number + "\nRouting number   " + routing_number)
                    .setFontSize(12).setBorder(Border.NO_BORDER);
            document.add(twoT2);
            document.add(new Paragraph("\n"));


            // todo bill to
            float[] twoR3 = {250f, 20f, 250f};
            Table twoT3 = new Table(twoR3);
            twoT3.addCell("BILL TO").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold();
            twoT3.addCell("").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold();
            twoT3.addCell("INVOICE INFO").setBold().setFontSize(12).setBorder(Border.NO_BORDER).setBold();
            document.add(twoT3);
            document.add(bt2);

            float[] twoR4 = {250f, 20f, 250f};
            Table twoT4 = new Table(twoR4);
            twoT4.addCell("AMX LOGISTICS").setFontSize(12).setBold().setBorder(Border.NO_BORDER);
            twoT4.addCell("").setFontSize(12).setBold().setBorder(Border.NO_BORDER);
            twoT4.addCell("").setFontSize(12).setBold().setBorder(Border.NO_BORDER);
            document.add(twoT4);


            document.close();

            System.out.println("PDF created successfully with borderless cells.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
