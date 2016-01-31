/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.f1.Constant;
import static com.scc.f1.backingbean.BKBPageImpl.SEARCH_ACTION_NEW;
import static com.scc.f1.backingbean.BKBPageImpl.SEARCH_ACTION_REQUERY;
import com.scc.pay.business.BusinessFactory;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.MessageUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Daily;
import com.scc.pay.util.CenterUtils;
import com.scc.pay.util.FaceUtil;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Font;

/**
 *
 * @author terex
 * @version 1.00.00
 * 18/06/2555  9:45:40
 */

@ManagedBean
@SessionScoped
public class ATR030800 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr030800e.xhtml";
    private static final String PAGE_Q  = "atr030800q.xhtml";
    
    private Map<String, String> searchselectedrow ;
    
    public Map<String, String> getSearchselectedrow() {
        if(searchselectedrow == null) searchselectedrow = new HashMap<String, String>();
        return searchselectedrow;
    }

    public void setSearchselectedrow(Map<String, String> searchselectedrow) {
        this.searchselectedrow = searchselectedrow;
    }

    public MainData getMasterdata() {
        if(this.masterdata == null){
           this.masterdata = new MainData();
        }
        return masterdata;
    }

    public void setMasterdata(MainData masterdata) {
        this.masterdata = masterdata;
    }

    public MainData getSearchparam() {
        if(this.searchparam == null){
           this.searchparam = new MainData();
        }
        return searchparam;
    }

    public void setSearchparam(MainData searchparam) {
        this.searchparam = searchparam;
    }
    
    
    public class MainData extends BBBase{
        private Daily daily = null;
        private String month = "";
        private String year = "";
        private String rdoflag = "";
        private String reporttype = "";
        private Date dailydate = null;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getRdoflag() {
            return rdoflag;
        }

        public void setRdoflag(String rdoflag) {
            this.rdoflag = rdoflag;
        }
        public Daily getDaily() {
            if(daily == null){
                daily = new Daily();
            }
            return daily;
        }

        public void setDaily(Daily daily) {
            this.daily = daily;
        }

        public String getReporttype() {
            return reporttype;
        }

        public void setReporttype(String reporttype) {
            this.reporttype = reporttype;
        }

        public Date getDailydate() {
            return dailydate;
        }

        public void setDailydate(Date dailydate) {
            this.dailydate = dailydate;
        }

    }
    
    
    public ATR030800() {
        setAutoconvertthai(true);
        setShowphase(true);
        
    }
    
    @Override
    protected void processCommand(String mode) {
        
        logger.debug("mode :" +mode);
        
        if(mode.equals(BKBPage.ModeAdd)){
            add();
        }else if(mode.equals(BKBPage.ModeQuery)){
        
            query();
            
        }else if(mode.equals(BKBPage.ModeInsert)){
        
            insert();
            
        }else if(mode.equals(BKBPage.ModeSearch)){
        
            search();
            
        }else if(mode.equals(BKBPage.ModeUpdate)){
        
            update();
            
        }else if(mode.equals(BKBPage.ModeDelete)){
        
            delete();
            
        }else if(mode.equals(BKBPage.ModeReset)){
        
            reset();
            
        }else if(mode.equals(BKBPage.ModeResetSearch)){
        
            resetSearch();
            
        }
    }

    @Override
    protected String doGoQuery() {
        //throw new UnsupportedOperationException("Not supported yet.");
        
        query();
        return "";
    }

    @Override
    protected String doGoInsert() {
        //throw new UnsupportedOperationException("Not supported yet.");
        
        insert();
        return "";
    }
    
    private void query(){
        
        setModequery(true);
        setModeadd(false);
        setModeupdate(false);

        clearAllData();

        
        
        redirectPage(PAGE_Q);
        
        if(searchaction.equals(SEARCH_ACTION_NEW)){
            
            BKBUQuery.getIns().clearListData();

        }else{
            BKBUQuery.getIns().autoResearch();
            
        }
        
        
        
    }
    
    private void insert(){
        
        setModeupdate(false);
        setModequery(false);
        setModeadd(true);

        clearAllData();
        
        redirectPage(PAGE_E);
        
        
        
    }
    
    @Override
    protected void clearAllData(){
            masterdata      = null; 
    }
    
    @Override
    protected void afterProcessValidate(){
        
        if(!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()){
            convertUIThaiAll();
        
            
        }
        
        
        
    }
    
    private void add(){
        
//       if(validateAdd()){
//            
////            toDB();
//            
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR030800A");
//            
//            
//            ib.process(this);
//            
//            genMessage(ib);
//            
//            if(ib.isOk()){
//                clearAllData();
//            }
//            
//            
//        }else{
//            
////            FacesContext.getCurrentInstance().renderResponse();
//            
//        }
        
        //logger.debug(">>terex "+validategenDataExcel());
        
        if(validategenDataExcel()){
            if(Utils.NVL(this.getMasterdata().getReporttype()).equals("1")){
                genDataExcel1();
            }else{
                genDataExcel();
            }
        }
        
    }
    
    
    private boolean validateAdd(){
        boolean isok = true;


        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030800U");
            
            
        ib.process(this);

        genMessage(ib);

        if(ib.isOk()){

            setModeupdate(false);
            setModeadd(false);
            setModequery(true);
            
            searchaction = SEARCH_ACTION_REQUERY;
            
           
            redirectPage(PAGE_Q);
            
            BKBUQuery.getIns().autoResearch();
            
            
        }
        
        
        
    }
    
    public String delete(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030800D");
            
            
        ib.process(this);

        genMessage(ib);

        if(ib.isOk()){

            setModeupdate(false);
            setModeadd(false);
            setModequery(true);
            
            searchaction = SEARCH_ACTION_REQUERY;
            

            redirectPage(PAGE_Q);
            
            BKBUQuery.getIns().autoResearch();
            
        }
        
        return "";
        
    }
    
    private void reset(){
        
        clearAllData();
        
        initialValue();
        
        redirectPage(PAGE_E);
        
    }
    
    private void resetSearch(){
        
        searchparam     = null;
        
        BKBUQuery.getIns().clearListData();
        
        redirectPage(PAGE_Q);
    }
    
    @Override
    protected void initFormMenu() {
        
        clearAllData();
        
        searchparam     = null;
        
//        BKBUQuery.getIns().clearListData();
//        
//        this.getSearchparam();
//        search();
        
        initialValue();
        
    }
    
    private void initialValue(){
        if(Utils.NVL(this.getMasterdata().getRdoflag()).equals("")){
            this.getMasterdata().setRdoflag("N");
        }
        
        if(Utils.NVL(this.getMasterdata().getMonth()).equals("")){
            this.getMasterdata().setMonth(Utils.getcurMonth());
        }
        
        if(Utils.NVL(this.getMasterdata().getYear()).equals("")){
            this.getMasterdata().setYear(Utils.getcurYear());
        }
        
        if(Utils.NVL(this.getMasterdata().getReporttype()).equals("")){
            this.getMasterdata().setReporttype("1");
        }
    }
    
    private void search(){
            
//            logger.debug("q para "+ this.getSearchparam().getProvCode()+", "+ 
//                        this.getSearchparam().getProvName()+" ");
//       
//            HashMap<String, String> hm = new HashMap<String, String>();
//            
//            hm.put("dailydate", Utils.formatDateToStringToDBEn(this.getSearchparam().getDailydatest()));
//            hm.put("dailydatefn", Utils.formatDateToStringToDBEn(this.getSearchparam().getDailydatefn()));
//            hm.put("jobref", this.getSearchparam().getDaily().getJobref());
//   
//            BKBUQuery.getIns().setQueryparam(hm);
//            BKBUQuery.getIns().search();
          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030800S");
        
        ib.process(this);

        if(ib.isOk()){
            
            
//            toScreen();
            
            setModeupdate(true);
            setModeadd(false);
            setModequery(false);

            redirectPage(PAGE_E);

            
        }
     
    }
    
    
    @Override
    protected void afterApplyRequestValues() {
        //super.afterApplyRequestValues();
        
        
    }

    @Override
    protected void toDB() {
        //throw new UnsupportedOperationException("Not supported yet.");
        
      
        
    }

    @Override
    protected void toScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    protected void beforeRenderResponse(){
        BKBUQuery.getIns().initDataTable(PAGE_Q);
        
    }
    
    
    public void genDataExcel1(){
        
            
            HSSFCell cell    = null; 
            OutputStream out = null;
            double ONEPIXEL      = 36.57;
            
            try{
                
                String pathFile = Constant.context_realpath+"/templeteExcel/ATR030100.xls";   //ชี้ path  file excel

                logger.debug(">>pathFile "+pathFile.replace("\\", "/"));

                FileInputStream fIn	 = new FileInputStream(pathFile.replace("\\", "/")); //instance เปิดไฟล์
                POIFSFileSystem fPOI = new POIFSFileSystem(fIn);                             //instance POI cycle
                HSSFWorkbook hWBook = new HSSFWorkbook(fPOI);                                //instance สร้าง workbook     
                

                Font font16 = hWBook.createFont();                                           //กำหนด font style
                font16.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font16.setFontName("Angsana New");                                         //กำหนด font
                font16.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา

                Font font14 = hWBook.createFont();                                           //กำหนด font style
                font14.setFontHeightInPoints((short)14);                                     //กำหนดขนาดของ font
                font14.setFontName("Angsana New");                                         //กำหนด font

                HSSFCellStyle hCellstyle = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         //กำหนด ตัวอักษรให้อยู่กึ่งกลาง
                hCellstyle.setFont(font14);       
                CenterUtils.setCellBorder(hCellstyle);
                
                HSSFCellStyle hCellstyleL = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleL.setFont(font14);      
                CenterUtils.setCellBorder(hCellstyleL);
                
                HSSFCellStyle hCellstyleR = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleR.setFont(font14);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyleR);
                
                HSSFCellStyle hCellstyleHColor = hWBook.createCellStyle();                         
                hCellstyleHColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         
                hCellstyleHColor.setFont(font16);                   
                hCellstyleHColor.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                hCellstyleHColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                CenterUtils.setCellBorder(hCellstyleHColor);
                
                HSSFCellStyle hCellstyleHColorL = hWBook.createCellStyle();                         
                hCellstyleHColorL.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         
                hCellstyleHColorL.setFont(font16);                   
                hCellstyleHColorL.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                hCellstyleHColorL.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                CenterUtils.setCellBorder(hCellstyleHColorL);
                
                Font font18B = hWBook.createFont();                                           //กำหนด font style
                font18B.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font18B.setFontName("Angsana New");
                font18B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา
                
                HSSFCellStyle hCellstyleCB = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleCB.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleCB.setFont(font18B);                                                  //เรียกใช้ style font

                

                
                //=======Header============ 
                HashMap<String, Object> hmdata = new HashMap<String, Object>();
                //Query Data company
                List lcompany = new ArrayList();
                HashMap hm = new HashMap<String, String>();
                HashMap<String, String> hmcompany = new HashMap<String, String>();
                hmcompany.put("invcomid", this.getMasterdata().getDaily().getCompanyid());
                hmcompany.put("submitdate", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydate()));
                lcompany = CenterUtils.selectData(hmcompany,"lookup_data_invoice_distinct");

                int sizecompany = lcompany.size();
                hmcompany.put("sizecompany",  Utils.NVL(sizecompany));
                for(int i=0;i<sizecompany;i++){
                    hm = (HashMap)lcompany.get(i);       
                    
                    
                    
                    hmcompany.put(Utils.NVL(i),  Utils.NVL(hm.get("invcomid")));
                    hmcompany.put(Utils.NVL(i)+"name",  Utils.NVL(hm.get("companyname")));
                    
                    
                    logger.debug(">>company name "+hmcompany.get(Utils.NVL(i)));
                }  
                
                //=============Process Data===================  
                
                HSSFSheet hSheet = hWBook.getSheetAt(0);
                String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());

                hSheet.addMergedRegion(new Region(0,(short)0,0,(short)2));
                HSSFRow row = hSheet.createRow(0);      
                cell = row.createCell(0);
                cell.setCellValue(header);
                cell.setCellStyle(hCellstyleCB);

                cell = row.createCell(6);
                cell.setCellValue("ATR030900");
                cell.setCellStyle(hCellstyleCB);

                String condition = "Condition : "+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydate()),"/")+" Status : "+(this.getMasterdata().getRdoflag().equals("Y")?"Clear":"Not Clear");
                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
                row = hSheet.createRow(1);      
                cell = row.createCell(0);
                cell.setCellValue(condition);
                cell.setCellStyle(hCellstyleCB);
                    
                int rowpad = 3;    
                for(int j=0;j<sizecompany;j++){

                    String companyid = hmcompany.get(Utils.NVL(j));

                    logger.debug(">>company name companyid "+companyid);

                    String st = Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydate());
                    HashMap hmmonth = new HashMap<String, String>();
                    hmmonth.put("invcomid", companyid);
                    hmmonth.put("invdatest", st);
                    hmmonth.put("invdatefn", st);
                    if(this.getMasterdata().getRdoflag().equals("Y")){
                        hmmonth.put("clearflagY", "Y");  //clear
                    }else if(this.getMasterdata().getRdoflag().equals("N")){
                        hmmonth.put("clearflagN", "N");
                    }
                    List lcompanydate = CenterUtils.selectData(hmmonth,"lookup_data_invoice");
                    
                    
                    //========================================
                    
                    int sizecompanydate = lcompanydate.size();
                    for(int k=0;k<sizecompanydate;k++){
                        hmmonth = (HashMap)lcompanydate.get(k);  
                        
                        if(k==0){
                            hSheet.addMergedRegion(new Region(rowpad,(short)0,rowpad,(short)3));
                            row = hSheet.createRow(rowpad);
                            cell = row.createCell(0);
                            cell.setCellValue(Utils.NVL(hmmonth.get("company")));
                            cell.setCellStyle(hCellstyleHColorL);
                            
                            for(int l=1;l<4;l++){
                                cell = row.createCell(l);
                                cell.setCellValue("");
                                cell.setCellStyle(hCellstyleHColorL);
                            }
                            rowpad++;
                        }
                        
                        
                        row = hSheet.createRow(rowpad);
                        cell = row.createCell(0);
                        cell.setCellValue(k+1);
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(1);
                        cell.setCellValue(Utils.NVL(hmmonth.get("invno")));
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(2);
                        cell.setCellValue(Utils.NVL(hmmonth.get("jobno")));
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(3);
                        cell.setCellValue(format(Utils.NVL(hmmonth.get("totalall"))));
                        cell.setCellStyle(hCellstyleR);
                        rowpad++;
                    }
                    
                    
                    rowpad++;

                }
                //=================================================
                
                
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030800_"+CenterUtils.formatfileNameDatetime()+".xls");

            }catch(FileNotFoundException e){    
                e.printStackTrace();
            }catch(IOException e){    
                e.printStackTrace();
            }finally{

            }
   
        
    }
     
    public void genDataExcel(){
        
            
            HSSFCell cell    = null; 
            OutputStream out = null;
            double ONEPIXEL      = 36.57;
            
            try{
                
                String pathFile = Constant.context_realpath+"/templeteExcel/ATR030100.xls";   //ชี้ path  file excel

                logger.debug(">>pathFile "+pathFile.replace("\\", "/"));

                FileInputStream fIn	 = new FileInputStream(pathFile.replace("\\", "/")); //instance เปิดไฟล์
                POIFSFileSystem fPOI = new POIFSFileSystem(fIn);                             //instance POI cycle
                HSSFWorkbook hWBook = new HSSFWorkbook(fPOI);                                //instance สร้าง workbook     
                

                Font font16 = hWBook.createFont();                                           //กำหนด font style
                font16.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font16.setFontName("Angsana New");                                         //กำหนด font
                font16.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา

                Font font14 = hWBook.createFont();                                           //กำหนด font style
                font14.setFontHeightInPoints((short)14);                                     //กำหนดขนาดของ font
                font14.setFontName("Angsana New");                                         //กำหนด font

                HSSFCellStyle hCellstyle = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         //กำหนด ตัวอักษรให้อยู่กึ่งกลาง
                hCellstyle.setFont(font14);       
                CenterUtils.setCellBorder(hCellstyle);
                
                HSSFCellStyle hCellstyleL = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleL.setFont(font14);      
                CenterUtils.setCellBorder(hCellstyleL);
                
                HSSFCellStyle hCellstyleR = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleR.setFont(font14);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyleR);
                
                HSSFCellStyle hCellstyleHColor = hWBook.createCellStyle();                         
                hCellstyleHColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         
                hCellstyleHColor.setFont(font14);                   
                hCellstyleHColor.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                hCellstyleHColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                CenterUtils.setCellBorder(hCellstyleHColor);
                
                Font font18B = hWBook.createFont();                                           //กำหนด font style
                font18B.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font18B.setFontName("Angsana New");
                font18B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา
                
                HSSFCellStyle hCellstyleCB = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleCB.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleCB.setFont(font18B);                                                  //เรียกใช้ style font

                

                
                //=======Header============ 
//                String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());
//
//                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
//                HSSFRow row = hSheet.createRow(1);      
//                cell = row.createCell(0);
//                cell.setCellValue(header);
//                cell.setCellStyle(hCellstyleCB);                
                
                //String condition = "Condition :"+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),"/");
         
                HashMap<String, Object> hmdata = new HashMap<String, Object>();
                //Query Data company
                List lcompany = new ArrayList();
                HashMap hm = new HashMap<String, String>();
                HashMap<String, String> hmcompany = new HashMap<String, String>();
                hmcompany.put("invcomid", this.getMasterdata().getDaily().getCompanyid());
                //lcompany = CenterUtils.selectData(hmcompany,"lookup_invoicecompany");
                lcompany = CenterUtils.selectData(hmcompany,"lookup_invoicecompany_invoice");

                int sizecompany = lcompany.size();
                hmcompany.put("sizecompany",  Utils.NVL(sizecompany));
                for(int i=0;i<sizecompany;i++){
                    hm = (HashMap)lcompany.get(i);       
                    
                    
                    
                    hmcompany.put(Utils.NVL(i),  Utils.NVL(hm.get("invcomid")));
                    hmcompany.put(Utils.NVL(i)+"name",  Utils.NVL(hm.get("companyname")));
                    
                    
                    logger.debug(">>company name "+hmcompany.get(Utils.NVL(i)));
                }  
                
                //=============Process Data===================
                ArrayList alallcompany = new ArrayList();
                ArrayList aldateheader = new ArrayList();
                ArrayList alcompanyrowx = new ArrayList();
                
                 for(int i=3;i>0;i--){
                    Calendar c = Calendar.getInstance();   
                    c.set(Integer.parseInt(this.getMasterdata().getYear()), Integer.parseInt(this.getMasterdata().getMonth())-1, 1);
                    c.add(Calendar.MONTH, -i);

                    System.out.println(">>"+i+" : "+Utils.formatDateToStringToDBEn(c.getTime()));
                    
                    //วันที่ย้อนหลัง
                    String d = Utils.formatDateToStringToDBEn(c.getTime()).substring(0, 6);
                    String st = d+"01";
                    String fn = d+"31";
                    aldateheader.add(d);
                    
                    for(int j=0;j<sizecompany;j++){
                        
                        String companyid = hmcompany.get(Utils.NVL(j));
                        
                        logger.debug(">>company name companyid "+companyid);
                        
                        HashMap hmmonth = new HashMap<String, String>();
                        hmmonth.put("invcomid", companyid);
                        hmmonth.put("invdatest", st);
                        hmmonth.put("invdatefn", fn);
                        if(this.getMasterdata().getRdoflag().equals("Y")){
                            hmmonth.put("clearflagY", "Y");
                        }else{
                            hmmonth.put("clearflagN", "N");
                        }
                        List lcompanymonth = CenterUtils.selectData(hmmonth,"lookup_data_invoice");
                        
                        hmdata.put(Utils.NVL(i)+"_"+companyid, lcompanymonth);
                        //hmdata.put(Utils.NVL(i)+"_date", CenterUtils.convertStringMonthYear(d));
                        hmdata.put(Utils.NVL(i)+"_date", d);
                    }
                }
                 
                    //เดือนปัจจุบัน
                    Calendar c = Calendar.getInstance();   
                    c.set(Integer.parseInt(this.getMasterdata().getYear()), Integer.parseInt(this.getMasterdata().getMonth())-1, 1);

                    System.out.println(">>now : "+Utils.formatDateToStringToDBEn(c.getTime()));
                    
                    //วันที่ย้อนหลัง
                    String d = Utils.formatDateToStringToDBEn(c.getTime()).substring(0, 6);
                    String st = d+"01";
                    String fn = d+"31";
                    aldateheader.add(d);
                    
                    for(int j=0;j<sizecompany;j++){
                        
                        String companyid = hmcompany.get(Utils.NVL(j));
                        
                        logger.debug(">>company name companyid "+companyid);
                        
                        HashMap hmmonth = new HashMap<String, String>();
                        hmmonth.put("invcomid", companyid);
                        hmmonth.put("invdatest", st);
                        hmmonth.put("invdatefn", fn);
                        if(this.getMasterdata().getRdoflag().equals("Y")){
                            hmmonth.put("clearflagY", "Y");
                        }else{
                            hmmonth.put("clearflagN", "N");
                        }
                        List lcompanymonth = CenterUtils.selectData(hmmonth,"lookup_data_invoice");
                        
                        hmdata.put(Utils.NVL(0)+"_"+companyid, lcompanymonth);
                        //hmdata.put(Utils.NVL(0)+"_date", CenterUtils.convertStringMonthYear(d));
                        hmdata.put(Utils.NVL(0)+"_date", d);
                    }
                //=================================================
                

                //===============write excel=======================
                 
                int rowpadformheader = 2;
                                    
                for(int j=0;j<sizecompany;j++){
                        
                        String companyid = hmcompany.get(Utils.NVL(j));  
                        //HSSFSheet hSheet = hWBook.getSheetAt(rowdatasheet+j);
                        logger.debug(">>sheet name "+this.replaceName(companyid+"_"+hmcompany.get(j+"name")));
                        alcompanyrowx = new ArrayList();
                        alcompanyrowx.add(companyid);
                        alcompanyrowx.add(hmcompany.get(j+"name"));
                        
                        HSSFSheet hSheet = hWBook.createSheet(this.replaceName(companyid+"_"+hmcompany.get(j+"name")));
                        
                        hSheet.setColumnWidth(0, 10000);
                        hSheet.setColumnWidth(1, 10000);
                        hSheet.setColumnWidth(2, 10000);
                        hSheet.setColumnWidth(3, 10000);
                        hSheet.setColumnWidth(4, 10000);
                        hSheet.setDefaultRowHeight(new Short("500"));
                        
                        
                        hSheet.addMergedRegion(new Region(0,(short)0,0,(short)4));
                        HSSFRow row = hSheet.createRow(0);      
                        cell = row.createCell(0);
                        cell.setCellValue(hmcompany.get(j+"name"));
                        cell.setCellStyle(hCellstyleCB);
                        
                        
                        row = hSheet.createRow(1);
                        cell = row.createCell(0);
                        cell.setCellValue("No.");
                        cell.setCellStyle(hCellstyleHColor);

                        cell = row.createCell(1);
                        cell.setCellValue("REF NO");
                        cell.setCellStyle(hCellstyleHColor);

                        cell = row.createCell(2);
                        cell.setCellValue("SUBMIT DATE");
                        cell.setCellStyle(hCellstyleHColor);

                        cell = row.createCell(3);
                        cell.setCellValue("INV");
                        cell.setCellStyle(hCellstyleHColor);
                        
                        cell = row.createCell(4);
                        cell.setCellValue("BATH");
                        cell.setCellStyle(hCellstyleHColor);
                    
                    BigDecimal sumtotalall = new BigDecimal(0);
                    BigDecimal sumtotal3 = new BigDecimal(0);
                    BigDecimal sumtotal2 = new BigDecimal(0);
                    BigDecimal sumtotal1 = new BigDecimal(0);
                    BigDecimal sumtotal0 = new BigDecimal(0);   
                    int sizelcompanymonth = 0;  
                    int rowdata = 0;
                    
                    for(int i=3;i>=0;i--){
                        
                        List lcompanymonth = (List)hmdata.get(Utils.NVL(i)+"_"+companyid);
                        String date = (String)hmdata.get(Utils.NVL(i)+"_date");
                        if(i==3){
                            alcompanyrowx.add(date);
                        }
                        date = CenterUtils.convertStringMonthYear(date);
                        sizelcompanymonth = lcompanymonth.size();
                        
                         if(sizelcompanymonth == 0){
                            rowdata++;
                            
                            //เดือนนั้นไม่มีค่า
                            //sum total
                            hSheet.addMergedRegion(new Region(rowpadformheader+rowdata,(short)0,rowpadformheader+rowdata,(short)3));
                            row = hSheet.createRow(rowpadformheader+rowdata);
                            cell = row.createCell(0);
                            cell.setCellValue("Total "+date);
                            cell.setCellStyle(hCellstyleR);
                            
                            cell = row.createCell(1);
                            cell.setCellValue("");
                            cell.setCellStyle(hCellstyleR);
                            
                            cell = row.createCell(2);
                            cell.setCellValue("");
                            cell.setCellStyle(hCellstyleR);
                            
                            cell = row.createCell(3);
                            cell.setCellValue("");
                            cell.setCellStyle(hCellstyleR);
                            
                            cell = row.createCell(4);
                            cell.setCellValue("0.00");
                            cell.setCellStyle(hCellstyleR);
                            
                            alcompanyrowx.add("0.00");
                        }else{
                            for(int k=0;k<sizelcompanymonth;k++){
                                HashMap hmcompanymonth = new HashMap<String, String>();
                                hmcompanymonth = (HashMap)lcompanymonth.get(k);


                                rowdata++;
                                row = hSheet.createRow(rowpadformheader+rowdata);
                                cell = row.createCell(0);
                                cell.setCellValue(k+1);
                                cell.setCellStyle(hCellstyle);

                                cell = row.createCell(1);
                                cell.setCellValue(Utils.NVL(hmcompanymonth.get("jobno")));
                                cell.setCellStyle(hCellstyle);

                                cell = row.createCell(2);
                                //cell.setCellValue(Utils.NVL(hmcompanymonth.get("invdate_disp")));
                                cell.setCellValue(Utils.NVL(hmcompanymonth.get("submitdate_disp")));
                                cell.setCellStyle(hCellstyle);

                                cell = row.createCell(3);
                                cell.setCellValue(Utils.NVL(hmcompanymonth.get("invno")));
                                cell.setCellStyle(hCellstyle);

                                cell = row.createCell(4);
                                cell.setCellValue(format(Utils.NVL(hmcompanymonth.get("totalall"))));
                                cell.setCellStyle(hCellstyleR);   



                                sumtotalall = sumtotalall.add(new BigDecimal(Utils.NVL(hmcompanymonth.get("totalall")).equals("")?"0":Utils.NVL(hmcompanymonth.get("totalall"))));
                                if(i==3){
                                    sumtotal3 = sumtotal3.add(new BigDecimal(Utils.NVL(hmcompanymonth.get("totalall")).equals("")?"0":Utils.NVL(hmcompanymonth.get("totalall"))));
                                }else if(i==2){
                                    sumtotal2 = sumtotal2.add(new BigDecimal(Utils.NVL(hmcompanymonth.get("totalall")).equals("")?"0":Utils.NVL(hmcompanymonth.get("totalall"))));
                                }else if(i==1){
                                    sumtotal1 = sumtotal1.add(new BigDecimal(Utils.NVL(hmcompanymonth.get("totalall")).equals("")?"0":Utils.NVL(hmcompanymonth.get("totalall"))));
                                }else if(i==0){
                                    sumtotal0 = sumtotal0.add(new BigDecimal(Utils.NVL(hmcompanymonth.get("totalall")).equals("")?"0":Utils.NVL(hmcompanymonth.get("totalall"))));
                                }
                            }  
                            
                             //sum total
                            rowdata++;
                            hSheet.addMergedRegion(new Region(rowpadformheader+rowdata,(short)0,rowpadformheader+rowdata,(short)3));
                            row = hSheet.createRow(rowpadformheader+rowdata);
                            cell = row.createCell(0);
                            cell.setCellValue("Total "+date);
                            cell.setCellStyle(hCellstyleR);
                            
                            cell = row.createCell(1);
                            cell.setCellValue("");
                            cell.setCellStyle(hCellstyleR);
                            
                            cell = row.createCell(2);
                            cell.setCellValue("");
                            cell.setCellStyle(hCellstyleR);
                            
                            cell = row.createCell(3);
                            cell.setCellValue("");
                            cell.setCellStyle(hCellstyleR);

                            cell = row.createCell(4);
                            if(i==3){
                                cell.setCellValue(format(sumtotal3.toString()));
                                
                                alcompanyrowx.add(sumtotal3.toString());
                            }else if(i==2){
                                cell.setCellValue(format(sumtotal2.toString()));
                                
                                alcompanyrowx.add(sumtotal2.toString());
                            }else if(i==1){
                                cell.setCellValue(format(sumtotal1.toString()));
                                
                                alcompanyrowx.add(sumtotal1.toString());
                            }else if(i==0){
                                cell.setCellValue(format(sumtotal0.toString()));
                                
                                alcompanyrowx.add(sumtotal0.toString());
                            }
                            cell.setCellStyle(hCellstyleR); 
                        
                         }
                    }
                    //sum total
                    rowdata++;
                    hSheet.addMergedRegion(new Region(rowpadformheader+rowdata,(short)0,rowpadformheader+rowdata,(short)3));
                    row = hSheet.createRow(rowpadformheader+rowdata);
                    cell = row.createCell(0);
                    cell.setCellValue("Total All");
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(2);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(3);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleR);

                    cell = row.createCell(4);
                    cell.setCellValue(format(sumtotalall.toString()));
                    cell.setCellStyle(hCellstyleR); 
                    
                    
                    if(companyid.equals("0001")){
                        logger.debug(">>terex xx"+sumtotalall.toString());
                    }
                    
                    alcompanyrowx.add(sumtotalall.toString());
                    
                    alallcompany.add(alcompanyrowx);
                } 
                     
                
                //================สรุปหน้าเปิด===================================
                HSSFSheet hSheet = hWBook.getSheetAt(0);
                
                    String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());

                    hSheet.addMergedRegion(new Region(0,(short)0,0,(short)2));
                    HSSFRow row = hSheet.createRow(0);      
                    cell = row.createCell(0);
                    cell.setCellValue(header);
                    cell.setCellStyle(hCellstyleCB);

                    cell = row.createCell(6);
                    cell.setCellValue("ATR030800");
                    cell.setCellStyle(hCellstyleCB);
                    
                    String condition = "Condition : "+CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth()),0)+" "+this.getMasterdata().getYear()+" Status : "+(this.getMasterdata().getRdoflag().equals("Y")?"Clear":"Not Clear");
                    hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
                    row = hSheet.createRow(1);      
                    cell = row.createCell(0);
                    cell.setCellValue(condition);
                    cell.setCellStyle(hCellstyleCB);
                    
                    //========================================
                
                    row = hSheet.createRow(2);
                    cell = row.createCell(0);
                    cell.setCellValue("Customer");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("Balance "+CenterUtils.convertStringMonthYear(Utils.NVL(aldateheader.get(0))));
                    cell.setCellStyle(hCellstyleHColor);
                    
                int size = alallcompany.size();
                for(int i=0;i<size;i++){
                    
                    ArrayList alcompanyrowxx = (ArrayList)alallcompany.get(i);
                    
//                    if(Utils.NVL(alcompanyrowxx.get(0)).equals("0001")){
//                        logger.debug(">>terex xx"+alcompanyrowxx.size());
//                        
//                        for(int x=0;x<alcompanyrowxx.size();x++){
//                            logger.debug(">>terex xx"+Utils.NVL(alcompanyrowxx.get(x)));
//                        }
//                        
//                    }
                    
                    hSheet.setColumnWidth(0,15000);
                    row = hSheet.createRow(i+3);
                    cell = row.createCell(0);
                    cell.setCellValue(Utils.NVL(alcompanyrowxx.get(1)));
                    cell.setCellStyle(hCellstyleL);
                                        
                    cell = row.createCell(1);
                    cell.setCellValue(format(Utils.NVL(alcompanyrowxx.get(6))));
                    cell.setCellStyle(hCellstyleR);
                    
//                    cell = row.createCell(1);
//                    cell.setCellValue(format(this.sumdata_invoice(Utils.NVL(alcompanyrowxx.get(0)),Utils.NVL(alcompanyrowxx.get(2)))));
//                    cell.setCellStyle(hCellstyleR);
//                    
//                    cell = row.createCell(2);
//                    cell.setCellValue(format(Utils.NVL(alcompanyrowxx.get(3))));
//                    cell.setCellStyle(hCellstyleR);
//                    
//                    cell = row.createCell(3);
//                    cell.setCellValue(format(Utils.NVL(alcompanyrowxx.get(4))));
//                    cell.setCellStyle(hCellstyleR);
//                    
//                    cell = row.createCell(4);
//                    cell.setCellValue(format(Utils.NVL(alcompanyrowxx.get(5))));
//                    cell.setCellStyle(hCellstyleR); 
//                    
//                    cell = row.createCell(5);
//                    cell.setCellValue(format(Utils.NVL(alcompanyrowxx.get(6))));
//                    cell.setCellStyle(hCellstyleR); 
                }     
                
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030800_"+CenterUtils.formatfileNameDatetime()+".xls");

            }catch(FileNotFoundException e){    
                e.printStackTrace();
            }catch(IOException e){    
                e.printStackTrace();
            }finally{

            }
   
        
    }
    
    private String format(String value){
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format( Utils.NVL(value).equals("")?new BigDecimal(0).doubleValue():new BigDecimal(Utils.NVL(value)).doubleValue());
    }
     
    
    private boolean validategenDataExcel(){
       boolean isok = true;
       
          if(Utils.NVL(this.getMasterdata().getRdoflag()).equals("") &&
             Utils.NVL(this.getMasterdata().getDaily().getCompanyid()).equals("") &&
             (Utils.NVL(this.getMasterdata().getMonth()).equals("") || Utils.NVL(this.getMasterdata().getYear()).equals(""))    ){
                
            String msg = MessageUtil.getMessage("EP011");
            addErrorMessage(null,msg,msg);
            return false;

        }
        
        return isok;
    }
    
    private String replaceName(String name){
        String result = name.replace("[", "");
        result = result.replace("]", "");
        result = result.replace("/", "");
        
        return result;
    }
    
    
    private String sumdata_invoice(String companyid,String date){
        
        String result = "0.00";
        
        HashMap hmmonth = new HashMap<String, String>();
        hmmonth.put("invcomid", companyid);
        hmmonth.put("invdatefn", date+"01");
        if(this.getMasterdata().getRdoflag().equals("Y")){
            hmmonth.put("clearflagY", "Y");
        }else{
            hmmonth.put("clearflagN", "N");
        }
        List lcompanymonth = CenterUtils.selectData(hmmonth,"lookup_sumdata_invoice");
        
        if(!lcompanymonth.isEmpty()){
            hmmonth = (HashMap)lcompanymonth.get(0);
            
            result = Utils.NVL(hmmonth.get("sumdata"));
        }
        
        
        return result;
    }
}
