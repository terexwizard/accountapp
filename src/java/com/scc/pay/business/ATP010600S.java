/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP010600;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.pay.db.TbVat;
import com.scc.pay.db.TbWhtax;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010600S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP010600 frmi = (ATP010600)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("tax"));
        
         TbWhtax recordu = em.find(TbWhtax.class, Integer.parseInt(frmi.getSearchselectedrow().get("tax")));
        
        if(recordu != null){
            BeanUtil.copyProperties(frmi.getMasterdata().getTbwhtax(), recordu);
        }
       
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
