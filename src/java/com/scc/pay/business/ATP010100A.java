/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.pay.bkbean.ATP010100;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.db.TbBank;
import com.scc.pay.util.AppMessage;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010100A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010100 frmi = (ATP010100)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        TbBank record = frmi.getMasterdata().getTbbank();
        
        
        record.setEntuser(frmi.getUserid());
        record.setEnttime(Utils.getcurDateTime());
        record.setUpdlcnt(1);
        record.setUpdtime( Utils.getcurDateTime() );
        record.setUpduser(frmi.getUserid());
        
        persist(record);
        
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
