/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.util;

import com.scc.f1.util.Utils;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
        
        
//        Map<Integer, String> testMap = new TreeMap<Integer, String>();
//        testMap.put(10, "a");
//        testMap.put(20, "b");
//        testMap.put(30, "c");
//        testMap.put(40, "d");
//        for (Entry<Integer, String> entry : testMap.entrySet()) {
//            //if (entry.getValue().equals("c")) {
//                System.out.println(entry.getKey());
//            //}
//        }

        System.out.println(new DateFormatSymbols().getMonths()[12-1]);
        
        
        getWeekofYear(2016, 2);
        
        System.out.println(Utils.convertDateStringToScreen(Utils.formatDateToStringToDBThai(Utils.getcurDateTime()),"/"));
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
    
    public static ArrayList<String> getWeekofYear(int yyyy,int mm){
        
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.set(Calendar.YEAR, yyyy);
            cal.set(Calendar.MONTH, (mm-1));
            
            System.out.println(cal.getTime());
            System.out.println(cal.getActualMaximum(Calendar.DATE));
            
            int maxdayformonth = cal.getActualMaximum(Calendar.DATE);
            TreeMap<String,String> tm = new TreeMap<String,String>();
            String weeks = "";
            int weeknum = 0;
            Date date = new Date();
            for(int i=1;i<=maxdayformonth;i++){
                
                try {
                    String d = Utils.NVL(i).length()==1?"0"+Utils.NVL(i):Utils.NVL(i);
                    String m = Utils.NVL(mm).length()==1?"0"+Utils.NVL(mm):Utils.NVL(mm);
                    String input = yyyy+m+d;
                    
                    //System.out.println(input);
                    String format = "yyyyMMdd";
                    SimpleDateFormat df = new SimpleDateFormat(format);
                    date = df.parse(input);
                    
                    Calendar calw = Calendar.getInstance();
                    calw.setTime(date);
                    
                    int week = calw.get(Calendar.WEEK_OF_YEAR);
                    //System.out.println(week);
                    
                    if(Utils.NVL(weeks).equals("")){
                        weeks = input;
                    }
                    
                    if(weeknum == 0){
                        weeknum = week;
                    }
                    
                    if(weeknum != week){
                        weeks += "-"+Utils.formatDateToStringToDBEn(CenterUtils.previousDayEn(date,1));
                        
                        tm.put(weeks, weeks);
                        weeks = "";
                        weeknum = 0;
                        
                        weeks = input;
                    }

                    
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
            
            weeks += "-"+Utils.formatDateToStringToDBEn(date);
            tm.put(weeks, weeks);
            
            
            System.out.println("====================");
            
            ArrayList<String> al = new ArrayList<String>();
            for (Map.Entry<String, String> entry : tm.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());                
                al.add(Utils.NVL(entry.getValue()));
            }
            
            
            return al;
    }
    
    
    
}
