package com.productnew.com.productnew;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StringToExcel {

	public static void main(String[] args) {
        // Your data as a string
        String data = "Name, Age, Email\nJohn Doe, 30, johndoe@example.com\nJane Smith, 25, janesmith@example.com";

        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a new sheet
            Sheet sheet = workbook.createSheet("Sheet1");

            // Split the data into rows and columns
            String[] rows = data.split("\n");
            for (int l = 0; l < rows.length; l++) {
                String[] columns = rows[l].split(",");
                Row row = sheet.createRow(l);

                for (int m = 0; m < columns.length; m++) {
                    Cell cell = row.createCell(m);
                    cell.setCellValue(columns[m].trim());
                }
            }

            // Save the workbook to a file
            try (FileOutputStream outputStream = new FileOutputStream("E:\\Excel\\Book1.xlsx")) {
                workbook.write(outputStream);
            }

            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
