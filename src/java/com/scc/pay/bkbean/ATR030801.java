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
public class ATR030801 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr030801e.xhtml";
    private static final String PAGE_Q  = "atr030801q.xhtml";
    
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
    
    
    public ATR030801() {
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

        genDataExcel();
        
    }
    
    
    private boolean validateAdd(){
        boolean isok = true;


        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030801U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030801D");
            
            
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
        
        
        initialValue();
        
    }
    
    private void initialValue(){
        if(Utils.NVL(this.getMasterdata().getRdoflag()).equals("")){
            this.getMasterdata().setRdoflag("Y");
        }
        
//        if(Utils.NVL(this.getMasterdata().getMonth()).equals("")){
//            this.getMasterdata().setMonth(Utils.getcurMonth());
//        }
        
        if(Utils.NVL(this.getMasterdata().getYear()).equals("")){
            this.getMasterdata().setYear(Utils.NVL(Integer.parseInt(Utils.getcurYear()) -1));
        }
//        
//        if(Utils.NVL(this.getMasterdata().getReporttype()).equals("")){
//            this.getMasterdata().setReporttype("1");
//        }
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030801S");
        
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
                DataFormat format = hWBook.createDataFormat();
                hCellstyleR.setDataFormat(format.getFormat("#,##0.00"));
                CenterUtils.setCellBorder(hCellstyleR);
                
                HSSFCellStyle hCellstyleHColor = hWBook.createCellStyle();                         
                hCellstyleHColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);                         
                hCellstyleHColor.setFont(font16);                   
                hCellstyleHColor.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                hCellstyleHColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                CenterUtils.setCellBorder(hCellstyleHColor);
                
                HSSFCellStyle hCellstyleHColorR = hWBook.createCellStyle();                         
                hCellstyleHColorR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);                         
                hCellstyleHColorR.setFont(font16);                   
                hCellstyleHColorR.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                hCellstyleHColorR.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                hCellstyleHColorR.setDataFormat(format.getFormat("#,##0.00"));
                CenterUtils.setCellBorder(hCellstyleHColorR);
                
                Font font18B = hWBook.createFont();                                           //กำหนด font style
                font18B.setFontHeightInPoints((short)16);                                     //กำหนดขนาดของ font
                font18B.setFontName("Angsana New");
                font18B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                              //กำหนด font ให้เป็นตัวหนา
                
                HSSFCellStyle hCellstyleCB = hWBook.createCellStyle();                          //กำหนด style cell
                hCellstyleCB.setAlignment(HSSFCellStyle.ALIGN_LEFT);                         //กำหนด ตัวอักษรให้อยู่ซ้าย
                hCellstyleCB.setFont(font18B);                                                  //เรียกใช้ style font

                //=============Process Data===================  
                
                HSSFSheet hSheet = hWBook.getSheetAt(0);
                String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());

                hSheet.addMergedRegion(new Region(0,(short)0,0,(short)2));
                HSSFRow row = hSheet.createRow(0);      
                cell = row.createCell(0);
                cell.setCellValue(header);
                cell.setCellStyle(hCellstyleCB);

                cell = row.createCell(6);
                cell.setCellValue("ATR030801 Year");
                cell.setCellStyle(hCellstyleCB);

                String condition = "";
                if(Utils.NVL(this.getMasterdata().getYear()).equals("")){
                    if(!Utils.NVL(this.getMasterdata().getDaily().getCompanyid()).equals("")){
                         condition = "Condition Company:"+this.getMasterdata().getDaily().getCompanyname();
                    }
                }else{
                
                    if(Utils.NVL(this.getMasterdata().getDaily().getCompanyid()).equals("")){
                        condition = "Condition Year: "+this.getMasterdata().getYear();
                    }else{
                        condition = "Condition Company:"+this.getMasterdata().getDaily().getCompanyname()+" Year: "+this.getMasterdata().getYear();
                    }
                }
                
                condition += " Status : ";
                condition += Utils.NVL(this.getMasterdata().getRdoflag()).equals("Y")?"Clear":"Not Clear";
                
                
                hSheet.addMergedRegion(new Region(1,(short)0,1,(short)3));
                row = hSheet.createRow(1);      
                cell = row.createCell(0);
                cell.setCellValue(condition);
                cell.setCellStyle(hCellstyleCB);
                
                int rowpad = 3;    
                
                hSheet.setColumnWidth(1, 15000);
                
                row = hSheet.createRow(rowpad);
                cell = row.createCell(0);
                cell.setCellValue("#");
                cell.setCellStyle(hCellstyleHColor);

                cell = row.createCell(1);
                cell.setCellValue("COMPANY");
                cell.setCellStyle(hCellstyleHColor);

                cell = row.createCell(2);
                cell.setCellValue("Total");
                cell.setCellStyle(hCellstyleHColor);
                
                
                List lcompany = new ArrayList();
                HashMap hm = new HashMap<String, String>();
                HashMap<String, String> hmcompany = new HashMap<String, String>();
                hmcompany.put("invcomid", this.getMasterdata().getDaily().getCompanyid());
                hmcompany.put("submitdatelike", this.getMasterdata().getYear());
                if(Utils.NVL(this.getMasterdata().getRdoflag()).equals("Y")){
                    hmcompany.put("clearflagY", "Y");
                }else{
                    hmcompany.put("clearflagN", "N");
                }
                lcompany = CenterUtils.selectData(hmcompany,"lookup_data_invoice_distinct_year");                
                
                rowpad++;
                int sizecompany = lcompany.size();
                
                if(lcompany.isEmpty()){
                    String msg = "ไม่พบข้อมูล";
                    addInfoMessage(null, msg, msg);
                    return;
                }
                
                BigDecimal sumtotal = new BigDecimal(0);
                for(int i=0;i<sizecompany;i++){
                    
                    hm = (HashMap)lcompany.get(i);   
                    
                    HashMap hmDataCompany = new HashMap<String, String>();
                    hmDataCompany.put("invcomid", Utils.NVL(hm.get("invcomid")));
                    hmDataCompany.put("submitdatelike", this.getMasterdata().getYear());
                    if(Utils.NVL(this.getMasterdata().getRdoflag()).equals("Y")){
                        hmDataCompany.put("clearflagY", "Y");
                    }else{
                        hmDataCompany.put("clearflagN", "N");
                    }
                    List lDataCompany = CenterUtils.selectData(hmDataCompany,"lookup_sumdata_invoice_year");
                    
                    int sizedata = lDataCompany.size();
                    for(int j=0;j<sizedata;j++){
                        HashMap hmvalue = (HashMap)lDataCompany.get(j); 
                        
                        row = hSheet.createRow(rowpad);
                        cell = row.createCell(0);
                        cell.setCellValue(i+1);
                        cell.setCellStyle(hCellstyle);
                        
                        //======================
                        HashMap<String, String> hmname = new HashMap<String, String>();
                        hmname.put("invcomid", Utils.NVL(hm.get("invcomid")));
                        List lname = CenterUtils.selectData(hmname,"lookup_invoicecompany");
                        if(!lname.isEmpty()){
                            hmname = (HashMap)lname.get(0);
                        }
                        //======================

                        logger.debug(">>terex :"+Utils.NVL(hm.get("invcomid"))+" ///"+Utils.NVL(hmname.get("companyname")));
                        
                        cell = row.createCell(1);
                        cell.setCellValue(Utils.NVL(hmname.get("companyname")).equals("")?Utils.NVL(hm.get("invcomid"))+" (No Company Name)":Utils.NVL(hmname.get("companyname")));
                        cell.setCellStyle(hCellstyleL);

                        cell = row.createCell(2);
                        cell.setCellValue(CenterUtils.format(Utils.NVL(hmvalue.get("sumdata"))));
                        cell.setCellStyle(hCellstyleR);
                        
                        
                        sumtotal = sumtotal.add(new BigDecimal(Utils.NVL(hmvalue.get("sumdata")).equals("")?"0.00":Utils.NVL(hmvalue.get("sumdata"))));
                        
                        rowpad++;
                    }
                    
                }
                
                
                row = hSheet.createRow(rowpad);
                 cell = row.createCell(2);
                 cell.setCellValue(CenterUtils.format(sumtotal.toString()));
                 cell.setCellStyle(hCellstyleHColorR);
                
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030801_YEAR_"+this.getMasterdata().getYear()+"_"+CenterUtils.formatfileNameDatetime()+".xls");

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
    
    private String replaceName(String name){
        String result = name.replace("[", "");
        result = result.replace("]", "");
        result = result.replace("/", "");
        
        return result;
    }
    
}
