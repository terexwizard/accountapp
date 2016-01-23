/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.backingbean.DetailRow;
import com.scc.pay.bkbean.ATP020300;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020300.DetailReceivable;
import com.scc.pay.db.Receivable;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020300U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {  
        
      ATP020300 frmi = (ATP020300)inobj;
        
      logger.debug(">>" + frmi.getUserid());
      
      processReceivable(frmi);
      
      frmi.setOk(true);
      return inobj;
    }
    
      
    private void processReceivable(ATP020300 frmi){
        
        for(DetailRow<DetailReceivable> item: frmi.getDetailreceivable().getListdetailrowdeleted()){
                Receivable dbu = em.find(Receivable.class, item.getData().getReceivable().getId());

                if(dbu != null){
                    remove(dbu);
                }
          }
        
        for(DetailRow<DetailReceivable> item :frmi.getDetailreceivable().getListdetailrow()){
            
                logger.debug(">>terex "+item.getData().getReceivable().getInvcomid());
            
                
                if(Utils.NVL(item.getRowstatus()).equals(DetailRow.ROW_STATUS_EDIT)){
                    Receivable db = em.find(Receivable.class, item.getData().getReceivable().getId());
                    
                    BeanUtil.copyProperties(db, item.getData().getReceivable());
                    
                    db.setInvcomid(frmi.getMasterdata().getInvoicecompany().getInvcomid());
                    db.setCompany(frmi.getMasterdata().getInvoicecompany().getCompanyname());
                    db.setInvdate(Utils.formatDateToStringToDBEn(item.getData().getInvdate()));
                    db.setSubmitdate(Utils.formatDateToStringToDBEn(item.getData().getSubmitdate()));
                    db.setEta(Utils.formatDateToStringToDBEn(item.getData().getEta()));
                    db.setEtd(Utils.formatDateToStringToDBEn(item.getData().getEtd()));

                    db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
                    db.setUpdtime(Utils.getcurDateTime());
                    db.setUpduser(frmi.getUserid());

                    merge(db);
                }else if(Utils.NVL(item.getRowstatus()).equals(DetailRow.ROW_STATUS_NEW)){
                    
                    Receivable dbbean = new Receivable();
                
                    BeanUtil.copyProperties(dbbean, item.getData().getReceivable());

                    dbbean.setInvcomid(frmi.getMasterdata().getInvoicecompany().getInvcomid());
                    dbbean.setCompany(frmi.getMasterdata().getInvoicecompany().getCompanyname());
                    dbbean.setInvdate(Utils.formatDateToStringToDBEn(item.getData().getInvdate()));
                    dbbean.setSubmitdate(Utils.formatDateToStringToDBEn(item.getData().getSubmitdate()));
                    dbbean.setEta(Utils.formatDateToStringToDBEn(item.getData().getEta()));
                    dbbean.setEtd(Utils.formatDateToStringToDBEn(item.getData().getEtd()));

                    dbbean.setEntuser(frmi.getUserid());
                    dbbean.setEnttime(Utils.getcurDateTime());
                    dbbean.setUpdlcnt(1);
                    dbbean.setUpdtime( Utils.getcurDateTime() );
                    dbbean.setUpduser(frmi.getUserid());

                    persist(dbbean);
                }
            
            
        }
        
    }
    
}
