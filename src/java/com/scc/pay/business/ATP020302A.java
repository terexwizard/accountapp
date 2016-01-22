/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020302;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020302A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020302 frmi = (ATP020302)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        insertReceivable(frmi);
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void insertReceivable(ATP020302 frmi){
     
        frmi.getMasterdata().getReceivable().setSubmitdate(Utils.getcurDateDB(false));
                
        frmi.getMasterdata().getReceivable().setEntuser(frmi.getUserid());
        frmi.getMasterdata().getReceivable().setEnttime(Utils.getcurDateTime());
        frmi.getMasterdata().getReceivable().setUpdlcnt(1);
        frmi.getMasterdata().getReceivable().setUpdtime( Utils.getcurDateTime() );
        frmi.getMasterdata().getReceivable().setUpduser(frmi.getUserid());

        persist(frmi.getMasterdata().getReceivable());
           
        
        
    }
}
