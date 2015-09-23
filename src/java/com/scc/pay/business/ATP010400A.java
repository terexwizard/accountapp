/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.pay.bkbean.ATP010400;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Invoicecompany;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010400A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010400 frmi = (ATP010400)inobj;
        
        logger.debug(">>" + frmi.getMasterdata().getInvoicecompany().getInvcomid());
        
        Invoicecompany record = frmi.getMasterdata().getInvoicecompany();
        
        
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
