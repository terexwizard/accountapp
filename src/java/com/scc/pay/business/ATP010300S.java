/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP010300;
import com.scc.f1.business.BusinessImpl;
import com.scc.pay.db.TbGroup;
import java.util.List;
import javax.persistence.Query;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010300S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP010300 frmi = (ATP010300)inobj;
        
        logger.debug("code id >>" + frmi.getSearchselectedrow().get("id"));
        
         String sql = "SELECT t FROM TbGroup t "
                   + "Where t.tbGroupPK.id = :id ";
        Query query = em.createQuery(sql);
        query.setParameter("id",Integer.parseInt(frmi.getSearchselectedrow().get("id")));

        List<TbGroup> l = query.getResultList();
        
        if(!l.isEmpty()){
            frmi.getMasterdata().setTbgroup(l.get(0));
        }
       
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
}
