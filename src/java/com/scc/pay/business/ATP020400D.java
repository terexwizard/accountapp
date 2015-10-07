/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020400;
import javax.persistence.Query;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020400D extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020400 frmi = (ATP020400)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        deleteBringforward(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteBringforward(ATP020400 frmi){
        
        String sql = "DELETE FROM Bringforward r "
                + "where r.bringforwardPK.bfdate = :bfdate ";

        Query query = em.createQuery(sql);
        query.setParameter("bfdate",Utils.formatDateToStringToDBEn(frmi.getMasterdata().getBfdate()));
        query.executeUpdate();
        
    }
    
    
}
