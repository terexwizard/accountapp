/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP020100;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.pay.db.Daily;
import com.scc.pay.util.CenterUtils;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020101S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP020100 frmi = (ATP020100)inobj;
        
        logger.debug(">>parameter search:" + frmi.getSearchselectedrow().get("dailyid"));
        
        
        Daily rec = em.find(Daily.class, frmi.getSearchselectedrow().get("dailyid"));
        
        if(rec!= null){
            BeanUtil.copyProperties(frmi.getMasterdata().getDaily(), rec);
            
            frmi.getMasterdata().setDailydate(CenterUtils.formatStringToDateToScreen(rec.getDailyid()));
        }
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
