/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.f1.Constant;
import com.scc.pay.business.BusinessFactory;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.Utils;
import com.scc.pay.db.TbBank;
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

import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
public class ATP010100 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atp010100e.xhtml";
    private static final String PAGE_Q  = "atp010100q.xhtml";
    
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
        private TbBank tbbank = null;

        public TbBank getTbbank() {
             if(tbbank == null){
                tbbank = new TbBank();
            }
            return tbbank;
        }

        public void setTbbank(TbBank tbbank) {
            this.tbbank = tbbank;
        }

        

        
        
    }
    
    
    public ATP010100() {
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
        
       if(validateAdd()){
            
//            toDB();
            
            IBusinessBase ib = BusinessFactory.getBusiness("ATP010100A");
            
            
            ib.process(this);
            
            genMessage(ib);
            
            if(ib.isOk()){
                clearAllData();
            }
            
            
        }else{
            
//            FacesContext.getCurrentInstance().renderResponse();
            
        }
        
        
    }
    
    
    private boolean validateAdd(){
        boolean isok = true;


        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP010100U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP010100D");
            
            
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
        
        BKBUQuery.getIns().clearListData();
        
        this.getSearchparam();
        search();
    }
    
    private void search(){
            
//            logger.debug("q para "+ this.getSearchparam().getProvCode()+", "+ 
//                        this.getSearchparam().getProvName()+" ");
//       
            HashMap<String, String> hm = new HashMap<String, String>();
            
            hm.put("bankname", this.getSearchparam().getTbbank().getBankname());
            hm.put("banknamesh", this.getSearchparam().getTbbank().getBanknamesh());
            hm.put("fixdeposit", this.getSearchparam().getTbbank().getFixdeposit());
   
            BKBUQuery.getIns().setQueryparam(hm);
            BKBUQuery.getIns().search();
          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP010100S");
        
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
                
                String pathFile = Constant.context_realpath+"/templeteExcel/ATP010100.xls";   //ชี้ path  file excel

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
                
                Font font18B = hWBook.createFont();                                           //กำหนด font style
                font18B.setFontHeightInPoints((short)18);                                     //กำหนดขนาดของ font
                font18B.setFontName("TH SarabunPSK");
                font18B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา
                
                HSSFCellStyle hCellstyleCB = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleCB.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
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

                //Query Data
                HashMap hm = new HashMap<String, String>();
                hm.put("dailydate", Utils.formatDateToStringToDBEn(new Date()));

                List l = CenterUtils.selectData(hm,"ATP010100SEARCH");

                if(!l.isEmpty()){
                    //=======Header============ 
                    

                    String header = "วันที่  " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());
                    

                    HSSFRow row = hSheet.createRow(2);      
                    cell = row.createCell(0);
                    cell.setCellValue(header);
                    cell.setCellStyle(hCellstyleCB);
                    

                    row = hSheet.createRow(4);      
                    cell = row.createCell(0);
                    cell.setCellValue("#");
                    cell.setCellStyle(hCellstyle);


                    cell = row.createCell(1);
                    cell.setCellValue("dailydate");
                    cell.setCellStyle(hCellstyle);

                    cell = row.createCell(2);
                    cell.setCellValue("NO");
                    cell.setCellStyle(hCellstyle);

                    cell = row.createCell(3);
                    cell.setCellValue("description");
                    cell.setCellStyle(hCellstyle);

                    cell = row.createCell(4);
                    cell.setCellValue("docno");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("chqno");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(6);
                    cell.setCellValue("jobno");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(7);
                    cell.setCellValue("received_cash");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(8);
                    cell.setCellValue("received_bank");
                    cell.setCellStyle(hCellstyle);
              
                    cell = row.createCell(9);
                    cell.setCellValue("received_cr");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(10);
                    cell.setCellValue("payment_cash");
                    cell.setCellStyle(hCellstyle);
                    
                    
                    cell = row.createCell(11);
                    cell.setCellValue("payment_bank");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(12);
                    cell.setCellValue("payment_cr");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(13);
                    cell.setCellValue("balance_cash");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(14);
                    cell.setCellValue("balance_cr");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(15);
                    cell.setCellValue("vendor");
                    cell.setCellStyle(hCellstyle);

                    //==================


                    for(int i=0;i<l.size();i++){

                        hm = (HashMap)l.get(i);


                        row = hSheet.createRow(4+i);      
                        cell = row.createCell(0);
                        cell.setCellValue((i+1)+".");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(1);
                        cell.setCellValue(Utils.NVL(hm.get("dailydate")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(Utils.NVL(hm.get("no")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(3);
                        cell.setCellValue(Utils.NVL(hm.get("description")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(Utils.NVL(hm.get("docno_code"))+Utils.NVL(hm.get("docno")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(5);
                        cell.setCellValue(Utils.NVL(hm.get("chqno")));
                        cell.setCellStyle(hCellstyleL);

                        cell = row.createCell(6);
                        cell.setCellValue(Utils.NVL(hm.get("jobno")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(7);
                        cell.setCellValue(Utils.NVL(hm.get("received_cash")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(8);
                        cell.setCellValue(Utils.NVL(hm.get("received_bank")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(9);
                        cell.setCellValue(Utils.NVL(hm.get("received_cr")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(10);
                        cell.setCellValue(Utils.NVL(hm.get("payment_cash")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(11);
                        cell.setCellValue(Utils.NVL(hm.get("payment_bank")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(12);
                        cell.setCellValue(Utils.NVL(hm.get("payment_cr")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(13);
                        cell.setCellValue(Utils.NVL(hm.get("balance_cash")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(14);
                        cell.setCellValue(Utils.NVL(hm.get("balance_cr")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(15);
                        cell.setCellValue(Utils.NVL(hm.get("vendor")));
                        cell.setCellStyle(hCellstyleL);

                    }
                                
                                
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATP010100data.xls");
                
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
      
}
