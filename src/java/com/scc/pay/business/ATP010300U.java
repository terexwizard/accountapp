/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP010300;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.f1.util.MessageUtil;
import com.scc.pay.db.TbDescriptioncode;
import com.scc.pay.util.AppMessage;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010300U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010300 frmi = (ATP010300)inobj;
        
        logger.debug(">>ATP010300U " + frmi.getUserid());
        
      TbDescriptioncode record = em.find(TbDescriptioncode.class, frmi.getMasterdata().getTbdescriptioncode().getId());

      if (record == null) {
        createBusinessException(AppMessage.BUSINESS_ERROR_SEARCH);
        frmi.setOk(false);
        return inobj;
      }
      if (!checkLcnt(record.getUpdlcnt(), frmi.getMasterdata().getTbdescriptioncode().getUpdlcnt(), frmi)) {
        createBusinessException(MessageUtil.RECORD_LCNT_CHANGE);
        frmi.setOk(false);
        return inobj;
      }
      
      record.setDsrptvalue(frmi.getMasterdata().getTbdescriptioncode().getDsrptvalue());
      record.setDscptdesc(frmi.getMasterdata().getTbdescriptioncode().getDscptdesc());
      
      record.setUpdlcnt(addLcnt(record.getUpdlcnt()));
      record.setUpdtime(Utils.getcurDateTime());
      record.setUpduser(frmi.getUserid());
      merge(record);
      
      frmi.setOk(true);
      return inobj;
    }
    
    
}
