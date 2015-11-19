/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP020101;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Daily;
import java.util.HashMap;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020101U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {  
        
      ATP020101 frmi = (ATP020101)inobj;
        
      logger.debug(">>" + frmi.getUserid());
      
      updateDaily(frmi);
      
       //===========================
        HashMap<String,String> vhm = new HashMap<String,String>();
        vhm.put("vuser", frmi.getUserid());
        vhm.put("vbfdate", Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydate()));
        
        IBusinessBase ib = BusinessFactory.getBusiness("PROCESSBRINGFORWARD");
        ib.processBackground(vhm);
      
      frmi.setOk(true);
      return inobj;
    }
    
    private void updateDaily(ATP020101 frmi){
        
        Daily db = em.find(Daily.class, frmi.getMasterdata().getDaily().getDailyid());
        
        if(db != null){
            BeanUtil.copyProperties(db, frmi.getMasterdata().getDaily());
            
            db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
            db.setUpdtime(Utils.getcurDateTime());
            db.setUpduser(frmi.getUserid());
            merge(db);
        }
    }
    
}
