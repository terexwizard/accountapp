/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.backingbean.DetailRow;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020112;
import com.scc.pay.bkbean.ATP020112.DetailDaily;
import com.scc.pay.db.Daily;
import java.util.HashMap;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020112U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020112 frmi = (ATP020112)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        processDaily(frmi);
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void processDaily(ATP020112 frmi){
        
        for(DetailRow<DetailDaily> item : frmi.getDetaildaily().getListdetailrow()){
                if(item.getRowstatus().equals(DetailRow.ROW_STATUS_EDIT)){
                    
                    Daily db = em.find(Daily.class, item.getData().getDaily().getDailyid());
                    if(db != null){
                        db.setChequedate(Utils.formatDateToStringToDBEn(item.getData().getTmpchequedate()));
                        
                        db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
                        db.setUpdtime(Utils.getcurDateTime());
                        db.setUpduser(frmi.getUserid());
                        merge(db);
                        
                        
//                        //===========================
//                        HashMap<String,String> vhm = new HashMap<String,String>();
//                        vhm.put("vuser", frmi.getUserid());
//                        vhm.put("vbfdate", item.getData().getDaily().getDailydate());
//
//                        IBusinessBase ib = BusinessFactory.getBusiness("PROCESSBRINGFORWARD");
//                        ib.processBackground(vhm);
                        
                        //===========================
                        HashMap<String,Object> vhm = new HashMap<String,Object>();
                        vhm.put("user", frmi.getUserid());
                        vhm.put("dailydate", Utils.formatDateToStringToDBEn(item.getData().getTmpchequedate()));
                        vhm.put("form", item.getData().getDaily());

                        IBusinessBase ib = BusinessFactory.getBusiness("PROCESSBRINGFORWARDUPDATE");
                        ib.processBackground(vhm);
                        
                    }
                    
                }
        }
    }
}
