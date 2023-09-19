package com.productnew.com.productnew;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class AppendDataToExistingExcel {

	public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("E:\\Excel reading\\Book1.xlsx");
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            // Get the sheet you want to work with (for example, the first sheet)
            Sheet sheet = workbook.getSheetAt(0);

            // Create a new row and add data to it
            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1); // Appends to the end of the sheet
            newRow.createCell(0).setCellValue("New Data 1");
            newRow.createCell(1).setCellValue("New Data 2");
            newRow.createCell(2).setCellValue("New Data 3");

            // Save the changes back to the same file
            try (FileOutputStream outputStream = new FileOutputStream("E:\\Excel reading\\Book1.xlsx")) {
                workbook.write(outputStream);
            }

            System.out.println("Data appended to existing Excel file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sagar is working on it");
            System.out.println("Aniket is with me");
        }
    }
	
}
