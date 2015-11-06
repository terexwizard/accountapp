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

import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
                hm.put("dailydatest", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                hm.put("dailydatefn", Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()));
                hm.put("jobref", this.getMasterdata().getDaily().getJobref());
                hm.put("paymentsuccess", this.getMasterdata().getReceivesuccess());

                List l = CenterUtils.selectData(hm,"ATR030200SEARCH");

                if(!l.isEmpty()){
                    //=======Header============ 
                    

                    String header = "Date : " + CenterUtils.formatDateToStringShowTime(Utils.getcurDateTime());
                    

                    HSSFRow row = hSheet.createRow(1);      
                    cell = row.createCell(0);
                    cell.setCellValue(header);
                    cell.setCellStyle(hCellstyleCB);
                    
                    String condition = "Condition :"+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()),"/") 
                        +"-"+Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()),"/");
                    
                    row = hSheet.createRow(2);      
                    cell = row.createCell(0);
                    cell.setCellValue(condition);
                    cell.setCellStyle(hCellstyleCB);
                    
                    

                    row = hSheet.createRow(4);      
                    cell = row.createCell(0);
                    cell.setCellValue("#");
                    cell.setCellStyle(hCellstyle);


                    cell = row.createCell(1);
                    cell.setCellValue("dailydate");
                    cell.setCellStyle(hCellstyle);

                    cell = row.createCell(2);
                    cell.setCellValue("dailytype");
                    cell.setCellStyle(hCellstyle);

                    cell = row.createCell(3);
                    cell.setCellValue("descriptioncode");
                    cell.setCellStyle(hCellstyle);

                    cell = row.createCell(4);
                    cell.setCellValue("voucherno_disp");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(5);
                    cell.setCellValue("jobref");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(6);
                    cell.setCellValue("transecsionno");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(7);
                    cell.setCellValue("bankname");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(8);
                    cell.setCellValue("exchangerate");
                    cell.setCellStyle(hCellstyle);
                    
                                  
                    cell = row.createCell(9);
                    cell.setCellValue("amount");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(10);
                    cell.setCellValue("paymentamount");
                    cell.setCellStyle(hCellstyle);

                    

                    
                    
                    cell = row.createCell(11);
                    cell.setCellValue("currencyname");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(12);
                    cell.setCellValue("remark");
                    cell.setCellStyle(hCellstyle);
                    
                    cell = row.createCell(13);
                    cell.setCellValue("paymentsuccess");
                    cell.setCellStyle(hCellstyle);

                    //==================
                    BigDecimal amount = new BigDecimal(0);
                    BigDecimal receivedamount = new BigDecimal(0);
                    
                    int size = l.size();
                    for(int i=0;i<size;i++){

                        hm = (HashMap)l.get(i);


                        row = hSheet.createRow(5+i);      
                        cell = row.createCell(0);
                        cell.setCellValue((i+1)+".");
                        cell.setCellStyle(hCellstyle);

                        cell = row.createCell(1);
                        cell.setCellValue(Utils.NVL(hm.get("dailydate")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(2);
                        cell.setCellValue(Utils.NVL(hm.get("dailytype")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(3);
                        cell.setCellValue(Utils.NVL(hm.get("descriptioncode")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(4);
                        cell.setCellValue(Utils.NVL(hm.get("voucherno_disp")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(5);
                        cell.setCellValue(Utils.NVL(hm.get("jobref")));
                        cell.setCellStyle(hCellstyleL);

                        cell = row.createCell(6);
                        cell.setCellValue(Utils.NVL(hm.get("transecsionno")));
                        cell.setCellStyle(hCellstyleL);
                                                
                        cell = row.createCell(7);
                        cell.setCellValue( Utils.NVL(hm.get("bankname")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(8);
                        cell.setCellValue(format(Utils.NVL(hm.get("exchangerate"))));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(9);
                        cell.setCellValue(format(Utils.NVL(hm.get("amount2"))));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(10);
                        cell.setCellValue(format( Utils.NVL(hm.get("paidamount"))));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(11);
                        cell.setCellValue(Utils.NVL(hm.get("currencyname")));
                        cell.setCellStyle(hCellstyleL);
                        
                        cell = row.createCell(12);
                        cell.setCellValue(Utils.NVL(hm.get("remark")));
                        cell.setCellStyle(hCellstyleL);
                        
//                        cell = row.createCell(13);
//                        cell.setCellValue(Utils.NVL(hm.get("receivesuccess")));
//                        cell.setCellStyle(hCellstyleL);
                        
                        if(Utils.NVL(hm.get("paymentsuccess")).equals("Y")){
                            insertImage(hWBook,hSheet,i,13);
                        }
                        
                        
                        
                        //====sum======
                       //if(new Double(Utils.NVL(hm.get("payby"))).intValue() == 2){
                        if(Utils.NVL(hm.get("monetaryusd")).equals("true")){
                            amount = amount.add(new BigDecimal(Utils.NVL(hm.get("amount2"))));
                        }else{
                            receivedamount = receivedamount.add(new BigDecimal(Utils.NVL(hm.get("paidamount"))));
                        }
                        

                    }
                    
                    
                    
                    //=======Footer======
                    row = hSheet.createRow(5+size);  

                    cell = row.createCell(8);
                    cell.setCellValue("Payment Amount USD");
                    cell.setCellStyle(hCellstyleL);

                    cell = row.createCell(9);
                    cell.setCellValue(format(amount.toString()));
                    cell.setCellStyle(hCellstyleL);
                    
                    row = hSheet.createRow(6+size);  

                    cell = row.createCell(9);
                    cell.setCellValue("Payment Amount THB");
                    cell.setCellStyle(hCellstyleL);

                    cell = row.createCell(10);
                    cell.setCellValue(format(receivedamount.toString()));
                    cell.setCellStyle(hCellstyleL);

                    
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

    private void insertImage(HSSFWorkbook hWBook,HSSFSheet hSheet,int row,int col) throws FileNotFoundException, IOException{
        //FileInputStream obtains input bytes from the image file
        String pathImage = Constant.context_realpath+"/resources/images/verify-true.gif";
        InputStream inputStream = new FileInputStream(pathImage);
        //Get the contents of an InputStream as a byte[].
        byte[] bytes = IOUtils.toByteArray(inputStream);
        //Adds a picture to the workbook
        int pictureIdx = hWBook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        //close the input stream
        inputStream.close();

        //Returns an object that handles instantiating concrete classes
        CreationHelper helper = hWBook.getCreationHelper();

        //Creates the top-level drawing patriarch.
        Drawing drawing = hSheet.createDrawingPatriarch();

        //Create an anchor that is attached to the worksheet
        ClientAnchor anchor = helper.createClientAnchor();
        //set top-left corner for the image
        anchor.setCol1(col);
        anchor.setRow1(5+row);

        //Creates a picture
        Picture pict = drawing.createPicture(anchor, pictureIdx);
        //Reset the image to the original size
        pict.resize();
    }
    
    private String format(String value){
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format( Utils.NVL(value).equals("")?new BigDecimal(0).doubleValue():new BigDecimal(Utils.NVL(value)).doubleValue());
    }
     
    
    private boolean validategenDataExcel(){
       boolean isok = true;
       
       if((this.getMasterdata().getDailydatest() == null && this.getMasterdata().getDailydatefn() == null)){
                
            String msg = MessageUtil.getMessage("EP006");
            addErrorMessage(null,msg,msg);
            return false;

        }
       
       if((this.getMasterdata().getDailydatest() != null && this.getMasterdata().getDailydatefn() == null) ||
                (this.getMasterdata().getDailydatest() == null && this.getMasterdata().getDailydatefn() != null)){
                
            String msg = MessageUtil.getMessage("EP007");
            addErrorMessage(null,msg,msg);
            return false;

        }
       
        if(this.getMasterdata().getDailydatest() != null && this.getMasterdata().getDailydatefn() != null){

                String s = Utils.NVL(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatest()));
                String e = Utils.NVL(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()));

                if(Integer.parseInt(s) > Integer.parseInt(e)){
                    String msg = MessageUtil.getMessage("EP007");
                    addErrorMessage(null,msg,msg);
                    return false;
                }

         }
        
        
        return isok;
    }
}
