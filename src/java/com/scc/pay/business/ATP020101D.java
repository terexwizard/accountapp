/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.business.BusinessImpl;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020101;
import com.scc.pay.db.Daily;
import com.scc.pay.util.CenterUtils;
import java.util.HashMap;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020101D extends BusinessImpl {
    
    private String changeBank = "";

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020101 frmi = (ATP020101)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        deleteDaily(frmi);
        
        //===========================
        HashMap<String,Object> vhm = new HashMap<String,Object>();
        vhm.put("user", frmi.getUserid());
        vhm.put("dailydate", Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydate()));
        vhm.put("form", frmi.getMasterdata().getDaily());
        vhm.put("changeBank", changeBank);
        
        IBusinessBase ib = BusinessFactory.getBusiness("PROCESSBRINGFORWARDUPDATE");
        ib.processBackground(vhm);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteDaily(ATP020101 frmi){
        
        Daily db = em.find(Daily.class, frmi.getMasterdata().getDaily().getDailyid());
        
        if(db != null){
            
            changeBank =  CenterUtils.isChangePayby(db, frmi.getMasterdata().getDaily());
            
            remove(db);
        }
        
    }
    
    
}
