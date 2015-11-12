
package com.scc.pay.bkbean;

import com.scc.f1.dbutil.DBUtils;
import java.util.HashMap;
import java.util.List;
import javax.faces.model.SelectItem;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 01/10/2555 21:09:37
 */
public class BKBListData extends com.scc.pay.backingbean.BKBListAppData{
    
    private static List<SelectItem> tb13_position_type;
    private static List<SelectItem> rd16_process_ref;  
    private static List<SelectItem> rd01_req_data_ref;  
    private static List<SelectItem> tb05_educ; 
    private static List<SelectItem> tb06_mjor;
    private static List<SelectItem> tb07_univ;
    private static List<SelectItem> tb08_coun;        
    private static List<SelectItem> Tb09_industry;
    private static List<SelectItem> rd18_other_ref;
    private static List<SelectItem> tb_currency;

    public static List<SelectItem> getTb13_position_type() {
        
        if(tb13_position_type == null){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("type_grp", "1");
            
            tb13_position_type    = DBUtils.getSelectItem("lookup_tb13_position_type" , hm ,"posi_type", "type_name");
        }
        return tb13_position_type;
    }

    public static void setTb13_position_type(List<SelectItem> tb13_position_type) {
        BKBListData.tb13_position_type = tb13_position_type;
    }
    
    public static List<SelectItem> getRd16_process_ref() {
        
        rd16_process_ref    = DBUtils.getSelectItem("lookup_rd16_process_ref" ,"proc_code", "proc_desc");
        
        return rd16_process_ref;
    }
    
    public static void setRd16_process_ref(List<SelectItem> rd16_process_ref) {
        BKBListData.rd16_process_ref = rd16_process_ref;
    }
    
    public static List<SelectItem> getRd18_other_ref() {
        
        rd18_other_ref    = DBUtils.getSelectItem("lookup_rd18_other_ref" ,"othr_code", "othr_desc");
        
        return rd18_other_ref;
    }
    
    public static void setRd18_other_ref(List<SelectItem> rd18_other_ref) {
        BKBListData.rd18_other_ref = rd18_other_ref;
    }

    public static List<SelectItem> getRd01_req_data_ref() {
        if(rd01_req_data_ref == null){
            rd01_req_data_ref = DBUtils.getSelectItem("lookup_rd01reqdataref", "req_data_code", "req_data_desc");
        }
        return rd01_req_data_ref;
    }
    
    public static List<SelectItem> getRd01_req_data_refStepNo() {
        //>>terex comment 15/08/2556
        //if(rd01_req_data_ref == null){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("step_no", "2");
            rd01_req_data_ref = DBUtils.getSelectItem("lookup_rd01reqdataref",hm ,"req_data_code", "req_data_desc");
        //}
        return rd01_req_data_ref;
    }

    public static void setRd01_req_data_ref(List<SelectItem> rd01_req_data_ref) {
        BKBListData.rd01_req_data_ref = rd01_req_data_ref;
    }
    
    
    
    public static List<SelectItem> getTb05_educ() {
        
        tb05_educ    = DBUtils.getSelectItem("lookup_tb05_educ", "educ_code", "educ_desc");
        
        return tb05_educ;
    }
    
    public static void getTb05_educ(List<SelectItem> tb05_educ) {
        BKBListData.tb05_educ = tb05_educ;
    }
    
    public static List<SelectItem> getTb06_mjor() {
        
        tb06_mjor    = DBUtils.getSelectItem("lookup_tb06_mjor", "mjor_code", "mjor_desc");
        
        return tb06_mjor;
    }
    
    public static void getTb06_mjor(List<SelectItem> tb06_mjor) {
        BKBListData.tb06_mjor = tb06_mjor;
    }
    
    public static List<SelectItem> getTb07_univ() {
        
        tb07_univ    = DBUtils.getSelectItem("lookup_tb07_univ", "univ_code", "univ_desc");
        
        return tb07_univ;
    }
    
    public static void getTb07_univ(List<SelectItem> tb07_univ) {
        BKBListData.tb07_univ = tb07_univ;
    }
    
    public static List<SelectItem> getTb08_coun() {
        
        tb08_coun    = DBUtils.getSelectItem("lookup_tb08_coun", "coun_code", "coun_desc");
        
        return tb08_coun;
    }
    
    public static void getTb08_coun(List<SelectItem> tb08_coun) {
        BKBListData.tb08_coun = tb08_coun;
    }
    
    public static List<SelectItem> getTb09_industry() {
        
        Tb09_industry    = DBUtils.getSelectItem("lookup_tb09_indu", "indu_code", "indu_desc");
        
        return Tb09_industry;
    }
    
    public static void getTb09_industry(List<SelectItem> Tb09_industry) {
        BKBListData.Tb09_industry = Tb09_industry;
    }
    
    
      public static List<SelectItem> getCombotb_currency() {

        if(tb_currency == null){
            tb_currency = DBUtils.getSelectItem("lookup_tb_currency","currencyid", "currencyname");
        }
        return tb_currency;
    }
}
