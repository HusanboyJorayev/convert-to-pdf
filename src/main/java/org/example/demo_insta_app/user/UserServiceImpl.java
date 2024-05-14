package org.example.demo_insta_app.user;

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
import com.itextpdf.layout.properties.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

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

        float headerCol = 250f;
        float[] headerRow = {headerCol};
        Table headerTable = new Table(headerRow);
        headerTable.addCell("USERS INFORMATION IN PDF FILE")
                //.setBackgroundColor(ColorConstants.DARK_GRAY)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMargin(15f)
                .setBold()
                .setBorder(Border.NO_BORDER);
        //Border solidBorder = new SolidBorder(ColorConstants.BLUE, 2f);
        Border b = new DashedBorder(ColorConstants.MAGENTA, 2f);
        headerTable.setBorder(b);
        document.add(headerTable.setBorder(Border.NO_BORDER).setMarginBottom(15f));
        //Paragraph paragraph = new Paragraph("\n");
        //document.add(paragraph);


        float rowCol = 150f;
        float[] rows = {rowCol, rowCol, rowCol, rowCol};

        Table rowTable = new Table(rows);
        rowTable.setBackgroundColor(ColorConstants.CYAN, 2f).setTextAlignment(TextAlignment.CENTER);

        rowTable.addCell("user_id")/*.setBackgroundColor(ColorConstants.WHITE)*/
                .setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLUE)
                //.setTextAlignment(TextAlignment.LEFT)
                .setBold();
        rowTable.addCell("firstname")/*.setBackgroundColor(ColorConstants.WHITE)*/
                .setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLUE)
                //.setTextAlignment(TextAlignment.CENTER)
                .setBold();
        rowTable.addCell("lastname")/*.setBackgroundColor(ColorConstants.WHITE)*/
                .setBackgroundColor(ColorConstants.BLUE)
                .setBorder(Border.NO_BORDER)
                //.setTextAlignment(TextAlignment.CENTER)
                .setBold();
        rowTable.addCell("age")/*.setBackgroundColor(ColorConstants.WHITE)*/
                .setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLUE)
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
        return null;
    }

    @Override
    public Response<UserDto> delete(Integer id) {
        return null;
    }

    @Override
    public Response<UserDto> update(Integer id, UserDto dto) {
        return null;
    }

    @Override
    public Response<List<UserDto>> getAll() {
        return null;
    }
}
