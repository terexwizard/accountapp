/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP010700;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.pay.db.TbPaymentType;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010700S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP010700 frmi = (ATP010700)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("id"));
        
         TbPaymentType recordu = em.find(TbPaymentType.class, Integer.parseInt(frmi.getSearchselectedrow().get("id")));
        
        if(recordu != null){
            BeanUtil.copyProperties(frmi.getMasterdata().getTbpaymenttype(), recordu);
        }
       
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
