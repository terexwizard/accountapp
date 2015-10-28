/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.taglib;

import com.scc.f1.util.Utils;
//import com.scc.nstda.rdconline.util.CenterUtils;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author terex
 */
public final class Functions {
    private Functions() {}


    public static String convertIntToScreen(Integer value){
        
        return Utils.NVL(value);
    }
    
    public static String formatMoney(Object mn){
        
        DecimalFormat df2 = new DecimalFormat( "#,##0.00" );
        
        if(mn==null){
            return "0.00";
        }else{
            return df2.format(mn);
        }
        
    }
    
    public static String formatDecimal(Object mn){
        
        DecimalFormat df2 = new DecimalFormat( "#,##0" );
        
        if(mn==null){
            return "0";
        }else{
            return df2.format(mn);
        }
        
    }
    
    public static String formatDecimalString(Object mn){
        
        if(Utils.NVL(mn).equals("")){
          return "0";  
        }else{
            return formatDecimal(Integer.parseInt(Utils.NVL(mn)));
        }
   
    }
    
    public static String convertPinTinToScreen(Object value){
        String NIDFormat = "";
        try{
             
            if(value == null){
                NIDFormat = "";
            }else{
                if(value.toString().length()== 10){
                    String PIN = ((String) value);
                    NIDFormat = PIN.substring(0, 1) + "-" + PIN.substring(1, 5) + "-" + 
                                PIN.substring(5, 9) + "-" + PIN.substring(9, 10);  
                }else if(value.toString().length()== 13){
                    String TIN = ((String) value); 
                    NIDFormat = TIN.substring(0, 1) + "-" + 
                                TIN.substring(1, 5) + "-" +
                                TIN.substring(5, 10) + "-" + 
                                TIN.substring(10, 12)+ "-" + 
                                TIN.substring(12);  
                }
            }
            return NIDFormat;
        }catch (Exception e){
            return NIDFormat;
        }
        
    }
    
//    public static String formatformGenSeqid(Object v){
//       
//        if(v==null){
//            return "";
//        }else{
//            
//            if(Utils.NVL(v).equals("")){
//                return "";
//            }else{
//                return CenterUtils.genRunno(v);
//            }
//        }
//        
//    }
    
    public static String convertDateTimeToScreen(Date dt) {
        if(dt != null){
            
            String date = Utils.convertDateStringToScreen(Utils.formatDateToStringToDBThai(dt),"/");
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm" , Locale.ENGLISH);
            dateFormat.setLenient(false);

            //return date+" "+dateFormat.format(dt);
            return date;
        }else{
            return "";
        }
    }
        
    public static String convertDateStringToScreen(String idt){
        return Utils.convertDateStringToScreen(idt, "/");
    }
    
    
}
