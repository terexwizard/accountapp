/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.f1.Constant;
import com.scc.pay.business.BusinessFactory;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Invoice;
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
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
public class ATR030200 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atr030200e.xhtml";
    private static final String PAGE_Q  = "atr030200q.xhtml";
    
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
        private Invoice invoice = null;
        private Date datest;
        private Date datefn;

        public Invoice getInvoice() {
            if(invoice == null){
                invoice = new Invoice();
            }
            return invoice;
        }

        public void setInvoice(Invoice invoice) {
            this.invoice = invoice;
        }

        public Date getDatest() {
            return datest;
        }

        public void setDatest(Date datest) {
            this.datest = datest;
        }

        public Date getDatefn() {
            return datefn;
        }

        public void setDatefn(Date datefn) {
            this.datefn = datefn;
        }
        
        
        
    }
    
    
    public ATR030200() {
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
//            IBusinessBase ib = BusinessFactory.getBusiness("ATR030200A");
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030200U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030200D");
            
            
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
//            HashMap<String, String> hm = new HashMap<String, String>();
//            
//            hm.put("dailydate", this.getSearchparam().getDaily().getDailyPK().getDailydate());
//            hm.put("jobno", this.getSearchparam().getDaily().getDailyPK().getJobno());
//   
//            BKBUQuery.getIns().setQueryparam(hm);
//            BKBUQuery.getIns().search();
//          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATR030200S");
        
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
                
                String pathFile = Constant.context_realpath+"/templeteExcel/ATR030200.xls";   //ชี้ path  file excel

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
                
                hSheet.addMergedRegion(new Region(2,(short)0,2,(short)2));

                //Query Data
                HashMap hm = new HashMap<String, String>();
                hm.put("datest", Utils.formatDateToStringToDBEn(this.getMasterdata().getDatest()));
                hm.put("datefn", Utils.formatDateToStringToDBEn(this.getMasterdata().getDatefn()));
                hm.put("jobno", this.getMasterdata().getInvoice().getJobno());

                List l = CenterUtils.selectData(hm,"ATR030200SEARCH");

                
                String invcomid = "";
                
                ArrayList<ArrayList> almain = new ArrayList<ArrayList>();
                ArrayList<DataExcel> aldata = new ArrayList<DataExcel>();
                
                //=======Header============ 
                String header = "วันที่  " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());


                HSSFRow rowhd = hSheet.createRow(2);      
                cell = rowhd.createCell(0);
                cell.setCellValue(header);
                cell.setCellStyle(hCellstyleCB);
                
                
                if(!l.isEmpty()){
                    
                    int size = l.size();
                    logger.debug(">>l size "+size);
                    
                    for(int i=0;i<size;i++){

                        hm = (HashMap)l.get(i);

                        if(Utils.NVL(invcomid).equals("")){
                            invcomid = Utils.NVL(hm.get("invcomid"));
                        }

                        if(Utils.NVL(hm.get("invcomid")).equals(invcomid)){
                            DataExcel data = new DataExcel();
                            data.setInvdate_disp(Utils.NVL(hm.get("invdate_disp")));
                            data.setCompany(Utils.NVL(hm.get("company")));
                            data.setInvno(Utils.NVL(hm.get("invno")));
                            data.setJobno(Utils.NVL(hm.get("jobno")));
                            data.setAmount(Utils.NVL(hm.get("amount")));
                            data.setDuedate(Utils.NVL(hm.get("duedate_disp")));
                            data.setPaid_amount(Utils.NVL(hm.get("paid_amount")));
                            data.setReceived_date(Utils.NVL(hm.get("received_date_disp")));
                            
                            logger.debug(">>terex if");
                            aldata.add(data);
                        }else{
                            logger.debug(">>terex else");
                            
                            almain.add(aldata);
                            
                            aldata = new ArrayList<DataExcel>();
                            invcomid = Utils.NVL(hm.get("invcomid"));
                            
                            //=======================
                            
                            DataExcel data = new DataExcel();
                            data.setInvdate_disp(Utils.NVL(hm.get("invdate_disp")));
                            data.setCompany(Utils.NVL(hm.get("company")));
                            data.setInvno(Utils.NVL(hm.get("invno")));
                            data.setJobno(Utils.NVL(hm.get("jobno")));
                            data.setAmount(Utils.NVL(hm.get("amount")));
                            data.setDuedate(Utils.NVL(hm.get("duedate_disp")));
                            data.setPaid_amount(Utils.NVL(hm.get("paid_amount")));
                            data.setReceived_date(Utils.NVL(hm.get("received_date_disp")));
                            
                            aldata.add(data);
                        }
                    }
                    
                    if(!Utils.NVL(aldata.get(0).getInvdate_disp()).equals("")){
                        almain.add(aldata);
                    }
                    
                    logger.debug(">>almain size "+almain.size());
                    
                    //==============
                    int rowheader = 4;
                    for(ArrayList<DataExcel> alvalue : almain){
                        
                        
                        HSSFRow row = hSheet.createRow(rowheader);      
                        cell = row.createCell(0);
                        cell.setCellValue("#");
                        cell.setCellStyle(hCellstyle);


                        cell = row.createCell(1);
                        cell.setCellValue("invdate_disp");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(2);
                        cell.setCellValue("company");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(3);
                        cell.setCellValue("invno");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(4);
                        cell.setCellValue("jobno");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(5);
                        cell.setCellValue("amount");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(6);
                        cell.setCellValue("duedate");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(7);
                        cell.setCellValue("paid_amount");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(8);
                        cell.setCellValue("received_date");
                        cell.setCellStyle(hCellstyle);

                        rowheader++;
                        //==================
                        
                        int i=0;
                        BigDecimal amount = new BigDecimal(0);
                        logger.debug(">>alvalue size "+alvalue.size());
                        
                        for(DataExcel dataexcel : alvalue){
                            
                            row = hSheet.createRow(rowheader);      
                            cell = row.createCell(0);
                            cell.setCellValue((i+1)+".");
                            cell.setCellStyle(hCellstyle);

                            cell = row.createCell(1);
                            cell.setCellValue(Utils.NVL(dataexcel.getInvdate_disp()));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(2);
                            cell.setCellValue(Utils.NVL(dataexcel.getCompany()));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(3);
                            cell.setCellValue(Utils.NVL(dataexcel.getInvno()));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(4);
                            cell.setCellValue(Utils.NVL(dataexcel.getJobno()));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(5);
                            cell.setCellValue(format(Utils.NVL(dataexcel.getAmount())));
                            cell.setCellStyle(hCellstyleL);
                            
                            if(!Utils.NVL(dataexcel.getAmount()).equals("")){
                                amount = amount.add(new BigDecimal(Utils.NVL(dataexcel.getAmount())));
                            }

                            cell = row.createCell(6);
                            cell.setCellValue(Utils.NVL(dataexcel.getDuedate()));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(7);
                            cell.setCellValue(format( Utils.NVL(dataexcel.getPaid_amount())));
                            cell.setCellStyle(hCellstyleL);

                            cell = row.createCell(8);
                            cell.setCellValue(Utils.NVL(dataexcel.getReceived_date()));
                            cell.setCellStyle(hCellstyleL);
                            
                            i++;
                            rowheader++;
                        }
                        
                        //=========Sum======
                        row = hSheet.createRow(rowheader);      
                        cell = row.createCell(5);
                        cell.setCellValue(format(amount.toString()));
                        cell.setCellStyle(hCellstyleL);
                        //==================
                        
                        rowheader++;
                        
                    }
                   
                    
                    
//                    //=======Header============ 
//                    
//
//                    String header = "วันที่  " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());
//                    
//
//                    HSSFRow row = hSheet.createRow(2);      
//                    cell = row.createCell(0);
//                    cell.setCellValue(header);
//                    cell.setCellStyle(hCellstyleCB);
//                    
//
//                    row = hSheet.createRow(4);      
//                    cell = row.createCell(0);
//                    cell.setCellValue("#");
//                    cell.setCellStyle(hCellstyle);
//
//
//                    cell = row.createCell(1);
//                    cell.setCellValue("invdate_disp");
//                    cell.setCellStyle(hCellstyle);
//
//                    cell = row.createCell(2);
//                    cell.setCellValue("company");
//                    cell.setCellStyle(hCellstyle);
//
//                    cell = row.createCell(3);
//                    cell.setCellValue("invno");
//                    cell.setCellStyle(hCellstyle);
//
//                    cell = row.createCell(4);
//                    cell.setCellValue("jobno");
//                    cell.setCellStyle(hCellstyle);
//                    
//                    cell = row.createCell(5);
//                    cell.setCellValue("amount");
//                    cell.setCellStyle(hCellstyle);
//                    
//                    cell = row.createCell(6);
//                    cell.setCellValue("duedate");
//                    cell.setCellStyle(hCellstyle);
//                    
//                    cell = row.createCell(7);
//                    cell.setCellValue("paid_amount");
//                    cell.setCellStyle(hCellstyle);
//                    
//                    cell = row.createCell(8);
//                    cell.setCellValue("received_date");
//                    cell.setCellStyle(hCellstyle);
//
//                    //==================
//                    
//                    
//                    int size = l.size();
//                    for(int i=0;i<size;i++){
//
//                        hm = (HashMap)l.get(i);
//
//
//                        row = hSheet.createRow(5+i);      
//                        cell = row.createCell(0);
//                        cell.setCellValue((i+1)+".");
//                        cell.setCellStyle(hCellstyle);
//
//                        cell = row.createCell(1);
//                        cell.setCellValue(Utils.NVL(hm.get("invdate_disp")));
//                        cell.setCellStyle(hCellstyleL);
//                        
//                        cell = row.createCell(2);
//                        cell.setCellValue(Utils.NVL(hm.get("company")));
//                        cell.setCellStyle(hCellstyleL);
//                        
//                        cell = row.createCell(3);
//                        cell.setCellValue(Utils.NVL(hm.get("invno")));
//                        cell.setCellStyle(hCellstyleL);
//                        
//                        cell = row.createCell(4);
//                        cell.setCellValue(Utils.NVL(hm.get("jobno")));
//                        cell.setCellStyle(hCellstyleL);
//                        
//                        cell = row.createCell(5);
//                        cell.setCellValue(format(Utils.NVL(hm.get("amount"))));
//                        cell.setCellStyle(hCellstyleL);
//
//                        cell = row.createCell(6);
//                        cell.setCellValue(Utils.NVL(hm.get("duedate_disp")));
//                        cell.setCellStyle(hCellstyleL);
//                        
//                        cell = row.createCell(7);
//                        cell.setCellValue(format( Utils.NVL(hm.get("paid_amount"))));
//                        cell.setCellStyle(hCellstyleL);
//                        
//                        cell = row.createCell(8);
//                        cell.setCellValue(Utils.NVL(hm.get("received_date")));
//                        cell.setCellStyle(hCellstyleL);
//                        
//                        
//
//                    }
//                    
//                    
//                    
//
//                                
                ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
                hWBook.write(bOutput);

                FaceUtil.getDownloadfile(bOutput, "ATR030200data.xls");
                
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
       
       if((this.getMasterdata().getDatest() != null && this.getMasterdata().getDatefn() == null) ||
                (this.getMasterdata().getDatest() == null && this.getMasterdata().getDatefn() != null)){
                
            String msg = "กรอกวันที่ไม่ถูกต้อง";
            addErrorMessage(null,msg,msg);
            return false;

        }
       
        if(this.getMasterdata().getDatest() != null && this.getMasterdata().getDatefn() != null){

                String s = Utils.NVL(Utils.formatDateToStringToDBEn(this.getMasterdata().getDatest()));
                String e = Utils.NVL(Utils.formatDateToStringToDBEn(this.getMasterdata().getDatefn()));

                if(Integer.parseInt(s) > Integer.parseInt(e)){
                    String msg = "กรอกวันที่ไม่ถูกต้อง";
                    addErrorMessage(null,msg,msg);
                    return false;
                }

         }
        
        
        return isok;
    }
    
    
     public class DataExcel extends BBBase{
         
        private String invdate_disp;
        private String company;
        private String invno;
        private String jobno;
        private String amount;
        private String duedate;
        private String paid_amount;
        private String received_date;

        public String getInvdate_disp() {
            return invdate_disp;
        }

        public void setInvdate_disp(String invdate_disp) {
            this.invdate_disp = invdate_disp;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getInvno() {
            return invno;
        }

        public void setInvno(String invno) {
            this.invno = invno;
        }

        public String getJobno() {
            return jobno;
        }

        public void setJobno(String jobno) {
            this.jobno = jobno;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDuedate() {
            return duedate;
        }

        public void setDuedate(String duedate) {
            this.duedate = duedate;
        }

        public String getPaid_amount() {
            return paid_amount;
        }

        public void setPaid_amount(String paid_amount) {
            this.paid_amount = paid_amount;
        }

        public String getReceived_date() {
            return received_date;
        }

        public void setReceived_date(String received_date) {
            this.received_date = received_date;
        }
        
        
         
         
     }
}
