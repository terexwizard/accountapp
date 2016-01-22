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
public class ATR031200 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr031200e.xhtml";
    private static final String PAGE_Q  = "atr031200q.xhtml";
    
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

    }
    
    
    public ATR031200() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR031200A");
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031200U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031200D");
            
            
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR031200S");
        
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

                
                HSSFCellStyle hCellstyleRB = hWBook.createCellStyle();                         
                hCellstyleRB.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         
                hCellstyleRB.setFont(font18B); 
                CenterUtils.setCellBorder(hCellstyleRB);
                
                

                
                //=======Header============ 
                HashMap<String, Object> hmdata = new HashMap<String, Object>();
                HashMap<String, String> hmdataall = new HashMap<String, String>();
                //Query Data company
                List lcompany = new ArrayList();
                HashMap hm = new HashMap<String, String>();
                HashMap<String, String> hmcompany = new HashMap<String, String>();
                hmcompany.put("companyid", this.getMasterdata().getDaily().getCompanyid());
                lcompany = CenterUtils.selectData(hmcompany,"ATR031200_PAYMENT");

                int sizecompany = lcompany.size();
                hmcompany.put("sizecompany",  Utils.NVL(sizecompany));
                for(int i=0;i<sizecompany;i++){
                    hm = (HashMap)lcompany.get(i);       
                    
                    
                    
                    hmcompany.put(Utils.NVL(i),  Utils.NVL(hm.get("companyid")));
                    hmcompany.put(Utils.NVL(i)+"name",  Utils.NVL(hm.get("companyname")));
                    
                    
                    logger.debug(">>company name "+hmcompany.get(Utils.NVL(i)));
                }  
                
                //=============Process Data===================
                ArrayList<String> alweekofyear = CenterUtils.getWeekofYear(Integer.parseInt(this.getMasterdata().getYear()), Integer.parseInt(this.getMasterdata().getMonth()));
                
                for(int i=0;i<sizecompany;i++){
                    String companyid = hmcompany.get(Utils.NVL(i));                    
                    
                    for(String week : alweekofyear){
                        
                        String[] result = week.split("-");
                        
                        HashMap hmmonthpayment = new HashMap<String, String>();
                        hmmonthpayment.put("companyid", companyid);
                        hmmonthpayment.put("dailydatedst", result[0]);
                        hmmonthpayment.put("dailydatedfn", result[1]);
                        List lcompanymonthpayment = CenterUtils.selectData(hmmonthpayment,"ATR031200_PAYMENT_DATA");
                        
                            
                        hmdata.put(Utils.NVL(i)+"_"+companyid+"_"+week, lcompanymonthpayment);
                        
                    }
                }                
                //===============write excel=======================
                for(int i=0;i<sizecompany;i++){
                    String companyid = hmcompany.get(Utils.NVL(i));   
                    
                    
                    HSSFSheet hSheet = hWBook.createSheet(this.replaceName(companyid+"_"+hmcompany.get(i+"name")));
                    hSheet.setColumnWidth(0,5000);
                    hSheet.setColumnWidth(1,14000);
                    hSheet.setColumnWidth(2,5000);
                    hSheet.setColumnWidth(3,10000);
                    hSheet.setDefaultRowHeight(new Short("500"));
                    
                    
                    HSSFRow row = hSheet.createRow(0);      
                        cell = row.createCell(0);
                        cell.setCellValue(hmcompany.get(i+"name"));
                        cell.setCellStyle(hCellstyleCB);


                        row = hSheet.createRow(2);
                        cell = row.createCell(0);
                        cell.setCellValue("JOB.NO");
                        cell.setCellStyle(hCellstyleHColor);

                        cell = row.createCell(1);
                        cell.setCellValue("DATE");
                        cell.setCellStyle(hCellstyleHColor);

                        cell = row.createCell(2);
                        cell.setCellValue("AV.NO.");
                        cell.setCellStyle(hCellstyleHColor);

                        cell = row.createCell(3);
                        cell.setCellValue("AMOUNT");
                        cell.setCellStyle(hCellstyleHColor);
                    
                    
                    int rowpad = 3;
                    BigDecimal totalallpay = new BigDecimal(0);
                    for(String week : alweekofyear){
                         List lcompanymonthpayment = (List)hmdata.get(Utils.NVL(i)+"_"+companyid+"_"+week);
                         
                            int sizelcompanymonthpayment = lcompanymonthpayment.size();
                            BigDecimal totalpay = new BigDecimal(0);
                            for(int k=0;k<sizelcompanymonthpayment;k++){
                                HashMap hmmonthpayment = (HashMap)lcompanymonthpayment.get(k);
                                
                                row = hSheet.createRow(rowpad);      
                                cell = row.createCell(0);
                                cell.setCellValue(Utils.NVL(hmmonthpayment.get("jobref")));
                                cell.setCellStyle(hCellstyleL);
                                
                                cell = row.createCell(1);
                                cell.setCellValue(Utils.NVL(hmmonthpayment.get("dailydate")));
                                cell.setCellStyle(hCellstyle);
                                
                                cell = row.createCell(2);
                                cell.setCellValue(Utils.NVL(hmmonthpayment.get("voucherno_disp")));
                                cell.setCellStyle(hCellstyleL);
                                
                                cell = row.createCell(3);
                                cell.setCellValue(format(Utils.NVL(hmmonthpayment.get("amount2"))));
                                cell.setCellStyle(hCellstyleR);
                                
                                totalpay = totalpay.add(new BigDecimal(Utils.NVL(hmmonthpayment.get("amount2")).equals("")?"0":Utils.NVL(hmmonthpayment.get("amount2"))));
                                        
                                rowpad++;
                            }
                            
                            if(sizelcompanymonthpayment > 0){   //total
                                hSheet.addMergedRegion(new Region(rowpad,(short)0,rowpad,(short)2));
                                row = hSheet.createRow(rowpad);      
                                cell = row.createCell(0);
                                cell.setCellValue("Total");
                                cell.setCellStyle(hCellstyleRB);

                                cell = row.createCell(1);
                                cell.setCellValue("");
                                cell.setCellStyle(hCellstyleRB);

                                cell = row.createCell(2);
                                cell.setCellValue("");
                                cell.setCellStyle(hCellstyleRB);

                                cell = row.createCell(3);
                                cell.setCellValue(format(totalpay.toString()));
                                cell.setCellStyle(hCellstyleRB);

                                rowpad++;
                            }
                        
                        totalallpay = totalallpay.add(totalpay);
                    }
                    
                    hmdataall.put(i+"name", hmcompany.get(i+"name"));
                    hmdataall.put(i+"amount", format(totalallpay.toString()));
                    
                    
                    hSheet.addMergedRegion(new Region(rowpad,(short)0,rowpad,(short)2));
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("Total All");
                    cell.setCellStyle(hCellstyleRB);

                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);

                    cell = row.createCell(2);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleRB);

                    cell = row.createCell(3);
                    cell.setCellValue(format(totalallpay.toString()));
                    cell.setCellStyle(hCellstyleRB);
                    
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
                cell.setCellValue("ATR031200");
                cell.setCellStyle(hCellstyleCB);

                String condition = "Condition : "+CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth()),0)+" "+this.getMasterdata().getYear();
                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
                row = hSheet.createRow(1);      
                cell = row.createCell(0);
                cell.setCellValue(condition);
                cell.setCellStyle(hCellstyleCB);
                
                row = hSheet.createRow(2);
                cell = row.createCell(0);
                cell.setCellValue("Company");
                cell.setCellStyle(hCellstyleHColor);

                cell = row.createCell(1);
                cell.setCellValue("Amount");
                cell.setCellStyle(hCellstyleHColor);

                //========================================
                for(int i=0;i<sizecompany;i++){
                    
                    String namecompany = hmdataall.get(i+"name");
                    String amountcompany = hmdataall.get(i+"amount");
                    
                    hSheet.setColumnWidth(0,15000);
                    row = hSheet.createRow(i+3);
                    cell = row.createCell(0);
                    cell.setCellValue(Utils.NVL(namecompany));
                    cell.setCellStyle(hCellstyleL);
                                        
                    cell = row.createCell(1);
                    cell.setCellValue(amountcompany);
                    cell.setCellStyle(hCellstyleR);
                }     
                
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR031200_"+CenterUtils.formatfileNameDatetime()+".xls");

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
