package org.example.demo_insta_app.user;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<Integer, UserDto> {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public Response<UserDto> create(UserDto dto) {
        User user = this.userMapper.toEntity(dto);
        this.userRepository.save(user);
        UserDto userDto = this.userMapper.toDto(user);
        try {
            userToPdf();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return Response.<UserDto>builder()
                .success(true)
                .message("User created successfully")
                .data(userDto)
                .build();
    }

    private void userToPdf() throws FileNotFoundException {

        File file = new File("users.pdf");
        String path = file.getName();


        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);


        try {
            String imagePath = "C:\\newFolder\\demo_insta_app\\img.png";
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData);
            float height = pdfDocument.getDefaultPageSize().getHeight() / 2;
            float width = pdfDocument.getDefaultPageSize().getWidth() / 2;
            image.setFixedPosition(height - 100, width - 100);
            image.setOpacity(0.3f);
            //document.add(image.setTextAlignment(TextAlignment.CENTER));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        float f = 120f;
        float[] bf = {f};
        Table bortable = new Table(bf);
        bortable.setBorder(new DashedBorder(ColorConstants.GREEN, 2f))
                .addCell("HELLO USERS")
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(ColorConstants.WHITE);

        //document.add(bortable);


        float headerCol = 250f;
        float[] headerRow = {headerCol, headerCol, headerCol};
        Table headerTable = new Table(headerRow);
        headerTable.addCell("")
                //.setBackgroundColor(ColorConstants.DARK_GRAY)
                .setTextAlignment(TextAlignment.LEFT)
                .setMargin(10f);
        //.setBold();
        /*.setBorder(new DashedBorder(ColorConstants.GREEN,1f));*/
        //Border solidBorder = new SolidBorder(ColorConstants.BLUE, 2f);

        headerTable.addCell("USERS INFORMATION")
                //.setBackgroundColor(ColorConstants.DARK_GRAY)
                .setTextAlignment(TextAlignment.CENTER)
                //.setBorder(new SolidBorder(0f))
                .setMargin(10f);
        //.setBold();
        /*.setBorder(new DashedBorder(ColorConstants.GREEN,1f));*/
        //Border solidBorder = new SolidBorder(ColorConstants.BLUE, 2f);

        headerTable.addCell("")
                //.setBackgroundColor(ColorConstants.DARK_GRAY)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMargin(10f);
        //.setBold();
        /*.setBorder(new DashedBorder(ColorConstants.GREEN,1f));*/
        //Border solidBorder = new SolidBorder(ColorConstants.BLUE, 2f);


        /*Border b = new DashedBorder(ColorConstants.MAGENTA, 2f);
        headerTable.setBorder(b);*/
        document.add(headerTable/*.setBorder(Border.NO_BORDER).setMarginBottom(15f)*/);
        //Paragraph paragraph = new Paragraph("\n");
        //document.add(paragraph);


        float rowCol = 150f;
        float[] rows = {rowCol, rowCol, rowCol, rowCol};

        Table rowTable = new Table(rows);
        //rowTable.setBackgroundColor(ColorConstants.CYAN, 2f).setTextAlignment(TextAlignment.CENTER);

        rowTable.addCell("user_id")/*.setBackgroundColor(ColorConstants.WHITE)*/
                .setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.GREEN)
                //.setTextAlignment(TextAlignment.LEFT)
                .setBold();
        rowTable.addCell("firstname")/*.setBackgroundColor(ColorConstants.WHITE)*/
                .setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.GREEN)
                //.setTextAlignment(TextAlignment.CENTER)
                .setBold();
        rowTable.addCell("lastname")/*.setBackgroundColor(ColorConstants.WHITE)*/
                .setBackgroundColor(ColorConstants.GREEN)
                .setBorder(Border.NO_BORDER)
                //.setTextAlignment(TextAlignment.CENTER)
                .setBold();
        rowTable.addCell("age")/*.setBackgroundColor(ColorConstants.WHITE)*/
                .setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.GREEN)
                //.setTextAlignment(TextAlignment.RIGHT)
                .setBold();
        document.add(rowTable);

        Table rowTable2 = new Table(rows);

        List<User> allUsers = this.userRepository.findAllUsers();
        UserDto dto = null;
        for (User user : allUsers) {
            dto = this.userMapper.toDto(user);
            rowTable2.addCell(String.valueOf(dto.getId())).setBorder(Border.NO_BORDER)/*.setBackgroundColor(ColorConstants.GRAY)*/;
            rowTable2.addCell(dto.getFirstname()).setBorder(Border.NO_BORDER)/*.setBackgroundColor(ColorConstants.GRAY)*/;
            rowTable2.addCell(dto.getLastname()).setBorder(Border.NO_BORDER)/*.setBackgroundColor(ColorConstants.GRAY)*/;
            rowTable2.addCell(String.valueOf(dto.getAge())).setBorder(Border.NO_BORDER)/*.setBackgroundColor(ColorConstants.GRAY)*/;
        }
        document.add(rowTable2);

        document.close();


    }

    @Override
    public Response<UserDto> get(Integer id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()) {
            var user = optional.get();
            return Response.<UserDto>builder()
                    .success(true)
                    .message("oK")
                    .data(this.userMapper.toDto(user))
                    .build();
        }
        return Response.<UserDto>builder()
                .code(-1)
                .message("User is not found")
                .build();
    }

    @Override
    public Response<UserDto> delete(Integer id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()) {
            var user = optional.get();
            this.userRepository.delete(user);
            return Response.<UserDto>builder()
                    .success(true)
                    .message("User deleted successfully")
                    .data(this.userMapper.toDto(user))
                    .build();
        }
        return Response.<UserDto>builder()
                .code(-1)
                .message("User is not found")
                .build();
    }

    @Override
    public Response<UserDto> update(Integer id, UserDto dto) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()) {
            var user = optional.get();
            this.userMapper.updateUser(user, dto);
            this.userRepository.save(user);
            return Response.<UserDto>builder()
                    .success(true)
                    .message("User updated successfully")
                    .data(this.userMapper.toDto(user))
                    .build();
        }
        return Response.<UserDto>builder()
                .code(-1)
                .message("User is not found")
                .build();
    }

    @Override
    public Response<List<UserDto>> getAll() {
        List<User> allUsers = this.userRepository.findAllUsers();
        if (allUsers.isEmpty()) {
            return Response.<List<UserDto>>builder()
                    .code(-1)
                    .message("User list is empty")
                    .build();
        }
        return Response.<List<UserDto>>builder()
                .success(true)
                .message("OK")
                .data(allUsers.stream().map(this.userMapper::toDto).toList())
                .build();
    }

    public Response<String> createPdfFile() {

        try {
            extractedToPdf();
        } catch (FileNotFoundException | MalformedURLException e) {
            throw new RuntimeException(e);
        }


        return Response.<String>builder()
                .success(true)
                .message("Ok")
                .data("pdf file created successfully")
                .build();
    }

    private void extractedToPdf() throws FileNotFoundException, MalformedURLException {
        File file = new File("inv.pdf");
        String path = file.getName();

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        String imPath = "C:\\newFolder\\demo_insta_app\\img.png";
        ImageData imageData = ImageDataFactory.create(imPath);
        Image image = new Image(imageData);
        float height = pdfDocument.getDefaultPageSize().getHeight() / 2;
        float width = pdfDocument.getDefaultPageSize().getWidth() / 2;
        image.setFixedPosition(height - 100, width - 80);
        image.setOpacity(0.3f);

        float one = 200f;
        float[] oneTable = {one, one, one};
        Table t1 = new Table(oneTable);
        t1.addCell("Brand Name").setFontSize(15)
                .setBackgroundColor(ColorConstants.GREEN).setTextAlignment(TextAlignment.LEFT)
                .setBold();
        t1.addCell("").setFontSize(15)
                .setBackgroundColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.LEFT)
                .setBold();
        t1.addCell("INVOICE").setBold().setFontSize(25).setTextAlignment(TextAlignment.RIGHT)
                .setBackgroundColor(ColorConstants.WHITE);
        document.add(t1);


        float two = 200f;
        float[] twoTable = {two, two, two};
        Table t2 = new Table(twoTable);
        t2.addCell("Invoice To\nyour name\nyour location").setFontSize(10)
                .setBackgroundColor(ColorConstants.GREEN).setTextAlignment(TextAlignment.LEFT)
                .setBold();
        t2.addCell("").setFontSize(10)
                .setBackgroundColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.LEFT)
                .setBold();
        t2.addCell("Invoice No:1234567\nitem No:009876\nInvoice date:" + LocalDate.now()).setBold().setFontSize(10).setTextAlignment(TextAlignment.RIGHT)
                .setBackgroundColor(ColorConstants.WHITE);
        document.add(t2);


        document.close();
    }
}
