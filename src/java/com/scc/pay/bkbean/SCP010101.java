/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.app.db.ScUser;
import com.scc.app.db.ScUserInfo;
import com.scc.app.db.ScUserPermitRole;
import com.scc.app.db.ScUserPermitRolePK;
import com.scc.f1.backingbean.DetailRow;
import com.scc.f1.backingbean.DetailTable;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.dbutil.DBUtils;
import com.scc.f1.dbutil.QueryXML;
import com.scc.f1.util.Utils;
import com.scc.pay.business.BusinessFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 18/06/2555  9:45:40
 */

@ManagedBean
@SessionScoped
public class SCP010101 extends BKBPage {
 
    private MainData masterdata; 
    private MainData searchparam;
    
    private String focus = "";
    private String rdo_reqcomptp = "";
    private boolean flag1 = false;
    private boolean flag2 = false;
    
    private static final String PAGE_E  = "scp010101e.xhtml";
    private static final String PAGE_Q  = "scp010101q.xhtml";
    
    private DetailTable<MainData>  detailScUserCertificate = null;
    private DetailTable<MainData>  detailScUserPermitRole = null;
    private DetailRow<MainData> row = null;
    
    private ReportObject report;
                                           
    private Map<String, String> searchselectedrow ;
    
    public MainData getMasterdata() {
        if(masterdata == null) masterdata = new MainData();
        return masterdata;
    }

    public void setMasterdata(MainData masterdata) {
        this.masterdata = masterdata;
    }

    public MainData getSearchparam() {
        if(searchparam == null) searchparam = new MainData();
        return searchparam;
    }

    public void setSearchparam(MainData searchparam) {
        this.searchparam = searchparam;
    }

    public Map<String, String> getSearchselectedrow() {
        if(searchselectedrow == null) searchselectedrow = new HashMap<String, String>();
        return searchselectedrow;
    }

    public void setSearchselectedrow(Map<String, String> searchselectedrow) {
        this.searchselectedrow = searchselectedrow;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getRdo_reqcomptp() {
        return rdo_reqcomptp;
    }

    public void setRdo_reqcomptp(String rdo_reqcomptp) {
        this.rdo_reqcomptp = rdo_reqcomptp;
    }
    
    public boolean isFlag1() {
        return flag1;
    }

    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    public boolean isFlag2() {
        return flag2;
    }

    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }

    public ReportObject getReport() {
        if(report==null) report = new ReportObject();
        return report;
    }

    public void setReport(ReportObject report) {
        this.report = report;
    }

    public DetailTable<MainData> getDetailScUserCertificate() {
        if(this.detailScUserCertificate  == null){
            initdetailScUserCertificate();
        }
        return detailScUserCertificate;
    }

    public void setDetailScUserCertificate(DetailTable<MainData> detailScUserCertificate) {
        this.detailScUserCertificate = detailScUserCertificate;
    }

    public DetailTable<MainData> getDetailScUserPermitRole() {
        if(this.detailScUserPermitRole == null){
            initdetailScUserPermitRole();
        }
        return detailScUserPermitRole;
    }

    public void setDetailScUserPermitRole(DetailTable<MainData> detailScUserPermitRole) {
        this.detailScUserPermitRole = detailScUserPermitRole;
    }

    public DetailRow<MainData> getRow() {
        return row;
    }

    public void setRow(DetailRow<MainData> row) {
        this.row = row;
    }

     public class MainData extends BBBase {
        private ScUser                  scuser              = null; 
        private ScUserInfo              scuserinfo          = null;
        private ScUserPermitRole        scuserpermitrole    = null;
        
        private String                  workName            = "";
        private String                  compName            = "";
        private String                  radioType           = "";
        private String                  roleTname           = "";
        private String                  roletypeName        = "";
        private String                  usertypegrpdetail   = "";
        private String                  usertypegrpregdetail = "";
        private String                  roletname            = "";
        private boolean                 flag                = true; 
        private Date                    expDate             = null;

        public ScUser getScuser() {
            if(scuser == null) scuser = new ScUser();
            return scuser;
        }

        public void setScuser(ScUser scuser) {
            this.scuser = scuser;
        }

        public ScUserInfo getScuserinfo() {
            if(scuserinfo == null) scuserinfo = new ScUserInfo();
            return scuserinfo;
        }

        public void setScuserinfo(ScUserInfo scuserinfo) {
            this.scuserinfo = scuserinfo;
        }

        public ScUserPermitRole getScuserpermitrole() {
            if(scuserpermitrole == null) scuserpermitrole = new ScUserPermitRole(new ScUserPermitRolePK());
            return scuserpermitrole;
        }

        public void setScuserpermitrole(ScUserPermitRole scuserpermitrole) {
            this.scuserpermitrole = scuserpermitrole;
        }

        
        public String getWorkName() {
            return workName;
        }

        public void setWorkName(String workName) {
            this.workName = workName;
        }

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }

        public String getRadioType() {
            return radioType;
        }

        public void setRadioType(String radioType) {
            this.radioType = radioType;
        }

        public String getRoleTname() {
            return roleTname;
        }

        public void setRoleTname(String roleTname) {
            this.roleTname = roleTname;
        }

        public String getRoletypeName() {
            return roletypeName;
        }

        public void setRoletypeName(String roletypeName) {
            this.roletypeName = roletypeName;
        }

        public String getUsertypegrpdetail() {
            return usertypegrpdetail;
        }

        public void setUsertypegrpdetail(String usertypegrpdetail) {
            this.usertypegrpdetail = usertypegrpdetail;
        }

        public String getUsertypegrpregdetail() {
            return usertypegrpregdetail;
        }

        public void setUsertypegrpregdetail(String usertypegrpregdetail) {
            this.usertypegrpregdetail = usertypegrpregdetail;
        }

        public String getRoletname() {
            return roletname;
        }

        public void setRoletname(String roletname) {
            this.roletname = roletname;
        }

        
        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        
        public Date getExpDate() {
            return expDate;
        }

        public void setExpDate(Date expDate) {
            this.expDate = expDate;
        }
 
    }
    
    private void  initdetailScUserCertificate(){
         
           detailScUserCertificate    = new DetailTable<MainData>(new MainData()); 
           detailScUserCertificate.setParentclass(this);
           
           detailScUserCertificate.setFormid("form1");
           detailScUserCertificate.setParentid("form1");
           detailScUserCertificate.setDetailname("detailScUserCertificate");
            
           addDetailtable(detailScUserCertificate);
            
    }
    
    private void initdetailScUserPermitRole(){
        
           detailScUserPermitRole    = new DetailTable<MainData>(new MainData()); 
           detailScUserPermitRole.setParentclass(this);
           
           detailScUserPermitRole.setFormid("form1");
           detailScUserPermitRole.setParentid("form1");
           detailScUserPermitRole.setDetailname("detailScUserPermitRole");
            
           addDetailtable(detailScUserPermitRole);
    }
    public SCP010101() {
        
        setAutoconvertthai(true);
         
        this.focus = "userCode";
        
        report  = new ReportObject();
        
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
            
        } else if(mode.equals(BKBPage.ModeReport)){
            
            report();
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
        
        this.focus = "userCode";
        
        redirectPage(PAGE_E);
         
    }
    
    @Override
    protected void clearAllData(){
        
            masterdata          = null;
            searchparam         = null;
            rdo_reqcomptp       = "";
            focus = "userCode";
            
    }
    
    @Override
    protected void afterProcessValidate(){
        
        if(!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()){
            convertUIThaiAll();
        
            
        }
        
        
        
    }
    
    private void add(){
         
        IBusinessBase ib = BusinessFactory.getBusiness("SCP010101A");
            
        ib.process(this);
            
        genMessage(ib);

        if(ib.isOk()){
            clearAllData();
        }
    }
   
    private void update(){
        
//        toDB();
        
        IBusinessBase ib = BusinessFactory.getBusiness("SCP010101U");
            
            
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
        
//        if(validateDelete() == true){
//        
//            IBusinessBase ib = BusinessFactory.getBusiness("SCP010101D");
//
//
//            ib.process(this);
//
//            genMessage(ib);
//
//            if(ib.isOk()){
//
//                setModeupdate(false);
//                setModeadd(false);
//                setModequery(true);
//
//                searchaction = SEARCH_ACTION_REQUERY;
//
//
//                redirectPage(PAGE_Q);
//
//                BKBUQuery.getIns().autoResearch();
//
//            }
//            
//            
//        }
        
        return "";
      
    }
    
    private boolean validateDelete(){
        
        
        logger.debug("checkUserCodeDelete >> "+ this.getMasterdata().getScuser().getUserCode());
        
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("user_code", this.getMasterdata().getScuser().getUserCode());
        List l = selectData(hm,"lookup_sc_user_certificate");
        
        if(l.size() > 0){
            hm = (HashMap)l.get(0);
            logger.debug(">>lookup_sc_user_certificate "+hm.get("user_code"));
            
            addErrorMessage(null, "รหัสผู้ใช้งานตามที่ระบุ  พบข้อมูล Certificate ในการใช้งาน <br/> ไม่สามารถลบข้อมูล  ยกเลิกการทำงาน", 
                                  "รหัสผู้ใช้งานตามที่ระบุ  พบข้อมูล Certificate ในการใช้งาน <br/> ไม่สามารถลบข้อมูล  ยกเลิกการทำงาน");
            
            return false;

        } 
        
        return true;
    }
    
    private void reset(){
        
        this.focus = "userCode";
        rdo_reqcomptp       = "";
        
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
        
    }
    
    private void search(){
            
            logger.debug("q para "+ searchparam.scuser.getUserCode()+", "+ 
                        searchparam.scuser.getUserFnm()+", "+ 
                        searchparam.scuser.getUserLnm()+" ");
       
            HashMap<String, String> hm = new HashMap<String, String>();
            
            hm.put("user_code", searchparam.scuser.getUserCode());
            hm.put("user_pin", searchparam.scuser.getUserPin());
            hm.put("user_lnm", searchparam.scuser.getUserLnm());
            hm.put("user_fnm", searchparam.scuser.getUserFnm());
//          
            if(!Utils.NVL(this.getSearchparam().getScuserinfo().getRegUsertype()).equals("A")){
                hm.put("reg_usertype", Utils.NVL(this.getSearchparam().getScuserinfo().getRegUsertype()));
            }
            if(!Utils.NVL(this.getSearchparam().getScuser().getUserActive()).equals("A")){
                hm.put("user_active", Utils.NVL(this.getSearchparam().getScuser().getUserActive()));
            }
            
            BKBUQuery.getIns().setQueryparam(hm);
            BKBUQuery.getIns().search();
          
    }
    

    @Override
    public void selectSearchList(String para, Map<String, String> rec) {
      
        logger.debug(">>" +para +" >>" +rec);
        
        searchselectedrow       = rec;
        
        IBusinessBase ib = BusinessFactory.getBusiness("SCP010101S");
            
        this.focus = "userPin";
        
        ib.process(this);
        
        if(ib.isOk()){
        
//            toScreen();
//            logger.debug("selectSearchList>>" + this.getMasterdata().getScuser().getUserTitle() + " "+ this.getMasterdata().getScuser().getUserTnm() );
//            
//            String title    = Utils.NVL(this.getMasterdata().getScuser().getUserTitle());
//            
//            if(title.equals("001")){
//                this.setRdo_reqcomptp("001");
//            } else if(title.equals("002")){
//                this.setRdo_reqcomptp("002");
//            } else if(title.equals("003")){
//                this.setRdo_reqcomptp("003");
//            } else{
//                this.setRdo_reqcomptp("-");
//            }
            
            setModeupdate(true);
            setModeadd(false);
            setModequery(false);

            redirectPage(PAGE_E);

            
        }
     
    }
    
    private void report(){
      
//        
////        logger.debug(report.getReportfiles());
//               
//        report.setReportfile( report.getReportfiles().get("reportfile1") );
//        
//        report.put("0", searchparam.getZoneCode());
//        report.put("1", searchparam.getZoneNameth());
//        
//        if(Utils.NVL(searchparam.getZoneCode()).equals("") && Utils.NVL(searchparam.getZoneNameth()).equals("")){
//            String sf   = "";
//            report.put(ReportObject.PARA_SF, sf);
//        }else{
//            String sf   = "";
//            if(!Utils.NVL(searchparam.getZoneCode()).equals("")){
//                sf   = "{TB_ZONE.ZONE_CODE} = '" +searchparam.getZoneCode() + "'";
//            }
//            if(!Utils.NVL(searchparam.getZoneNameth()).equals("")){
//                if(!sf.equals("")){
//                    sf += " OR ";
//                }
//                sf   += "{TB_ZONE.ZONE_NAMETH} like '"+searchparam.getZoneNameth() +"%'";
//            }
//            report.put(ReportObject.PARA_SF, sf);
//           
//        }
//        
////        else {
////            String sf   = "{TB_ZONE.ZONE_CODE} = '" +searchparam.getZoneCode() + "' OR {TB_ZONE.ZONE_NAMETH} = '"+searchparam.getZoneNameth() +"' ";
////            report.put(ReportObject.PARA_SF, sf);
////        }
//        
//        report.updateForm();
//        
//        
//        logger.debug("End. " + report.getReportfile() +" para :" +report.getReportprompt());
   
    }
    
    @Override
    protected void afterApplyRequestValues() {
        //super.afterApplyRequestValues();
        
        report.setValid(false);
        
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
    
    //======== ajax ใส่คำนำหน้าชื่อ
    
    public void changeEvent(AjaxBehaviorEvent ev){
        
        logger.debug(">>changeEvent "+this.masterdata.getScuser().getUserTitle());
        
        UIComponent uic     = ev.getComponent();
       
        logger.debug(">>checkValueTitle id : "+Utils.NVL(uic.getId()));
       
        if(Utils.NVL(uic.getId()).equals("rdo_reqcomptp")){
    
            if(Utils.NVL(this.getRdo_reqcomptp()).equals("001")){
                this.setFlag1(true);
                this.getMasterdata().getScuser().setUserTitle("001");
                this.getMasterdata().getScuser().setUserTnm("นาย");

            }else if(Utils.NVL(this.getRdo_reqcomptp()).equals("002")){
                this.setFlag1(true);
                this.getMasterdata().getScuser().setUserTitle("002");
                this.getMasterdata().getScuser().setUserTnm("นางสาว");

            }else if(Utils.NVL(this.getRdo_reqcomptp()).equals("003")){
                this.setFlag1(true);
                this.getMasterdata().getScuser().setUserTitle("003");
                this.getMasterdata().getScuser().setUserTnm("นาง");
            }
        }
        else {
            
            this.setFlag1(false);
            
//            this.getMasterdata().getScuser().setUserTnm("");
        
        }
        
        
    }
    
    public void ajaxreset(){
        
        logger.debug(">>ajaxreset");
        Map<String, String> rec = new HashMap<String, String>();
        
        
        rec.put("user_passwd", getSearchparam().getScuser().getUserCode());
        logger.debug(">>reset"+ rec);
        IBusinessBase ib = BusinessFactory.getBusiness("SCP010101R");
            
            
        ib.process(this);

        genMessage(ib);

        if(ib.isOk()){
            
            addInfoMessage(null, "ตั้งค่ารหัสผ่านเริ่มต้นเรียบร้อย", "ตั้งค่ารหัสผ่านเริ่มต้นเรียบร้อย");
            
//            setModeupdate(false);
//            setModeadd(false);
//            setModequery(true);
//            
//            searchaction = SEARCH_ACTION_REQUERY;
//            
//            redirectPage(PAGE_Q);
//            
//            BKBUQuery.getIns().autoResearch();
            
            
        }
        
    }
    
    public void callCheckTitle1(){
       
       if(Utils.NVL(this.getMasterdata().getScuser().getUserTitle()).equals("001") || 
               Utils.NVL(this.getMasterdata().getScuser().getUserTitle()).equals("002") ||
               Utils.NVL(this.getMasterdata().getScuser().getUserTitle()).equals("003")){
           this.setRdo_reqcomptp(this.getMasterdata().getScuser().getUserTitle());
       }
   }
    
    private boolean checkvalues(){
        
//        if(Utils.NVL(this.getMasterdata().getScuserinfo().getUserAuthority()).equals("")  ){
//            
//            addErrorMessage(null,"กรุณาระบุเป็นผู้มีอำนาจสูงสุดของหน่วยงาน","กรุณาระบุเป็นผู้มีอำนาจสูงสุดของหน่วยงาน");
//             
//            return false;
//        }
//        if(Utils.NVL(this.getMasterdata().getScuserinfo().getUserCerttype()).equals("")  ){
//            
//            addErrorMessage(null,"กรุณาระบุประเภทใบรับรอง","กรุณาระบุประเภทใบรับรอง");
//             
//            return false;
//        }
//        if(Utils.NVL(this.getMasterdata().getScuserinfo().getUserType()).equals("")  ){
//            
//            addErrorMessage(null,"กรุณาระบุประเภทผู้ใช้งาน","กรุณาระบุประเภทผู้ใช้งาน");
//             
//            return false;
//        }
////        if(Utils.NVL(this.getMasterdata().getScuserinfo().getUserWebimage()).equals("")  ){
////            
////            addErrorMessage(null,"กรุณาระบุสิทธิ Web Image","กรุณาระบุสิทธิ Web Image");
////             
////            return false;
////        }
//        if(Utils.NVL(this.getMasterdata().getScuserinfo().getUserPrivilege()).equals("")  ){
//            
//            addErrorMessage(null,"กรุณาระบุสิทธิในการดูข้อมูล","กรุณาระบุสิทธิในการดูข้อมูล");
//             
//            return false;
//        }
//        if(Utils.NVL(this.getMasterdata().getScuser().getUserActive()).equals("")  ){
//            
//            addErrorMessage(null,"กรุณาระบุสิทธิการใช้งานให้ถูกต้อง","กรุณาระบุสิทธิการใช้งานให้ถูกต้อง");
//             
//            return false;
//        }
        return true;
    }
   
    public void checkUserCode(){
        
        logger.debug("checkUserCode >> "+ this.getMasterdata().getScuser().getUserCode());
        
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("user_code", this.getMasterdata().getScuser().getUserCode());
        List l = selectData(hm,"lookup_scuser");
        
        if(l.size() > 0){
            hm = (HashMap)l.get(0);
            logger.debug(">>lookup_scuser "+hm.get("user_code"));
            
            addErrorMessage(null, "มีรหัสผู้ใช้งาน "+hm.get("user_code") +" แล้ว", "มีรหัสผู้ใช้งาน "+hm.get("user_code") +" แล้ว");

            this.getMasterdata().getScuser().setUserCode("");
        }    
        
    }
   
    private List selectData(HashMap<String, String> hm,String trancode){
          
          List l = null;
          try{
              
            String sql  = QueryXML.createSQL(trancode, hm);
            
            l = DBUtils.queryData(sql);

          }finally{
              
          }
        
        return l;
    }
    
}
