/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.business.BusinessImpl;
import com.scc.pay.bkbean.ATP020101;
import com.scc.pay.db.Daily;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020101D extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020101 frmi = (ATP020101)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        deleteDaily(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteDaily(ATP020101 frmi){
        
        Daily db = em.find(Daily.class, frmi.getMasterdata().getDaily().getDailyid());
        
        if(db != null){
            remove(db);
        }
        
    }
    
    
}
