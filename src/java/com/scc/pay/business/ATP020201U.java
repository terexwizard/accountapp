/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.backingbean.DetailRow;
import com.scc.pay.bkbean.ATP020201;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020201.DetailInvoice;
import com.scc.pay.db.Invoice;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020201U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {  
        
      ATP020201 frmi = (ATP020201)inobj;
        
      logger.debug(">>" + frmi.getUserid());
      
      processInvoice(frmi);
      
      frmi.setOk(true);
      return inobj;
    }
    
      
    private void processInvoice(ATP020201 frmi){
        
        for(DetailRow<DetailInvoice> item: frmi.getDetailinvoice().getListdetailrowdeleted()){
                Invoice dbu = em.find(Invoice.class, item.getData().getInvoice().getInvid());

                if(dbu != null){
                    remove(dbu);
                }
          }
        
        for(DetailRow<DetailInvoice> item :frmi.getDetailinvoice().getListdetailrow()){
            
            logger.debug(">>terex "+item.getData().getInvoice().getInvid()+" // "+item.getRowstatus()+" // "+item.getData().getInvoice().getClearflag());
            
                
            Invoice db = em.find(Invoice.class, item.getData().getInvoice().getInvid());
            if(db != null){
                
                
                if(Utils.NVL(item.getData().getInvoice().getClearflag()).equals("false")){
                    db.setClearflag(null);
                    db.setCleardate(null);
                }else{
                    db.setClearflag(item.getData().getInvoice().getClearflag());
                    db.setCleardate(Utils.formatDateToStringToDBEn(item.getData().getCleardate()));
                }
                

                db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
                db.setUpdtime(Utils.getcurDateTime());
                db.setUpduser(frmi.getUserid());

                merge(db);
            }
        }
        
    }
    
}
