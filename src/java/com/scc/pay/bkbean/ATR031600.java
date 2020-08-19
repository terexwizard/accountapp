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
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
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
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;

/**
 *
 * @author terex
 * @version 1.00.00
 * 18/06/2555  9:45:40
 */

@ManagedBean
@SessionScoped
public class ATR031600 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr031600e.xhtml";
    private static final String PAGE_Q  = "atr031600q.xhtml";
    
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

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        

    }
    
    
    public ATR031600() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR031600A");
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031600U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031600D");
            
            
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031600S");
        
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
                DataFormat format = hWBook.createDataFormat();
                hCellstyleR.setDataFormat(format.getFormat("#,##0.00"));
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
                
                HSSFCellStyle hCellstyleRBMoney = hWBook.createCellStyle();
                hCellstyleRBMoney.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                hCellstyleRBMoney.setFont(font18B); 
                hCellstyleRBMoney.setDataFormat(format.getFormat("#,##0.00"));
                CenterUtils.setCellBorder(hCellstyleRBMoney);

                hSheet.setColumnWidth(0,8000);
                hSheet.setColumnWidth(1,19000);
                hSheet.setColumnWidth(2,14000);
                hSheet.setColumnWidth(3,6000);
                hSheet.setColumnWidth(4,6000);
                hSheet.setColumnWidth(5,6000);
                hSheet.setColumnWidth(6,6000);
                
                //hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));

                //Query Data
                HashMap hm = new HashMap<String, String>();
                hm.put("dailydatest", this.getMasterdata().getYear()+"0101");
                hm.put("dailydatefn", this.getMasterdata().getYear()+"1231");
                hm.put("voucherno", "ORV"); //OVERSEA INCOME
                List l = CenterUtils.selectData(hm,"ATR031600_RECEIVE");

                
                
                //Query Data2
                HashMap hm2 = new HashMap<String, String>();
                hm2.put("dailydatest", this.getMasterdata().getYear()+"0101");
                hm2.put("dailydatefn", this.getMasterdata().getYear()+"1231");
                hm2.put("voucherno", "OPV"); //OVERSEA INCOME
                List l2 = CenterUtils.selectData(hm2,"ATR031600_PAYMENT");

                if(l.isEmpty() && l2.isEmpty()){
                    String msg = "ไม่พบข้อมูล";
                    addInfoMessage(null, msg, msg);
                }else{
                    
                   
                    
                    //=================Write Excel=================================
                    
                    HSSFRow row = hSheet.createRow(0);
                    cell = row.createCell(3);
                    cell.setCellValue("USD");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("BAHT");
                    cell.setCellStyle(hCellstyleRB);
       
                    
                    row = hSheet.createRow(3);      
                    cell = row.createCell(0);
                    cell.setCellValue("Oversea Report Received");
                    cell.setCellStyle(hCellstyleCB);
                    
                    cell = row.createCell(3);
                    cell.setCellStyle(hCellstyleR);
                    
                    //==================RECEIVED======================
                    
                    row = hSheet.createRow(4);      
                    cell = row.createCell(0);
                    cell.setCellValue("DATE");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("COMPANYNAME");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(2);
                    cell.setCellValue("REF NO.");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(3);
                    cell.setCellValue("AMOUNT(USD)");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("AMOUNT(BAHT)");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("REMARKS");
                    cell.setCellStyle(hCellstyleHColor);

                    
                    int rowpad = 5;
                    BigDecimal totalrvusd = new BigDecimal(0);
                    BigDecimal totalrvth = new BigDecimal(0);
                    for(int i=1;i<13;i++){
                        String m = Integer.toString(i).length() == 1?"0"+i:""+i;
                        
                        HashMap hmmonth = new HashMap<String, String>();
                        hmmonth.put("dailydatest", this.getMasterdata().getYear()+m+"01");
                        hmmonth.put("dailydatefn", this.getMasterdata().getYear()+m+"31");
                        hmmonth.put("voucherno", "ORV");
                        List lmrv = CenterUtils.selectData(hmmonth,"ATR031600_RECEIVE");
                        
                        int sizemrv = lmrv.size();
                        
                        if(!lmrv.isEmpty()){ //Header Month
                            row = hSheet.createRow(rowpad);      
                            cell = row.createCell(0);
                            cell.setCellValue(new DateFormatSymbols(Locale.ENGLISH).getMonths()[(i-1)]);
                            cell.setCellStyle(hCellstyleL);
                            
                            rowpad++;
                        }                        
                        
                        for(int j=0;j<sizemrv;j++){
                            hmmonth = (HashMap)lmrv.get(j);
                            
                            row = hSheet.createRow(rowpad);      
                            cell = row.createCell(0);
                            cell.setCellValue(Utils.NVL(hmmonth.get("dailydate")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(1);
                            cell.setCellValue(Utils.NVL(hmmonth.get("companyname")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(2);
                            cell.setCellValue(Utils.NVL(hmmonth.get("bankname")));
                            cell.setCellStyle(hCellstyleL);
                            
                            cell = row.createCell(3);
                            cell.setCellValue(CenterUtils.format(Utils.NVL(hmmonth.get("amountusd"))));
                            cell.setCellStyle(hCellstyleR);
                            
                            cell = row.createCell(4);
                            cell.setCellValue(CenterUtils.format(Utils.NVL(hmmonth.get("amountth"))));
                            cell.setCellStyle(hCellstyleR);

                            cell = row.createCell(5);
                            cell.setCellValue(Utils.NVL(hmmonth.get("voucherno_disp")));
                            cell.setCellStyle(hCellstyleL);
                            
                            
                            totalrvusd = totalrvusd.add(new BigDecimal(Utils.NVL(hmmonth.get("amountusd")).equals("")?"0":Utils.NVL(hmmonth.get("amountusd"))));
                            totalrvth = totalrvth.add(new BigDecimal(Utils.NVL(hmmonth.get("amountth")).equals("")?"0":Utils.NVL(hmmonth.get("amountth"))));
                        
                            rowpad++;
                        }
                        
                        
                    }
                    
                    //rowpad++; //เว้นขึ้นเดือนใหม่
                    
                    hSheet.addMergedRegion(new Region(rowpad,(short)0,rowpad,(short)2));
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("TOTAL");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(2);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(3);
                    cell.setCellValue(CenterUtils.format(totalrvusd.toString()));
                    cell.setCellStyle(hCellstyleRBMoney);
                    
                    cell = row.createCell(4);
                    cell.setCellValue(CenterUtils.format(totalrvth.toString()));
                    cell.setCellStyle(hCellstyleRBMoney);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);
                    
                     rowpad++;
                    //TODO Total RECEIVED
                     
                     
                    //================================================
                    rowpad++;
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("Oversea Report Payment");
                    cell.setCellStyle(hCellstyleCB);
                    
                    //==================Payment======================
                    rowpad++;
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("DATE");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("COMPANY'NAME");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(2);
                    cell.setCellValue("REF NO.");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(3);
                    cell.setCellValue("AMOUNT(USD)");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("AMOUNT(BAHT)");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("REMARKS");
                    cell.setCellStyle(hCellstyleHColor);

                    rowpad++;
                    BigDecimal totalpvusd = new BigDecimal(0);
                    BigDecimal totalpvth = new BigDecimal(0);
                    for(int i=1;i<13;i++){
                        String m = Integer.toString(i).length() == 1?"0"+i:""+i;
                        
                        HashMap hmmonth = new HashMap<String, String>();
                        hmmonth.put("dailydatest", this.getMasterdata().getYear()+m+"01");
                        hmmonth.put("dailydatefn", this.getMasterdata().getYear()+m+"31");
                        hmmonth.put("voucherno", "OPV");
                        List lmrv = CenterUtils.selectData(hmmonth,"ATR031600_PAYMENT");
                        
                        int sizemrv = lmrv.size();
                        
                        if(!lmrv.isEmpty()){ //Header Month
                            row = hSheet.createRow(rowpad);      
                            cell = row.createCell(0);
                            cell.setCellValue(new DateFormatSymbols(Locale.ENGLISH).getMonths()[(i-1)]);
                            cell.setCellStyle(hCellstyleL);
                            
                            rowpad++;
                        }                        
                        
                        for(int j=0;j<sizemrv;j++){
                            hmmonth = (HashMap)lmrv.get(j);
                            
                            row = hSheet.createRow(rowpad);      
                            cell = row.createCell(0);
                            cell.setCellValue(Utils.NVL(hmmonth.get("dailydate")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(1);
                            cell.setCellValue(Utils.NVL(hmmonth.get("companyname")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(2);
                            cell.setCellValue(Utils.NVL(hmmonth.get("bankname")));
                            cell.setCellStyle(hCellstyleL);
                            
                            cell = row.createCell(3);
                            cell.setCellValue(CenterUtils.format(Utils.NVL(hmmonth.get("amountusd"))));
                            cell.setCellStyle(hCellstyleR);

                            cell = row.createCell(4);
                            cell.setCellValue(CenterUtils.format(Utils.NVL(hmmonth.get("amountth"))));
                            cell.setCellStyle(hCellstyleR);                            

                            cell = row.createCell(5);
                            cell.setCellValue(Utils.NVL(hmmonth.get("voucherno_disp")));
                            cell.setCellStyle(hCellstyleL);
                            
                            
                            totalpvusd = totalpvusd.add(new BigDecimal(Utils.NVL(hmmonth.get("amountusd")).equals("")?"0":Utils.NVL(hmmonth.get("amountusd"))));
                            totalpvth = totalpvth.add(new BigDecimal(Utils.NVL(hmmonth.get("amountth")).equals("")?"0":Utils.NVL(hmmonth.get("amountth"))));
                        
                            rowpad++;
                        }
                        
                        
                    }
                    
                    //rowpad++; //เว้นขึ้นเดือนใหม่
                    
                    hSheet.addMergedRegion(new Region(rowpad,(short)0,rowpad,(short)2));
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("TOTAL");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(2);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(3);
                    cell.setCellValue(CenterUtils.format(totalpvusd.toString()));
                    cell.setCellStyle(hCellstyleRBMoney);
                    
                    cell = row.createCell(4);
                    cell.setCellValue(CenterUtils.format(totalpvth.toString()));
                    cell.setCellStyle(hCellstyleRBMoney);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);
                    
                     rowpad++;
                    //TODO Total Payment
                    //================================================
                     
                     
                    //Header value sum not query database
                    String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());
                    String condition = "Condition :"+Utils.NVL(this.getMasterdata().getYear());
                    
                    row = hSheet.createRow(1);      
                    cell = row.createCell(0);
                    cell.setCellValue(header);
                    cell.setCellStyle(hCellstyleCB);
                    
                    cell = row.createCell(2);
                    cell.setCellValue("Received");
                    cell.setCellStyle(hCellstyleCB);
                    
                    cell = row.createCell(3);
                    //cell.setCellValue(sum_receive_us(Utils.NVL(this.getMasterdata().getYear())));
                    cell.setCellValue(CenterUtils.format(totalrvusd.toString()));
                    cell.setCellStyle(hCellstyleRBMoney);
                    
                    cell = row.createCell(4);
                    //cell.setCellValue(sum_receive_th(Utils.NVL(this.getMasterdata().getYear())));
                    cell.setCellValue(CenterUtils.format(totalrvth.toString()));
                    cell.setCellStyle(hCellstyleRBMoney);
                    
                    cell = row.createCell(6);
                    cell.setCellValue("ATR031600");
                    cell.setCellStyle(hCellstyleCB);
                    
                    
                    row = hSheet.createRow(2);      
                    cell = row.createCell(0);
                    cell.setCellValue(condition);
                    cell.setCellStyle(hCellstyleCB);
                    
                    cell = row.createCell(2);
                    cell.setCellValue("Payment");
                    cell.setCellStyle(hCellstyleCB);
                    
                    cell = row.createCell(3);
                    //cell.setCellValue(sum_payment_us(Utils.NVL(this.getMasterdata().getYear())));
                    cell.setCellValue(CenterUtils.format(totalpvusd.toString()));
                    cell.setCellStyle(hCellstyleRBMoney);
                    
                    cell = row.createCell(4);
                    //cell.setCellValue(sum_payment_th(Utils.NVL(this.getMasterdata().getYear())));
                    cell.setCellValue(CenterUtils.format(totalpvth.toString()));
                    cell.setCellStyle(hCellstyleRBMoney);
                     
                    
                    
                    ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                    hWBook.write(bOutput);

                    FaceUtil.getDownloadfile(bOutput, "ATR031600_"+CenterUtils.formatfileNameDatetime()+".xls");
                
                
                
                
                    
                }

                }catch(FileNotFoundException e){    
                    e.printStackTrace();
                }catch(IOException e){    
                    e.printStackTrace();
                }finally{

                }
   
        
    }
    
//    private String format(String value){
//        DecimalFormat df = new DecimalFormat("###,##0.00");
//        return df.format( Utils.NVL(value).equals("")?new BigDecimal(0).doubleValue():new BigDecimal(Utils.NVL(value)).doubleValue());
//    }
     
    
    private boolean validategenDataExcel(){
       boolean isok = true;
       
        if(Utils.NVL(this.getMasterdata().getYear()).equals("")){
                
            String msg = MessageUtil.getMessage("EP011");
            addErrorMessage(null,msg,msg);
            return false;

        }
        
        
        //Query Data
        HashMap hm = new HashMap<String, String>();
        hm.put("dailydatest", this.getMasterdata().getYear()+"0101");
        hm.put("dailydatefn", this.getMasterdata().getYear()+"1231");
        hm.put("dailytype", "OIN");
        List l = CenterUtils.selectData(hm,"ATR031600_RECEIVE");



        //Query Data2
        HashMap hm2 = new HashMap<String, String>();
        hm2.put("dailydatest", this.getMasterdata().getYear()+"0101");
        hm2.put("dailydatefn", this.getMasterdata().getYear()+"1231");
        hm2.put("dailytype", "OIN");
        List l2 = CenterUtils.selectData(hm2,"ATR031600_PAYMENT");

        
        
        if(l.isEmpty() && l2.isEmpty()){
            String msg = MessageUtil.getMessage("EP005");
            addInfoMessage(null, msg, msg);
            return false;
        }
        
        return isok;
    }
    
}
