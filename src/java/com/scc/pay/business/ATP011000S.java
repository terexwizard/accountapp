/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP011000;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.pay.db.TbPaymentVoucherno;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP011000S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP011000 frmi = (ATP011000)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("id"));
        
         TbPaymentVoucherno recordu = em.find(TbPaymentVoucherno.class, Integer.parseInt(frmi.getSearchselectedrow().get("id")));
        
        if(recordu != null){
            BeanUtil.copyProperties(frmi.getMasterdata().getTbpaymentvoucherno(), recordu);
        }
       
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
