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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;

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
public class ATR030300 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr030300e.xhtml";
    private static final String PAGE_Q  = "atr030300q.xhtml";
    
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
        private String month = "";
        private String year = "";
        private String month2 = "";
        private String year2 = "";

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

        public String getMonth2() {
            return month2;
        }

        public void setMonth2(String month2) {
            this.month2 = month2;
        }

        public String getYear2() {
            return year2;
        }

        public void setYear2(String year2) {
            this.year2 = year2;
        }
     
    }
    
    
    public ATR030300() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR030300A");
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
            if(Utils.NVL(this.getMasterdata().getType()).equals("1")){  //รายวัน
                genDataExcel();
            }else if(Utils.NVL(this.getMasterdata().getType()).equals("2")){  //รายเดือน
                genDataExcelMonth();
            }
        }
        
    }
    
    
    private boolean validateAdd(){
        boolean isok = true;


        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030300U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030300D");
            
            
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
            this.getMasterdata().setType("1");
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030300S");
        
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
                
                HSSFCellStyle hCellstyleHColor = hWBook.createCellStyle();                         
                hCellstyleHColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         
                hCellstyleHColor.setFont(font14);                   
                hCellstyleHColor.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                hCellstyleHColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                CenterUtils.setCellBorder(hCellstyleHColor);
                
                HSSFCellStyle hCellstyleL = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleL.setFont(font14);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyleL);
                
                HSSFCellStyle hCellstyleR = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleR.setFont(font14);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyleR);
                
                HSSFCellStyle hCellstyleRMoney = hWBook.createCellStyle();                       
                hCellstyleRMoney.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         
                hCellstyleRMoney.setFont(font14);                                                  
                DataFormat format = hWBook.createDataFormat();
                hCellstyleRMoney.setDataFormat(format.getFormat("#,##0.00"));
                CenterUtils.setCellBorder(hCellstyleRMoney);
                
                Font font18B = hWBook.createFont();                                           //กำหนด font style
                font18B.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font18B.setFontName("Angsana New");
                font18B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา
                
                HSSFCellStyle hCellstyleCB = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleCB.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleCB.setFont(font18B);                                                  //เรียกใช้ style font
                
                HSSFCellStyle hCellstyleHBC = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleHBC.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleHBC.setFont(font18B);                                                  //เรียกใช้ style font

                
//                if (!Utils.NVL(this.getMasterdata().getSortoption()).equals("1")){
//                    		hSheet.addMergedRegion(new Region(0,(short)0,0,(short)4));
//			    	hSheet.addMergedRegion(new Region(1,(short)0,1,(short)4));
//			    	hSheet.addMergedRegion(new Region(2,(short)0,2,(short)4));
//			    	
//			    	hSheet.setColumnWidth((short)0,(short)(ONEPIXEL*46));
//			    	hSheet.setColumnWidth((short)1,(short)(ONEPIXEL*300));
//			    	hSheet.setColumnWidth((short)2,(short)(ONEPIXEL*110));
//			    	hSheet.setColumnWidth((short)3,(short)(ONEPIXEL*110));
//			    	hSheet.setColumnWidth((short)4,(short)(ONEPIXEL*100));
//                }
                
                hSheet.setColumnWidth(0,10000);
                
                //=======Header============ 
                hSheet.addMergedRegion(new Region(0,(short)0,0,(short)2));
                HSSFRow row = hSheet.createRow(0);      
                cell = row.createCell(0);
                cell.setCellValue("Cash Flow Status");
                cell.setCellStyle(hCellstyleHBC);
                
                
                
                String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());

                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
                row = hSheet.createRow(1);      
                cell = row.createCell(0);
                cell.setCellValue(header);
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(6);
                cell.setCellValue("ATR030300");
                cell.setCellStyle(hCellstyleCB);
                                
                String condition = "Condition Date:"+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),"/");
                condition += "-"+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()),"/");
                
                hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));
                row = hSheet.createRow(2);      
                cell = row.createCell(0);
                cell.setCellValue(condition);
                cell.setCellStyle(hCellstyleCB);

                //Query Data
                HashMap hm = new HashMap<String, String>();
                hm.put("dailydatest", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                hm.put("dailydatefn", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()));

                List l = CenterUtils.selectData(hm,"sumdaily_receivedall");

                BigDecimal bfth = new BigDecimal(0);
                BigDecimal bfus = new BigDecimal(0);
                if(!l.isEmpty()){

                    
                    //hSheet.addMergedRegion(new Region(3,(short)0,3,(short)2));
                    row = hSheet.createRow(3);      
                    cell = row.createCell(0);
                    cell.setCellValue("Received");
                    cell.setCellStyle(hCellstyleCB);
                    
                    //hSheet.addMergedRegion(new Region(4,(short)0,4,(short)2));
                    row = hSheet.createRow(4);      
                    cell = row.createCell(0);
                    cell.setCellValue("Detail");
                    cell.setCellStyle(hCellstyleHColor);


                    cell = row.createCell(1);
                    cell.setCellValue("BAHT");
                    cell.setCellStyle(hCellstyleHColor);

                    cell = row.createCell(2);
                    cell.setCellValue("USD");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    //terex 09/01/2018 ปิด column bf เนื่องจากหน้าจอหาเป็นช่วงวันที่
//                    row = hSheet.createRow(5);      
//                    cell = row.createCell(0);
//                    cell.setCellValue("BF");
//                    cell.setCellStyle(hCellstyleCB);
//                    
//                    //Query Data th
//                    HashMap hmpreth = new HashMap<String, String>();
//                    hmpreth.put("bfdate", CenterUtils.previousDayEn(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),1));
//                    List lpreth = CenterUtils.selectData(hmpreth,"sumbringforward_th");
//                    if(!lpreth.isEmpty()){
//                        hmpreth = (HashMap)lpreth.get(0);
//                        
//                        bfth = bfth.add(new BigDecimal(Utils.NVL(hmpreth.get("sumbf_th")).equals("")?"0":Utils.NVL(hmpreth.get("sumbf_th"))));
//                    }
//                    
//                    cell = row.createCell(1);
//                    cell.setCellValue(CenterUtils.format(bfth.toString()));
//                    cell.setCellStyle(hCellstyleRMoney);
//
//                    //Query Data us
//                    HashMap hmpreus = new HashMap<String, String>();
//                    hmpreus.put("bfdate", CenterUtils.previousDayEn(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),1));
//                    List lprethus = CenterUtils.selectData(hmpreus,"sumbringforward_us");
//                    if(!lprethus.isEmpty()){
//                        hmpreus = (HashMap)lprethus.get(0);
//                        
//                        bfus = bfus.add(new BigDecimal(Utils.NVL(hmpreus.get("sumbf_us")).equals("")?"0":Utils.NVL(hmpreus.get("sumbf_us"))));
//                    }
//                    
//                    cell = row.createCell(2);
//                    cell.setCellValue(CenterUtils.format(bfus.toString()));
//                    cell.setCellStyle(hCellstyleRMoney);
                    
                    
                    BigDecimal totalrevth = new BigDecimal(0);
                    BigDecimal totalrevus = new BigDecimal(0);
                    int size = l.size();
                    for(int i=0;i<size;i++){

                        hm = (HashMap)l.get(i);

                        row = hSheet.createRow(5+i);
                        cell = row.createCell(0);
                        cell.setCellValue(Utils.NVL(hm.get("redesc")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(1);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hm.get("receivedamount"))));
                        cell.setCellStyle(hCellstyleRMoney);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hm.get("amount"))));
                        cell.setCellStyle(hCellstyleRMoney);
                        
                        
                        totalrevth = totalrevth.add(new BigDecimal(Utils.NVL(hm.get("receivedamount")).equals("")?"0":Utils.NVL(hm.get("receivedamount"))));
                        totalrevus = totalrevus.add(new BigDecimal(Utils.NVL(hm.get("amount")).equals("")?"0":Utils.NVL(hm.get("amount"))));
                                

                }
                 
                //=========Total=============== 
                row = hSheet.createRow(size+5);
                cell = row.createCell(0);
                cell.setCellValue("Total");
                cell.setCellStyle(hCellstyleR);   
                
                cell = row.createCell(1);
                cell.setCellValue(CenterUtils.format(totalrevth.toString()));
                cell.setCellStyle(hCellstyleRMoney);

                cell = row.createCell(2);
                cell.setCellValue(CenterUtils.format(totalrevus.toString()));
                cell.setCellStyle(hCellstyleRMoney);
                //=========================    
                    
                //Query Data sumdaily_paiddall
                HashMap hm2 = new HashMap<String, String>();
                hm2.put("dailydatest", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                hm2.put("dailydatefn", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()));

                List l2 = CenterUtils.selectData(hm2,"sumdaily_paiddall");
                int rowpad = (size+8);
                if(!l2.isEmpty()){

                    
                    //hSheet.addMergedRegion(new Region(3,(short)0,3,(short)2));
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("Paid");
                    cell.setCellStyle(hCellstyleCB);
                    
                    row = hSheet.createRow(rowpad+1);      
                    cell = row.createCell(0);
                    cell.setCellValue("Detail");
                    cell.setCellStyle(hCellstyleHColor);
                    cell = row.createCell(1);
                    cell.setCellValue("BAHT");
                    cell.setCellStyle(hCellstyleHColor);

                    cell = row.createCell(2);
                    cell.setCellValue("USD");
                    cell.setCellStyle(hCellstyleHColor);
                    //===================
                    
                    BigDecimal totalpaidth = new BigDecimal(0);
                    BigDecimal totalpaidus = new BigDecimal(0);
                    int size2 = l2.size();
                    for(int i=0;i<size2;i++){

                        hm2 = (HashMap)l2.get(i);

                        row = hSheet.createRow((rowpad+2)+i);      
                        cell = row.createCell(0);
                        cell.setCellValue(Utils.NVL(hm2.get("paydesc")));
                        cell.setCellStyle(hCellstyleL);

                        cell = row.createCell(1);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hm2.get("paidamount"))));
                        cell.setCellStyle(hCellstyleRMoney);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hm2.get("amount2"))));
                        cell.setCellStyle(hCellstyleRMoney);

                        totalpaidth = totalpaidth.add(new BigDecimal(Utils.NVL(hm2.get("paidamount")).equals("")?"0":Utils.NVL(hm2.get("paidamount"))));
                        totalpaidus = totalpaidus.add(new BigDecimal(Utils.NVL(hm2.get("amount2")).equals("")?"0":Utils.NVL(hm2.get("amount2"))));
                        
                    }
                    
                    //=========Total=============== 
                    row = hSheet.createRow(rowpad+1+size2+1);
                    cell = row.createCell(0);
                    cell.setCellValue("Total");
                    cell.setCellStyle(hCellstyleR);   

                    cell = row.createCell(1);
                    cell.setCellValue(CenterUtils.format(totalpaidth.toString()));
                    cell.setCellStyle(hCellstyleRMoney);

                    cell = row.createCell(2);
                    cell.setCellValue(CenterUtils.format(totalpaidus.toString()));
                    cell.setCellStyle(hCellstyleRMoney);
                    //=========================  
                    
                    row = hSheet.createRow(rowpad+1+size2+1+2);
                    cell = row.createCell(0);
                    cell.setCellValue("AJ.PENDING CHEQUE.");
                    cell.setCellStyle(hCellstyleL);
                    
                    BigDecimal pendingth = calpendingth(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),
                            Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()));                     
                    
                    cell = row.createCell(1);
                    cell.setCellValue(CenterUtils.format(  pendingth.toString()  ));
                    cell.setCellStyle(hCellstyleRMoney);

                    cell = row.createCell(2);
                    cell.setCellValue("-");
                    cell.setCellStyle(hCellstyleR); 
                    rowpad++;
                    
                    row = hSheet.createRow(rowpad+1+size2+1+2);
                    cell = row.createCell(0);
                    cell.setCellValue("CF");
                    cell.setCellStyle(hCellstyleR);   

                    
                    BigDecimal tmpbfth = new BigDecimal(0);
                    tmpbfth = tmpbfth.add(bfth);
                    tmpbfth = tmpbfth.add(totalrevth);    
                    tmpbfth = tmpbfth.subtract(totalpaidth);
                    tmpbfth = tmpbfth.add(pendingth);
                            
                    
                    BigDecimal tmpbfus = new BigDecimal(0);
                    tmpbfus = tmpbfus.add(bfus);
                    tmpbfus = tmpbfus.add(totalrevus);    
                    tmpbfus = tmpbfus.subtract(totalpaidus); 
                    
                    cell = row.createCell(1);
                    cell.setCellValue(CenterUtils.format(  tmpbfth.toString()    ));
                    cell.setCellStyle(hCellstyleRMoney);

                    cell = row.createCell(2);
                    cell.setCellValue(CenterUtils.format( tmpbfus.toString() ));
                    cell.setCellStyle(hCellstyleRMoney);
                }
                
                

   
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030300_"+CenterUtils.formatfileNameDatetime()+".xls");
                
            }else{
                String msg = "ไม่พบข้อมูล";
                addInfoMessage(null, msg, msg);
            }

            }catch(FileNotFoundException e){    
                e.printStackTrace();
            }catch(IOException e){    
                e.printStackTrace();
            }finally{

            }
   
        
    }
    
    
    
     public void genDataExcelMonth(){
        
            
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
                
                HSSFCellStyle hCellstyleHColor = hWBook.createCellStyle();                         
                hCellstyleHColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         
                hCellstyleHColor.setFont(font14);                   
                hCellstyleHColor.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                hCellstyleHColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                CenterUtils.setCellBorder(hCellstyleHColor);
                
                HSSFCellStyle hCellstyleL = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleL.setFont(font14);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyleL);
                
                HSSFCellStyle hCellstyleR = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleR.setFont(font14);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyleR);
                
                HSSFCellStyle hCellstyleRMoney = hWBook.createCellStyle();                       
                hCellstyleRMoney.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         
                hCellstyleRMoney.setFont(font14);                                                  
                DataFormat format = hWBook.createDataFormat();
                hCellstyleRMoney.setDataFormat(format.getFormat("#,##0.00"));
                CenterUtils.setCellBorder(hCellstyleRMoney);
                
                Font font18B = hWBook.createFont();                                           //กำหนด font style
                font18B.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font18B.setFontName("Angsana New");
                font18B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา
                
                HSSFCellStyle hCellstyleCB = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleCB.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleCB.setFont(font18B);                                                  //เรียกใช้ style font
                
                HSSFCellStyle hCellstyleHBC = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleHBC.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleHBC.setFont(font18B);                                                  //เรียกใช้ style font

                
                hSheet.setColumnWidth(0,10000);
                
                //=======Header============ 
                hSheet.addMergedRegion(new Region(0,(short)0,0,(short)2));
                HSSFRow row = hSheet.createRow(0);      
                cell = row.createCell(0);
                cell.setCellValue("Cash Flow Status");
                cell.setCellStyle(hCellstyleHBC);
                
                
                
                String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());

                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
                row = hSheet.createRow(1);      
                cell = row.createCell(0);
                cell.setCellValue(header);
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(6);
                cell.setCellValue("ATR030300");
                cell.setCellStyle(hCellstyleCB);
                                
                String condition = "Condition Month:";
//                if(this.getMasterdata().getYear().equals(this.getMasterdata().getYear2()) && this.getMasterdata().getMonth().equals(this.getMasterdata().getMonth2())){
//                    condition += CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth()),0)+" "+this.getMasterdata().getYear();
//                }else if(this.getMasterdata().getYear().equals(this.getMasterdata().getYear2())){
//                    condition += CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth()),0)+"-"+
//                            CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth2()),0)+" "+
//                            this.getMasterdata().getYear();
//                }else{
//                    condition += CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth()),0)+" "+this.getMasterdata().getYear();
//                    condition += "-";
//                    condition += CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth2()),0)+" "+this.getMasterdata().getYear2();
//                }
                
                condition += "01 "+CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth()),0)+" "+this.getMasterdata().getYear();
                condition += "-";
                condition += CenterUtils.getMaxDayofMonthofYear(Integer.parseInt(this.getMasterdata().getYear2()),Integer.parseInt(this.getMasterdata().getMonth2()))
                        +" "+ 
                        CenterUtils.getENMonth(Integer.parseInt(this.getMasterdata().getMonth2()),0)+" "+this.getMasterdata().getYear2();
                
                hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));
                row = hSheet.createRow(2);      
                cell = row.createCell(0);
                cell.setCellValue(condition);
                cell.setCellStyle(hCellstyleCB);

                //Query Data
                HashMap hm = new HashMap<String, String>();
                hm.put("dailydatest", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"01");
                hm.put("dailydatefn", this.getMasterdata().getYear2()+this.getMasterdata().getMonth2()+"31");

                List l = CenterUtils.selectData(hm,"sumdaily_receivedall");


                if(!l.isEmpty()){

                    
                    row = hSheet.createRow(3);      
                    cell = row.createCell(0);
                    cell.setCellValue("Received");
                    cell.setCellStyle(hCellstyleCB);
                    
                    row = hSheet.createRow(4);      
                    cell = row.createCell(0);
                    cell.setCellValue("Detail");
                    cell.setCellStyle(hCellstyleHColor);


                    cell = row.createCell(1);
                    cell.setCellValue("BAHT");
                    cell.setCellStyle(hCellstyleHColor);

                    cell = row.createCell(2);
                    cell.setCellValue("USD");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    
                    
                    BigDecimal totalrevth = new BigDecimal(0);
                    BigDecimal totalrevus = new BigDecimal(0);
                    int size = l.size();
                    for(int i=0;i<size;i++){

                        hm = (HashMap)l.get(i);

                        row = hSheet.createRow(5+i);
                        cell = row.createCell(0);
                        cell.setCellValue(Utils.NVL(hm.get("redesc")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(1);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hm.get("receivedamount"))));
                        cell.setCellStyle(hCellstyleRMoney);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hm.get("amount"))));
                        cell.setCellStyle(hCellstyleRMoney);
                        
                        
                        totalrevth = totalrevth.add(new BigDecimal(Utils.NVL(hm.get("receivedamount")).equals("")?"0":Utils.NVL(hm.get("receivedamount"))));
                        totalrevus = totalrevus.add(new BigDecimal(Utils.NVL(hm.get("amount")).equals("")?"0":Utils.NVL(hm.get("amount"))));
                                

                }
                 
                //=========Total=============== 
                row = hSheet.createRow(size+5);
                cell = row.createCell(0);
                cell.setCellValue("Total");
                cell.setCellStyle(hCellstyleR);   
                
                cell = row.createCell(1);
                cell.setCellValue(CenterUtils.format(totalrevth.toString()));
                cell.setCellStyle(hCellstyleRMoney);

                cell = row.createCell(2);
                cell.setCellValue(CenterUtils.format(totalrevus.toString()));
                cell.setCellStyle(hCellstyleRMoney);
                //=========================    
                    
                //Query Data sumdaily_paiddall
                HashMap hm2 = new HashMap<String, String>();
                hm2.put("dailydatest", this.getMasterdata().getYear()+this.getMasterdata().getMonth()+"01");
                hm2.put("dailydatefn", this.getMasterdata().getYear2()+this.getMasterdata().getMonth2()+"31");

                List l2 = CenterUtils.selectData(hm2,"sumdaily_paiddall");
                int rowpad = (size+8);
                if(!l2.isEmpty()){

                    
                    row = hSheet.createRow(rowpad);      
                    cell = row.createCell(0);
                    cell.setCellValue("Paid");
                    cell.setCellStyle(hCellstyleCB);
                    
                    row = hSheet.createRow(rowpad+1);      
                    cell = row.createCell(0);
                    cell.setCellValue("Detail");
                    cell.setCellStyle(hCellstyleHColor);
                    cell = row.createCell(1);
                    cell.setCellValue("BAHT");
                    cell.setCellStyle(hCellstyleHColor);

                    cell = row.createCell(2);
                    cell.setCellValue("USD");
                    cell.setCellStyle(hCellstyleHColor);
                    //===================
                    
                    BigDecimal totalpaidth = new BigDecimal(0);
                    BigDecimal totalpaidus = new BigDecimal(0);
                    int size2 = l2.size();
                    for(int i=0;i<size2;i++){

                        hm2 = (HashMap)l2.get(i);

                        row = hSheet.createRow((rowpad+2)+i);      
                        cell = row.createCell(0);
                        cell.setCellValue(Utils.NVL(hm2.get("paydesc")));
                        cell.setCellStyle(hCellstyleL);

                        cell = row.createCell(1);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hm2.get("paidamount"))));
                        cell.setCellStyle(hCellstyleRMoney);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hm2.get("amount2"))));
                        cell.setCellStyle(hCellstyleRMoney);

                        totalpaidth = totalpaidth.add(new BigDecimal(Utils.NVL(hm2.get("paidamount")).equals("")?"0":Utils.NVL(hm2.get("paidamount"))));
                        totalpaidus = totalpaidus.add(new BigDecimal(Utils.NVL(hm2.get("amount2")).equals("")?"0":Utils.NVL(hm2.get("amount2"))));
                        
                    }
                    
                    //=========Total=============== 
                    row = hSheet.createRow(rowpad+1+size2+1);
                    cell = row.createCell(0);
                    cell.setCellValue("Total");
                    cell.setCellStyle(hCellstyleR);   

                    cell = row.createCell(1);
                    cell.setCellValue(CenterUtils.format(totalpaidth.toString()));
                    cell.setCellStyle(hCellstyleRMoney);

                    cell = row.createCell(2);
                    cell.setCellValue(CenterUtils.format(totalpaidus.toString()));
                    cell.setCellStyle(hCellstyleRMoney);
                    
                }
                
                

   
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030300_MONTH_"+CenterUtils.formatfileNameDatetime()+".xls");
                
            }else{
                String msg = "ไม่พบข้อมูล";
                addInfoMessage(null, msg, msg);
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
       
       if(Utils.NVL(this.getMasterdata().getType()).equals("1") && Utils.NVL(this.getMasterdata().getDailydatest()).equals("")){
            String msg = MessageUtil.getMessage("EP006");
            addErrorMessage(null,msg,msg);
            return false;
       }
       
       if(Utils.NVL(this.getMasterdata().getType()).equals("2")){
           int year = Integer.parseInt(this.getMasterdata().getYear()+this.getMasterdata().getMonth());
           int year2 = Integer.parseInt(this.getMasterdata().getYear2()+this.getMasterdata().getMonth2());
           
           
           if(year > year2){
                String msg = MessageUtil.getMessage("EP012");
                addErrorMessage(null,msg,msg);
                return false;
           }
       }
       
       
//       if((this.getMasterdata().getDailydatest() == null)){
//                
//            String msg = MessageUtil.getMessage("EP006");
//            addErrorMessage(null,msg,msg);
//            return false;
//
//        }
       
//       if((this.getMasterdata().getDailydatest() == null && this.getMasterdata().getDailydatefn() == null)){
//                
//            String msg = MessageUtil.getMessage("EP006");
//            addErrorMessage(null,msg,msg);
//            return false;
//
//        }
//       
//       if((this.getMasterdata().getDailydatest() != null && this.getMasterdata().getDailydatefn() == null) ||
//                (this.getMasterdata().getDailydatest() == null && this.getMasterdata().getDailydatefn() != null)){
//                
//            String msg = MessageUtil.getMessage("EP007");
//            addErrorMessage(null,msg,msg);
//            return false;
//
//        }
//       
//        if(this.getMasterdata().getDailydatest() != null && this.getMasterdata().getDailydatefn() != null){
//
//                String s = Utils.NVL(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
//                String e = Utils.NVL(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()));
//
//                if(Integer.parseInt(s) > Integer.parseInt(e)){
//                    String msg = MessageUtil.getMessage("EP007");
//                    addErrorMessage(null,msg,msg);
//                    return false;
//                }
//
//         }
        
        
        return isok;
    }
    
    
    private BigDecimal calpendingth(String date,String fndate){
        
        BigDecimal sum = new BigDecimal(0);
        
        HashMap hm = new HashMap<String, String>();
        hm.put("bfdatest", date);
        hm.put("bfdatefn", fndate);
        hm.put("bankid", "3");
        List l = CenterUtils.selectData(hm,"search_bringforward");
        
        if(!l.isEmpty()){
            int size = l.size();
            for(int i=0;i<size;i++){
                 hm = (HashMap)l.get(i);
                 
                 
                 BigDecimal bpchqrcv = new BigDecimal(Utils.NVL(hm.get("bpchqrcv")).equals("")?"0":Utils.NVL(hm.get("bpchqrcv")));
                 BigDecimal bpchqpaid = new BigDecimal(Utils.NVL(hm.get("bpchqpaid")).equals("")?"0":Utils.NVL(hm.get("bpchqpaid")));
                 BigDecimal btchqrcv = new BigDecimal(Utils.NVL(hm.get("btchqrcv")).equals("")?"0":Utils.NVL(hm.get("btchqrcv")));
                 BigDecimal btchqpaid = new BigDecimal(Utils.NVL(hm.get("btchqpaid")).equals("")?"0":Utils.NVL(hm.get("btchqpaid")));
                 
                 sum = sum.add(bpchqrcv);
                 sum = sum.subtract(bpchqpaid);
                 sum = sum.subtract(btchqrcv);
                 sum = sum.add(btchqpaid);
                 
            }
        }else{
            
             BigDecimal bpchqrcv = new BigDecimal(countChequeClearDailyReceived(date,fndate,3,true));
             BigDecimal bpchqpaid = new BigDecimal(countChequeClearDailyPaid(date,fndate,3,true));
             BigDecimal btchqrcv = new BigDecimal(countChequeClearDailyReceived(date,fndate,3,false));
             BigDecimal btchqpaid = new BigDecimal(countChequeClearDailyPaid(date,fndate,3,false));

             sum = sum.add(bpchqrcv);
             sum = sum.subtract(bpchqpaid);
             sum = sum.subtract(btchqrcv);
             sum = sum.add(btchqpaid);
          
        }
        
        return sum;
    }
    
    
    private Double countChequeClearDailyReceived(String dailydate,String fndailydate,int payby,boolean vclear){
        
        
        String pselect = "";
        String pfrom = " daily r ";
        String pwhere = "";
        

        if(checkmonetaryusd(payby)){
          pselect += " sum(r.amount) as amount ";
        }else{
          pselect += " sum(r.receivedamount) as amount ";
        }                                              

        if(vclear){

            pwhere += " (r.chequedate >= '"+dailydate+"' "
                   + "and r.chequedate <= '"+fndailydate+"') "
                   + "and r.payby = '"+payby+"' "
                   + "and r.chequedate is not null "; //cheque clear
        }else{
            pwhere += " (r.dailydate >= '"+dailydate+"' "
                   + "and r.dailydate <= '"+dailydate+"') "
                   + "and r.payby = '"+payby+"' "
                    + "and r.chequedate is null "; //cheque not clear
        }
        pwhere += "and r.cheque = 'true' ";
        
        
        HashMap hm = new HashMap<String, String>();
        hm.put("pselect", pselect);
        hm.put("pfrom", pfrom);
        hm.put("pwhere", pwhere);
        List l = CenterUtils.selectData(hm,"lookup_data_querytemplate");
        
        if(l.isEmpty()){            
            return new Double("0");
        }else{
            hm = (HashMap)l.get(0);
            return new Double(Utils.NVL(hm.get("amount")).equals("")?"0":Utils.NVL(hm.get("amount")));            
        }        
        
    }
    
    
    private Double countChequeClearDailyPaid(String dailydate,String fndailydate,int payby,boolean vclear){
        
        
        String pselect = "";
        String pfrom = " daily r ";
        String pwhere = "";
        

        if(checkmonetaryusd(payby)){
          pselect += " sum(r.amount2) as amount ";
        }else{
          pselect += " sum(r.paidamount) as amount ";
        }                                              

        if(vclear){

            pwhere += " (r.chequedate >= '"+dailydate+"' "
                   + "and r.chequedate <= '"+fndailydate+"') "
                   + "and r.payby = '"+payby+"' "
                   + "and r.chequedate is not null "; //cheque clear
        }else{
            pwhere += " (r.dailydate >= '"+dailydate+"' "
                   + "and r.dailydate <= '"+fndailydate+"') "
                   + "and r.payby = '"+payby+"' "
                    + "and r.chequedate is null "; //cheque not clear
        }
        pwhere += "and r.cheque = 'true' ";
        
        
        HashMap hm = new HashMap<String, String>();
        hm.put("pselect", pselect);
        hm.put("pfrom", pfrom);
        hm.put("pwhere", pwhere);
        List l = CenterUtils.selectData(hm,"lookup_data_querytemplate");
        
        if(l.isEmpty()){            
            return new Double("0");
        }else{
            hm = (HashMap)l.get(0);
            return new Double(Utils.NVL(hm.get("amount")).equals("")?"0":Utils.NVL(hm.get("amount")));            
        }        

    }      
    
    
    private boolean checkmonetaryusd(int payby){
        
        HashMap hm = new HashMap<String, String>();
        hm.put("bankid", Utils.NVL(payby));
        List l = CenterUtils.selectData(hm,"ATP010100Q");
        
        if(!l.isEmpty()){
            hm = (HashMap)l.get(0);
            
            if(Utils.NVL(hm.get("monetaryusd")).equals("true")){
                return true;
            }
            
            return false;
        }        
        return false;
    }
    
}
