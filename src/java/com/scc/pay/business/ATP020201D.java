/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.business.BusinessImpl;
import com.scc.pay.bkbean.ATP020201;
import com.scc.pay.db.Invoice;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020201D extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020201 frmi = (ATP020201)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        deleteInvoice(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteInvoice(ATP020201 frmi){
        
        if(frmi.getDetailinvoice().getListdetailrow().size() > 0){

            String sql = "SELECT t FROM Invoice t "
                       + "Where t.invcomid = :invcomid ";
            Query query = em.createQuery(sql);
            query.setParameter("invcomid",frmi.getDetailinvoice().getListdetailrow().get(0).getData().getInvoice().getInvcomid());

            List<Invoice> l = query.getResultList();

            if(!l.isEmpty()){
                for(Invoice db : l){
                    remove(db);
                }
            }
        }
        
    }
    
    
}
