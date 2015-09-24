/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP010900;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.f1.util.MessageUtil;
import com.scc.pay.db.TbReceivedVoucherno;
import com.scc.pay.util.AppMessage;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010900U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010900 frmi = (ATP010900)inobj;
        
        logger.debug(">>ATP010900U " + frmi.getUserid());
        
        TbReceivedVoucherno recordn = frmi.getMasterdata().getTbreceivedvoucherno();
        TbReceivedVoucherno record = em.find(TbReceivedVoucherno.class, recordn.getId());

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
      
      record.setRevvalue(recordn.getRevvalue());
      record.setRevdesc(recordn.getRevdesc());
      
      record.setUpdlcnt(addLcnt(record.getUpdlcnt()));
      record.setUpdtime(Utils.getcurDateTime());
      record.setUpduser(frmi.getUserid());
      merge(record);
      
      frmi.setOk(true);
      return inobj;
    }
    
    
}
