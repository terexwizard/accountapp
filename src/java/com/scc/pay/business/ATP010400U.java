/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP010400;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.f1.util.MessageUtil;
import com.scc.pay.db.Invoicecompany;
import com.scc.pay.util.AppMessage;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010400U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010400 frmi = (ATP010400)inobj;
        
        logger.debug(">>ATP010400U " + frmi.getUserid());
        
        Invoicecompany recordn = frmi.getMasterdata().getInvoicecompany();
        Invoicecompany record = em.find(Invoicecompany.class, recordn.getInvcomid());

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
      
      record.setCompanyname(recordn.getCompanyname());
      record.setRemark(recordn.getRemark());
      record.setTaxid(recordn.getTaxid());
      record.setAddress(recordn.getAddress());
      record.setTransport(recordn.getTransport());
      
      record.setUpdlcnt(addLcnt(record.getUpdlcnt()));
      record.setUpdtime(Utils.getcurDateTime());
      record.setUpduser(frmi.getUserid());
      merge(record);
      
      frmi.setOk(true);
      return inobj;
    }
    
    
}
