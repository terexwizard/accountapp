/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.pay.bkbean.ATP010100;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.db.Bringforward;
import com.scc.pay.db.TbBank;
import com.scc.pay.util.CenterUtils;
import javax.persistence.Query;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP010100A extends BusinessImpl {
    
    private String user = "";

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP010100 frmi = (ATP010100)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        user = frmi.getUserid();
        
        TbBank record = frmi.getMasterdata().getTbbank();
        
        
        record.setEntuser(frmi.getUserid());
        record.setEnttime(Utils.getcurDateTime());
        record.setUpdlcnt(1);
        record.setUpdtime( Utils.getcurDateTime() );
        record.setUpduser(frmi.getUserid());
        
        persist(record);
        
        
        logger.debug(">>checkMinDataBringforward "+record.getBankid());
        
        processBringforward(record.getBankid());

        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void processBringforward(int bankid){
        String min = checkMinDataBringforward();
        String max = checkMaxDataBringforward();
        
        checkBringforward(bankid,min,max);
    }
    
    private void checkBringforward(int bankid,String min,String max){
        if(!Utils.NVL(min).equals(Utils.NVL(max))){
            insertBringforward(bankid,min);
            
            checkBringforward(bankid,CenterUtils.nextDayEn(min, 1),max);
        }else{
            insertBringforward(bankid,min);
        }
    }
    
    private void insertBringforward(int bankid,String min){
            Bringforward db = new Bringforward(min, bankid);
            
            db.setPaid(0.0);
            db.setReceived(0.0);
            db.setBpchqrcv(0.0);
            db.setBpchqpaid(0.0);
            db.setBtchqrcv(0.0);
            db.setBtchqpaid(0.0);
            db.setActualmoney(0.0);
            db.setEntuser(user);
            db.setEnttime(Utils.getcurDateTime());
            db.setUpdlcnt(1);
            db.setUpdtime( Utils.getcurDateTime() );
            db.setUpduser(user);

            persist(db);
    }
    
    private String checkMinDataBringforward(){
        String sql = "select min(r.bringforwardPK.bfdate) as mindate FROM Bringforward r ";

        Query query = em.createQuery(sql);
        
        String mindate = (String)query.getSingleResult();
        logger.debug(">>checkMinDataBringforward "+mindate);
        
        return mindate;
    }
    
    private String checkMaxDataBringforward(){
        String sql = "select max(r.bringforwardPK.bfdate) as maxdate FROM Bringforward r ";

        Query query = em.createQuery(sql);
        
        String maxdate = (String)query.getSingleResult();
        logger.debug(">>checkMaxDataBringforward "+maxdate);
        
        return maxdate;
    }
}
