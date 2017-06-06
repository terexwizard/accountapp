/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP020100;
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
public class ATP020100U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {  
        
      ATP020100 frmi = (ATP020100)inobj;
        
      logger.debug(">>" + frmi.getUserid());
      
      updateDaily(frmi);
      
      //===========================
      if(!frmi.getMasterdata().isIsProcess()){
        HashMap<String,Object> vhm = new HashMap<String,Object>();
        vhm.put("user", frmi.getUserid());
        vhm.put("dailydate", Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydate()));
        vhm.put("form", frmi.getMasterdata().getDaily());

        IBusinessBase ib = BusinessFactory.getBusiness("PROCESSBRINGFORWARDUPDATE");
        ib.processBackground(vhm);
      }
      
      frmi.setOk(true);
      return inobj;
    }
    
    private void updateDaily(ATP020100 frmi){
        
        //แก้ไขวันที่ ลบของเก่า insert ของใหม่
        if(!Utils.NVL(frmi.getMasterdata().getDaily().getDailydate()).equals(Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydate()))){
            Daily db = em.find(Daily.class, frmi.getMasterdata().getDaily().getDailyid());
            
            remove(db);
            
            insertDaily(frmi);
        }else{
        
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
    
    private void insertDaily(ATP020100 frmi){
        
        Daily db = new Daily();
        
        BeanUtil.copyProperties(db, frmi.getMasterdata().getDaily());
        
        db.setDailydate(Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydate()));
        
        db.setEntuser(frmi.getUserid());
        db.setEnttime(Utils.getcurDateTime());
        db.setUpdlcnt(1);
        db.setUpdtime( Utils.getcurDateTime() );
        db.setUpduser(frmi.getUserid());
        
        persist(db);
    }
    
}
