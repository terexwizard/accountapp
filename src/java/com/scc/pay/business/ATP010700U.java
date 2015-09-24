/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP010700;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.f1.util.MessageUtil;
import com.scc.pay.db.TbPaymentType;
import com.scc.pay.db.TbWhtax;
import com.scc.pay.util.AppMessage;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010700U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010700 frmi = (ATP010700)inobj;
        
        logger.debug(">>ATP010700U " + frmi.getUserid());
        
        TbPaymentType recordn = frmi.getMasterdata().getTbpaymenttype();
        TbPaymentType record = em.find(TbPaymentType.class, recordn.getId());

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
      
      record.setPayvalue(recordn.getPayvalue());
      record.setPaydesc(recordn.getPaydesc());
      
      record.setUpdlcnt(addLcnt(record.getUpdlcnt()));
      record.setUpdtime(Utils.getcurDateTime());
      record.setUpduser(frmi.getUserid());
      merge(record);
      
      frmi.setOk(true);
      return inobj;
    }
    
    
}
