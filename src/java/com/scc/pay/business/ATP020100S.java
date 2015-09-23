/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP020100;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Daily;
import com.scc.pay.db.DailyPK;
import com.scc.pay.util.CenterUtils;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020100S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP020100 frmi = (ATP020100)inobj;
        
        logger.debug(">>parameter search:" + frmi.getSearchselectedrow().get("dailydate")+frmi.getSearchselectedrow().get("jobno"));
        
        
        DailyPK pk = new DailyPK(frmi.getSearchselectedrow().get("dailydate"), frmi.getSearchselectedrow().get("jobno"));
        
        Daily rec = em.find(Daily.class, pk);
        
        if(rec!= null){
            BeanUtil.copyProperties(frmi.getMasterdata().getDaily(), rec);
            
            frmi.getMasterdata().setDailydate(CenterUtils.formatStringToDateToScreen(rec.getDailyPK().getDailydate()));
        }
        
        if(!Utils.NVL(frmi.getMasterdata().getDaily().getReceivedCash()).equals("")){
             frmi.getMasterdata().setChkmode("1");
        }else{
             frmi.getMasterdata().setChkmode("2");
        }
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
