/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Bringforward;
import com.scc.pay.db.TbBank;
import com.scc.pay.util.CenterUtils;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
        logger.debug(">>processBringforward date:" + vhm.get("vbfdate"));
        
        //if(countBringforward(vbfdate)){
            bfdate = Integer.parseInt(vbfdate);
            
           
             if(!Utils.NVL(vbfdate).equals("")){
                //มีการแก้ไขวันที่
                if(!Utils.NVL(vbfdate).equals(Utils.formatDateToStringToDBEn(Utils.getcurDateTime()))){

                    //ลบแล้วคำนวณใหม่
                   String sql = "delete FROM Bringforward r "
                           + "where r.bringforwardPK.bfdate >= :bfdate ";
                           //+ "where r.bringforwardPK.bfdate > :bfdate ";

                   Query query = em.createQuery(sql);
                   query.setParameter("bfdate",vbfdate);
                   query.executeUpdate();
                }
             }
            
            
            
            checkLastBringforward();
            processBringforward(vuser);
        //}
        
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
            //bfdate--;
            bfdate = Integer.parseInt(CenterUtils.previousDayEn(Integer.toString(bfdate), 1));
            checkLastBringforward();
        }else{
            logger.debug(">>processBringforward finish:"+bfdate);                       
        }
    }
    
    
    private void processBringforward(String vuser){   
        if(isDateValid(Utils.NVL(bfdate))){
            
//             if(bfdate < (Integer.parseInt(Utils.getcurDateDB(false))-2)){ //ไม่ทำวันที่ ปัจจุบัน -2
//                bfdate = Integer.parseInt(CenterUtils.nextDayEn(Integer.toString(bfdate),1));
//                
//                processBringforward(vuser);
//            }
            logger.debug(">>processBringforward loop:"+bfdate+" // "+Utils.getcurDateDB(false));
            if(bfdate == (Integer.parseInt(Utils.getcurDateDB(false)))){
                processBringforwardUpdate(vuser,bfdate);
            }else{
            
                processBringforwardInsert(vuser,bfdate);

                //if(bfdate < (Integer.parseInt(Utils.getcurDateDB(false))-2)){ //ไม่ทำวันที่ ปัจจุบัน -2
                if(bfdate < (Integer.parseInt(Utils.getcurDateDB(false))-1)){ //ทำถึงวันที่ ปัจจุบัน -1
                    bfdate = Integer.parseInt(CenterUtils.nextDayEn(Integer.toString(bfdate),1));

                    processBringforward(vuser);
                }
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
            
            logger.debug(">>processBringforwardInsert "+processdate+" "+nextbfdate);
            
            db.getBringforwardPK().setBfdate(nextbfdate);
            db.setReceived(countDailyReceived(nextbfdate,db.getBringforwardPK().getBankid()));
            db.setPaid(countDailyPaid(nextbfdate,db.getBringforwardPK().getBankid()));
            db.setBpchqrcv(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),true));
            db.setBpchqpaid(countChequeClearDailyPaid(nextbfdate,db.getBringforwardPK().getBankid(),true));
            db.setBtchqrcv(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),false));
            db.setBtchqpaid(countChequeClearDailyPaid(nextbfdate,db.getBringforwardPK().getBankid(),false));
            
            if(db.getBringforwardPK().getBankid() == 3){
                logger.debug(">>processBringforwardInsert terex "+nextbfdate+" // "+db.getActualmoney());
                logger.debug(">>processBringforwardInsert terex "+nextbfdate+" // getActualmoney:"+db.getActualmoney()+
                        " // getReceived:"+db.getReceived()+" // db.getBpchqrcv:"+db.getBpchqrcv()+
                        " // getBtchqpaid:"+db.getBtchqpaid()+" // getPaid:"+db.getPaid()
                        +" // getBpchqpaid:"+db.getBpchqpaid()+" // getBtchqrcv:"+db.getBtchqrcv());
            }
            
            //Double actualmoney = (db.getActualmoney()+db.getReceived()) - db.getPaid();
            Double actualmoney = (db.getActualmoney()+db.getReceived()+db.getBpchqrcv()+db.getBtchqpaid()) - db.getPaid()-db.getBpchqpaid()-db.getBtchqrcv();
            db.setActualmoney(actualmoney);
                        
            db.setUpdlcnt(1);
            db.setUpdtime( Utils.getcurDateTime() );
            db.setUpduser(vuser);
                        
            //==========================
            //persist(db);
            
            Bringforward dbvn = em.find(Bringforward.class, db.getBringforwardPK());
            if(dbvn == null){
                persist(db);
            }else{
                
                BeanUtil.copyProperties(dbvn, db);
                
                merge(dbvn);
            }
            
        }
    }
    
    
     
    private void processBringforwardUpdate(String vuser,int processdate){
        
        logger.debug(">>processBringforwardUpdate "+processdate);
        
        String sql = "select r FROM Bringforward r "
                + "where r.bringforwardPK.bfdate = :bfdate ";

        Query query = em.createQuery(sql);
        query.setParameter("bfdate",Integer.toString(processdate));
        
        List<Bringforward> l = query.getResultList();
        
        for(Bringforward db : l){
            
            em.detach(db);
            
            
            String sqlpre = "select r FROM Bringforward r "
                    + "where r.bringforwardPK.bfdate = :bfdate and r.bringforwardPK.bankid = :bankid";

            Query querypre = em.createQuery(sqlpre);
            String previousDay = CenterUtils.previousDayEn(Integer.toString(processdate), 1);
            querypre.setParameter("bfdate",previousDay);
            querypre.setParameter("bankid",new BigDecimal(db.getBringforwardPK().getBankid()));

            List<Bringforward> lpre = querypre.getResultList();
            Bringforward bringforward = new Bringforward();
            for(Bringforward dbpre : lpre){
                BeanUtil.copyProperties(bringforward, dbpre);
            }

            //=================================
            
            //String nextbfdate = Integer.toString(processdate +1);
            String nextbfdate = Integer.toString(processdate);
            
            db.getBringforwardPK().setBfdate(nextbfdate);
            db.setReceived(countDailyReceived(nextbfdate,db.getBringforwardPK().getBankid()));
            db.setPaid(countDailyPaid(nextbfdate,db.getBringforwardPK().getBankid()));
            db.setBpchqrcv(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),true));
            db.setBpchqpaid(countChequeClearDailyPaid(nextbfdate,db.getBringforwardPK().getBankid(),true));
            db.setBtchqrcv(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),false));
            db.setBtchqpaid(countChequeClearDailyPaid(nextbfdate,db.getBringforwardPK().getBankid(),false));
            
            //Double actualmoney = (db.getActualmoney()+db.getReceived()) - db.getPaid();
            //Double actualmoney = (db.getActualmoney()+db.getReceived()+db.getBpchqrcv()+db.getBtchqpaid()) - db.getPaid()-db.getBpchqpaid()-db.getBtchqrcv();
            logger.debug("  getActualmoney:"+bringforward.getActualmoney()+
                            "  getReceived:"+db.getReceived()+"  getBpchqrcv:"+db.getBpchqrcv()+
                            " getBtchqpaid:"+db.getBtchqpaid()+" -getPaid:"+db.getPaid()+
                            " -getBpchqpaid:"+db.getBpchqpaid()+" -getBtchqrcv:"+db.getBtchqrcv());
            Double actualmoney = (bringforward.getActualmoney()+db.getReceived()+db.getBpchqrcv()+db.getBtchqpaid()) - db.getPaid()-db.getBpchqpaid()-db.getBtchqrcv();
            
            db.setActualmoney(actualmoney);
            
            db.setUpdlcnt(1);
            db.setUpdtime( Utils.getcurDateTime() );
            db.setUpduser(vuser);
                        
            //==========================
            //persist(db);
            
            Bringforward dbvn = em.find(Bringforward.class, db.getBringforwardPK());
            if(dbvn == null){
                persist(db);
            }else{
                
                BeanUtil.copyProperties(dbvn, db);
                
                merge(dbvn);
            }
            
        }
    }
    
    
    private Double countDailyReceived(String dailydate,int payby){
        String sql = "select ";
               //if(payby == 2){
               if(checkmonetaryusd(payby)){
                   sql += "sum(r.amount) FROM Daily r ";
               }else{
                   sql += "sum(r.receivedamount) FROM Daily r ";
               }
               sql += "where r.dailydate = :dailydate and r.payby = :payby ";
                       //+ "and r.cheque = :cheque";

        Query query = em.createQuery(sql);
        query.setParameter("dailydate",dailydate);
        query.setParameter("payby",new BigDecimal(payby).doubleValue());
        //query.setParameter("cheque","false");
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
                  //if(payby == 2){
                 if(checkmonetaryusd(payby)){
                      sql += "sum(r.amount2) FROM Daily r ";
                  }else{
                      sql += "sum(r.paidamount) FROM Daily r ";
                  }
                        
                sql += "where r.dailydate = :dailydate and r.payby = :payby ";
                        //+ "and r.cheque = :cheque";

        Query query = em.createQuery(sql);
        query.setParameter("dailydate",dailydate);
        query.setParameter("payby",new BigDecimal(payby).doubleValue());
        //query.setParameter("cheque","false");
        Double sum = (Double)query.getSingleResult();
        
        logger.debug(">>countDailyPaid "+sum);
        
        if(sum == null){
            return new Double("0");
        }else{        
            return sum;
        }
    }
    
 
    private Double countChequeClearDailyReceived(String dailydate,int payby,boolean vclear){
        String sql = "select ";
               //if(payby == 2){
               if(checkmonetaryusd(payby)){
                   sql += "sum(r.amount) FROM Daily r ";
               }else{
                   sql += "sum(r.receivedamount) FROM Daily r ";
               }               
               
               if(vclear){
                   sql += "where r.chequedate = :chequedate and r.payby = :payby ";
                   sql += "and r.chequedate is not null "; //cheque clear
               }else{
                   sql += "where r.dailydate = :dailydate and r.payby = :payby ";
                   sql += "and r.chequedate is null "; //cheque not clear
               }
               
               sql += "and r.cheque = :cheque ";

        Query query = em.createQuery(sql);
        if(vclear){
            query.setParameter("chequedate",dailydate);
        }else{
            query.setParameter("dailydate",dailydate);
        }
        
        query.setParameter("payby",new BigDecimal(payby).doubleValue());
        query.setParameter("cheque","true");
        Double sum = (Double)query.getSingleResult();
        
        logger.debug(">>countChequeClearDailyReceived "+sum);
        
        if(sum == null){
            return new Double("0");
        }else{        
            return sum;
        }
    }
    
    private Double countChequeClearDailyPaid(String dailydate,int payby,boolean vclear){
        
                String sql = "select ";
                  //if(payby == 2){
                 if(checkmonetaryusd(payby)){
                      sql += "sum(r.amount2) FROM Daily r ";
                  }else{
                      sql += "sum(r.paidamount) FROM Daily r ";
                  }                                              

                if(vclear){
                   sql += "where r.chequedate = :chequedate and r.payby = :payby ";
                   sql += "and r.chequedate is not null "; //cheque clear
                }else{
                   sql += "where r.dailydate = :dailydate and r.payby = :payby ";
                   sql += "and r.chequedate is null "; //cheque not clear
                }
                
                sql += "and r.cheque = :cheque ";
                
        Query query = em.createQuery(sql);
        if(vclear){
            query.setParameter("chequedate",dailydate);
        }else{
            query.setParameter("dailydate",dailydate);
        }
        query.setParameter("payby",new BigDecimal(payby).doubleValue());
        query.setParameter("cheque","true");
        Double sum = (Double)query.getSingleResult();
        
        logger.debug(">>countChequeClearDailyPaid "+sum);
        
        if(sum == null){
            return new Double("0");
        }else{        
            return sum;
        }
    }         
    
    private boolean checkmonetaryusd(int payby){
        
        
        TbBank db = em.find(TbBank.class, payby);
        if(db != null){
            if(db.getMonetaryusd().equals("true")){
                return true;
            }
            
            return false;
        }
        return false;
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
