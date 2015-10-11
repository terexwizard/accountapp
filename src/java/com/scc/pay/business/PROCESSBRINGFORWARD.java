/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Bringforward;
import com.scc.pay.util.CenterUtils;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class PROCESSBRINGFORWARD extends BusinessImpl {
    
    private int bfdate = 0;

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        HashMap<String,String> vhm = (HashMap<String,String>)inobj;
        
        String vuser = vhm.get("vuser");
        String vbfdate = vhm.get("vbfdate");
        logger.debug(">>" + vhm.get("vbfdate"));
        
        if(countBringforward(vbfdate)){
            bfdate = Integer.parseInt(vbfdate);
            checkLastBringforward();
            processBringforward(vuser);
        }
        
        return inobj;
    }
    
    private boolean countBringforward(String dailydate){
        
        String previousDay = CenterUtils.previousDayEn(dailydate,1);
        Long count = checkLastDataBringforward(previousDay);
        
        if(count == 0){
            return true;
        }else{
            return false;
        }
    }
    
    private void checkLastBringforward(){                
        
        logger.debug(">>processBringforward :"+bfdate);
        
        Long count = checkLastDataBringforward(Integer.toString(bfdate));
        
        if(count == 0){
            bfdate--;
            checkLastBringforward();
        }else{
            logger.debug(">>processBringforward finish:"+bfdate);                       
        }
    }
    
    
    private void processBringforward(String vuser){   
        if(isDateValid(Utils.NVL(bfdate))){
            processBringforwardInsert(vuser,bfdate);
            
            if(bfdate < (Integer.parseInt(Utils.getcurDateDB(false))-2)){ //ไม่ทำวันที่ ปัจจุบัน -2
                bfdate = Integer.parseInt(CenterUtils.nextDayEn(Integer.toString(bfdate),1));
                
                processBringforward(vuser);
            }
        }
    }
    
    private Long checkLastDataBringforward(String bfdate){
        String sql = "select count(r.bringforwardPK.bfdate) FROM Bringforward r "
                + "where r.bringforwardPK.bfdate = :bfdate ";

        Query query = em.createQuery(sql);
        query.setParameter("bfdate",bfdate);
        
        Long count = (Long)query.getSingleResult();
        logger.debug(">>processBringforward "+bfdate +" // "+count);
        
        return count;
    }
    
    
    private void processBringforwardInsert(String vuser,int processdate){
        
        String sql = "select r FROM Bringforward r "
                + "where r.bringforwardPK.bfdate = :bfdate ";

        Query query = em.createQuery(sql);
        query.setParameter("bfdate",Integer.toString(processdate));
        
        List<Bringforward> l = query.getResultList();
        
        for(Bringforward db : l){
            
            em.detach(db);
            
            //String nextbfdate = Integer.toString(processdate +1);
            String nextbfdate = CenterUtils.nextDayEn(Integer.toString(processdate),1);
            
            db.getBringforwardPK().setBfdate(nextbfdate);
            db.setReceived(countDailyReceived(nextbfdate,db.getBringforwardPK().getBankid()));
            db.setPaid(countDailyPaid(nextbfdate,db.getBringforwardPK().getBankid()));
            
            Double actualmoney = (db.getActualmoney()+db.getReceived()) - db.getPaid();
            db.setActualmoney(actualmoney);
            
            db.setUpdlcnt(1);
            db.setUpdtime( Utils.getcurDateTime() );
            db.setUpduser(vuser);
            
            persist(db);
            
        }
    }
    
    
    private Double countDailyReceived(String dailydate,int payby){
        String sql = "select ";
               if(payby == 2){
                   sql += "sum(r.amount) FROM Daily r ";
               }else{
                   sql += "sum(r.receivedamount) FROM Daily r ";
               }
               sql += "where r.dailydate = :dailydate and r.payby = :payby";

        Query query = em.createQuery(sql);
        query.setParameter("dailydate",dailydate);
        query.setParameter("payby",new BigDecimal(payby).doubleValue());
        Double sum = (Double)query.getSingleResult();
        
        logger.debug(">>countDailyReceived "+sum);
        
        if(sum == null){
            return new Double("0");
        }else{        
            return sum;
        }
    }
    
    private Double countDailyPaid(String dailydate,int payby){
        
                String sql = "select ";
                    if(payby == 2){
                      sql += "sum(r.amount2) FROM Daily r ";
                  }else{
                      sql += "sum(r.paidamount) FROM Daily r ";
                  }
                        
                sql += "where r.dailydate = :dailydate and r.payby = :payby";

        Query query = em.createQuery(sql);
        query.setParameter("dailydate",dailydate);
        query.setParameter("payby",new BigDecimal(payby).doubleValue());
        Double sum = (Double)query.getSingleResult();
        
        logger.debug(">>countDailyPaid "+sum);
        
        if(sum == null){
            return new Double("0");
        }else{        
            return sum;
        }
    }
    
    public static boolean isDateValid(String date) 
    {
            try {
                DateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
                df.setLenient(false);
                df.parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
    }
    
 
}
