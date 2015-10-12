/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.backingbean.DetailRow;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020300;
import com.scc.pay.bkbean.ATP020300.DetailReceivable;
import com.scc.pay.db.Receivable;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020300A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020300 frmi = (ATP020300)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        processReceivable(frmi);
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void processReceivable(ATP020300 frmi){
        
        for(DetailRow<DetailReceivable> item :frmi.getDetailreceivable().getListdetailrow()){
            

                
                Receivable dbbean = new Receivable();
                
                BeanUtil.copyProperties(dbbean, item.getData().getReceivable());
                
                dbbean.setInvcomid(frmi.getMasterdata().getInvoicecompany().getInvcomid());
                dbbean.setCompany(frmi.getMasterdata().getInvoicecompany().getCompanyname());
                dbbean.setInvdate(Utils.formatDateToStringToDBEn(item.getData().getInvdate()));
                dbbean.setSubmitdate(Utils.formatDateToStringToDBEn(item.getData().getSubmitdate()));
                
                dbbean.setEntuser(frmi.getUserid());
                dbbean.setEnttime(Utils.getcurDateTime());
                dbbean.setUpdlcnt(1);
                dbbean.setUpdtime( Utils.getcurDateTime() );
                dbbean.setUpduser(frmi.getUserid());

                persist(dbbean);

            
        }
        
    }
}
