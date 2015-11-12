/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.business.BusinessImpl;
import com.scc.pay.bkbean.ATP020301;
import com.scc.pay.db.Receivable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020301D extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020301 frmi = (ATP020301)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        deleteReceivable(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteReceivable(ATP020301 frmi){
        
        if(frmi.getDetailreceivable().getListdetailrow().size() > 0){

            String sql = "SELECT t FROM Receivable t "
                       + "Where t.invcomid = :invcomid ";
            Query query = em.createQuery(sql);
            query.setParameter("invcomid",frmi.getMasterdata().getInvoicecompany().getInvcomid());

            List<Receivable> l = query.getResultList();

            if(!l.isEmpty()){
                for(Receivable db : l){
                    remove(db);
                }
            }
        }
        
    }
    
    
}
