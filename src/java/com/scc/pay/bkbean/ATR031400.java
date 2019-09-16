/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.f1.Constant;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
public class ATR031400 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr031400e.xhtml";
    private static final String PAGE_Q  = "atr031400q.xhtml";
    
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
        private Date dailydatest;
        private Date dailydatefn;
        private String receivesuccess = "";
        private String month = "";
        private String year = "";

        public Daily getDaily() {
            if(daily == null){
                daily = new Daily();
            }
            return daily;
        }

        public void setDaily(Daily daily) {
            this.daily = daily;
        }

        public Date getDailydatest() {
            return dailydatest;
        }

        public void setDailydatest(Date dailydatest) {
            this.dailydatest = dailydatest;
        }

        public Date getDailydatefn() {
            return dailydatefn;
        }

        public void setDailydatefn(Date dailydatefn) {
            this.dailydatefn = dailydatefn;
        }

        public String getReceivesuccess() {
            return receivesuccess;
        }

        public void setReceivesuccess(String receivesuccess) {
            this.receivesuccess = receivesuccess;
        }

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

        

    }
    
    
    public ATR031400() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR031400A");
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
            genDataExcel();
        }
        
    }
    
    
    private boolean validateAdd(){
        boolean isok = true;


        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031400U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031400D");
            
            
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
    }
    
    private void search(){
            
//            logger.debug("q para "+ this.getSearchparam().getProvCode()+", "+ 
//                        this.getSearchparam().getProvName()+" ");
//       
            HashMap<String, String> hm = new HashMap<String, String>();
            
            hm.put("dailydate", Utils.formatDateToStringToDBEn(this.getSearchparam().getDailydatest()));
            hm.put("dailydatefn", Utils.formatDateToStringToDBEn(this.getSearchparam().getDailydatefn()));
            hm.put("jobref", this.getSearchparam().getDaily().getJobref());
   
            BKBUQuery.getIns().setQueryparam(hm);
            BKBUQuery.getIns().search();
          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031400S");
        
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
                HSSFSheet hSheet = hWBook.getSheetAt(0);                                     //instance เลือก sheetที่ 1

                Font font16 = hWBook.createFont();                                           //กำหนด font style
                font16.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font16.setFontName("Angsana New");                                         //กำหนด font
                font16.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา

                Font font14 = hWBook.createFont();                                           //กำหนด font style
                font14.setFontHeightInPoints((short)14);                                     //กำหนดขนาดของ font
                font14.setFontName("Angsana New");                                         //กำหนด font

                HSSFCellStyle hCellstyle = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         //กำหนด ตัวอักษรให้อยู่กึ่งกลาง
                hCellstyle.setFont(font14);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyle);
                
                HSSFCellStyle hCellstyleL = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleL.setFont(font14);                                                  //เรียกใช้ style font
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
                
                Font font18B = hWBook.createFont();                                           //กำหนด font style
                font18B.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font18B.setFontName("Angsana New");
                font18B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา
                
                HSSFCellStyle hCellstyleCB = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleCB.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleCB.setFont(font18B);                                                  //เรียกใช้ style font

                HSSFCellStyle hCellstyleRB = hWBook.createCellStyle();
                hCellstyleRB.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                hCellstyleRB.setFont(font18B); 
                CenterUtils.setCellBorder(hCellstyleRB);

                hSheet.setColumnWidth(0,5000);
                hSheet.setColumnWidth(1,14000);
                hSheet.setColumnWidth(2,5000);
                hSheet.setColumnWidth(3,10000);
                hSheet.setColumnWidth(4,10000);
                hSheet.setColumnWidth(5,14000);
                hSheet.setColumnWidth(6,14000);
                
                hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));

                //Query Data
                HashMap hm = new HashMap<String, String>();
                hm.put("dailydatest", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"01");
                hm.put("dailydatefn", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"31");
                List l = CenterUtils.selectData(hm,"ATR031400_RECEIVE");

                //Query Data2
                HashMap hm2 = new HashMap<String, String>();
                hm2.put("dailydatest", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"01");
                hm2.put("dailydatefn", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"31");
                List l2 = CenterUtils.selectData(hm2,"ATR031400_PAYMENT");


                if(l.isEmpty() && l2.isEmpty()){
                    String msg = "ไม่พบข้อมูล";
                    addInfoMessage(null, msg, msg);
                }else{
                    
                    ArrayList<String> aldateheader = new ArrayList<String>();
                    ArrayList<ArrayList<String>> alcompanyrv = new ArrayList<ArrayList<String>>();
                    ArrayList<ArrayList<String>> alcompanypay = new ArrayList<ArrayList<String>>();
                        
                        
                    //Query Data
                    for(int j=0;j<l.size();j++){
                            hm = (HashMap)l.get(j); 
                            String companyid = Utils.NVL(hm.get("companyid"));
                            String companyname = Utils.NVL(hm.get("companyname"));
                            
                            ArrayList<String> alrv = new ArrayList<String>();
                            alrv.add(companyname);
                            
                            for(int k=3;k>=0;k--){
                                Calendar c = Calendar.getInstance(Locale.ENGLISH);   
                                c.set(Integer.parseInt(this.getMasterdata().getYear()), Integer.parseInt(this.getMasterdata().getMonth())-1, 1);
                                c.add(Calendar.MONTH, -k);

                                System.out.println(">>"+k+" : "+Utils.formatDateToStringToDBEn(c.getTime()));

                                //วันที่ย้อนหลัง
                                String d = Utils.formatDateToStringToDBEn(c.getTime()).substring(0, 6);
                                String st = d+"01";
                                String fn = d+"31";
                                if(aldateheader.isEmpty() || aldateheader.size() < 4){
                                    aldateheader.add(d); //วันที่
                                }
                            
                                HashMap hmprv = new HashMap<String, String>();
                                hmprv.put("dailydatest", st);
                                hmprv.put("dailydatefn", fn);
                                hmprv.put("companyid",companyid);
                                List lprv = CenterUtils.selectData(hmprv,"ATR031400_SUMRECEIVE");
                                
                                if(!lprv.isEmpty()){
                                    hmprv = (HashMap)lprv.get(0);
                                    
                                    alrv.add(Utils.NVL(hmprv.get("sumdata")).equals("")?"0.00":Utils.NVL(hmprv.get("sumdata")));
                                }  
                            }  
                            
                            alcompanyrv.add(alrv);
                    }
                    
                    
                    
                    //Query Data2
                    for(int j=0;j<l2.size();j++){
                            hm = (HashMap)l2.get(j); 
                            String companyid = Utils.NVL(hm.get("companyid"));
                            String companyname = Utils.NVL(hm.get("companyname"));
                            
                            ArrayList<String> alpay = new ArrayList<String>();
                            alpay.add(companyname);
                            
                            for(int k=3;k>=0;k--){
                                Calendar c = Calendar.getInstance(Locale.ENGLISH);   
                                c.set(Integer.parseInt(this.getMasterdata().getYear()), Integer.parseInt(this.getMasterdata().getMonth())-1, 1);
                                c.add(Calendar.MONTH, -k);

                                System.out.println(">>"+k+" : "+Utils.formatDateToStringToDBEn(c.getTime()));

                                //วันที่ย้อนหลัง
                                String d = Utils.formatDateToStringToDBEn(c.getTime()).substring(0, 6);
                                String st = d+"01";
                                String fn = d+"31";
                                if(aldateheader.isEmpty() || aldateheader.size() < 4){
                                    aldateheader.add(d); //วันที่
                                }
                            
                                HashMap hmprv = new HashMap<String, String>();
                                hmprv.put("dailydatest", st);
                                hmprv.put("dailydatefn", fn);
                                hmprv.put("companyid",companyid);
                                List lprv = CenterUtils.selectData(hmprv,"ATR031400_SUMPAYMENT");
                                
                                if(!lprv.isEmpty()){
                                    hmprv = (HashMap)lprv.get(0);
                                    
                                    alpay.add(Utils.NVL(hmprv.get("sumdata")).equals("")?"0.00":Utils.NVL(hmprv.get("sumdata")));
                                }  
                            }
                            
                            alcompanypay.add(alpay);
                    }
                    
                    //=================Write Excel=================================
                    
                    String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());
                    String condition = "Condition :"+ new DateFormatSymbols().getMonths()[(Integer.parseInt(this.getMasterdata().getMonth())-1)]+" "+Utils.NVL(this.getMasterdata().getYear());
                    
                    HSSFRow row = hSheet.createRow(1);      
                    cell = row.createCell(0);
                    cell.setCellValue(header);
                    cell.setCellStyle(hCellstyleCB);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("ATR031400");
                    cell.setCellStyle(hCellstyleCB);
                    
                    
                    row = hSheet.createRow(2);      
                    cell = row.createCell(0);
                    cell.setCellValue(condition);
                    cell.setCellStyle(hCellstyleCB);
                    
                    row = hSheet.createRow(3);      
                    cell = row.createCell(0);
                    cell.setCellValue("Deposit Container");
                    cell.setCellStyle(hCellstyleCB);
                    
                    //==================RECEIVED======================
                    
                    row = hSheet.createRow(4);      
                    cell = row.createCell(0);
                    cell.setCellValue("SEQ");
                    cell.setCellStyle(hCellstyleHColor);

                    cell = row.createCell(1);
                    cell.setCellValue("CUSTOMER");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    int columndate = 2;
                    for(String date :aldateheader){
                        cell = row.createCell(columndate);
                                                
                        
                        String smonth = new DateFormatSymbols().getMonths()[(Integer.parseInt(date.substring(4))-1)]+" "+Utils.NVL(date.substring(0,4));
                        
                        cell.setCellValue(smonth);
                        cell.setCellStyle(hCellstyleHColor);
                        
                        columndate++;
                    }
                    
                    
                    int rowpad = 5;
                    
                    //===========Recevice============
                    BigDecimal sumrv1 = new BigDecimal(0);
                    BigDecimal sumrv2 = new BigDecimal(0);
                    BigDecimal sumrv3 = new BigDecimal(0);
                    BigDecimal sumrv4 = new BigDecimal(0);
                    int sizealcompanyrv = alcompanyrv.size();
                    for(int i=0;i<sizealcompanyrv;i++){
                        
                        row = hSheet.createRow(rowpad); 
                        cell = row.createCell(0);
                        cell.setCellValue((i+1)+".");
                        cell.setCellStyle(hCellstyleL);
                        
                        ArrayList<String> almonthrv = alcompanyrv.get(i);
                        for(int j=0;j<almonthrv.size();j++){  
                            
                            String data = almonthrv.get(j);
                            
                            cell = row.createCell((j+1));
                            if(j==0){
                                cell.setCellValue(data);
                                cell.setCellStyle(hCellstyleL);
                            }else{
                                cell.setCellValue(format(data));
                                cell.setCellStyle(hCellstyleR);
                            }
                            
                            
                            logger.debug(">>terex "+data);
                            if(j==1){
                                sumrv1 = sumrv1.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==2){
                                sumrv2 = sumrv2.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==3){
                                sumrv3 = sumrv3.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==4){
                                sumrv4 = sumrv4.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }
                            
                        }
                        
                        rowpad++;
                    }
                    
                    hSheet.addMergedRegion(new Region(rowpad,(short)0,rowpad,(short)1));
                    row = hSheet.createRow(rowpad); 
                    cell = row.createCell(0);
                    cell.setCellValue("Total Recevice");
                    cell.setCellStyle(hCellstyleRB);
                    
                    
                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);
                    
                    
                    cell = row.createCell(2);
                    cell.setCellValue(format(sumrv1.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(3);
                    cell.setCellValue(format(sumrv2.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(4);
                    cell.setCellValue(format(sumrv3.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(5);
                    cell.setCellValue(format(sumrv4.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    rowpad++;  
                    
                    row = hSheet.createRow(rowpad); 
                    cell = row.createCell(0);
                    cell.setCellValue("PAYMENT");
                    cell.setCellStyle(hCellstyleHColor);
                        
                    rowpad++;    
                    
                    //===========Payment============
                    BigDecimal sumpay1 = new BigDecimal(0);
                    BigDecimal sumpay2 = new BigDecimal(0);
                    BigDecimal sumpay3 = new BigDecimal(0);
                    BigDecimal sumpay4 = new BigDecimal(0);
                    int sizealcompanypay = alcompanypay.size();
                    for(int i=0;i<sizealcompanypay;i++){
                        
                        row = hSheet.createRow(rowpad); 
                        cell = row.createCell(0);
                        cell.setCellValue((i+1)+".");
                        cell.setCellStyle(hCellstyleL);
                        
                        
                        ArrayList<String> almonthpay = alcompanypay.get(i);
                        for(int j=0;j<almonthpay.size();j++){  
                            
                            String data = almonthpay.get(j);
                            
                            cell = row.createCell((j+1));
                            if(j==0){
                                cell.setCellValue(data);
                                cell.setCellStyle(hCellstyleL);
                            }else{
                                cell.setCellValue(format(data));
                                cell.setCellStyle(hCellstyleR);
                            }
                            
                            if(j==1){
                                sumpay1 = sumpay1.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==2){
                                sumpay2 = sumpay2.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==3){
                                sumpay3 = sumpay3.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==4){
                                sumpay4 = sumpay4.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }
                        }
                        
                        rowpad++;
                    }
                    
                    
                    hSheet.addMergedRegion(new Region(rowpad,(short)0,rowpad,(short)1)); 
                    row = hSheet.createRow(rowpad); 
                    cell = row.createCell(0);
                    cell.setCellValue("Total Payment");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);
                    
                    
                    cell = row.createCell(2);
                    cell.setCellValue(format(sumpay1.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(3);
                    cell.setCellValue(format(sumpay2.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(4);
                    cell.setCellValue(format(sumpay3.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(5);
                    cell.setCellValue(format(sumpay4.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    
                    //===================================         
                    
                    
                    ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                    hWBook.write(bOutput);

                    FaceUtil.getDownloadfile(bOutput, "ATR031400_"+CenterUtils.formatfileNameDatetime()+".xls");
                
                
                
                
                    
                }

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
       
        if(Utils.NVL(this.getMasterdata().getYear()).equals("")){
                
            String msg = MessageUtil.getMessage("EP011");
            addErrorMessage(null,msg,msg);
            return false;

        }
        
        
        //Query Data
        HashMap hm = new HashMap<String, String>();
        hm.put("dailydatest", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"01");
        hm.put("dailydatefn", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"31");
        List l = CenterUtils.selectData(hm,"ATR031400_RECEIVE");



        //Query Data2
        HashMap hm2 = new HashMap<String, String>();
        hm2.put("dailydatest", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"01");
        hm2.put("dailydatefn", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"31");
        List l2 = CenterUtils.selectData(hm2,"ATR031400_PAYMENT");

        
        
        if(l.isEmpty() && l2.isEmpty()){
            String msg = MessageUtil.getMessage("EP005");
            addInfoMessage(null, msg, msg);
            return false;
        }
        
        return isok;
    }
}
