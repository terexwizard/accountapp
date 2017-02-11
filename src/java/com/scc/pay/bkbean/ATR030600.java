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
import java.math.BigInteger;

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
import org.apache.poi.ss.usermodel.DataFormat;
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
public class ATR030600 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr030600e.xhtml";
    private static final String PAGE_Q  = "atr030600q.xhtml";
    
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
    
    
    public ATR030600() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR030600A");
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030600U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030600D");
            
            
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030600S");
        
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
                
                                
                HSSFCellStyle hCellstyleRMoney = hWBook.createCellStyle();                       
                hCellstyleRMoney.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         
                hCellstyleRMoney.setFont(font14);                                                  
                DataFormat format = hWBook.createDataFormat();
                hCellstyleRMoney.setDataFormat(format.getFormat("#,##0.00"));
                CenterUtils.setCellBorder(hCellstyleRMoney);
                
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
                String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());

                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
                HSSFRow row = hSheet.createRow(1);      
                cell = row.createCell(0);
                cell.setCellValue(header);
                cell.setCellStyle(hCellstyleCB);
                
                cell = row.createCell(6);
                cell.setCellValue("ATR030600");
                cell.setCellStyle(hCellstyleCB);
                
                String condition = "Condition :"+Utils.NVL(this.getMasterdata().getDaily().getVoucherno())+" "+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),"/");
                
                
                hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));
                row = hSheet.createRow(2);      
                cell = row.createCell(0);
                cell.setCellValue(condition);
                cell.setCellStyle(hCellstyleCB);

                //Query Data
                HashMap hm = new HashMap<String, String>();
                hm.put("dailydate", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                hm.put("voucherno", Utils.NVL(this.getMasterdata().getDaily().getVoucherno()));
                hm.put("companyid", this.getMasterdata().getDaily().getCompanyid());

                List l = CenterUtils.selectData(hm,"lookup_sumdaily_voucherno_th");
                
                BigDecimal totalth = new BigDecimal(0);
                BigDecimal totalus = new BigDecimal(0);

                if(!l.isEmpty()){

                    
                    //hSheet.addMergedRegion(new Region(3,(short)0,3,(short)2));
                    hSheet.setColumnWidth((short)0,(short)(ONEPIXEL*300));
                    row = hSheet.createRow(4);      
                    cell = row.createCell(0);
                    cell.setCellValue("Dailydate");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(1);
                    cell.setCellValue("Companyname");
                    cell.setCellStyle(hCellstyleHColor);
                         
                    cell = row.createCell(2);
                    cell.setCellValue("Shippment");
                    cell.setCellStyle(hCellstyleHColor);

                    cell = row.createCell(3);
                    cell.setCellValue("Amount");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    cell = row.createCell(4);
                    cell.setCellValue("Voucherno");
                    cell.setCellStyle(hCellstyleHColor);
                    
                    int size = l.size();
                    for(int i=0;i<size;i++){
                        

                        hm = (HashMap)l.get(i);

                        hSheet.autoSizeColumn(1);
                        row = hSheet.createRow(5+i);
                        cell = row.createCell(0);
                        cell.setCellValue(Utils.convertDateStringToScreen(Utils.NVL(hm.get("dailydate")),"/"));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(1);
                        cell.setCellValue(Utils.NVL(hm.get("companyname")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(Utils.NVL(hm.get("shippment")));
                        cell.setCellStyle(hCellstyle);
                        
                        cell = row.createCell(3);
                        logger.debug(">>terex "+hm.get("moneyr"));
                        if(!Utils.NVL(hm.get("moneyr")).equals("0.0")){
                            cell.setCellValue(CenterUtils.format(Utils.NVL(hm.get("moneyr"))));
                            
                            totalth = totalth.add(new BigDecimal(Utils.NVL(hm.get("moneyr")).equals("")?"0":Utils.NVL(hm.get("moneyr"))));
                        }else{
                            cell.setCellValue(CenterUtils.format(Utils.NVL(hm.get("moneyp"))));
                            
                            totalth = totalth.add(new BigDecimal(Utils.NVL(hm.get("moneyp")).equals("")?"0":Utils.NVL(hm.get("moneyp"))));
                        }
                        cell.setCellStyle(hCellstyleRMoney);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(getvaluevoucherno(Utils.NVL(hm.get("dailyid"))));
                        cell.setCellStyle(hCellstyle);
                }
                    
                //=============total th
                int paddingHeader = 4;
                if(size > 0){
                    row = hSheet.createRow(size+paddingHeader+1);
                    for(int i=0;i<2;i++){
                            cell = row.createCell(i);
                            cell.setCellStyle(hCellstyleR);
                    }

                    cell = row.createCell(2);
                    cell.setCellValue("Total in Thai");
                    cell.setCellStyle(hCellstyleR);


                    cell = row.createCell(3);
                    cell.setCellValue(CenterUtils.format(totalth.toString()));
                    cell.setCellStyle(hCellstyleRMoney); 
                    
                    cell = row.createCell(4);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleR); 
                }
                
                    
                //Query Data2
                HashMap hm2 = new HashMap<String, String>();
                hm2.put("dailydate", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                hm2.put("voucherno", Utils.NVL(this.getMasterdata().getDaily().getVoucherno()));
                hm2.put("companyid", this.getMasterdata().getDaily().getCompanyid());
                List l2 = CenterUtils.selectData(hm2,"lookup_sumdaily_voucherno_us");
                
                
                int rowpad = (size+4);
                int size2 = l2.size();
                    for(int i=0;i<size2;i++){
                        
                        hm2 = (HashMap)l2.get(i);

                        hSheet.autoSizeColumn(1);
                        row = hSheet.createRow((rowpad+1+1)+i);
                        cell = row.createCell(0);
                        cell.setCellValue(Utils.convertDateStringToScreen(Utils.NVL(hm2.get("dailydate")),"/"));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(1);
                        cell.setCellValue(Utils.NVL(hm2.get("companyname")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(Utils.NVL(hm2.get("shippment")));
                        cell.setCellStyle(hCellstyle);
                        
                        cell = row.createCell(3);
                        if(!Utils.NVL(hm2.get("moneyr")).equals("0.0")){
                            cell.setCellValue(CenterUtils.format(Utils.NVL(hm2.get("moneyr"))));
                            
                            totalus = totalus.add(new BigDecimal(Utils.NVL(hm2.get("moneyr")).equals("")?"0":Utils.NVL(hm2.get("moneyr"))));
                        }else{
                            cell.setCellValue(CenterUtils.format(Utils.NVL(hm2.get("moneyp"))));
                            
                            totalus = totalus.add(new BigDecimal(Utils.NVL(hm2.get("moneyp")).equals("")?"0":Utils.NVL(hm2.get("moneyp"))));
                        }
                        cell.setCellStyle(hCellstyleRMoney);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(getvaluevoucherno(Utils.NVL(hm2.get("dailyid"))));
                        cell.setCellStyle(hCellstyle);                        
                }
                
                    
                //=============total us
                if(size2 > 0){
                    row = hSheet.createRow((rowpad+1+1)+size2);
                    for(int i=0;i<2;i++){
                            cell = row.createCell(i);
                            cell.setCellStyle(hCellstyleR);
                    }

                    cell = row.createCell(2);
                    cell.setCellValue("Total in USD");
                    cell.setCellStyle(hCellstyleR);

                    cell = row.createCell(3);
                    cell.setCellValue(CenterUtils.format(totalus.toString()));
                    cell.setCellStyle(hCellstyleRMoney);  
                    
                    cell = row.createCell(4);
                    cell.setCellValue("");
                    cell.setCellStyle(hCellstyleR); 
                }
                //==========================================
                    
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030600_"+CenterUtils.formatfileNameDatetime()+".xls");
                
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
       
         if((this.getMasterdata().getDailydatest() == null ) &&
             Utils.NVL(this.getMasterdata().getDaily().getCompanyid()).equals("")){
                
            String msg = MessageUtil.getMessage("EP011");
            addErrorMessage(null,msg,msg);
            return false;

        }
        
        return isok;
    }
    
    
    private String getvaluevoucherno(String dailyid){
        HashMap hm = new HashMap<String, String>();
        hm.put("dailyid", dailyid);

        List l = CenterUtils.selectData(hm,"lookup_daily");
        String voucherno_disp = "";
        if(!l.isEmpty()){
             hm = (HashMap)l.get(0);

             voucherno_disp = Utils.NVL(hm.get("voucherno_disp"));
        }
        
        return voucherno_disp;
    }
}
