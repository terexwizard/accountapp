/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.f1.backingbean.DetailTable;
import com.scc.pay.business.BusinessFactory;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Invoicecompany;
import com.scc.pay.db.Receivable;
import java.math.BigDecimal;
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
public class ATP020301 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    private DetailTable<DetailReceivable> detailreceivable;
    
    private static final String PAGE_E  = "atp020301e.xhtml";
    private static final String PAGE_Q  = "atp020301q.xhtml";
    
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

    public DetailTable<DetailReceivable> getDetailreceivable() {
        if(detailreceivable == null){
            detailreceivable = initDetailReceivable();
        }
        return detailreceivable;
    }

    public void setDetailreceivable(DetailTable<DetailReceivable> detailreceivable) {
        this.detailreceivable = detailreceivable;
    }
    
    

    
    private DetailTable<DetailReceivable> initDetailReceivable(){
        
           DetailTable<DetailReceivable> detailtable     = new DetailTable<DetailReceivable>(new DetailReceivable());
           
           detailtable.setParentclass(this);
        
           detailtable.setFormid("form1");
           detailtable.setParentid("form1");
           detailtable.setDetailname("detailreceivable");
           
           detailtable.setBeforebuttonset("beforeDetailReceivable");
           detailtable.setAfterbuttonset("afterDetailReceivable");
           
           //DetailRow<DetailRd09PersonCostsD> row  = detailtable.addDetail(null);
           
           addDetailtable(detailtable);
           
           return detailtable;
    }
    
     
    public void beforeDetailReceivable(String mode) throws Exception{
        logger.debug(">>beforeDetailReceivable :"+mode);
        
        if(mode.equals(DetailTable.ROW_ADD) || mode.equals(DetailTable.ROW_EDIT)){
            
            //logger.debug(">>beforeDetailInvoice add :"+Utils.formatDateToStringToDBEn(this.getDetailinvoice().getRow().getData().getInvdate()));
           this.getDetailreceivable().getRow().getData().getReceivable().setInvdate(Utils.formatDateToStringToDBEn(this.getDetailreceivable().getRow().getData().getInvdate()));
           this.getDetailreceivable().getRow().getData().getReceivable().setCompany(this.getMasterdata().getInvoicecompany().getCompanyname());
           
           this.getDetailreceivable().getRow().getData().getReceivable().setSubmitdate(Utils.formatDateToStringToDBEn(this.getDetailreceivable().getRow().getData().getSubmitdate()));
           
           caltotalAll();
        }
    }
    
    public void afterDetailReceivable(String mode) throws Exception{
        
        logger.debug(">>afterDetailReceivable :"+mode);
        
//        if(mode.equals(DetailTable.ROW_ADD) || mode.equals(DetailTable.ROW_EDIT)){
//            
//            logger.debug(">>afterDetailInvoice add :"+Utils.formatDateToStringToDBEn(this.getDetailinvoice().getRow().getData().getInvdate()));
//            
//           this.getDetailinvoice().getRow().getData().getInvoice().setInvdate(Utils.formatDateToStringToDBEn(this.getDetailinvoice().getRow().getData().getInvdate()));
//        }
        
        if(mode.equals(DetailTable.ROW_NEW)){
            
            this.getDetailreceivable().getRow().getData().setSubmitdate(Utils.getcurDateTime());
        }
    }
    
    
    public class MainData extends BBBase{
        
        private Invoicecompany  invoicecompany;
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

        public Date getInvdate() {
            return invdate;
        }

        public void setInvdate(Date invdate) {
            this.invdate = invdate;
        }
        
        


        

        
        
    }
    
  public class DetailReceivable extends BBBase{
        private Receivable  receivable;
        private Date submitdate;
        private Date invdate;
        private Date duedate;
        private Date receivedDate;
        private String currency_disp;

        public Receivable getReceivable() {
            if(receivable == null){
                receivable = new Receivable();
            }
            return receivable;
        }

        public void setReceivable(Receivable receivable) {
            this.receivable = receivable;
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

        public Date getSubmitdate() {
            return submitdate;
        }

        public void setSubmitdate(Date submitdate) {
            this.submitdate = submitdate;
        }

        public String getCurrency_disp() {
            return currency_disp;
        }

        public void setCurrency_disp(String currency_disp) {
            this.currency_disp = currency_disp;
        }
        
  }
    
    
    public ATP020301() {
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
            detailreceivable = null;
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
            
            IBusinessBase ib = BusinessFactory.getBusiness("ATP020301U");
            
            
            ib.process(this);
            
            genMessage(ib);
            
            if(ib.isOk()){
                //clearAllData();
                searchinvcomid();
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020301U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020301D");
            
            
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
            hm.put("date", Utils.formatDateToStringToDBEn(this.getSearchparam().getInvdate()));
   
            BKBUQuery.getIns().setQueryparam(hm);
            BKBUQuery.getIns().search();
          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020301S");
        
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
    public void calVat(){
        
        if(!Utils.NVL(this.getDetailreceivable().getRow().getData().getReceivable().getService()).equals("") &&
            !Utils.NVL(this.getDetailreceivable().getRow().getData().getReceivable().getVatdata()).equals("")    ){
            BigDecimal service = new BigDecimal(this.getDetailreceivable().getRow().getData().getReceivable().getService());
            BigDecimal vatdata = new BigDecimal(this.getDetailreceivable().getRow().getData().getReceivable().getVatdata());
            vatdata = vatdata.divide(new BigDecimal("100"));
            
            
            
            this.getDetailreceivable().getRow().getData().getReceivable().setVat(service.multiply(vatdata).doubleValue());
            
            
            BigDecimal total = new BigDecimal(0);
            if(!Utils.NVL(this.getDetailreceivable().getRow().getData().getReceivable().getReimbursement()).equals("")){
                total = total.add(new BigDecimal(this.getDetailreceivable().getRow().getData().getReceivable().getReimbursement()));
            }
            total = total.add(service);
            total = total.add(new BigDecimal(this.getDetailreceivable().getRow().getData().getReceivable().getVat()));
            
            this.getDetailreceivable().getRow().getData().getReceivable().setTotal(total.doubleValue());
            
            calwhtax();
        }
    }
    
    public void calwhtax(){
        
        logger.debug(">>calwhtax ");
        
         if(!Utils.NVL(this.getDetailreceivable().getRow().getData().getReceivable().getTotal()).equals("") &&
                 !Utils.NVL(this.getDetailreceivable().getRow().getData().getReceivable().getWhtaxdata()).equals("")){
             
             logger.debug(">>calwhtax if "+this.getDetailreceivable().getRow().getData().getReceivable().getTotal());
             
             BigDecimal total = new BigDecimal(this.getDetailreceivable().getRow().getData().getReceivable().getTotal());
             BigDecimal whtaxdata = new BigDecimal(this.getDetailreceivable().getRow().getData().getReceivable().getWhtaxdata());
             whtaxdata = whtaxdata.divide(new BigDecimal("100"));
             
            this.getDetailreceivable().getRow().getData().getReceivable().setWhtax(total.multiply(whtaxdata).doubleValue());
            
            caltotalAll();
         }
    }
     
    private void caltotalAll(){
        double whtax = this.getDetailreceivable().getRow().getData().getReceivable().getWhtax() == null?0:this.getDetailreceivable().getRow().getData().getReceivable().getWhtax();
        double advance = this.getDetailreceivable().getRow().getData().getReceivable().getAdvance() == null?0:this.getDetailreceivable().getRow().getData().getReceivable().getAdvance();
           
        this.getDetailreceivable().getRow().getData().getReceivable().setTotalall(this.getDetailreceivable().getRow().getData().getReceivable().getTotal() - (whtax + advance));
        
    }
    
        
    public void searchinvcomid(){
        
        if(!Utils.NVL(this.getMasterdata().getInvoicecompany().getInvcomid()).equals("")){
            this.getSearchselectedrow().put("invcomid", this.getMasterdata().getInvoicecompany().getInvcomid());

            IBusinessBase ib = BusinessFactory.getBusiness("ATP020301S");

            ib.process(this);

            if(ib.isOk()){

            }
        }
    }
}
