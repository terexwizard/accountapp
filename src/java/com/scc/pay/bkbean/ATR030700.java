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
public class ATR030700 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr030700e.xhtml";
    private static final String PAGE_Q  = "atr030700q.xhtml";
    
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
        private String type = "";

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        

    }
    
    
    public ATR030700() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR030700A");
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
        
        logger.debug(">>terex "+validategenDataExcel());
        
        if(validategenDataExcel()){
            genDataExcel();
        }
        
    }
    
    
    private boolean validateAdd(){
        boolean isok = true;


        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030700U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030700D");
            
            
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
        
        
        if(Utils.NVL(this.getMasterdata().getType()).equals("")){
            this.getMasterdata().setType("N");
        }
        
        if(Utils.NVL(this.getMasterdata().getDailydatest()).equals("")){
            this.getMasterdata().setDailydatest(Utils.getcurDateTime());
        }
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030700S");
        
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
                font16.setFontName("TH SarabunPSK");                                         //กำหนด font
                font16.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา

                Font font14 = hWBook.createFont();                                           //กำหนด font style
                font14.setFontHeightInPoints((short)14);                                     //กำหนดขนาดของ font
                font14.setFontName("TH SarabunPSK");                                         //กำหนด font

                HSSFCellStyle hCellstyle = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         //กำหนด ตัวอักษรให้อยู่กึ่งกลาง
                hCellstyle.setFont(font16);       
                CenterUtils.setCellBorder(hCellstyle);
                
                HSSFCellStyle hCellstyleL = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleL.setFont(font16);      
                CenterUtils.setCellBorder(hCellstyleL);
                
                HSSFCellStyle hCellstyleR = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleR.setFont(font16);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyleR);
                
                HSSFCellStyle hCellstyleHColor = hWBook.createCellStyle();                         
                hCellstyleHColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         
                hCellstyleHColor.setFont(font16);                   
                hCellstyleHColor.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                hCellstyleHColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                CenterUtils.setCellBorder(hCellstyleHColor);
                
                Font font18B = hWBook.createFont();                                           //กำหนด font style
                font18B.setFontHeightInPoints((short)18);                                     //กำหนดขนาดของ font
                font18B.setFontName("TH SarabunPSK");
                font18B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา
                
                HSSFCellStyle hCellstyleCB = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleCB.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleCB.setFont(font18B);                                                  //เรียกใช้ style font

                

                
                //=======Header============ 
                String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());

                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
                HSSFRow row = hSheet.createRow(1);      
                cell = row.createCell(0);
                cell.setCellValue(header);
                cell.setCellStyle(hCellstyleCB);
                
                String type = "";
                if(Utils.NVL(this.getMasterdata().getType()).equals("C")){
                    type = "CLEARING CHEQUE";
                }else if(Utils.NVL(this.getMasterdata().getType()).equals("N")){
                    type = "NOT CLEARING CHEQUE";
                }else{
                   type = "ALL"; 
                }
                
                String condition = "Condition :"+type+" "+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),"/");
                
                
                hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));
                row = hSheet.createRow(2);      
                cell = row.createCell(0);
                cell.setCellValue(condition);
                cell.setCellStyle(hCellstyleCB);

                BigDecimal rcvpastusd = new BigDecimal(0);
                BigDecimal rcvpastth = new BigDecimal(0);
                BigDecimal paidpastusd = new BigDecimal(0);
                BigDecimal paidpastth = new BigDecimal(0);
                
//                if(!l.isEmpty()){
                  
                    hSheet.setColumnWidth(3, 10000);
                    hSheet.setColumnWidth(4, 10000);
                    
                    //hSheet.addMergedRegion(new Region(3,(short)0,3,(short)2));
                    hSheet.setColumnWidth((short)0,(short)(ONEPIXEL*300));
                    row = hSheet.createRow(8);      
                    cell = row.createCell(0);
                    cell.setCellValue("CHQ DATE");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("DOC.");
                    cell.setCellStyle(hCellstyleHColor);
                         
                    cell = row.createCell(2);
                    cell.setCellValue("CHQ.NO");
                    cell.setCellStyle(hCellstyleHColor);

                    cell = row.createCell(3);
                    cell.setCellValue("BANK");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("COMPANY NAME");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("RCV");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(6);
                    cell.setCellValue("PAID");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(7);
                    cell.setCellValue("CLEARED DATE");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    
                    //Query Data
                    List lold = new ArrayList();
                    HashMap hmold = new HashMap<String, String>();
                    if(Utils.NVL(this.getMasterdata().getType()).equals("N")){  //ถ้ายังไม่เคลียร์
                        hmold = new HashMap<String, String>();
//                        hmold.put("chequedateold", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                        hmold.put("chequedatenotclear", Utils.NVL(this.getMasterdata().getType()));

                        lold = CenterUtils.selectData(hmold,"lookup_daily_cheque");

                        int sizeold = lold.size();
                        for(int i=0;i<sizeold;i++){
                            hmold = (HashMap)lold.get(i);


                            row = hSheet.createRow(9+i);
                            cell = row.createCell(0);
                            cell.setCellValue(Utils.convertDateStringToScreen(Utils.NVL(hmold.get("dailydate")),"/"));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(1);
                            cell.setCellValue(Utils.NVL(hmold.get("vouchernodocno")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(2);
                            cell.setCellValue(Utils.NVL(hmold.get("chequeno")));
                            cell.setCellStyle(hCellstyle);

                            
                            cell = row.createCell(3);
                            cell.setCellValue(Utils.NVL(hmold.get("bankname")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(4);
                            cell.setCellValue(Utils.NVL(hmold.get("companyname")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(5);
                            if(Utils.NVL(hmold.get("monetaryusd")).equals("false")){
                                cell.setCellValue(format(Utils.NVL(hmold.get("receivedamount"))));

                                rcvpastth = rcvpastth.add(new BigDecimal(Utils.NVL(hmold.get("receivedamount")).equals("")?"0":Utils.NVL(hmold.get("receivedamount"))));
                            }else{
                                cell.setCellValue(format(Utils.NVL(hmold.get("amount")))+"$");

                                rcvpastusd = rcvpastusd.add(new BigDecimal(Utils.NVL(hmold.get("amount")).equals("")?"0":Utils.NVL(hmold.get("amount"))));
                            }
                            cell.setCellStyle(hCellstyleR);

                            cell = row.createCell(6);
                            if(Utils.NVL(hmold.get("monetaryusd")).equals("false")){
                                cell.setCellValue(format(Utils.NVL(hmold.get("paidamount"))));

                                paidpastth = paidpastth.add(new BigDecimal(Utils.NVL(hmold.get("paidamount")).equals("")?"0":Utils.NVL(hmold.get("paidamount"))));
                            }else{
                                cell.setCellValue(format(Utils.NVL(hmold.get("amount2")))+"$");

                                paidpastusd = paidpastusd.add(new BigDecimal(Utils.NVL(hmold.get("amount2")).equals("")?"0":Utils.NVL(hmold.get("amount2"))));
                            }
                            cell.setCellStyle(hCellstyleR);


                            cell = row.createCell(7);
                            cell.setCellValue(Utils.convertDateStringToScreen(Utils.NVL(hmold.get("chequedate")),"/"));
                            cell.setCellStyle(hCellstyle);


                        }
                    }

                    //=====================
                    if(Utils.NVL(this.getMasterdata().getType()).equals("C")){  //เคลียร์
                        HashMap hm = new HashMap<String, String>();
//                        if(Utils.NVL(this.getMasterdata().getType()).equals("C")){
                            hm.put("chequedateclear", Utils.NVL(this.getMasterdata().getType()));
//                        }else if(Utils.NVL(this.getMasterdata().getType()).equals("N")){
//                            hm.put("chequedatenotclear", Utils.NVL(this.getMasterdata().getType()));
//                        }
//
//                        if(Utils.NVL(this.getMasterdata().getType()).equals("A")){
//                            hm.put("chequedateold", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
//                        }else{
                            hm.put("chequedate", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
//                        }

                        List l = CenterUtils.selectData(hm,"lookup_daily_cheque");
                        int size = l.size();
                        for(int i=0;i<size;i++){


                            hm = (HashMap)l.get(i);



                            row = hSheet.createRow(9+lold.size()+i);
                            cell = row.createCell(0);
                            cell.setCellValue(Utils.convertDateStringToScreen(Utils.NVL(hm.get("dailydate")),"/"));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(1);
                            cell.setCellValue(Utils.NVL(hm.get("vouchernodocno")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(2);
                            cell.setCellValue(Utils.NVL(hm.get("chequeno")));
                            cell.setCellStyle(hCellstyle);

                            cell = row.createCell(3);
                            cell.setCellValue(Utils.NVL(hm.get("bankname")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(4);
                            cell.setCellValue(Utils.NVL(hm.get("companyname")));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(5);
                            if(Utils.NVL(hm.get("monetaryusd")).equals("false")){
                                cell.setCellValue(format(Utils.NVL(hm.get("receivedamount"))));

                                rcvpastth = rcvpastth.add(new BigDecimal(Utils.NVL(hm.get("receivedamount")).equals("")?"0":Utils.NVL(hm.get("receivedamount"))));

                            }else{
                                cell.setCellValue(format(Utils.NVL(hm.get("amount")))+"$");

                                rcvpastusd = rcvpastusd.add(new BigDecimal(Utils.NVL(hm.get("amount")).equals("")?"0":Utils.NVL(hm.get("amount"))));
                            }
                            cell.setCellStyle(hCellstyleR);

                            cell = row.createCell(6);
                            if(Utils.NVL(hm.get("monetaryusd")).equals("false")){
                                cell.setCellValue(format(Utils.NVL(hm.get("paidamount"))));

                                paidpastth = paidpastth.add(new BigDecimal(Utils.NVL(hm.get("paidamount")).equals("")?"0":Utils.NVL(hm.get("paidamount"))));
                            }else{
                                cell.setCellValue(format(Utils.NVL(hm.get("amount2")))+"$");

                                paidpastusd = paidpastusd.add(new BigDecimal(Utils.NVL(hm.get("amount2")).equals("")?"0":Utils.NVL(hm.get("amount2"))));
                            }
                            cell.setCellStyle(hCellstyleR);


                            cell = row.createCell(7);
                            cell.setCellValue(Utils.convertDateStringToScreen(Utils.NVL(hm.get("chequedate")),"/"));
                            cell.setCellStyle(hCellstyle);

                        }
                }
                
                //===========Total==========================
                row = hSheet.createRow(4);
                cell = row.createCell(2);
                cell.setCellValue("RCV THB");
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(3);
                cell.setCellValue("RCV USD");
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(4);
                cell.setCellValue("PAID THB");
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(5);
                cell.setCellValue("PAID USD");
                cell.setCellStyle(hCellstyleCB);    
                    

                hSheet.addMergedRegion(new Region(4,(short)0,4,(short)1));
                row = hSheet.createRow(5);      
                cell = row.createCell(0);
                cell.setCellValue("TOTAL CHQ.");
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(2);
                cell.setCellValue(format(rcvpastth.toString()));
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(3);
                cell.setCellValue(format(rcvpastusd.toString()));
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(4);
                cell.setCellValue(format(paidpastth.toString()));
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(5);
                cell.setCellValue(format(paidpastusd.toString()));
                cell.setCellStyle(hCellstyleCB);
                    
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030700data.xls");
                
//            }else{
//                String msg = "ไม่พบข้อมูล";
//                addInfoMessage(null, msg, msg);
//            }

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
       
        
        
        return isok;
    }
}
