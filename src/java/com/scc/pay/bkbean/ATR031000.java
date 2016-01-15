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
public class ATR031000 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr031000e.xhtml";
    private static final String PAGE_Q  = "atr031000q.xhtml";
    
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
    
    
    public ATR031000() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR031000A");
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031000U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031000D");
            
            
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031000S");
        
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

                hSheet.setColumnWidth(0,14000);
                hSheet.setColumnWidth(1,14000);
                hSheet.setColumnWidth(2,14000);
                hSheet.setColumnWidth(3,10000);
                hSheet.setColumnWidth(4,10000);
                hSheet.setColumnWidth(5,14000);
                hSheet.setColumnWidth(6,14000);
                
                hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));

                //Query Data
                HashMap hm = new HashMap<String, String>();
                hm.put("dailydateyst", this.getMasterdata().getYear()+"0101");
                hm.put("dailydateyfn", this.getMasterdata().getYear()+"1231");
                List l = CenterUtils.selectData(hm,"ATR031000_WAREHOUSE_RECEIVE");

                
                
                //Query Data2
                HashMap hm2 = new HashMap<String, String>();
                hm2.put("dailydateyst", this.getMasterdata().getYear()+"0101");
                hm2.put("dailydateyfn", this.getMasterdata().getYear()+"1231");
                List l2 = CenterUtils.selectData(hm2,"ATR031000_WAREHOUSE_PAYMENT");

                if(l.isEmpty() && l2.isEmpty()){
                    String msg = "ไม่พบข้อมูล";
                    addInfoMessage(null, msg, msg);
                }else{
                    
                    ArrayList<ArrayList<String>> almainrv = new ArrayList<ArrayList<String>>();
                    ArrayList<ArrayList<String>> almainpay = new ArrayList<ArrayList<String>>();
                    
                    //===================
                    
                    int sizewarehouse_receive = l.size();
                    //hm.put("size ATR031000_WAREHOUSE_RECEIVE",  Utils.NVL(sizewarehouse_receive));
                    for(int i=0;i<sizewarehouse_receive;i++){
                        hm = (HashMap)l.get(i);  
                        
                        String payby = Utils.NVL(hm.get("payby"));
                        ArrayList<String> almonthrv = new ArrayList<String>();
                        for(int j=1;j<13;j++){
                            HashMap hmmonth = new HashMap<String, String>();
                            String m = Integer.toString(j).length() == 1?"0"+j:""+j;
                            hmmonth.put("dailydatedst", this.getMasterdata().getYear()+m+"01");
                            hmmonth.put("dailydatedfn", this.getMasterdata().getYear()+m+"31");
                            hmmonth.put("payby", payby);
                            List ld = CenterUtils.selectData(hmmonth,"ATR031000_WAREHOUSE_SUMRECEIVE");
                            
                            
                            //TODO
                            if(!ld.isEmpty()){
                                hmmonth = (HashMap)ld.get(0);  
                                
                                almonthrv.add(Utils.NVL(hmmonth.get("amount")).equals("")?"0.00":Utils.NVL(hmmonth.get("amount")));
                            }else{
                                almonthrv.add("0.00");
                            }
                        }
                        
                        almainrv.add(almonthrv);
                    }
                
                    //==============================
                    int sizewarehouse_payment = l2.size();
                    //hm2.put("size ATR031000_WAREHOUSE_PAYMENT",  Utils.NVL(sizewarehouse_payment));
                    for(int i=0;i<sizewarehouse_payment;i++){
                        hm2 = (HashMap)l2.get(i);   
                        
                        
                        String descriptioncode = Utils.NVL(hm.get("descriptioncode"));  
                        ArrayList<String> almonthpay = new ArrayList<String>();
                        for(int j=1;j<13;j++){                            
                            
                            HashMap hmmonth = new HashMap<String, String>();
                            String m = Integer.toString(j).length() == 1?"0"+j:""+j;
                            hmmonth.put("dailydatedst", this.getMasterdata().getYear()+m+"01");
                            hmmonth.put("dailydatedfn", this.getMasterdata().getYear()+m+"31");
                            hmmonth.put("descriptioncode", descriptioncode);
                            List ld = CenterUtils.selectData(hmmonth,"ATR031000_WAREHOUSE_SUMPAYMENT");
                            
                            
                            //TODO
                            if(!ld.isEmpty()){
                                hmmonth = (HashMap)ld.get(0);  
                                
                                almonthpay.add(Utils.NVL(hmmonth.get("amount2")).equals("")?"0.00":Utils.NVL(hmmonth.get("amount2")));
                            }else{
                                almonthpay.add("0.00");
                            }
                        }
                        
                        almainpay.add(almonthpay);
                    }
                    
                    //=================Write Excel=================================
                    
                    String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());
                    String condition = "Condition :"+Utils.NVL(this.getMasterdata().getYear());
                    
                    HSSFRow row = hSheet.createRow(1);      
                    cell = row.createCell(0);
                    cell.setCellValue(header);
                    cell.setCellStyle(hCellstyleCB);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("ATR031000");
                    cell.setCellStyle(hCellstyleCB);
                    
                    
                    row = hSheet.createRow(2);      
                    cell = row.createCell(0);
                    cell.setCellValue(condition);
                    cell.setCellStyle(hCellstyleCB);
                    
                    row = hSheet.createRow(3);      
                    cell = row.createCell(0);
                    cell.setCellValue("WHAREHOUSE/CASH FLOW");
                    cell.setCellStyle(hCellstyleCB);
                    
                    //==================RECEIVED======================
                    
                    row = hSheet.createRow(4);      
                    cell = row.createCell(0);
                    cell.setCellValue("RECEIVED");
                    cell.setCellStyle(hCellstyleHColor);

                    for(int i=1;i<13;i++){
                        cell = row.createCell(i);
                        cell.setCellValue(new DateFormatSymbols().getMonths()[(i-1)]+" "+Utils.NVL(this.getMasterdata().getYear()));
                        cell.setCellStyle(hCellstyleHColor);
                    }
                    
                    int rowpad = 5;
                    int sizealmrv = almainrv.size();
                    BigDecimal totalrvx1 = new BigDecimal(0);
                    BigDecimal totalrvx2 = new BigDecimal(0);
                    BigDecimal totalrvx3 = new BigDecimal(0);
                    BigDecimal totalrvx4 = new BigDecimal(0);
                    BigDecimal totalrvx5 = new BigDecimal(0);
                    BigDecimal totalrvx6 = new BigDecimal(0);
                    BigDecimal totalrvx7 = new BigDecimal(0);
                    BigDecimal totalrvx8 = new BigDecimal(0);
                    BigDecimal totalrvx9 = new BigDecimal(0);
                    BigDecimal totalrvx10 = new BigDecimal(0);
                    BigDecimal totalrvx11 = new BigDecimal(0);
                    BigDecimal totalrvx12 = new BigDecimal(0);
                    for(int i=0;i<sizealmrv;i++){
                        
                        row = hSheet.createRow(rowpad);                        
                        cell = row.createCell(0);
                        hm = (HashMap)l.get(i);                          
                        String bankname = Utils.NVL(hm.get("bankname"));
                        cell.setCellValue(bankname);
                        cell.setCellStyle(hCellstyleL);
                        
                        
                        ArrayList<String> almonthrv = almainrv.get(i);
                        for(int j=1;j<13;j++){                            
                            String data = almonthrv.get((j-1));
                            
                            cell = row.createCell(j);
                            cell.setCellValue(format(Utils.NVL(data)));
                            cell.setCellStyle(hCellstyleR);

                            if(j==1){
                                totalrvx1 = totalrvx1.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==2){
                                totalrvx2 = totalrvx2.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==3){
                                totalrvx3 = totalrvx3.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==4){
                                totalrvx4 = totalrvx4.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==5){
                                totalrvx5 = totalrvx5.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==6){
                                totalrvx6 = totalrvx6.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==7){
                                totalrvx7 = totalrvx7.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==8){
                                totalrvx8 = totalrvx8.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==9){
                                totalrvx9 = totalrvx9.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==10){
                                totalrvx10 = totalrvx10.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==11){
                                totalrvx11 = totalrvx11.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==12){
                                totalrvx12 = totalrvx12.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }
                        }
                        
                        rowpad++;
                    }
                    
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("TOTAL RECEIVED");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(1);
                    cell.setCellValue(format(totalrvx1.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(2);
                    cell.setCellValue(format(totalrvx2.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(3);
                    cell.setCellValue(format(totalrvx3.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(4);
                    cell.setCellValue(format(totalrvx4.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(5);
                    cell.setCellValue(format(totalrvx5.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(6);
                    cell.setCellValue(format(totalrvx6.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(7);
                    cell.setCellValue(format(totalrvx7.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(8);
                    cell.setCellValue(format(totalrvx8.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(9);
                    cell.setCellValue(format(totalrvx9.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(10);
                    cell.setCellValue(format(totalrvx10.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(11);
                    cell.setCellValue(format(totalrvx11.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(12);
                    cell.setCellValue(format(totalrvx12.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    //TODO Total RECEIVED
                    
                    rowpad++;
                    
                    //============Payment==============
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("COST");
                    cell.setCellStyle(hCellstyleHColor);

                    rowpad++;
                    
                    int sizealmpay = almainpay.size();
                    BigDecimal totalpayx1 = new BigDecimal(0);
                    BigDecimal totalpayx2 = new BigDecimal(0);
                    BigDecimal totalpayx3 = new BigDecimal(0);
                    BigDecimal totalpayx4 = new BigDecimal(0);
                    BigDecimal totalpayx5 = new BigDecimal(0);
                    BigDecimal totalpayx6 = new BigDecimal(0);
                    BigDecimal totalpayx7 = new BigDecimal(0);
                    BigDecimal totalpayx8 = new BigDecimal(0);
                    BigDecimal totalpayx9 = new BigDecimal(0);
                    BigDecimal totalpayx10 = new BigDecimal(0);
                    BigDecimal totalpayx11 = new BigDecimal(0);
                    BigDecimal totalpayx12 = new BigDecimal(0);
                    for(int i=0;i<sizealmpay;i++){
                        
                        row = hSheet.createRow(rowpad);                        
                        cell = row.createCell(0);
                        hm = (HashMap)l2.get(i);                          
                        String dscptdesc = Utils.NVL(hm.get("dscptdesc"));
                        cell.setCellValue(dscptdesc);
                        cell.setCellStyle(hCellstyleL);
                        
                        ArrayList<String> almonthpay = almainpay.get(i);
                        for(int j=1;j<13;j++){                            
                            String data = almonthpay.get((j-1));
                            
                            cell = row.createCell(j);
                            cell.setCellValue(format(Utils.NVL(data)));
                            cell.setCellStyle(hCellstyleR);

                            
                            if(j==1){
                                totalpayx1 = totalpayx1.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==2){
                                totalpayx2 = totalpayx2.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==3){
                                totalpayx3 = totalpayx3.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==4){
                                totalpayx4 = totalpayx4.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==5){
                                totalpayx5 = totalpayx5.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==6){
                                totalpayx6 = totalpayx6.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==7){
                                totalpayx7 = totalpayx7.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==8){
                                totalpayx8 = totalpayx8.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==9){
                                totalpayx9 = totalpayx9.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==10){
                                totalpayx10 = totalpayx10.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==11){
                                totalpayx11 = totalpayx11.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }else if(j==12){
                                totalpayx12 = totalpayx12.add(new BigDecimal(Utils.NVL(data).equals("")?"0":Utils.NVL(data)));
                            }
                        }
                        
                        rowpad++;
                    }
                    
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("TOTAL COST");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(1);
                    cell.setCellValue(format(totalpayx1.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(2);
                    cell.setCellValue(format(totalpayx2.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(3);
                    cell.setCellValue(format(totalpayx3.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(4);
                    cell.setCellValue(format(totalpayx4.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(5);
                    cell.setCellValue(format(totalpayx5.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(6);
                    cell.setCellValue(format(totalpayx6.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(7);
                    cell.setCellValue(format(totalpayx7.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(8);
                    cell.setCellValue(format(totalpayx8.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(9);
                    cell.setCellValue(format(totalpayx9.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(10);
                    cell.setCellValue(format(totalpayx10.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(11);
                    cell.setCellValue(format(totalpayx11.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(12);
                    cell.setCellValue(format(totalpayx12.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    //TODO Total COST
                
                    rowpad++;
                    
                    //BALANCE COST FLOW
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("BALANCE COST FLOW");
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(1);
                    cell.setCellValue(format((totalpayx1.subtract(totalrvx1)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(2);
                    cell.setCellValue(format((totalpayx2.subtract(totalrvx2)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(3);
                    cell.setCellValue(format((totalpayx3.subtract(totalrvx3)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(4);
                    cell.setCellValue(format((totalpayx4.subtract(totalrvx4)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(5);
                    cell.setCellValue(format((totalpayx5.subtract(totalrvx5)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(6);
                    cell.setCellValue(format((totalpayx6.subtract(totalrvx6)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(7);
                    cell.setCellValue(format((totalpayx7.subtract(totalrvx7)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(8);
                    cell.setCellValue(format((totalpayx8.subtract(totalrvx8)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(9);
                    cell.setCellValue(format((totalpayx9.subtract(totalrvx9)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(10);
                    cell.setCellValue(format((totalpayx10.subtract(totalrvx10)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(11);
                    cell.setCellValue(format((totalpayx11.subtract(totalrvx11)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    cell = row.createCell(12);
                    cell.setCellValue(format((totalpayx12.subtract(totalrvx12)).toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
                    
                    ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                    hWBook.write(bOutput);

                    FaceUtil.getDownloadfile(bOutput, "ATR031000_"+CenterUtils.formatfileNameDatetime()+".xls");
                
                
                
                
                    
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
        hm.put("dailydateyst", this.getMasterdata().getYear()+"0101");
        hm.put("dailydateyfn", this.getMasterdata().getYear()+"1231");
        List l = CenterUtils.selectData(hm,"ATR031000_WAREHOUSE_RECEIVE");



        //Query Data2
        HashMap hm2 = new HashMap<String, String>();
        hm2.put("dailydateyst", this.getMasterdata().getYear()+"0101");
        hm2.put("dailydateyfn", this.getMasterdata().getYear()+"1231");
        List l2 = CenterUtils.selectData(hm2,"ATR031000_WAREHOUSE_PAYMENT");

        
        
        if(l.isEmpty() && l2.isEmpty()){
            String msg = MessageUtil.getMessage("EP005");
            addInfoMessage(null, msg, msg);
            return false;
        }
        
        return isok;
    }
}
