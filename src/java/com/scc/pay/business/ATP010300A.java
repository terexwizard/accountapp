/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.pay.bkbean.ATP010300;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.db.TbCurrency;
import com.scc.pay.db.TbGroup;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010300A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010300 frmi = (ATP010300)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        TbGroup record = frmi.getMasterdata().getTbgroup();
        
        
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
