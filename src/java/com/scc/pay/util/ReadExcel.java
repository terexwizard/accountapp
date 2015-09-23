/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.util;

import com.scc.f1.util.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author terex
 */
public class ReadExcel {
    
    public List ReadExcel(String pathFilename,int selectsheet,int startRow,int colStart){
        List data = new ArrayList();
        try{
            String[] typeFilename = pathFilename.split("\\.");

            if(typeFilename[typeFilename.length -1].toLowerCase().equals("xls")){
                data = excelTypeXLS(pathFilename,selectsheet,startRow,colStart);
            }else if(typeFilename[typeFilename.length -1].toLowerCase().equals("xlsx")){
                data = excelTypeXLSX(pathFilename,selectsheet,startRow,colStart);
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            
        }
        
        return data;
    }
    
   private List excelTypeXLS(String pathFilename,int selectsheet,int startRow,int colStart) throws IOException{
        List sheetData = new ArrayList();
 
        FileInputStream fis = null;
        try {

            fis = new FileInputStream(pathFilename);
 
            HSSFWorkbook workbook = new HSSFWorkbook(fis);

            HSSFSheet sheet = workbook.getSheetAt(selectsheet);

            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();
 
                List data = new ArrayList();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    data.add(cell);
                }
 
                sheetData.add(data);
            }
        } catch (FileNotFoundException  e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        
        return getDataExcelXLS(sheetData,startRow,colStart);
    }
   
   private List getDataExcelXLS(List sheetData,int startRow,int colStart) {

        List result = new ArrayList();
        Hashtable<String,String> ht = null;
        String value = "";
        try{
            
            
            for (int i = startRow; i < sheetData.size(); i++) {
                List list = (List) sheetData.get(i);
                
                ht = new Hashtable<String, String>();
                
                for (int j = colStart; j < list.size(); j++) {
                    HSSFCell cell = (HSSFCell) list.get(j);
                    
                    int type = cell.getCellType();
                    if (type == HSSFCell.CELL_TYPE_STRING) {
                        value = cell.getRichStringCellValue().toString();
                    } else if (type == HSSFCell.CELL_TYPE_NUMERIC) {
                         if(DateUtil.isCellDateFormatted(cell)){
                            value = Utils.formatDateToStringToDBThai(cell.getDateCellValue());
                        }else{
                            value = String.valueOf(cell.getNumericCellValue());
                        }
                    } else if (type == HSSFCell.CELL_TYPE_BOOLEAN) {
                        value = String.valueOf(cell.getBooleanCellValue());
                    } else if (type == HSSFCell.CELL_TYPE_BLANK) {
                        value = "";
                    }
                    
                    
                    
                    ht.put("#"+cell.getColumnIndex(), value);
                    
                    

                }
                
                result.add(ht);
            }

            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
   
   
    private List excelTypeXLSX(String pathFilename,int selectsheet,int startRow,int colStart) throws IOException{
        List sheetData = new ArrayList();
 
        FileInputStream fis = null;
        try {

            InputStream inputStream = new FileInputStream(pathFilename);
            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            

                XSSFSheet sheet = workBook.getSheetAt(selectsheet);
                int totalRows = sheet.getPhysicalNumberOfRows();
                System.out.println("total no of rows >>>>"+totalRows);

                Iterator rows = sheet.rowIterator();

                while (rows.hasNext()) {
                    //XSSFRow row = sheet.getRow(0); //row start 0
                    //XSSFCell c =  row.getCell(0); //cell start 0
                    //System.out.println(c.getStringCellValue());

                    XSSFRow row = (XSSFRow) rows.next();
                    Iterator cells = row.cellIterator();

                    List data = new ArrayList();
                    while (cells.hasNext()) {
                        XSSFCell cell = (XSSFCell) cells.next();
                        data.add(cell);
                    }

                    sheetData.add(data);
                }
            
            
        } catch (FileNotFoundException  e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        
        return getDataExcelXLSX(sheetData,startRow,colStart);
    }
    
    
    private List getDataExcelXLSX(List sheetData,int startRow,int colStart) {

        List result = new ArrayList();
        Hashtable<String,String> ht = null;
        String value = "";
        try{
            
            
            for (int i = startRow; i < sheetData.size(); i++) {
                List list = (List) sheetData.get(i);
                
                ht = new Hashtable<String, String>();
                
                for (int j = colStart; j < list.size(); j++) {
                    XSSFCell cell = (XSSFCell) list.get(j);
                    
                    int type = cell.getCellType();
                    if (type == XSSFCell.CELL_TYPE_STRING) {
                        value = cell.getRichStringCellValue().toString();
                    } else if (type == XSSFCell.CELL_TYPE_NUMERIC) {
                        if(DateUtil.isCellDateFormatted(cell)){
                            value = Utils.formatDateToStringToDBThai(cell.getDateCellValue());
                        }else{
                            value = String.valueOf(cell.getNumericCellValue());
                        }
                        
                    } else if (type == XSSFCell.CELL_TYPE_BOOLEAN) {
                        value = String.valueOf(cell.getBooleanCellValue());
                    } else if (type == XSSFCell.CELL_TYPE_BLANK) {
                        value = "";
                    }
                    
                    
                    ht.put("#"+cell.getColumnIndex(), value);
                    
                }
                
                result.add(ht);
            }

            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
    
    /*
     * Format data result
     * ("000000") (-1234.567);  // -001235
     * ("##")  (-1234.567);  // -1235  , (0);    // 0
     * ("##00") (0);        // 00
     * (".00") (-.567);             // -.57
     * ("0.00") (-.567);             // -0.57
     * ("#.#")(-1234.567);         // -1234.6
     * ("#.######") (-1234.567);         // -1234.567
     * (".######")  (-1234.567);         // -1234.567
     * ("#.000000") (-1234.567);         // -1234.567000
     * 
     * ("#,###,###") (-1234.567);         // -1,235 , (-1234567.890);      // -1,234,568
     * 
     * ("#;(#)") (-1234.567);         // (1235)
     * 
     * ("'#'#") (-1234.567);         // -#1235
     * ("'abc'#") (-1234.567);       // -abc1235
     * 
     */
    public static String convertTypeDoubleToDecimal(String value,String format){
        if(!Utils.NVL(value).equals("")){
            value = value.replace(",", "");
            double d = new Double(value);
            return new DecimalFormat(format).format(d);
        }else{
            return "";
        }
    }
    
}
