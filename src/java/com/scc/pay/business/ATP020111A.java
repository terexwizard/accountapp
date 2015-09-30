/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.backingbean.DetailRow;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020111;
import com.scc.pay.bkbean.ATP020111.DetailDaily;
import com.scc.pay.db.Daily;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020111A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020111 frmi = (ATP020111)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        processDaily(frmi);
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void processDaily(ATP020111 frmi){
        
        for(DetailRow<DetailDaily> item : frmi.getDetaildaily().getListdetailrow()){
                if(item.getData().isDetailCheckbox()){
                    
                    Daily db = em.find(Daily.class, item.getData().getDaily().getDailyid());
                    if(db != null){
                        db.setPaymentsuccess("Y");
                        db.setPaymentsuccessdate(Utils.getcurDateDB(false));
                        db.setPaymentsuccessuser(frmi.getUserid());
                        
                        db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
                        db.setUpdtime(Utils.getcurDateTime());
                        db.setUpduser(frmi.getUserid());
                        merge(db);
                    }
                    
                }
        }
    }
}
