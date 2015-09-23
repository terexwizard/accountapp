/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP010100;
import com.scc.f1.business.BusinessImpl;
import com.scc.pay.db.TbBank;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010100S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP010100 frmi = (ATP010100)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("bankid"));
        
        
        TbBank rec = em.find(TbBank.class, Integer.parseInt(frmi.getSearchselectedrow().get("bankid")));
            
        frmi.getMasterdata().setTbbank(rec);
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
