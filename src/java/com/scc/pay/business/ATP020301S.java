/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP020301;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020301.DetailReceivable;
import com.scc.pay.bkbean.BKBListData;
import com.scc.pay.db.Invoicecompany;
import com.scc.pay.db.Receivable;
import com.scc.pay.util.CenterUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.Query;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020301S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP020301 frmi = (ATP020301)inobj;
        
        logger.debug(">>invcomid :" + frmi.getSearchselectedrow().get("invcomid")+" // "+frmi.getSearchselectedrow().get("clearflag"));
        
        searchInvoicecompany(frmi);
        searchInvoice(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void searchInvoicecompany(ATP020301 frmi){
        
        Invoicecompany db = em.find(Invoicecompany.class, frmi.getSearchselectedrow().get("invcomid"));
        
        if(db != null){
            BeanUtil.copyProperties(frmi.getMasterdata().getInvoicecompany(), db);
        }
        
    }
    
    private void searchInvoice(ATP020301 frmi){
        
        String clearflag = frmi.getSearchselectedrow().get("clearflag");
        
         String sql = "SELECT t FROM Receivable t "
                       + "Where t.invcomid = :invcomid ";
                if(Utils.NVL(clearflag).equals("Y")){
                    sql += "and (t.clearflag is not null) ";
                }else{
                    sql += "and (t.clearflag is null) ";
                }
                sql += "order by t.submitdate desc";
        Query query = em.createQuery(sql);
        query.setParameter("invcomid",frmi.getMasterdata().getInvoicecompany().getInvcomid());
        //query.setParameter("clearflag","false");

        List<Receivable> l = query.getResultList();
        List<DetailReceivable> ld = new ArrayList<DetailReceivable>();
        
         for(Receivable db : l){
              DetailReceivable row = frmi.new DetailReceivable();
              
              BeanUtil.copyProperties(row.getReceivable(), db);
              
              row.setInvdate(CenterUtils.formatStringToDateToScreen(db.getInvdate()));
              row.setSubmitdate(CenterUtils.formatStringToDateToScreen(db.getSubmitdate()));
              row.setCurrency_disp(getLabelCombotb_currency(db.getCurrency()));
              row.setCleardate(CenterUtils.formatStringToDateToScreen(db.getCleardate()));
              
              ld.add(row);
         }
         
         frmi.getDetailreceivable().convertSetListdetailrow(ld);
         
        
    }
    
       
    private String getLabelCombotb_currency(String code){
        
        String result = "";
        if(!Utils.NVL(code).equals("")){
            for(SelectItem si :  BKBListData.getCombotb_currency()){
                    if(Utils.NVL(code).equals(Utils.NVL(si.getValue()))){
                        result = si.getLabel();
                        break;
                    }
             }
        }
        return result;
    }
}
