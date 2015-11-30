/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.business.BusinessImpl;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020100;
import com.scc.pay.db.Daily;
import java.util.HashMap;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020100D extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020100 frmi = (ATP020100)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        deleteDaily(frmi);
        
        //===========================
        HashMap<String,Object> vhm = new HashMap<String,Object>();
        vhm.put("user", frmi.getUserid());
        vhm.put("dailydate", Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydate()));
        vhm.put("form", frmi.getMasterdata().getDaily());
        
        IBusinessBase ib = BusinessFactory.getBusiness("PROCESSBRINGFORWARDUPDATE");
        ib.processBackground(vhm);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteDaily(ATP020100 frmi){
        
        Daily db = em.find(Daily.class, frmi.getMasterdata().getDaily().getDailyid());
        
        if(db != null){
            remove(db);
        }
        
    }
    
    
}
