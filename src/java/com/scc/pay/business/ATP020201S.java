/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP020201;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020201.DetailInvoice;
import com.scc.pay.db.Invoice;
import com.scc.pay.db.Invoicecompany;
import com.scc.pay.util.CenterUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020201S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP020201 frmi = (ATP020201)inobj;
        
        logger.debug(">>invcomid :" + frmi.getSearchselectedrow().get("invcomid")+" // "+frmi.getSearchselectedrow().get("clearflag"));
        
        searchInvoicecompany(frmi);
        searchInvoice(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void searchInvoicecompany(ATP020201 frmi){
        
        Invoicecompany db = em.find(Invoicecompany.class, frmi.getSearchselectedrow().get("invcomid"));
        
        if(db != null){
            BeanUtil.copyProperties(frmi.getMasterdata().getInvoicecompany(), db);
        }
        
    }
    
    private void searchInvoice(ATP020201 frmi){
        
        String clearflag = frmi.getSearchselectedrow().get("clearflag");
        
         String sql = "SELECT t FROM Invoice t "
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

        List<Invoice> l = query.getResultList();
        List<DetailInvoice> ld = new ArrayList<DetailInvoice>();
        
         for(Invoice db : l){
              DetailInvoice row = frmi.new DetailInvoice();
              
              BeanUtil.copyProperties(row.getInvoice(), db);
              
              row.setInvdate(CenterUtils.formatStringToDateToScreen(db.getInvdate()));
              row.setDuedate(CenterUtils.formatStringToDateToScreen(db.getDuedate()));
              row.setReceivedDate(CenterUtils.formatStringToDateToScreen(db.getReceivedDate()));
              row.setJobdate(CenterUtils.formatStringToDateToScreen(db.getJobdate()));
              row.setSubmitdate(CenterUtils.formatStringToDateToScreen(db.getSubmitdate()));
              row.setCurrency_disp(CenterUtils.getLabelCombotb_currency(db.getCurrency()));
              row.setCleardate(CenterUtils.formatStringToDateToScreen(db.getCleardate()));
              
              ld.add(row);
         }
         
         frmi.getDetailinvoice().convertSetListdetailrow(ld);
         
        
    }
    
}
