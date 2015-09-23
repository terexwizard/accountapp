/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP020200;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.pay.bkbean.ATP020200.DetailInvoice;
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
public class ATP020200S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP020200 frmi = (ATP020200)inobj;
        
        logger.debug(">>invcomid :" + frmi.getSearchselectedrow().get("invcomid"));
        
        searchInvoicecompany(frmi);
        searchInvoice(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void searchInvoicecompany(ATP020200 frmi){
        
        Invoicecompany db = em.find(Invoicecompany.class, frmi.getSearchselectedrow().get("invcomid"));
        
        if(db != null){
            BeanUtil.copyProperties(frmi.getMasterdata().getInvoicecompany(), db);
        }
        
    }
    
    private void searchInvoice(ATP020200 frmi){
        
         String sql = "SELECT t FROM Invoice t "
                       + "Where t.invcomid = :invcomid "
                       + "order by t.invdate desc";
        Query query = em.createQuery(sql);
        query.setParameter("invcomid",frmi.getMasterdata().getInvoicecompany().getInvcomid());

        List<Invoice> l = query.getResultList();
        List<DetailInvoice> ld = new ArrayList<DetailInvoice>();
        
         for(Invoice db : l){
              DetailInvoice row = frmi.new DetailInvoice();
              
              BeanUtil.copyProperties(row.getInvoice(), db);
              
              row.setInvdate(CenterUtils.formatStringToDateToScreen(db.getInvdate()));
              row.setDuedate(CenterUtils.formatStringToDateToScreen(db.getDuedate()));
              row.setReceivedDate(CenterUtils.formatStringToDateToScreen(db.getReceivedDate()));
              row.setJobdate(CenterUtils.formatStringToDateToScreen(db.getJobdate()));
              
              ld.add(row);
         }
         
         frmi.getDetailinvoice().convertSetListdetailrow(ld);
         
        
    }
    
}
