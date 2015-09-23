/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.pay.bkbean.ATP010600;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.db.TbWhtax;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010600A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010600 frmi = (ATP010600)inobj;
        
        logger.debug(">>" + frmi.getMasterdata().getTbwhtax().getTax());
        
        TbWhtax record = frmi.getMasterdata().getTbwhtax();
        
        
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
