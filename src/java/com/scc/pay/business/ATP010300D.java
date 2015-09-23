/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP010300;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.MessageUtil;
import com.scc.pay.db.TbGroup;
import com.scc.pay.util.AppMessage;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010300D extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010300 frmi = (ATP010300)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        TbGroup record = frmi.getMasterdata().getTbgroup();
        
        TbGroup recordu = em.find(TbGroup.class, record.getTbGroupPK() );
  
        if (recordu == null) {
          createBusinessException(AppMessage.BUSINESS_ERROR_DELETE);
          frmi.setOk(false);
          return inobj;
        }
        if (!checkLcnt(record.getUpdlcnt(), recordu.getUpdlcnt(), frmi)) {
          createBusinessException(MessageUtil.RECORD_LCNT_CHANGE);
          frmi.setOk(false);
          return inobj;
        }

        remove(recordu);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
