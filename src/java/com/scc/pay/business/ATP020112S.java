/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;





import com.scc.pay.bkbean.ATP020112;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020112.DetailDaily;
import com.scc.pay.db.Daily;
import com.scc.pay.db.TbBank;
import com.scc.pay.db.TbCurrency;
import com.scc.pay.db.TbDescriptioncode;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;



/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020112S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        ATP020112 frmi = (ATP020112)inobj;
        
        logger.debug(">>parameter search:" + frmi.getMasterdata().getCheque());
        
        searchDaily(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void searchDaily(ATP020112 frmi){
        frmi.setDetaildaily(null);
                
        String sql = "SELECT t FROM Daily t "
                   + "Where t.cheque = :cheque ";
        
        if(Utils.NVL(frmi.getMasterdata().getCheque()).equals("C")){
            sql += " and t.chequedate is not null ";
        }else if(Utils.NVL(frmi.getMasterdata().getCheque()).equals("N")){
            sql += " and t.chequedate is null ";
        }
        
        if(!Utils.NVL(frmi.getMasterdata().getDailydate()).equals("")){
            sql += " and t.dailydate >= :dailydate ";
        }
        if(!Utils.NVL(frmi.getMasterdata().getDailydatefn()).equals("")){
            sql += " and t.dailydate <= :dailydatefn ";
        }
        
        Query query = em.createQuery(sql);
        query.setParameter("cheque","true");
        if(!Utils.NVL(frmi.getMasterdata().getDailydate()).equals("")){
            query.setParameter("dailydate",Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydate()));
        }
        if(!Utils.NVL(frmi.getMasterdata().getDailydatefn()).equals("")){
            query.setParameter("dailydatefn",Utils.formatDateToStringToDBEn(frmi.getMasterdata().getDailydatefn()));
        }

        List<Daily> l = query.getResultList();
        List<DetailDaily> ld = new ArrayList<DetailDaily>();
        
        
        logger.debug(">>searchDaily:" + l.size());
        
         for(Daily db : l){
              DetailDaily row = frmi.new DetailDaily();
              
              BeanUtil.copyProperties(row.getDaily(), db);
              
              
              row.setTbbank(searchTbBank(db.getPayby())); 
              row.setTbdescriptioncode(searchTbdescriptioncode(db.getDescriptioncode()));
              row.setTbcurrency(searchTbcurrency(db.getCurrency()));
              
              
              if(row.getTbbank().getMonetaryusd().equals("true")){
                  if(db.getReceivedamount() == 0.00 && db.getAmount() == 0.00){
                      row.setMoney(new BigDecimal(db.getAmount2()));
                  }
                  
                  if(db.getPaidamount()== 0.00 && db.getAmount2() == 0.00){
                      row.setMoney(new BigDecimal(db.getAmount()));
                  }
                  
              }else{
                  if(db.getReceivedamount() == 0.00 && db.getAmount() == 0.00){
                      row.setMoney(new BigDecimal(db.getPaidamount()));
                  }
                  
                  if(db.getPaidamount()== 0.00 && db.getAmount2() == 0.00){
                      row.setMoney(new BigDecimal(db.getReceivedamount()));
                  }
              }
              
              row.setTmpchequedate(Utils.formatStringToDateToScreen(db.getChequedate()));
              
              ld.add(row);
         }
        
          if(ld.size() > 0){
              frmi.getDetaildaily().convertSetListdetailrow(ld);
          }else{
              frmi.getDetaildaily().convertSetListdetailrow(new ArrayList<DetailDaily>());
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
