/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.pay.bkbean.ATP011000;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.db.TbPaymentVoucherno;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP011000A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
       
        ATP011000 frmi = (ATP011000)inobj;
        
        logger.debug(">>" + frmi.getMasterdata().getTbpaymentvoucherno().getPayvalue());
        
        TbPaymentVoucherno record = frmi.getMasterdata().getTbpaymentvoucherno();
        
        
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
