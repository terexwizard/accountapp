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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author terex
 * @version 1.00.00
 * 18/06/2555  9:45:40
 */

@ManagedBean
@SessionScoped
public class ATR030500 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr030500e.xhtml";
    private static final String PAGE_Q  = "atr030500q.xhtml";
    
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

        

    }
    
    
    public ATR030500() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR030500A");
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030500U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030500D");
            
            
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030500S");
        
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
                hCellstyle.setFont(font16);                                                  //เรียกใช้ style font
                
                HSSFCellStyle hCellstyleL = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleL.setFont(font16);                                                  //เรียกใช้ style font
                CenterUtils.setCellBorder(hCellstyleL);
                
                HSSFCellStyle hCellstyleR = hWBook.createCellStyle();                         
                hCellstyleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         
                hCellstyleR.setFont(font16);                                                 
                CenterUtils.setCellBorder(hCellstyleR);
                
                HSSFCellStyle hCellstyleHColor = hWBook.createCellStyle();                         
                hCellstyleHColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
                hCellstyleHColor.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
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
                
                hSheet.setColumnWidth((short)0,(short)(ONEPIXEL*300));
                hSheet.setColumnWidth(4,8000);
                hSheet.setColumnWidth(5,8000);
                hSheet.setColumnWidth(6,8000);
                hSheet.setColumnWidth(7,8000);
                
                //=======Header============ 
                String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());

                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
                HSSFRow row = hSheet.createRow(1);      
                cell = row.createCell(0);
                cell.setCellValue(header);
                cell.setCellStyle(hCellstyleCB);
                
                
                String condition = "Condition :"+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),"/");
                
                
                hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));
                row = hSheet.createRow(2);      
                cell = row.createCell(0);
                cell.setCellValue(condition);
                cell.setCellStyle(hCellstyleCB);
                
                row = hSheet.createRow(3);      
                cell = row.createCell(0);
                cell.setCellValue("Cash Flow Status");
                cell.setCellStyle(hCellstyleCB);

                //Query Data
                HashMap hmpre = new HashMap<String, String>();
                hmpre.put("bfdate", CenterUtils.previousDayEn(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),1));
                hmpre.put("bankidnot2", "2");
                List lpre = CenterUtils.selectData(hmpre,"search_bringforward");
                
                
                HashMap hm = new HashMap<String, String>();
                hm.put("bfdate", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                hm.put("bankidnot2", "2");

                List l = CenterUtils.selectData(hm,"search_bringforward");

                if(!l.isEmpty()){

                    hSheet.addMergedRegion(new Region(5,(short)4,5,(short)7));
                    row = hSheet.createRow(5);      
                    
                    cell = row.createCell(0);
                    cell.setCellValue("Description");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("B/F");
                    cell.setCellStyle(hCellstyleHColor);
                         
                    cell = row.createCell(2);
                    cell.setCellValue("Received");
                    cell.setCellStyle(hCellstyleHColor);

                    cell = row.createCell(3);
                    cell.setCellValue("Paid");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("Adjusting Entry For Cheque");
                    cell.setCellStyle(hCellstyleHColor); 
                    
                    cell = row.createCell(5);
                    cell.setCellValue("Adjusting Entry For Cheque");
                    cell.setCellStyle(hCellstyleHColor); 
                    
                    cell = row.createCell(6);
                    cell.setCellValue("Adjusting Entry For Cheque");
                    cell.setCellStyle(hCellstyleHColor); 
                    
                    cell = row.createCell(7);
                    cell.setCellValue("Adjusting Entry For Cheque");
                    cell.setCellStyle(hCellstyleHColor); 
                    
                    cell = row.createCell(8);
                    cell.setCellValue("Actual Money");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    hSheet.addMergedRegion(new Region(5,(short)0,7,(short)0));
                    hSheet.addMergedRegion(new Region(5,(short)1,7,(short)1));
                    hSheet.addMergedRegion(new Region(5,(short)2,7,(short)2));
                    hSheet.addMergedRegion(new Region(5,(short)3,7,(short)3));
                    hSheet.addMergedRegion(new Region(5,(short)8,7,(short)8));
                    
                    
                    hSheet.addMergedRegion(new Region(6,(short)4,6,(short)5));
                    hSheet.addMergedRegion(new Region(6,(short)6,6,(short)7));
                    row = hSheet.createRow(6);      
                   
                    cell = row.createCell(0);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                         
                    cell = row.createCell(2);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("Previous Cheque Clear");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(6);
                    cell.setCellValue("Today Cheque Not Clear");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(7);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(8);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    //============================================
                    //hSheet.addMergedRegion(new Region(3,(short)0,3,(short)2));
                    row = hSheet.createRow(7);      
                   
                    cell = row.createCell(0);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                         
                    cell = row.createCell(2);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("Cheque Clear RCV");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("Cheque Clear PAID");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(6);
                    cell.setCellValue("Cheque Not Clear RCV");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(7);
                    cell.setCellValue("Cheque Not Clear PAID");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(8);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleHColor);


                    
                    BigDecimal actualmoneyN = new BigDecimal(0);
                    BigDecimal actualmoneyY = new BigDecimal(0);
                    int size = l.size();
                    ArrayList<String[]> alN = new ArrayList<String[]>();
                    ArrayList<String[]> alY = new ArrayList<String[]>();
                    for(int i=0;i<size;i++){
                        
                        if(lpre.isEmpty()){
                            hmpre = new HashMap<String, String>();
                        }else{
                            hmpre = (HashMap)lpre.get(i);
                        }                              
                        

                        hm = (HashMap)l.get(i);
                        
                        if(Utils.NVL(hm.get("fixdeposit")).equals("N")){
                            String[] fixdepositN = new String[9];
                            fixdepositN[0] = Utils.NVL(hm.get("bankname"));
                            fixdepositN[1] = Utils.NVL(hmpre.get("actualmoney"));
                            fixdepositN[2] = Utils.NVL(hm.get("received"));
                            fixdepositN[3] = Utils.NVL(hm.get("paid"));
                            
                            fixdepositN[4] = Utils.NVL(hm.get("bpchqrcv"));
                            fixdepositN[5] = Utils.NVL(hm.get("bpchqpaid"));
                            fixdepositN[6] = Utils.NVL(hm.get("btchqrcv"));
                            fixdepositN[7] = Utils.NVL(hm.get("btchqpaid"));
                            
                            
                            fixdepositN[8] = Utils.NVL(hm.get("actualmoney"));
                            alN.add(fixdepositN);
                        }else{
                            String[] fixdepositY = new String[9];
                            fixdepositY[0] = Utils.NVL(hm.get("bankname"));
                            fixdepositY[1] = Utils.NVL(hmpre.get("actualmoney"));
                            fixdepositY[2] = Utils.NVL(hm.get("received"));
                            fixdepositY[3] = Utils.NVL(hm.get("paid"));
                            
                            fixdepositY[4] = Utils.NVL(hm.get("bpchqrcv"));
                            fixdepositY[5] = Utils.NVL(hm.get("bpchqpaid"));
                            fixdepositY[6] = Utils.NVL(hm.get("btchqrcv"));
                            fixdepositY[7] = Utils.NVL(hm.get("btchqpaid"));
                            
                            fixdepositY[8] = Utils.NVL(hm.get("actualmoney"));
                            alY.add(fixdepositY);
                        }

//                        row = hSheet.createRow(5+i);
//                        cell = row.createCell(0);
//                        cell.setCellValue(Utils.NVL(hm.get("bankname")));
//                        cell.setCellStyle(hCellstyleL);
//                        
//                        
//                        cell = row.createCell(1);
//                        cell.setCellValue(format(Utils.NVL(hmpre.get("actualmoney"))));
//                        cell.setCellStyle(hCellstyleR);
//                        
//                        cell = row.createCell(2);
//                        cell.setCellValue(format(Utils.NVL(hm.get("received"))));
//                        cell.setCellStyle(hCellstyleR);
//                        
//                        cell = row.createCell(3);
//                        cell.setCellValue(format(Utils.NVL(hm.get("paid"))));
//                        cell.setCellStyle(hCellstyleR);
//                        
//                        cell = row.createCell(4);
//                        cell.setCellValue(format(Utils.NVL(hm.get("actualmoney"))));
//                        cell.setCellStyle(hCellstyleR);
//                        
//                        
//                        actualmoney = actualmoney.add(new BigDecimal(Utils.NVL(hm.get("actualmoney")).equals("")?"0":Utils.NVL(hm.get("actualmoney"))));

                }
                    
                int sizealN = alN.size();
                logger.debug(">>terex sizealN:"+sizealN);
                for(int i=0;i<sizealN;i++){
                    
                    String[] fixdepositN = alN.get(i);
                    
                    row = hSheet.createRow(8+i);
                    cell = row.createCell(0);
                    cell.setCellValue(Utils.NVL(fixdepositN[0]));
                    cell.setCellStyle(hCellstyleL);


                    cell = row.createCell(1);
                    cell.setCellValue(format(Utils.NVL(fixdepositN[1])));
                    cell.setCellStyle(hCellstyleR);

                    cell = row.createCell(2);
                    cell.setCellValue(format(Utils.NVL(fixdepositN[2])));
                    cell.setCellStyle(hCellstyleR);

                    cell = row.createCell(3);
                    cell.setCellValue(format(Utils.NVL(fixdepositN[3])));
                    cell.setCellStyle(hCellstyleR);

                    cell = row.createCell(4);
                    cell.setCellValue(format(Utils.NVL(fixdepositN[4])));
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(5);
                    cell.setCellValue(format(Utils.NVL(fixdepositN[5])));
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(6);
                    cell.setCellValue(format(Utils.NVL(fixdepositN[6])));
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(7);
                    cell.setCellValue(format(Utils.NVL(fixdepositN[7])));
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(8);
                    cell.setCellValue(format(Utils.NVL(fixdepositN[8])));
                    cell.setCellStyle(hCellstyleR);


                    actualmoneyN = actualmoneyN.add(new BigDecimal(Utils.NVL(fixdepositN[8]).equals("")?"0":Utils.NVL(fixdepositN[8])));

                }    
                    
                    
                //=========Total=============== 
                row = hSheet.createRow(sizealN+8);
                cell = row.createCell(0);
                cell.setCellValue("Total (BATH)");
                cell.setCellStyle(hCellstyleR);   
                
                for(int i=1;i<8;i++){
                        cell = row.createCell(i);
                        cell.setCellStyle(hCellstyleR);
                }
                
                cell = row.createCell(8);
                cell.setCellValue(format(actualmoneyN.toString()));
                cell.setCellStyle(hCellstyleR);
                
                
                //=====================================
                int sizealY = alY.size();
                logger.debug(">>terex sizealY:"+sizealY);
                for(int i=0;i<sizealY;i++){
                    
                    String[] fixdepositY = alY.get(i);
                    
                    row = hSheet.createRow(sizealN+1+8+i);
                    cell = row.createCell(0);
                    cell.setCellValue(Utils.NVL(fixdepositY[0]));
                    cell.setCellStyle(hCellstyleL);


                    cell = row.createCell(1);
                    cell.setCellValue(format(Utils.NVL(fixdepositY[1])));
                    cell.setCellStyle(hCellstyleR);

                    cell = row.createCell(2);
                    cell.setCellValue(format(Utils.NVL(fixdepositY[2])));
                    cell.setCellStyle(hCellstyleR);

                    cell = row.createCell(3);
                    cell.setCellValue(format(Utils.NVL(fixdepositY[3])));
                    cell.setCellStyle(hCellstyleR);

                    cell = row.createCell(4);
                    cell.setCellValue(format(Utils.NVL(fixdepositY[4])));
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(5);
                    cell.setCellValue(format(Utils.NVL(fixdepositY[5])));
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(6);
                    cell.setCellValue(format(Utils.NVL(fixdepositY[6])));
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(7);
                    cell.setCellValue(format(Utils.NVL(fixdepositY[7])));
                    cell.setCellStyle(hCellstyleR);
                    
                    cell = row.createCell(8);
                    cell.setCellValue(format(Utils.NVL(fixdepositY[8])));
                    cell.setCellStyle(hCellstyleR);


                    actualmoneyY = actualmoneyY.add(new BigDecimal(Utils.NVL(fixdepositY[8]).equals("")?"0":Utils.NVL(fixdepositY[8])));

                }    
                    
                    
                //=========Total=============== 
                row = hSheet.createRow(sizealN+sizealY+1+8);
                cell = row.createCell(0);
                cell.setCellValue("Total Fix Deposit (BATH)");
                cell.setCellStyle(hCellstyleR);   
                
                for(int i=1;i<8;i++){
                        cell = row.createCell(i);
                        cell.setCellStyle(hCellstyleR);
                }
                
                cell = row.createCell(8);
                cell.setCellValue(format(actualmoneyY.toString()));
                cell.setCellStyle(hCellstyleR);
                
                row = hSheet.createRow(sizealN+sizealY+2+8);
                cell = row.createCell(0);
                cell.setCellValue("Total All (BATH)");
                cell.setCellStyle(hCellstyleR);   
                
                for(int i=1;i<8;i++){
                        cell = row.createCell(i);
                        cell.setCellStyle(hCellstyleR);
                }
                
                cell = row.createCell(8);
                cell.setCellValue(format(actualmoneyN.add(actualmoneyY).toString()));
                cell.setCellStyle(hCellstyleR);

                //=====================================    
                //Query Data2
                HashMap hmpre2 = new HashMap<String, String>();
                hmpre2.put("bfdate", CenterUtils.previousDayEn(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),1));
                hmpre2.put("bankid2", "2");
                List lpre2 = CenterUtils.selectData(hmpre2,"search_bringforward");
                
                
                HashMap hm2 = new HashMap<String, String>();
                hm2.put("bfdate", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                hm2.put("bankid2", "2");

                List l2 = CenterUtils.selectData(hm2,"search_bringforward");
                
                int rowpad = (size+9)+2;
                int size2 = l2.size();
                    for(int i=0;i<size2;i++){
                        
                        if(lpre.isEmpty()){
                            hmpre2 = new HashMap<String, String>();
                        }else{
                            hmpre2 = (HashMap)lpre2.get(i);
                        }                              
                        

                        hm2 = (HashMap)l2.get(i);

                        row = hSheet.createRow((rowpad+1)+i);
                        cell = row.createCell(0);
                        cell.setCellValue(Utils.NVL(hm2.get("bankname")));
                        cell.setCellStyle(hCellstyleL);
                        
                        
                        cell = row.createCell(1);
                        cell.setCellValue(format(Utils.NVL(hmpre2.get("actualmoney"))));
                        cell.setCellStyle(hCellstyleR);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(format(Utils.NVL(hm2.get("received"))));
                        cell.setCellStyle(hCellstyleR);
                        
                        cell = row.createCell(3);
                        cell.setCellValue(format(Utils.NVL(hm2.get("paid"))));
                        cell.setCellStyle(hCellstyleR);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(format(Utils.NVL(hmpre2.get("bpchqrcv"))));
                        cell.setCellStyle(hCellstyleR);
                        
                        cell = row.createCell(5);
                        cell.setCellValue(format(Utils.NVL(hm2.get("bpchqpaid"))));
                        cell.setCellStyle(hCellstyleR);
                        
                        cell = row.createCell(6);
                        cell.setCellValue(format(Utils.NVL(hm2.get("btchqrcv"))));
                        cell.setCellStyle(hCellstyleR);
                        
                        cell = row.createCell(7);
                        cell.setCellValue(format(Utils.NVL(hm2.get("btchqpaid"))));
                        cell.setCellStyle(hCellstyleR);
                        
                        cell = row.createCell(8);
                        cell.setCellValue(format(Utils.NVL(hm2.get("actualmoney"))));
                        cell.setCellStyle(hCellstyleR);
                        

                }
                
                //==========================================
                    
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030500data.xls");
                
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
    
    private String format(String value){
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format( Utils.NVL(value).equals("")?new BigDecimal(0).doubleValue():new BigDecimal(Utils.NVL(value)).doubleValue());
    }
     
    
    private boolean validategenDataExcel(){
       boolean isok = true;
       
        
        
        return isok;
    }
}
