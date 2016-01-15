/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.util;

import com.scc.f1.util.Utils;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;

/**
 *
 * @author terex
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        String d = "25570102";System.out.println(d.substring(0, 6));
//        
//        DecimalFormat df2 = new DecimalFormat( "#,##0.00" );
//        
//        System.out.println(df2.format(new BigDecimal("12345")));
//        
//        System.out.println(nextDayEn("20150929",1));
//        for(int i=3;i>0;i--){
//            Calendar c = Calendar.getInstance();   
//            c.set(Integer.parseInt(Utils.getcurYear()), Integer.parseInt(Utils.getcurMonth())-1, 1);
//            c.add(Calendar.MONTH, -i);
//            
//            System.out.println(">>"+i+" : "+Utils.formatDateToStringToDBEn(c.getTime()));
//            
//        }
        
        
//        SimpleDateFormat sm = new SimpleDateFormat("yyyyMM");
//        Date date = sm.parse("201512");
//        System.out.println(new SimpleDateFormat("MMM yyyy").format(date.getTime()));
        
        System.out.println(new SimpleDateFormat("yyyyMMdd-HH-mm-ss").format(Utils.getcurDateTime().getTime()));
        
        
        Map<Integer, String> testMap = new TreeMap<Integer, String>();
        testMap.put(10, "a");
        testMap.put(20, "b");
        testMap.put(30, "c");
        testMap.put(40, "d");
        for (Entry<Integer, String> entry : testMap.entrySet()) {
            //if (entry.getValue().equals("c")) {
                System.out.println(entry.getKey());
            //}
        }

        System.out.println(new DateFormatSymbols().getMonths()[12-1]);

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
