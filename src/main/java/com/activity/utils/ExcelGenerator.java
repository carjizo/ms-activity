package com.activity.utils;

import com.activity.entities.Activity;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ExcelGenerator {

    private List<Activity> activityList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<Activity> activityList) {
        this.activityList = activityList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Actividades");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        createCell(row, 0, "ID Document", style);
        createCell(row, 1, "Full Name", style);
        createCell(row, 2, "Nombre Cliente", style);
        createCell(row, 3, "Celular Cliente", style);
        createCell(row, 4, "Descripción", style);
        createCell(row, 5, "Nota", style);
        createCell(row, 6, "Precio", style);
        createCell(row, 7, "Adelanto", style);
        createCell(row, 8, "Estado", style);
        createCell(row, 9, "Última Actualización", style);
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell == null) {
            cell.setCellValue("");
        }else if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Double) {
            cell.setCellValue((Double) valueOfCell);
        } else if (valueOfCell instanceof OffsetDateTime) {
            OffsetDateTime offsetDateTime = (OffsetDateTime) valueOfCell;
            // Formatear solo la fecha (sin hora ni zona horaria)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = offsetDateTime.format(formatter);
            cell.setCellValue(formattedDate);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
        for (Activity record: activityList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getIdDocument(), style);
            createCell(row, columnCount++, record.getFullName(), style);
            createCell(row, columnCount++, record.getClientFullName(), style);
            createCell(row, columnCount++, record.getClientPhone(), style);
            createCell(row, columnCount++, record.getDescription(), style);
            createCell(row, columnCount++, record.getNote(), style);
            createCell(row, columnCount++, record.getAmount(), style);
            createCell(row, columnCount++, record.getCustomerPayment(), style);
            createCell(row, columnCount++, record.getStatus(), style);
            createCell(row, columnCount++, record.getUpdatedAt(), style);
        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
