/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.business.BusinessImpl;
import com.scc.pay.bkbean.ATP020302;
import com.scc.pay.db.Receivable;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020302D extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020302 frmi = (ATP020302)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        deleteReceivable(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteReceivable(ATP020302 frmi){
        
        Receivable db = em.find(Receivable.class, frmi.getMasterdata().getReceivable().getId());

        if(db != null){
            remove(db);
        }   
    }
    
    
}
