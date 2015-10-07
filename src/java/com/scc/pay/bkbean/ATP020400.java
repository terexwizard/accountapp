/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.pay.business.BusinessFactory;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.MessageUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Bringforward;
import com.scc.pay.db.BringforwardPK;
import com.scc.pay.db.TbBank;
import com.scc.pay.util.CenterUtils;
import java.util.ArrayList;
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
public class ATP020400 extends BKBPage {

    
    private MainData masterdata ;
    private MainData searchparam;
    
    private static final String PAGE_E  = "atp020400e.xhtml";
    private static final String PAGE_Q  = "atp020400q.xhtml";
    
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
        
        private Bringforward  bringforward;
        private Date bfdate;
        
        private List<BringforwardData> listbringforwarddata  = null;

        public Bringforward getBringforward() {
            if(bringforward == null){
                bringforward = new Bringforward(new BringforwardPK());
            }
            return bringforward;
        }

        public void setBringforward(Bringforward bringforward) {
            this.bringforward = bringforward;
        }        

        public List<BringforwardData> getListbringforwarddata() {
            if(listbringforwarddata == null){
                initiallistbringforwarddata();
            }
            return listbringforwarddata;
        }

        public void setListbringforwarddata(List<BringforwardData> listbringforwarddata) {
            this.listbringforwarddata = listbringforwarddata;
        }
        
        public Date getBfdate() {
            return bfdate;
        }

        public void setBfdate(Date bfdate) {
            this.bfdate = bfdate;
        }
        
    }
    
  public class BringforwardData extends BBBase{
        private Bringforward  bringforward;
        private TbBank tbbank;

         public Bringforward getBringforward() {
            if(bringforward == null){
                bringforward = new Bringforward(new BringforwardPK());
            }
            return bringforward;
        }

        public void setBringforward(Bringforward bringforward) {
            this.bringforward = bringforward;
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
  }
  
  
  private void initiallistbringforwarddata(){
      
        HashMap<String, String> hm = new HashMap<String, String>();
        List l = CenterUtils.selectData(null, "lookup_tb_bank");
      
        if(!l.isEmpty()){
            int size = l.size(); 
            ArrayList<BringforwardData> al = new ArrayList<BringforwardData>();
            for(int i=0;i<size;i++){
                 hm = (HashMap)l.get(i);
                 BringforwardData bringforwarddata = new BringforwardData();

                 bringforwarddata.getTbbank().setBankid(Integer.parseInt(Utils.NVL(hm.get("bankid"))));
                 bringforwarddata.getTbbank().setBankname(Utils.NVL(hm.get("bankname")));
                 
                 //bringforwarddata.getBringforward().getBringforwardPK().setBfdate(Utils.formatDateToStringToDBEn(Utils.getcurDateTime()));
                 bringforwarddata.getBringforward().getBringforwardPK().setBankid(bringforwarddata.getTbbank().getBankid());
                 bringforwarddata.getBringforward().setReceived(Double.parseDouble("0.00"));
                 bringforwarddata.getBringforward().setPaid(Double.parseDouble("0.00"));
                 bringforwarddata.getBringforward().setActualmoney(Double.parseDouble("0.00"));

                 al.add(bringforwarddata);
            }
            
            this.getMasterdata().setListbringforwarddata(al);
        }
  }
          
    
    
    public ATP020400() {
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
            
            IBusinessBase ib = BusinessFactory.getBusiness("ATP020400A");
            
            
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

        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("bfdate", Utils.formatDateToStringToDBEn(this.getSearchparam().getBfdate()));
        List l =  CenterUtils.selectData(hm, "ATP020400Q");
        
        if(!l.isEmpty()){
            String msg = MessageUtil.getMessage("EP008");
            addErrorMessage(null,msg,msg);
            return false;
        }

        return isok;
    }
    
    private void update(){
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020400U");
            
            
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
        
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020400D");
            
            
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
        
        if(this.getMasterdata().getBfdate() == null){
            this.getMasterdata().setBfdate(Utils.getcurDateTime());
        }
    }
    
    private void search(){
            
//            logger.debug("q para "+ this.getSearchparam().getTbgroup().getTbGroupPK().getType()+", "+ 
//                        this.getSearchparam().getTbgroup().getData());
//       
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("bfdate", Utils.formatDateToStringToDBEn(this.getSearchparam().getBfdate()));
   
            BKBUQuery.getIns().setQueryparam(hm);
            BKBUQuery.getIns().search();
          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("ATP020400S");
        
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
    
  
      
}
