/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;

import com.scc.app.db.ScUser;
import com.scc.app.db.ScUserPermitRole;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.SCP010101;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class SCP010101D extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        SCP010101 frmi = (SCP010101)inobj;
        
        logger.debug(">>" + frmi.getUserid());
       

        deleteScUser(frmi);
        deleteScUserPermitRole(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void deleteScUser(SCP010101 frmi){
        try{
            ScUser recordn = frmi.getMasterdata().getScuser();
        
            ScUser recordu = em.find(ScUser.class, recordn.getUserCode() );
            
            if(recordu != null){
                remove(recordu);
            }
            
        }finally{
            
        }
    }
    
    
    private void deleteScUserPermitRole(SCP010101 frmi){
        
        String sql = "SELECT t FROM ScUserPermitRole t "
                       + "Where t.scUserPermitRolePK.userCode = :userCode ";
        Query query = em.createQuery(sql);
        query.setParameter("userCode",Utils.NVL(frmi.getMasterdata().getScuser().getUserCode()));
        
        List<ScUserPermitRole> l = query.getResultList();        
        if(!l.isEmpty()){
            for(ScUserPermitRole db : l){
                remove(db);
            }
            
        }
    }
    
}
