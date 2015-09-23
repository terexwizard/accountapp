/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP010600;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.f1.util.MessageUtil;
import com.scc.pay.db.TbWhtax;
import com.scc.pay.util.AppMessage;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010600U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010600 frmi = (ATP010600)inobj;
        
        logger.debug(">>ATP010600U " + frmi.getUserid());
        
        TbWhtax recordn = frmi.getMasterdata().getTbwhtax();
        TbWhtax record = em.find(TbWhtax.class, recordn.getTax());

      if (record == null) {
        createBusinessException(AppMessage.BUSINESS_ERROR_SEARCH);
        frmi.setOk(false);
        return inobj;
      }
      if (!checkLcnt(recordn.getUpdlcnt(), record.getUpdlcnt(), frmi)) {
        createBusinessException(MessageUtil.RECORD_LCNT_CHANGE);
        frmi.setOk(false);
        return inobj;
      }
      
      record.setDescription(recordn.getDescription());
      
      record.setUpdlcnt(addLcnt(record.getUpdlcnt()));
      record.setUpdtime(Utils.getcurDateTime());
      record.setUpduser(frmi.getUserid());
      merge(record);
      
      frmi.setOk(true);
      return inobj;
    }
    
    
}
