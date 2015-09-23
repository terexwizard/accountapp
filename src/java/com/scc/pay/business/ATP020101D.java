/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.business.BusinessImpl;
import com.scc.pay.bkbean.ATP020100;
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
        
        
        
        ATP020100 frmi = (ATP020100)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        deleteDaily(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteDaily(ATP020100 frmi){
        
        Daily db = em.find(Daily.class, frmi.getMasterdata().getDaily().getDailyPK());
        
        if(db != null){
            remove(db);
        }
        
    }
    
    
}
