/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP020110;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020110.DetailDaily;
import com.scc.pay.db.Daily;
import com.scc.pay.db.TbBank;
import com.scc.pay.db.TbCurrency;
import com.scc.pay.db.TbDescriptioncode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020110S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP020110 frmi = (ATP020110)inobj;
        
        logger.debug(">>parameter search:" + frmi.getMasterdata().getDaily().getCompanyid());
        
        searchDaily(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void searchDaily(ATP020110 frmi){
        frmi.setDetaildaily(null);
                
        String sql = "SELECT t FROM Daily t "
                   + "Where t.companyid = :companyid and "
                   + " t.receivedamount is not null and t.amount is not null and"
                   + " t.receivesuccess is null "
                   + "ORDER BY t.dailydate";
        Query query = em.createQuery(sql);
        query.setParameter("companyid",frmi.getMasterdata().getDaily().getCompanyid());

        List<Daily> l = query.getResultList();
        List<DetailDaily> ld = new ArrayList<DetailDaily>();
        
         for(Daily db : l){
              DetailDaily row = frmi.new DetailDaily();
              
              BeanUtil.copyProperties(row.getDaily(), db);
              
              
              row.setTbbank(searchTbBank(db.getPayby())); 
              row.setTbdescriptioncode(searchTbdescriptioncode(db.getDescriptioncode()));
              row.setTbcurrency(searchTbcurrency(db.getCurrency()));
              
              
              ld.add(row);
         }
        
          if(ld.size() > 0){
              frmi.getDetaildaily().convertSetListdetailrow(ld);
          }
    }
    
    private TbBank searchTbBank(Double bankid){
        
        if(bankid != null){
            TbBank db = em.find(TbBank.class, bankid.intValue());
            if(db != null){
                return db;
            }else{
                return new TbBank();
            }
        }
        
        return new TbBank();
        
        
    }
    
     private TbDescriptioncode searchTbdescriptioncode(String dsrptvalue){
        
        if(!Utils.NVL(dsrptvalue).equals("")){
            String sql = "SELECT t FROM TbDescriptioncode t "
                   + "Where t.dsrptvalue = :dsrptvalue ";
            Query query = em.createQuery(sql);
            query.setParameter("dsrptvalue",dsrptvalue);
            
            List<TbDescriptioncode> l = query.getResultList();
            if(!l.isEmpty()){
                return l.get(0);
            }
            
            return new TbDescriptioncode();
        }
        
        return new TbDescriptioncode();
        
        
    }
     
    private TbCurrency searchTbcurrency(String id){
        
        if(!Utils.NVL(id).equals("")){
            TbCurrency db = em.find(TbCurrency.class, Integer.parseInt(id));
            if(db != null){
                return db;
            }else{
                return new TbCurrency();
            }
        }
        
        return new TbCurrency();
        
        
    }
}
