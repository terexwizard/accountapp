/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;
 
import com.scc.app.db.ScUser;
import com.scc.app.db.ScUserPermitRole;
import com.scc.f1.business.BusinessException;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.MessageUtil;
import com.scc.f1.util.SecurityUtil;
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
public class SCP010101U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
         
        SCP010101 frmi = (SCP010101)inobj;
        
        try{
            
            logger.debug(">>" + frmi.getUserid());

            updateScUser(frmi);
            updateScUserPermitRole(frmi);

            frmi.setOk(true);
        }catch(BusinessException e){
            logger.debug("doProcess Exception");
            e.printStackTrace();
            frmi.setOk(false);
        }
        return inobj;
    }
    
    private void updateScUser(SCP010101 frmi)throws BusinessException{
        
        ScUser record = null;
         
        try{
            
            record = em.find(ScUser.class, frmi.getMasterdata().getScuser().getUserCode());
            
            if(record==null){
                
                createBusinessException(MessageUtil.SEARCH_DATA_NOTFOUND); 
                throw new BusinessException();
                
            }
            
            if(!checkLcnt(record.getTupdlcnt(),frmi.getMasterdata().getScuser().getTupdlcnt(),frmi)){
                createBusinessException(MessageUtil.RECORD_LCNT_CHANGE );
                throw new BusinessException();
            }
            
            if(record != null){
                
                ScUser recordn = frmi.getMasterdata().getScuser();

                if(frmi.getMasterdata().isChkpwd()){
                    record.setUserPasswd(SecurityUtil.encryptPasswordBase64(frmi.getMasterdata().getChangeuserPasswd()));
                }
            
                record.setUserPin( recordn.getUserPin());
                record.setUserTnm( recordn.getUserTnm());
                record.setUserFnm( recordn.getUserFnm());
                record.setUserLnm( recordn.getUserLnm());
//                record.setUserTnmen( recordn.getUserTnmen());
//                record.setUserFnmen( recordn.getUserFnmen());
//                record.setUserLnmen( recordn.getUserLnmen());
                record.setUserActive( recordn.getUserActive());

                record.setTupdlcnt(addLcnt(record.getTupdlcnt()));
                record.setTupdtime( Utils.getcurDateTime() );
                record.setTupduser(frmi.getUserid());

                merge(record);
            
            }
            
        } finally{
            record = null;
        }
    }
    
    private void updateScUserPermitRole(SCP010101 frmi){
        
        deleteScUserPermitRole(frmi);
        
        ScUserPermitRole db = new ScUserPermitRole(frmi.getMasterdata().getScuser().getUserCode(), 
                frmi.getMasterdata().getScuserpermitrole().getScUserPermitRolePK().getRoleCode());
        
        db.setTentuser(frmi.getUserid());
        db.setTenttime(Utils.getcurDateTime());
        db.setTupdlcnt(1);
        db.setTupdtime( Utils.getcurDateTime() );
        db.setTupduser(frmi.getUserid());

        persist(db);
    }
    
    
    private void deleteScUserPermitRole(SCP010101 frmi){
        
        String sql = "SELECT t FROM ScUserPermitRole t "
                       + "Where r.scUserPermitRolePK.userCode = :userCode ";
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
