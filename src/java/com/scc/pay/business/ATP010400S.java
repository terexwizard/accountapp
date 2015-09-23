/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP010400;
import com.scc.f1.business.BusinessImpl;
import com.scc.pay.db.Invoicecompany;
import java.util.List;
import javax.persistence.Query;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010400S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP010400 frmi = (ATP010400)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("invcomid"));
        
         String sql = "SELECT t FROM Invoicecompany t "
                   + "Where t.invcomid = :invcomid ";
        Query query = em.createQuery(sql);
        query.setParameter("invcomid",frmi.getSearchselectedrow().get("invcomid"));

        List<Invoicecompany> l = query.getResultList();
        
        if(!l.isEmpty()){
            frmi.getMasterdata().setInvoicecompany(l.get(0));
        }
       
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
