/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.util;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author terex
 */
public class TestExcelFuncSum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws  IOException {
        // TODO code application logic here
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data Received");
         
        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };
 
        int rowCount = 0;
         
        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);
             
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
         
        Row rowTotal = sheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(2);
        cellTotalText.setCellValue("Total:");
         
        Cell cellTotal = rowTotal.createCell(3);
        cellTotal.setCellFormula("SUM(D2:D5)");
         
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\terex\\Desktop\\epay-input\\JavaBooks4BeginnerPrice.xlsx")) {
            workbook.write(outputStream);
//            workbook.close();
        }
        
//        ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
//        workbook.write(bOutput);
//        
//        FaceUtil.getDownloadfile(bOutput, "ATR031600_"+CenterUtils.formatfileNameDatetime()+".xlsx");
        
        if(workbook!= null){
            System.out.println("!null");                    
            workbook = null;
        }
        
    }
}
