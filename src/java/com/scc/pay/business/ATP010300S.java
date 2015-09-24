/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP010300;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.pay.db.TbDescriptioncode;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010300S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP010300 frmi = (ATP010300)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("id"));
        
        TbDescriptioncode db = em.find(TbDescriptioncode.class, Integer.parseInt(frmi.getSearchselectedrow().get("id")) );
  
        if (db == null) {
            BeanUtil.copyProperties(frmi.getMasterdata().getTbdescriptioncode(), db);
        }
       
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
