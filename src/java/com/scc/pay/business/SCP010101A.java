/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.app.db.ScUser;
import com.scc.app.db.ScUserPermitRole;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.SecurityUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.SCP010101;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class SCP010101A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        SCP010101 frmi = (SCP010101)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        insertScUser (frmi);
        insertScUserPermitRole(frmi);  
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void insertScUser(SCP010101 frmi){
        
        try{

            ScUser form = frmi.getMasterdata().getScuser();

            form.setUserPasswd(SecurityUtil.encryptPasswordBase64(frmi.getMasterdata().getScuser().getUserPasswd()));
           
            form.setTentuser(frmi.getUserid());
            form.setTenttime(Utils.getcurDateTime());
            form.setTupdlcnt(1);
            form.setTupdtime( Utils.getcurDateTime() );
            form.setTupduser(frmi.getUserid());

            persist(form);

        }finally{

        }
    }
    
    
    private void insertScUserPermitRole(SCP010101 frmi){
        
        ScUserPermitRole db = new ScUserPermitRole(frmi.getMasterdata().getScuser().getUserCode(), 
                frmi.getMasterdata().getScuserpermitrole().getScUserPermitRolePK().getRoleCode());
        
        db.setTentuser(frmi.getUserid());
        db.setTenttime(Utils.getcurDateTime());
        db.setTupdlcnt(1);
        db.setTupdtime( Utils.getcurDateTime() );
        db.setTupduser(frmi.getUserid());

        persist(db);
    }

    
 
}

