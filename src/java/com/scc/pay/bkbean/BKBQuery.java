/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.bkbean;


import com.scc.f1.dbutil.DBUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
public class BKBQuery {
    
    public List<SelectItem> getComboProvince() {
            //return DBUtils.getSelectItem("lookup_tb_province", "pv_code", "pv_nameth");
          return BKBListData.getSelectprovincelistOnly();  
    } 
    
    public List<SelectItem> getCombotb_bank() {
        return DBUtils.getSelectItem("lookup_tb_bank","bankid", "bankname");
    }
    
     public List<SelectItem> getCombotb_currency() {
        //return DBUtils.getSelectItem("lookup_tb_currency","currencyid", "currencyname");
         return BKBListData.getCombotb_currency();
    }
    
     public List<SelectItem> getCombotb_vat() {
        return DBUtils.getSelectItem("lookup_tb_vat","vat", "vat");
    }
     
    public List<SelectItem> getCombotb_whtax() {
        return DBUtils.getSelectItem("lookup_tb_whtax","tax", "tax");
    }
    
    public List<SelectItem> getCombotb_received_voucherno() {
        return DBUtils.getSelectItem("lookup_tb_received_voucherno","revvalue", "revvalue");
    }
    
    public List<SelectItem> getCombotb_payment_voucherno() {
        return DBUtils.getSelectItem("lookup_tb_payment_voucherno","payvalue", "payvalue");
    }
    
    public List<SelectItem> getCombotb_received_type() {
        return DBUtils.getSelectItem("lookup_tb_received_type","revalue", "revalue_disp");
    }
         
    public List<SelectItem> getCombotb_descriptioncode() {
        return DBUtils.getSelectItem("lookup_tb_descriptioncode","dsrptvalue", "dscptdesc");
    }
    
    public List<SelectItem> getCombotb_payment_type() {
        return DBUtils.getSelectItem("lookup_tb_payment_type","payvalue", "payvalue_disp");
    }
    
    public List<SelectItem> getCombosc_role() {
        return DBUtils.getSelectItem("lookup_sc_role","role_code", "role_tname");
    }
    
     public List<SelectItem> getCombovoucherno() {
        return DBUtils.getSelectItem("lookup_combo_voucherno","result", "result");
    }
    
     
    public List<SelectItem> getComboYear(){
        
        Locale myLocale = new Locale("en","US");
        String syear = new SimpleDateFormat( "yyyyMMdd", myLocale ).format(new Date());
        syear = String.valueOf(Integer.parseInt(syear.substring(0,4)));
        
        int year = Integer.parseInt(syear);
        
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        
        int loop = 10;
        
        //for(int x = (year-loop);x < (year+loop) ;x++){
        for(int x = (year);x > (year-loop) ;x--){
            selectItems.add(new SelectItem(Integer.toString(x) , Integer.toString(x)));
        }
        
        
        
        return selectItems;
        
    }
}
