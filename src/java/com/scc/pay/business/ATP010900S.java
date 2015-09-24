/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP010900;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.pay.db.TbReceivedVoucherno;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010900S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP010900 frmi = (ATP010900)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("id"));
        
         TbReceivedVoucherno recordu = em.find(TbReceivedVoucherno.class, Integer.parseInt(frmi.getSearchselectedrow().get("id")));
        
        if(recordu != null){
            BeanUtil.copyProperties(frmi.getMasterdata().getTbreceivedvoucherno(), recordu);
        }
       
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
