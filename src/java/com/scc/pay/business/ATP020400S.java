/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP020400;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020400.BringforwardData;
import com.scc.pay.db.Bringforward;
import com.scc.pay.db.TbBank;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020400S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP020400 frmi = (ATP020400)inobj;
        
        logger.debug(">>bfdate :" + frmi.getSearchselectedrow().get("bfdate"));
        
        searchBringforward(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    
    private void searchBringforward(ATP020400 frmi){
        
        frmi.getMasterdata().setListbringforwarddata(new ArrayList<BringforwardData>());
        
        String sql = "SELECT t FROM Bringforward t "
                       + "Where r.bringforwardPK.bfdate = :bfdate "
                       + "order by r.bringforwardPK.bankid ";
        Query query = em.createQuery(sql);
        query.setParameter("bfdate",Utils.NVL(frmi.getSearchselectedrow().get("bfdate")));

        List<Bringforward> l = query.getResultList();
        
         for(Bringforward db : l){
            BringforwardData bringforwarddata = frmi.new BringforwardData();
                    
             BeanUtil.copyProperties(bringforwarddata.getBringforward(), db);
            
             bringforwarddata.setTbbank(searchTbBank(db.getBringforwardPK().getBankid()));
             
             
            frmi.getMasterdata().setBfdate(Utils.formatStringToDateToScreen(db.getBringforwardPK().getBfdate()));
            frmi.getMasterdata().getListbringforwarddata().add(bringforwarddata);
        }
         
        
    }
    
    private TbBank searchTbBank(int bankid){
        TbBank db = em.find(TbBank.class, bankid);
        
        return db;
    }
    
}
