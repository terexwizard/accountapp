/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.f1.backingbean.DetailTable;
import com.scc.pay.business.BusinessFactory;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Invoice;
import com.scc.pay.db.Invoicecompany;
import com.scc.pay.util.CenterUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

/**
 *
 * @author terex
 * @version 1.00.00
 * 18/06/2555  9:45:40
 */

@ManagedBean
@SessionScoped
public class ATP020200 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    private DetailTable<DetailInvoice> detailinvoice;
    
    private static final String PAGE_E  = "atp020200e.xhtml";
    private static final String PAGE_Q  = "atp020200q.xhtml";
    
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

    public DetailTable<DetailInvoice> getDetailinvoice() {
         if(detailinvoice == null){
            detailinvoice = initDetailInvoice();
        }
        return detailinvoice;
    }

    public void setDetailinvoice(DetailTable<DetailInvoice> detailinvoice) {
        this.detailinvoice = detailinvoice;
    }

    
    private DetailTable<DetailInvoice> initDetailInvoice(){
        
           DetailTable<DetailInvoice> detailtable     = new DetailTable<DetailInvoice>(new DetailInvoice());
           
           detailtable.setParentclass(this);
        
           detailtable.setFormid("form1");
           detailtable.setParentid("form1");
           detailtable.setDetailname("detailinvoice");
           
           detailtable.setBeforebuttonset("beforeDetailInvoice");
           detailtable.setAfterbuttonset("afterDetailInvoice");
           
           //DetailRow<DetailRd09PersonCostsD> row  = detailtable.addDetail(null);
           
           addDetailtable(detailtable);
           
           return detailtable;
    }
    
     
    public void beforeDetailInvoice(String mode) throws Exception{
        logger.debug(">>beforeDetailInvoice :"+mode);
        
        if(mode.equals(DetailTable.ROW_ADD) || mode.equals(DetailTable.ROW_EDIT)){
            
            logger.debug(">>beforeDetailInvoice add :"+Utils.formatDateToStringToDBEn(this.getDetailinvoice().getRow().getData().getInvdate()));
            
           this.getDetailinvoice().getRow().getData().getInvoice().setInvdate(Utils.formatDateToStringToDBEn(this.getDetailinvoice().getRow().getData().getInvdate()));
        }
    }
    
    public void afterDetailInvoice(String mode) throws Exception{
        
        logger.debug(">>afterDetailInvoice :"+mode);
        
//        if(mode.equals(DetailTable.ROW_ADD) || mode.equals(DetailTable.ROW_EDIT)){
//            
//            logger.debug(">>afterDetailInvoice add :"+Utils.formatDateToStringToDBEn(this.getDetailinvoice().getRow().getData().getInvdate()));
//            
//           this.getDetailinvoice().getRow().getData().getInvoice().setInvdate(Utils.formatDateToStringToDBEn(this.getDetailinvoice().getRow().getData().getInvdate()));
//        }
    }
    
    
    public class MainData extends BBBase{
        
        private Invoicecompany  invoicecompany;
        private Date jobdate;
        private Date invdate;

        public Invoicecompany getInvoicecompany() {
            if(invoicecompany == null){
                invoicecompany = new Invoicecompany();
            }
            return invoicecompany;
        }

        public void setInvoicecompany(Invoicecompany invoicecompany) {
            this.invoicecompany = invoicecompany;
        }

        public Date getJobdate() {
            return jobdate;
        }

        public void setJobdate(Date jobdate) {
            this.jobdate = jobdate;
        }

        public Date getInvdate() {
            return invdate;
        }

        public void setInvdate(Date invdate) {
            this.invdate = invdate;
        }
        
        


        

        
        
    }
    
   public class DetailInvoice extends BBBase{
        private Invoice  invoice;
        private Date invdate;
        private Date jobdate;
        private Date duedate;
        private Date receivedDate;

        public Invoice getInvoice() {
            if(invoice == null){
                invoice = new Invoice();
            }
            return invoice;
        }

        public void setInvoice(Invoice invoice) {
            this.invoice = invoice;
        }

        public Date getJobdate() {
            return jobdate;
        }

        public void setJobdate(Date jobdate) {
            this.jobdate = jobdate;
        }

        public Date getInvdate() {
            return invdate;
        }

        public void setInvdate(Date invdate) {
            this.invdate = invdate;
        }

        public Date getDuedate() {
            return duedate;
        }

        public void setDuedate(Date duedate) {
            this.duedate = duedate;
        }

        public Date getReceivedDate() {
            return receivedDate;
        }

        public void setReceivedDate(Date receivedDate) {
            this.receivedDate = receivedDate;
        }
        
  }
    
    
    public ATP020200() {
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
            detailinvoice = null;
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
            
            IBusinessBase ib = BusinessFactory.getBusiness("ATP020200A");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020200U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020200D");
            
            
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
        

        //search();
    }
    
    private void search(){
            
//            logger.debug("q para "+ this.getSearchparam().getTbgroup().getTbGroupPK().getType()+", "+ 
//                        this.getSearchparam().getTbgroup().getData());
//       
            HashMap<String, String> hm = new HashMap<String, String>();
            
            hm.put("invcomid", this.getSearchparam().getInvoicecompany().getInvcomid());
            hm.put("invdate", Utils.formatDateToStringToDBEn(this.getSearchparam().getInvdate()));
   
            BKBUQuery.getIns().setQueryparam(hm);
            BKBUQuery.getIns().search();
          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020200S");
        
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
    
    public void checkdatalookup_daily(){
        if(!Utils.NVL(this.getDetailinvoice().getRow().getData().getInvoice().getJobdate()).equals("")){
            this.getDetailinvoice().getRow().getData().setJobdate(CenterUtils.formatStringToDateToScreen(this.getDetailinvoice().getRow().getData().getInvoice().getJobdate()+543));
        }
    }
    
      
}
