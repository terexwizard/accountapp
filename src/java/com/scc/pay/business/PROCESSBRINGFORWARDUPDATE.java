/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.business.BusinessImpl;
import com.scc.f1.business.IBusinessBase;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Bringforward;
import com.scc.pay.db.Daily;
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
public class PROCESSBRINGFORWARDUPDATE extends BusinessImpl {    

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        HashMap<String,Object> vhm = (HashMap<String,Object>)inobj;
        
        String user = (String)vhm.get("user");
        String dailydate = (String)vhm.get("dailydate");
        Daily frmi = (Daily)vhm.get("form");
        String changeBank = (String)vhm.get("changeBank");
        logger.debug(">>processbringforwardupdate user:" + user);
        logger.debug(">>processbringforwardupdate dailydate:" + dailydate);
        logger.debug(">>processbringforwardupdate dailydate:" + frmi.getPayby());
        logger.debug(">>processbringforwardupdate changeBank:" + changeBank);
        
        
        processBringforward( frmi , dailydate,user,changeBank);

        
        return inobj;
    }
    
     private void processBringforward(Daily dbdaily ,String dailydate,String user,String changeBank){
         if(!Utils.NVL(dailydate).equals("")){
             //มีการแก้ไขวันที่
             if(!Utils.NVL(dailydate).equals(Utils.NVL(dbdaily.getDailydate())) || 
                     !Utils.NVL(dailydate).equals(Utils.formatDateToStringToDBEn(Utils.getcurDateTime()))){
                 
                 logger.debug(">>updateBringforwardByBankid if:");

                 //ลบแล้วคำนวณใหม่
                String sql = "delete FROM Bringforward r "
                        + "where r.bringforwardPK.bfdate >= :bfdate ";
                        //+ "where r.bringforwardPK.bfdate > :bfdate ";

                Query query = em.createQuery(sql);
                query.setParameter("bfdate",dailydate);
                query.executeUpdate();

                //==========================
                HashMap<String,String> vhm = new HashMap<String,String>();
                vhm.put("vuser", user);
                vhm.put("vbfdate", dailydate);

                IBusinessBase ib = BusinessFactory.getBusiness("PROCESSBRINGFORWARD");
                ib.processBackground(vhm);

             }else{

//>>terex comment 09/08/2560
//                String sql = "select r FROM Bringforward r "
//                        + "where r.bringforwardPK.bfdate = :bfdate and r.bringforwardPK.bankid = :bankid ";
//
//                Query query = em.createQuery(sql);
//                query.setParameter("bfdate",dailydate);
//                query.setParameter("bankid",new BigDecimal(dbdaily.getPayby()).intValue());
//
//                List<Bringforward> l = query.getResultList();
//
//                for(Bringforward db : l){
//
//                    String sqlpre = "select r FROM Bringforward r "
//                            + "where r.bringforwardPK.bfdate = :bfdate and r.bringforwardPK.bankid = :bankid";
//
//                    Query querypre = em.createQuery(sqlpre);
//                    String previousDay = CenterUtils.previousDayEn(dailydate,1);
//                    querypre.setParameter("bfdate",previousDay);
//                    querypre.setParameter("bankid",new BigDecimal(dbdaily.getPayby()).intValue());
//
//                    List<Bringforward> lpre = querypre.getResultList();
//                    Bringforward bringforward = new Bringforward();
//                    for(Bringforward dbpre : lpre){
//                        BeanUtil.copyProperties(bringforward, dbpre);
//                    }
//
//                    //=================================
//
//                    db.setReceived(countDailyReceived(dailydate,db.getBringforwardPK().getBankid()));
//                    db.setPaid(countDailyPaid(dailydate,db.getBringforwardPK().getBankid()));
//                    db.setBpchqrcv(countChequeClearDailyReceived(dailydate,db.getBringforwardPK().getBankid(),true));
//                    db.setBpchqpaid(countChequeClearDailyPaid(dailydate,db.getBringforwardPK().getBankid(),true));
//                    db.setBtchqrcv(countChequeClearDailyReceived(dailydate,db.getBringforwardPK().getBankid(),false));
//                    db.setBtchqpaid(countChequeClearDailyPaid(dailydate,db.getBringforwardPK().getBankid(),false));
//
//
//                    logger.debug("  getActualmoney:"+bringforward.getActualmoney()+
//                            "  getReceived:"+db.getReceived()+"  getBpchqrcv:"+db.getBpchqrcv()+
//                            " getBtchqpaid:"+db.getBtchqpaid()+" -getPaid:"+db.getPaid()+
//                            " -getBpchqpaid:"+db.getBpchqpaid()+" -getBtchqrcv:"+db.getBtchqrcv());
//                    Double actualmoney = (bringforward.getActualmoney()+db.getReceived()+db.getBpchqrcv()+db.getBtchqpaid()) - db.getPaid()-db.getBpchqpaid()-db.getBtchqrcv();
//
//                    db.setActualmoney(actualmoney);
//
//                    db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
//                    db.setUpdtime(Utils.getcurDateTime() );
//                    db.setUpduser(user);
//
//
//                    merge(db);
//
//
//                }
                
                 //รหัสตาม ธนาคาร ที่เปลี่ยนใหม่
                 updateBringforwardByBankid(new BigDecimal(dbdaily.getPayby()).intValue(),dailydate,user);
                 
                //คำนวณกรณีเปลี่ยนธนาคารหรือเงินสด
                if(!Utils.NVL(changeBank).equals("")){
                    logger.debug(">>updateBringforwardByBankid changeBank:"+changeBank);
                    updateBringforwardByBankid(Integer.parseInt(changeBank),dailydate,user);
                }
             }
         }
     }
     
     private void updateBringforwardByBankid(int bankid,String dailydate,String user){
         
                 logger.debug(">>updateBringforwardByBankid :"+bankid);
         
                String sql = "select r FROM Bringforward r "
                        + "where r.bringforwardPK.bfdate = :bfdate and r.bringforwardPK.bankid = :bankid ";

                Query query = em.createQuery(sql);
                query.setParameter("bfdate",dailydate);
                query.setParameter("bankid",bankid);

                List<Bringforward> l = query.getResultList();

                for(Bringforward db : l){

                    String sqlpre = "select r FROM Bringforward r "
                            + "where r.bringforwardPK.bfdate = :bfdate and r.bringforwardPK.bankid = :bankid";

                    Query querypre = em.createQuery(sqlpre);
                    String previousDay = CenterUtils.previousDayEn(dailydate,1);
                    querypre.setParameter("bfdate",previousDay);
                    querypre.setParameter("bankid",bankid);

                    List<Bringforward> lpre = querypre.getResultList();
                    Bringforward bringforward = new Bringforward();
                    for(Bringforward dbpre : lpre){
                        BeanUtil.copyProperties(bringforward, dbpre);
                    }

                    //=================================

                    db.setReceived(countDailyReceived(dailydate,db.getBringforwardPK().getBankid()));
                    db.setPaid(countDailyPaid(dailydate,db.getBringforwardPK().getBankid()));
                    db.setBpchqrcv(countChequeClearDailyReceived(dailydate,db.getBringforwardPK().getBankid(),true));
                    db.setBpchqpaid(countChequeClearDailyPaid(dailydate,db.getBringforwardPK().getBankid(),true));
                    db.setBtchqrcv(countChequeClearDailyReceived(dailydate,db.getBringforwardPK().getBankid(),false));
                    db.setBtchqpaid(countChequeClearDailyPaid(dailydate,db.getBringforwardPK().getBankid(),false));


                    logger.debug("  getActualmoney:"+bringforward.getActualmoney()+
                            "  getReceived:"+db.getReceived()+"  getBpchqrcv:"+db.getBpchqrcv()+
                            " getBtchqpaid:"+db.getBtchqpaid()+" -getPaid:"+db.getPaid()+
                            " -getBpchqpaid:"+db.getBpchqpaid()+" -getBtchqrcv:"+db.getBtchqrcv());
                    Double actualmoney = (bringforward.getActualmoney()+db.getReceived()+db.getBpchqrcv()+db.getBtchqpaid()) - db.getPaid()-db.getBpchqpaid()-db.getBtchqrcv();

                    db.setActualmoney(actualmoney);

                    db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
                    db.setUpdtime(Utils.getcurDateTime() );
                    db.setUpduser(user);


                    merge(db);


                }
     }
     
    
//    private void processBringforwardInsert(String vuser,int processdate){
//        
//        String sql = "select r FROM Bringforward r "
//                + "where r.bringforwardPK.bfdate = :bfdate ";
//
//        Query query = em.createQuery(sql);
//        query.setParameter("bfdate",Integer.toString(processdate));
//        
//        List<Bringforward> l = query.getResultList();
//        
//        for(Bringforward db : l){
//            
//            em.detach(db);
//            
//            //String nextbfdate = Integer.toString(processdate +1);
//            String nextbfdate = CenterUtils.nextDayEn(Integer.toString(processdate),1);
//            
//            db.getBringforwardPK().setBfdate(nextbfdate);
//            db.setReceived(countDailyReceived(nextbfdate,db.getBringforwardPK().getBankid()));
//            db.setPaid(countDailyPaid(nextbfdate,db.getBringforwardPK().getBankid()));
//            db.setBpchqrcv(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),true));
//            db.setBpchqpaid(countChequeClearDailyPaid(nextbfdate,db.getBringforwardPK().getBankid(),true));
//            db.setBtchqrcv(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),false));
//            db.setBtchqpaid(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),false));
//            
//            //Double actualmoney = (db.getActualmoney()+db.getReceived()) - db.getPaid();
//            Double actualmoney = (db.getActualmoney()+db.getReceived()+db.getBpchqrcv()+db.getBtchqpaid()) - db.getPaid()-db.getBpchqpaid()-db.getBtchqrcv();
//            db.setActualmoney(actualmoney);
//            
//            db.setUpdlcnt(1);
//            db.setUpdtime( Utils.getcurDateTime() );
//            db.setUpduser(vuser);
//                        
//            //==========================
//            //persist(db);
//            
//            Bringforward dbvn = em.find(Bringforward.class, db.getBringforwardPK());
//            if(dbvn == null){
//                persist(db);
//            }else{
//                
//                BeanUtil.copyProperties(dbvn, db);
//                
//                merge(dbvn);
//            }
//            
//        }
//    }
//    
//    
//     
//    private void processBringforwardUpdate(String vuser,int processdate){
//        
//        String sql = "select r FROM Bringforward r "
//                + "where r.bringforwardPK.bfdate = :bfdate ";
//
//        Query query = em.createQuery(sql);
//        query.setParameter("bfdate",Integer.toString(processdate));
//        
//        List<Bringforward> l = query.getResultList();
//        
//        for(Bringforward db : l){
//            
//            em.detach(db);
//            
//            //String nextbfdate = Integer.toString(processdate +1);
//            String nextbfdate = Integer.toString(processdate);
//            
//            db.getBringforwardPK().setBfdate(nextbfdate);
//            db.setReceived(countDailyReceived(nextbfdate,db.getBringforwardPK().getBankid()));
//            db.setPaid(countDailyPaid(nextbfdate,db.getBringforwardPK().getBankid()));
//            db.setBpchqrcv(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),true));
//            db.setBpchqpaid(countChequeClearDailyPaid(nextbfdate,db.getBringforwardPK().getBankid(),true));
//            db.setBtchqrcv(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),false));
//            db.setBtchqpaid(countChequeClearDailyReceived(nextbfdate,db.getBringforwardPK().getBankid(),false));
//            
//            //Double actualmoney = (db.getActualmoney()+db.getReceived()) - db.getPaid();
//            Double actualmoney = (db.getActualmoney()+db.getReceived()+db.getBpchqrcv()+db.getBtchqpaid()) - db.getPaid()-db.getBpchqpaid()-db.getBtchqrcv();
//            
//            db.setActualmoney(actualmoney);
//            
//            db.setUpdlcnt(1);
//            db.setUpdtime( Utils.getcurDateTime() );
//            db.setUpduser(vuser);
//                        
//            //==========================
//            //persist(db);
//            
//            Bringforward dbvn = em.find(Bringforward.class, db.getBringforwardPK());
//            if(dbvn == null){
//                persist(db);
//            }else{
//                
//                BeanUtil.copyProperties(dbvn, db);
//                
//                merge(dbvn);
//            }
//            
//        }
//    }
    
    
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
