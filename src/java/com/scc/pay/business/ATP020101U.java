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


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020101U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {  
        
      ATP020100 frmi = (ATP020100)inobj;
        
      logger.debug(">>" + frmi.getUserid());
      
      updateDaily(frmi);
      
      frmi.setOk(true);
      return inobj;
    }
    
    private void updateDaily(ATP020100 frmi){
        
        Daily db = em.find(Daily.class, frmi.getMasterdata().getDaily().getDailyPK());
        
        if(db != null){
            BeanUtil.copyProperties(db, frmi.getMasterdata().getDaily());
            
            db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
            db.setUpdtime(Utils.getcurDateTime());
            db.setUpduser(frmi.getUserid());
            merge(db);
        }
    }
    
}
