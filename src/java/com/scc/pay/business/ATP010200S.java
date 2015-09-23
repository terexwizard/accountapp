/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP010200;
import com.scc.f1.business.BusinessImpl;
import com.scc.pay.db.TbCurrency;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010200S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP010200 frmi = (ATP010200)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("currencyid"));
        
        
        TbCurrency rec = em.find(TbCurrency.class, Integer.parseInt(frmi.getSearchselectedrow().get("currencyid")));
            
        frmi.getMasterdata().setTbcurrency(rec);
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
