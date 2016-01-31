/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.pay.business.BusinessFactory;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.MessageUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Daily;
import com.scc.pay.util.CenterUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class ATP020100 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atp020100e.xhtml";
    private static final String PAGE_Q  = "atp020100q.xhtml";
    
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
        private Date dailydate;
        private Date dailydatefn;
        private String nowdate;
        
        private boolean monetaryusd = false;

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

        public boolean isMonetaryusd() {
            return monetaryusd;
        }

        public void setMonetaryusd(boolean monetaryusd) {
            this.monetaryusd = monetaryusd;
        }

        public String getNowdate() {
            nowdate = Utils.convertDateStringToScreen(Utils.formatDateToStringToDBThai(Utils.getcurDateTime()),"/");
            return nowdate;
        }

        public void setNowdate(String nowdate) {
            this.nowdate = nowdate;
        }

        

    }
    
    
    public ATP020100() {
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
            
            IBusinessBase ib = BusinessFactory.getBusiness("ATP020100A");
            
            
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


        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020100U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020100D");
            
            
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
        
        initialValue();
        
        //search();
    }
    
    private void initialValue(){
        if(this.getMasterdata().getDailydate() == null){
            this.getMasterdata().setDailydate(Utils.getcurDateTime());
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
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020100S");
        
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
     public void calexchangerate(){
        logger.debug(">>calexchangerate "+this.getMasterdata().getDaily().getPayby());
        logger.debug(">>calexchangerate "+this.getMasterdata().getDaily().getCurrency());
        logger.debug(">>calexchangerate "+this.getMasterdata().getDaily().getExchangerate() );
        logger.debug(">>calexchangerate "+this.getMasterdata().getDaily().getAmount());
        
            if(this.getMasterdata().isMonetaryusd()){ //US
                this.getMasterdata().getDaily().setReceivedamount(null);
            }else{
                if(this.getMasterdata().getDaily().getExchangerate() != null && this.getMasterdata().getDaily().getAmount() != null){
                    Double x = this.getMasterdata().getDaily().getExchangerate() * this.getMasterdata().getDaily().getAmount();

                    this.getMasterdata().getDaily().setReceivedamount(x);
                }
            }
     }
    
    public void calamount(){
        logger.debug(">>calamount "+this.getMasterdata().getDaily().getPayby());
        logger.debug(">>calamount "+this.getMasterdata().getDaily().getCurrency());
        logger.debug(">>calamount "+this.getMasterdata().getDaily().getExchangerate() );
        logger.debug(">>calamount "+this.getMasterdata().getDaily().getAmount());
        
            //if(Utils.NVLNumber(this.getMasterdata().getDaily().getPayby()) == 2){ //US
            if(this.getMasterdata().isMonetaryusd()){ //US
                this.getMasterdata().getDaily().setReceivedamount(null);
            }else{
                if(this.getMasterdata().getDaily().getExchangerate() != null && this.getMasterdata().getDaily().getAmount() != null){
                    Double x = this.getMasterdata().getDaily().getExchangerate() * this.getMasterdata().getDaily().getAmount();

                    this.getMasterdata().getDaily().setReceivedamount(x);
                }else{

                    this.getMasterdata().getDaily().setReceivedamount(null);
                    this.getMasterdata().getDaily().setAmount(null);

                    String msg = MessageUtil.getMessage("EP001");
                    addInfoMessage(null,msg, msg);
                }
            }
        
//        if(this.getMasterdata().getDaily().getAmount() == null){
//            String msg = MessageUtil.getMessage("EP002");
//            addInfoMessage(null,msg, msg);
//        }else{
//        
//            if(Utils.NVL(this.getMasterdata().getDaily().getCurrency()).equals("1")){ // US
//                this.getMasterdata().getDaily().setReceivedamount(this.getMasterdata().getDaily().getAmount());
//                this.getMasterdata().getDaily().setExchangerate(new Double("1"));
//            }else{
//                if(this.getMasterdata().getDaily().getExchangerate() != null && this.getMasterdata().getDaily().getAmount() != null){
//                    Double x = this.getMasterdata().getDaily().getExchangerate() * this.getMasterdata().getDaily().getAmount();
//
//                    this.getMasterdata().getDaily().setReceivedamount(x);
//                }else{
//                    
//                    this.getMasterdata().getDaily().setAmount(null);
//                    
//                    String msg = MessageUtil.getMessage("EP001");
//                    addInfoMessage(null,msg, msg);
//                }
//            }
//        }
    }
    
    public void clearamount(){
        this.getMasterdata().getDaily().setReceivedamount(null);
        this.getMasterdata().getDaily().setExchangerate(null);
        this.getMasterdata().getDaily().setReceivedamount(null);
        this.getMasterdata().getDaily().setAmount(null);
        
        searachTbbank();
    }
    
     private void searachTbbank(){
        
        logger.debug(">>searachTbbank :"+this.getMasterdata().getDaily().getPayby());
        
        HashMap hm = new HashMap<String, String>();
        hm.put("bankid", Utils.NVL(this.getMasterdata().getDaily().getPayby()));
        List l = CenterUtils.selectData(hm, "lookup_tb_bank");
        
        if(!l.isEmpty()){
            hm = (HashMap)l.get(0);
            if(Utils.NVL(hm.get("monetaryusd")).equals("true")){
                this.getMasterdata().setMonetaryusd(true);
            }else{
            
                this.getMasterdata().setMonetaryusd(false);
            }
        }else{
        
            this.getMasterdata().setMonetaryusd(false);
        }
    }
     
     public void resetpayby(){
         //this.getMasterdata().getDaily().setPayby(null);
         this.getMasterdata().getDaily().setChequeno(null);
         //this.getMasterdata().setMonetaryusd(false);
     }
      
}
