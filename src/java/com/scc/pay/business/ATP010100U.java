/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP010100;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.f1.util.MessageUtil;
import com.scc.pay.db.TbBank;
import com.scc.pay.util.AppMessage;
import javax.persistence.Query;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010100U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010100 frmi = (ATP010100)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        TbBank recordn = frmi.getMasterdata().getTbbank();
        TbBank record = em.find(TbBank.class, recordn.getBankid());

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
      
              checkMinDataBringforward();
        checkMaxDataBringforward();
      
//      record.setBankname(recordn.getBankname());
//      record.setBanknamesh(recordn.getBanknamesh());
//      record.setBankdesc(recordn.getBankdesc());
//      
//      record.setMonetaryusd(recordn.getMonetaryusd());
      
      BeanUtil.copyProperties(record, recordn);
      
      record.setUpdlcnt(addLcnt(record.getUpdlcnt()));
      record.setUpdtime(Utils.getcurDateTime());
      record.setUpduser(frmi.getUserid());
      merge(record);
      
      frmi.setOk(true);
      return inobj;
    }
    
    private String checkMinDataBringforward(){
        String sql = "select min(r.bringforwardPK.bfdate) as mindate FROM Bringforward r ";

        Query query = em.createQuery(sql);
        
        String mindate = (String)query.getSingleResult();
        logger.debug(">>checkMinDataBringforward "+mindate);
        
        return mindate;
    }
    
    private String checkMaxDataBringforward(){
        String sql = "select max(r.bringforwardPK.bfdate) as maxdate FROM Bringforward r ";

        Query query = em.createQuery(sql);
        
        String maxdate = (String)query.getSingleResult();
        logger.debug(">>checkMaxDataBringforward "+maxdate);
        
        return maxdate;
    }
}
