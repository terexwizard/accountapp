/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.util;

import com.scc.f1.util.Utils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author terex
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String d = "25570102";
        
        DecimalFormat df2 = new DecimalFormat( "#,##0.00" );
        
        System.out.println(df2.format(new BigDecimal("12345")));
        
        System.out.println(nextDayEn("20150929",1));
    }
    
    public static String nextDayEn(String d,Integer i){  
        
        Date date = null; 
 
        Calendar c = Calendar.getInstance();
        
        c.setTime(Utils.formatStringToDateToScreen(d));
        c.add(Calendar.DATE, +i);
  
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
        
        try {
            date = sm.parse(Utils.formatDateToStringToDBThai(c.getTime())); 
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CenterUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        System.out.println(">>"+date);
        return Utils.formatDateToStringToDBEn(date);
 
    }
}
