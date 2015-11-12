/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.backingbean.DetailRow;
import com.scc.pay.bkbean.ATP020301;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020301.DetailReceivable;
import com.scc.pay.db.Receivable;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020301U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {  
        
      ATP020301 frmi = (ATP020301)inobj;
        
      logger.debug(">>" + frmi.getUserid());
      
      processReceivable(frmi);
      
      frmi.setOk(true);
      return inobj;
    }
    
      
    private void processReceivable(ATP020301 frmi){
        
        for(DetailRow<DetailReceivable> item: frmi.getDetailreceivable().getListdetailrowdeleted()){
                Receivable dbu = em.find(Receivable.class, item.getData().getReceivable().getId());

                if(dbu != null){
                    remove(dbu);
                }
          }
        
        for(DetailRow<DetailReceivable> item :frmi.getDetailreceivable().getListdetailrow()){
            
                logger.debug(">>terex "+item.getData().getReceivable().getInvcomid());
                
                Receivable db = em.find(Receivable.class, item.getData().getReceivable().getId());
                if(db != null){    
                    db.setClearflag(item.getData().getReceivable().getClearflag());

                    db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
                    db.setUpdtime(Utils.getcurDateTime());
                    db.setUpduser(frmi.getUserid());

                    merge(db);
                }
        }
        
    }
    
}
