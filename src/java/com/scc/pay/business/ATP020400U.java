/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;



import com.scc.pay.bkbean.ATP020400;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020400.BringforwardData;
import java.util.List;
import javax.persistence.Query;


/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020400U extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {  
        
      ATP020400 frmi = (ATP020400)inobj;
        
      logger.debug(">>" + frmi.getUserid());
      
      processBringforward(frmi);
      
      frmi.setOk(true);
      return inobj;
    }
    
      
    private void processBringforward(ATP020400 frmi){
        
      
        String sql = "DELETE FROM Bringforward r "
                + "where r.bringforwardPK.bfdate = :bfdate ";

        Query query = em.createQuery(sql);
        query.setParameter("bfdate",Utils.formatDateToStringToDBEn(frmi.getMasterdata().getBfdate()));
        query.executeUpdate();
        
        //==================================
        List<BringforwardData> l = frmi.getMasterdata().getListbringforwarddata();
        
        for(BringforwardData bringforwarddata : l){
            
            logger.debug(">>Bringforward "+bringforwarddata.getBringforward().getReceived() +"//"+bringforwarddata.getBringforward().getPaid());
            
            bringforwarddata.getBringforward().getBringforwardPK().setBfdate(Utils.formatDateToStringToDBEn(frmi.getMasterdata().getBfdate()));
            
            bringforwarddata.getBringforward().setEntuser(frmi.getUserid());
            bringforwarddata.getBringforward().setEnttime(Utils.getcurDateTime());
            bringforwarddata.getBringforward().setUpdlcnt(1);
            bringforwarddata.getBringforward().setUpdtime( Utils.getcurDateTime() );
            bringforwarddata.getBringforward().setUpduser(frmi.getUserid());

            persist(bringforwarddata.getBringforward());
            
        }
    }
    
}
