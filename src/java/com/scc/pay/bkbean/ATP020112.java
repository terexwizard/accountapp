/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.f1.backingbean.DetailRow;
import com.scc.f1.backingbean.DetailTable;
import com.scc.pay.business.BusinessFactory;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.MessageUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Daily;
import com.scc.pay.db.TbBank;
import com.scc.pay.db.TbCurrency;
import com.scc.pay.db.TbDescriptioncode;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author terex
 * @version 1.00.00
 * 18/06/2555  9:45:40
 */

@ManagedBean
@SessionScoped
public class ATP020112 extends BKBPage {

    private static final String PAGE_E  = "atp020112e.xhtml";
    private static final String PAGE_Q  = "atp020112q.xhtml";
    
    private MainData masterdata ;
    private MainData searchparam;

    private DetailTable<DetailDaily>  detaildaily = null;
    private Map<String, String> searchselectedrow ;
    
    private String statuscheckboxAllDetail = "";
    private boolean checkboxAllDetail;
    
    
    
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

    public DetailTable<DetailDaily> getDetaildaily() {
        if(this.detaildaily == null){
            initdetaildaily();
        }
        return detaildaily;
    }

    public void setDetaildaily(DetailTable<DetailDaily> detaildaily) {
        this.detaildaily = detaildaily;
    }

    public String getStatuscheckboxAllDetail() {
        return statuscheckboxAllDetail;
    }

    public void setStatuscheckboxAllDetail(String statuscheckboxAllDetail) {
        this.statuscheckboxAllDetail = statuscheckboxAllDetail;
    }

    public boolean isCheckboxAllDetail() {
        return checkboxAllDetail;
    }

    public void setCheckboxAllDetail(boolean checkboxAllDetail) {
        this.checkboxAllDetail = checkboxAllDetail;
    }
    
    
    
    
    
    public class MainData extends BBBase{
        private Daily daily = null;
        private Date dailydate;
        private Date dailydatefn;
        
        private String cheque;
        
        private BigDecimal revamus;
        private BigDecimal amus;
        private BigDecimal revamth;
        private BigDecimal amth;

        public Daily getDaily() {
            if(daily == null){
                daily = new Daily();
            }
            return daily;
        }

        public void setDaily(Daily daily) {
            this.daily = daily;
        }

        public Date getDailydate() {
            return dailydate;
        }

        public void setDailydate(Date dailydate) {
            this.dailydate = dailydate;
        }        
        public Date getDailydatefn() {
            return dailydatefn;
        }

        public void setDailydatefn(Date dailydatefn) {
            this.dailydatefn = dailydatefn;
        }

        public BigDecimal getRevamus() {
            return revamus;
        }

        public void setRevamus(BigDecimal revamus) {
            this.revamus = revamus;
        }

        public BigDecimal getAmus() {
            return amus;
        }

        public void setAmus(BigDecimal amus) {
            this.amus = amus;
        }

        public BigDecimal getRevamth() {
            return revamth;
        }

        public void setRevamth(BigDecimal revamth) {
            this.revamth = revamth;
        }

        public BigDecimal getAmth() {
            return amth;
        }

        public void setAmth(BigDecimal amth) {
            this.amth = amth;
        }

        public String getCheque() {
            return cheque;
        }

        public void setCheque(String cheque) {
            this.cheque = cheque;
        }

        

        
        
    }
    
    public class DetailDaily extends BBBase{
        private Daily daily = null;
        private TbBank tbbank;
        private TbDescriptioncode tbdescriptioncode;
        private TbCurrency tbcurrency;
        
        private BigDecimal money;
        private Date tmpchequedate;
        
        private boolean detailCheckbox = false;
        private String isOk  = "";

        public Daily getDaily() {
            if(daily == null){
                daily = new Daily();
            }
            return daily;
        }

        public void setDaily(Daily daily) {
            this.daily = daily;
        }

        public TbBank getTbbank() {
            if(tbbank == null){
                tbbank = new TbBank();
            }
            return tbbank;
        }

        public void setTbbank(TbBank tbbank) {
            this.tbbank = tbbank;
        }

        public TbDescriptioncode getTbdescriptioncode() {
            if(tbdescriptioncode == null){
                tbdescriptioncode = new TbDescriptioncode();
            }
            return tbdescriptioncode;
        }

        public void setTbdescriptioncode(TbDescriptioncode tbdescriptioncode) {
            this.tbdescriptioncode = tbdescriptioncode;
        }

        public TbCurrency getTbcurrency() {
            if(tbcurrency == null){
                tbcurrency = new TbCurrency();
            }
            return tbcurrency;
        }

        public void setTbcurrency(TbCurrency tbcurrency) {
            this.tbcurrency = tbcurrency;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public Date getTmpchequedate() {
            return tmpchequedate;
        }

        public void setTmpchequedate(Date tmpchequedate) {
            this.tmpchequedate = tmpchequedate;
        }
        
        
        
        
        public boolean isDetailCheckbox() {
            return detailCheckbox;
        }

        public void setDetailCheckbox(boolean detailCheckbox) {
            this.detailCheckbox = detailCheckbox;
        }

        public String getIsOk() {
            return isOk;
        }

        public void setIsOk(String isOk) {
            this.isOk = isOk;
        }
        
     }
    
     
     
    private void initdetaildaily(){
        
        
           detaildaily     = new DetailTable<DetailDaily>(new DetailDaily());
        
           detaildaily.setParentclass(this);
           
           detaildaily.setFormid("form1");
           detaildaily.setParentid("form1");
           detaildaily.setDetailname("detaildaily");
           
//         detailReqMast.newRow(null
           //detaildaily.setV("validateDetail");
           detaildaily.setBeforebuttonset("callBeforedetaildaily");
           detaildaily.setAfterbuttonset("callAfterdetaildaily");
           
            addDetailtable(detaildaily);

    }
    
     
    public void callBeforedetaildaily(String mode){
        
        logger.debug(">>callBeforedetaildaily "+mode);
         
        if(mode.equals(DetailTable.ROW_ADD)){ 
           
        }else if(mode.equals(DetailTable.ROW_EDIT)){
           
        }else if(mode.equals(DetailTable.ROW_CANCEL)){  
            
        }  
    }
    
    public void callAfterdetaildaily(String mode){
        
        logger.debug(">>callAfterdetaildaily "+mode);
      
        if(mode.equals(DetailTable.ROW_NEW)){
            
        }else if(mode.equals(DetailTable.ROW_ADD) || mode.equals(DetailTable.ROW_EDIT)
                || mode.equals(DetailTable.ROW_DELDETAIL)){

            
       }   
        
    }
    
    
    
    public ATP020112() {
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
            masterdata  = null; 
            detaildaily = null;
            statuscheckboxAllDetail = "";
            checkboxAllDetail = false;
            
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
            
            IBusinessBase ib = BusinessFactory.getBusiness("ATP020112A");
            
            
            ib.process(this);
            
            genMessage(ib);
            
            if(ib.isOk()){
                clearAllData();
                
                initialValue();
            }
            
            
        }else{
            
//            FacesContext.getCurrentInstance().renderResponse();
            
        }
        
        
    }
    
    
    private boolean validateAdd(){
        boolean isok = true;

        boolean flag = true;
        for(DetailRow<DetailDaily> row : this.getDetaildaily().getListdetailrow()){
            
            if(row.getData().isDetailCheckbox()){
                flag = false;
                break;
            }
            
        }
        
        if(flag){
            String msg = MessageUtil.getMessage("EP004");
            addInfoMessage(null, msg,msg);
            return false;
        }

        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020112U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020112D");
            
            
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
        
        BKBUQuery.getIns().clearListData();
        
        initialValue();
        
        //search();
    }
    
    private void initialValue(){
//        if(this.getMasterdata().getDailydate() == null){
//            this.getMasterdata().setDailydate(Utils.getcurDateTime());
//        }
        
        if(Utils.NVL(this.getMasterdata().getCheque()).equals("")){
            this.getMasterdata().setCheque("N");
        }
    }
    
    private void search(){
            
//            logger.debug("q para "+ this.getSearchparam().getTbgroup().getTbGroupPK().getType()+", "+ 
//                        this.getSearchparam().getTbgroup().getData());
//       
            HashMap<String, String> hm = new HashMap<String, String>();
            
            hm.put("dailydatest", Utils.formatDateToStringToDBEn(this.getSearchparam().getDailydate()));
            hm.put("dailydatefn", Utils.formatDateToStringToDBEn(this.getSearchparam().getDailydatefn()));
            hm.put("jobref", this.getSearchparam().getDaily().getJobref());
   
            BKBUQuery.getIns().setQueryparam(hm);
            BKBUQuery.getIns().search();
          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020112S");
        
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
    
    //======================
    
    public void searchData(){
        
        
        if(validatesearchData()){
            
            
            IBusinessBase ib = BusinessFactory.getBusiness("ATP020112S");        
            ib.process(this);
            
            
            if(ib.isOk()){
                
                logger.debug(">>searchData :"+this.getDetaildaily().getListdetailrow().isEmpty());
                
                if(this.getDetaildaily().getListdetailrow().isEmpty()){
                    String msg = MessageUtil.getMessage("EP005");
                    addInfoMessage(null, msg,msg);
                    
                    clearAllData();
                    initialValue();
                    //redirectPage(PAGE_E);
                    
                }
            }
        }
        
    }
    
     private boolean validatesearchData(){
        boolean isok = true;

        
        if((this.getMasterdata().getDailydate() != null && this.getMasterdata().getDailydatefn() == null) ||
                (this.getMasterdata().getDailydate() == null && this.getMasterdata().getDailydatefn() != null)){
                
            String msg = MessageUtil.getMessage("EP007");
            addErrorMessage(null,msg,msg);
            return false;

        }
        
         if(this.getMasterdata().getDailydate() != null && this.getMasterdata().getDailydatefn() != null){

                String s = Utils.NVL(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydate()));
                String e = Utils.NVL(Utils.formatDateToStringToDBEn(this.getMasterdata().getDailydatefn()));

                if(Integer.parseInt(s) > Integer.parseInt(e)){
                    String msg = MessageUtil.getMessage("EP007");
                    addErrorMessage(null,msg,msg);
                    return false;
                }

         }

        return isok;
    }
    
    public void checkAllDetail(){
        
        logger.debug(">>checkAllDetail "+this.getStatuscheckboxAllDetail());
        
        if(Utils.NVL(this.getStatuscheckboxAllDetail()).equals("") ||
                Utils.NVL(this.getStatuscheckboxAllDetail()).equals("N")){
            this.setStatuscheckboxAllDetail("Y");
        }else{
            this.setStatuscheckboxAllDetail("N");
        }
        
        for(DetailRow<DetailDaily> item : this.getDetaildaily().getListdetailrow()){
                if(Utils.NVL(this.getStatuscheckboxAllDetail()).equals("N")){
                    item.getData().setDetailCheckbox(false);
                    this.setCheckboxAllDetail(false);
                }else{
                    item.getData().setDetailCheckbox(true);
                    this.setCheckboxAllDetail(true);
                }
        }
        
        calSumAmount();
    }
    
    public void calSumAmount(){
        
        
        BigDecimal revamus = new BigDecimal(0);
        BigDecimal amus = new BigDecimal(0);
        BigDecimal revamth = new BigDecimal(0);
        BigDecimal amth = new BigDecimal(0);
        
        boolean flagchkall = true;
        for(DetailRow<DetailDaily> item : this.getDetaildaily().getListdetailrow()){
            if(item.getData().isDetailCheckbox()){

//                if(Utils.NVL(item.getData().getDaily().getCurrency()).equals("1")){ //us
//                    revamus = revamus.add(new BigDecimal(item.getData().getDaily().getReceivedamount()));
//                    amus = amus.add(new BigDecimal(item.getData().getDaily().getAmount()));
//
//                }else{
//                    revamth = revamth.add(new BigDecimal(item.getData().getDaily().getReceivedamount()));
//                    amth = amth.add(new BigDecimal(item.getData().getDaily().getAmount()));
//                }
                
                if(item.getData().getDaily().getPayby().intValue() == 2){
                    amus = amus.add(new BigDecimal(item.getData().getDaily().getAmount2()));
                }else{
                    revamth = revamth.add(new BigDecimal(item.getData().getDaily().getPaidamount()));
                }
            }else{
                flagchkall = false;
            }
        }
        
        this.setCheckboxAllDetail(flagchkall);
        
        
        logger.debug(">>calSumAmount "+revamus);
        logger.debug(">>calSumAmount "+amus);
        logger.debug(">>calSumAmount "+revamth);
        logger.debug(">>calSumAmount "+amth);
        
        
        this.getMasterdata().setRevamus(revamus);
        this.getMasterdata().setRevamth(revamth);
        this.getMasterdata().setAmus(amus);
        this.getMasterdata().setAmth(amth);
    }
    
     public void onEditDetaildaily(RowEditEvent event) {  
        DetailRow<DetailDaily> r = ((DetailRow<DetailDaily>) event.getObject());
        if(Utils.NVL(r.getRowstatus()).equals("")){
            r.setRowstatus(DetailRow.ROW_STATUS_EDIT);
        }
        //r.getData().setPosiType_disp(getLabelTb13positiontype(r.getData().getRd09personcostsd().getPosiType()));
        
        //============      
//        if(Utils.NVL(r.getData().getRd09personcostsd().getPersName()).equals("") || 
//                Utils.NVL(r.getData().getRd09personcostsd().getPersTaxId()).equals("")){
//            r.getData().setIsOk("Y");
//            addErrorMessage(null, "กรุณากรอกให้ครบถ้วน", "กรุณากรอกให้ครบถ้วน");
//        }else{
//            r.getData().setIsOk("");
//        }
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020112U");        
        ib.process(this);


        if(ib.isOk()){
           
            clearAllData();
            initialValue();
            
            searchData();
        }
        
     }
     
      public void onCancelDetaildaily(RowEditEvent event) {  
          
      }
  
}
