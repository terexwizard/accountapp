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
    
    public List<SelectItem> getEquipType() {
            return RdbXML.getSelectItem("equipType");
    }
    
    public List<SelectItem> getStatus() {
            return RdbXML.getSelectItem("status");
    }
    
    public List<SelectItem> getTypegroup() {
            return RdbXML.getSelectItem("typegroup");
    }
    
    public List<SelectItem> getMonth() {
            return RdbXML.getSelectItem("month");
    }
}
