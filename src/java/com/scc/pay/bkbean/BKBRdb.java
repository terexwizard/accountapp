/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;

import com.scc.f1.dbutil.RdbXML;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 23 พ.ค. 2555 16:28:27
 */

@ManagedBean
@ApplicationScoped
public class BKBRdb {
    
    public List<SelectItem> getItemUsed(){
        
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
//        for (NamePk item : items) {
//                selectItems.add(new SelectItem(item.getPk(), item.getName()));
//        }
        
        
        selectItems.add(new SelectItem("Y" , "ใช้งาน"));
        selectItems.add(new SelectItem("N" , "ไม่ใช้งาน"));
        
        return selectItems;
        
    }
    
    public List<SelectItem> getSelectRdb1(){
        
        return RdbXML.getSelectItem("example_rdb1");
        
    }
    
    public List<SelectItem> getSelectRdb1withNotsp(){
        
        return RdbXML.getSelectItem("example_rdb1","",null);
        
    }
    
    public List<SelectItem> getSelectRdb2(){
        
        return RdbXML.getSelectItem("example_rdb2");
        
    }
    
    public List<SelectItem> getSelectRdb2withAll(){
        
        return RdbXML.getSelectItem("example_rdb2",null,"ALL");
        
    }
    
    
     public List<SelectItem> getNews_type() {
            return RdbXML.getSelectItem("news_type");
    }
     
     public List<SelectItem> getRclose_fgext() {
            return RdbXML.getSelectItem("rclose_fgext");
    }
     
    public List<SelectItem> getCountry_fgdisp() {
            return RdbXML.getSelectItem("country_fgdisp");
    }

    public List<SelectItem> getTitel_fgdisp() {
            return RdbXML.getSelectItem("titel_fgdisp");
    }

    public List<SelectItem> getTitel_active() {
            return RdbXML.getSelectItem("titel_active");
    }

    public List<SelectItem> getWebdeli_fgext() {
            return RdbXML.getSelectItem("webdeli_fgext");
    }

    public List<SelectItem> getRegtra_print() {
            return RdbXML.getSelectItem("regtra_print");
    }

    public List<SelectItem> getRegtra_active() {
            return RdbXML.getSelectItem("regtra_active");
    }

    public List<SelectItem> getCmoffType() {
            return RdbXML.getSelectItem("cmofftype");
    }

    public List<SelectItem> getPvZone() {
            return RdbXML.getSelectItem("pvzone");
    }
    
    public List<SelectItem> getCmownGrpCode() {
            return RdbXML.getSelectItem("cmowngrpcode");
    }
    
    public List<SelectItem> getIobj_cmflag() {
            return RdbXML.getSelectItem("iobj_cmflag");
    }

    public List<SelectItem> getWebpay_fgext() {
            return RdbXML.getSelectItem("webpay_fgext");
    }

    public List<SelectItem> getFuncInitmode() {
            return RdbXML.getSelectItem("funcinitmode");
    }
    
    public List<SelectItem> getFuncActive() {
            return RdbXML.getSelectItem("funcactive");
    }
    
    public List<SelectItem> getTypera() {
            return RdbXML.getSelectItem("typera");
    }
    
    public List<SelectItem> getTypeCmoff() {
            return RdbXML.getSelectItem("typeCmoff");
    }
    
    public List<SelectItem> getCmlfiletype() {
            return RdbXML.getSelectItem("cmlfiletype");
    }
    
    public List<SelectItem> getAddorno() {
            return RdbXML.getSelectItem("addorno");
    }
    
    public List<SelectItem> getAddorall() {
            return RdbXML.getSelectItem("addorall");
    }
    
    public List<SelectItem> getYesorall() {
            return RdbXML.getSelectItem("yesorall");
    }
    
    public List<SelectItem> getYesorno() {
            return RdbXML.getSelectItem("yesorno");
    }
    
    public List<SelectItem> getExporttype() {
            return RdbXML.getSelectItem("exporttype");
    }
    
    public List<SelectItem> getCmmecommerce() {
            return RdbXML.getSelectItem("cmmecommerce");
    }
    
    public List<SelectItem> getDatatype() {
            return RdbXML.getSelectItem("datatype");
    }
    
    public List<SelectItem> getReqtpFlag() {
            return RdbXML.getSelectItem("reqtpflag");
    }
    
    public List<SelectItem> getUserType() {
            return RdbXML.getSelectItem("usertype");
    }
    
    public List<SelectItem> getUserFgpriv() {
            return RdbXML.getSelectItem("userfgpriv");
    }
    public List<SelectItem> getUserPriviledge() {
            return RdbXML.getSelectItem("userpriviledge");
    }
    
    public List<SelectItem> getReqfmonth() {
            return RdbXML.getSelectItem("reqfmonth");
    }
    
    public List<SelectItem> getReqsflag() {
            return RdbXML.getSelectItem("reqsflag");
    }
    
    public List<SelectItem> getUserAuthority() {
            return RdbXML.getSelectItem("userAuthority");
    }
    
    public List<SelectItem> getUserCerttype() {
            return RdbXML.getSelectItem("userCerttype");
    }
    
    public List<SelectItem> getUserType1() {
            return RdbXML.getSelectItem("userType1");
    }
    
    public List<SelectItem> getUserWebimage() {
            return RdbXML.getSelectItem("userWebimage");
    }
    
    public List<SelectItem> getUserPrivilege() {
            return RdbXML.getSelectItem("userPrivilege");
    }
      
    public List<SelectItem> getServiceList() {
            return RdbXML.getSelectItem("serviceList");
    }
      
    public List<SelectItem> getExtendList() {
            return RdbXML.getSelectItem("extendList");
    }
    
    public List<SelectItem> getActiveList() {
            return RdbXML.getSelectItem("activeList"); 
    }
    
    public List<SelectItem> getMsg_flag() {
            return RdbXML.getSelectItem("msg_flag"); 
    }
    
    public List<SelectItem> getHolDay() {
            return RdbXML.getSelectItem("holDay"); 
    }
    
    public List<SelectItem> getReqscanstat() {
            return RdbXML.getSelectItem("reqscanstat");
    }
    
    public List<SelectItem> getReqwformgrpRadio() {
            return RdbXML.getSelectItem("reqwformgrpRadio");
    }
    
    public List<SelectItem> getWorkDayen() {
            return RdbXML.getSelectItem("workDayen");
    }
    
    public List<SelectItem> getWorkDayth() {
            return RdbXML.getSelectItem("workDayth");
    }
    
    public List<SelectItem> getWorkFlag() {
            return RdbXML.getSelectItem("workFlag");
    }
    
    public List<SelectItem> getLevelWebImage() {
            return RdbXML.getSelectItem("level_webimage");
    }
    
    public List<SelectItem> getRegisterRole() {
            return RdbXML.getSelectItem("register_role");
    }
    
    public List<SelectItem> getReqscanflag() {
            return RdbXML.getSelectItem("reqscanflag");
    }
    
    public List<SelectItem> getSubmitInd() {
            return RdbXML.getSelectItem("submitInd");
    }
    
    public List<SelectItem> getCertstat() {
            return RdbXML.getSelectItem("certstat");
    }
            
    public List<SelectItem> getCertregtype() {
            return RdbXML.getSelectItem("certregtype");
    }
            
    public List<SelectItem> getCertrevoketype() {
            return RdbXML.getSelectItem("certrevoketype");
    }
                    
    public List<SelectItem> getCerttype() {
            return RdbXML.getSelectItem("certtype");
    }
    
    public List<SelectItem> getCertprovider() {
            return RdbXML.getSelectItem("certprovider");
    }
    
    public List<SelectItem> getRegUsertype() {
            return RdbXML.getSelectItem("regUsertype");
    }
    
    public List<SelectItem> getWkLevel() {
            return RdbXML.getSelectItem("wkLevel");
    }
    
    public List<SelectItem> getRegUsertypeGrpreg() {
            return RdbXML.getSelectItem("regUsertypeGrpreg");
    }
    
    public List<SelectItem> getFormreqflag() {
            return RdbXML.getSelectItem("formreqflag");
    }
    
    public List<SelectItem> getStepNo() {
            return RdbXML.getSelectItem("stepNo");
    }
    
    public List<SelectItem> getEquipType() {
            return RdbXML.getSelectItem("equipType");
    }
    
    public List<SelectItem> getStatus() {
            return RdbXML.getSelectItem("status");
    }
    
    public List<SelectItem> getTypegroup() {
            return RdbXML.getSelectItem("typegroup");
    }
}
