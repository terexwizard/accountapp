/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020100;
import com.scc.pay.db.Daily;
import com.scc.pay.db.DailyPK;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020101A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020100 frmi = (ATP020100)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        insertDaily(frmi);
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void insertDaily(ATP020100 frmi){
        
        Daily db = new Daily(new DailyPK());
        
        BeanUtil.copyProperties(db, frmi.getMasterdata().getDaily());
        
        db.getDailyPK().setDailydate(Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydate()));
        db.getDailyPK().setJobno(frmi.getMasterdata().getDaily().getDailyPK().getJobno());
        
        db.setEntuser(frmi.getUserid());
        db.setEnttime(Utils.getcurDateTime());
        db.setUpdlcnt(1);
        db.setUpdtime( Utils.getcurDateTime() );
        db.setUpduser(frmi.getUserid());
        
        persist(db);
    }
}
