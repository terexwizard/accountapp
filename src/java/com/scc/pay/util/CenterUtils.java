/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.util;

import com.scc.f1.Constant;
import com.scc.f1.dbutil.DBUtils;
import com.scc.f1.dbutil.QueryXML;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.BKBListData;
import com.scc.pay.db.Daily;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author terex
 */
public class CenterUtils{
    
    private static final Logger logger = Logger.getLogger(CenterUtils.class);
    
    public static List selectData(HashMap<String, String> hm,String trancode){
        List l = null;
        try{
            
            l = searchData(hm,trancode,1,0);
            
        }finally{
              
          }
        
        return l;
    }
    
    public static List searchData(HashMap<String, String> hm,String trancode,int connection ,int counttype){
        
          List l = null;
          try{
              
            String sql  = QueryXML.createSQL(trancode, hm);
            
            l = DBUtils.queryDataSPU(sql,DBUtils.getPUName(connection),counttype);

          }catch(Exception e){
              logger.debug("searchData Exception");
              return new ArrayList();
          }finally{
              
          }
        
        return l;
    }
    
    public static String cutFilename(String filename){
        
        String name ="";
        
        if(!Utils.NVL(filename).equals("")){
            
            StringTokenizer st = new StringTokenizer(filename, "\\"); 
            while(st.hasMoreTokens()) {
                name = st.nextToken();
            }
            
        }
        
        return name;
    } 
     
    public static int getRowIndex(String detailname,String cid){
        
        int rid = -1;
        
        String[] carr = cid.split(":");
        String  ind   = "-1";
        
        for(int i = 0; i< carr.length ; i++){
            if(carr[i].equals(detailname+"_table")){
                ind = carr[i+1];
                break;
            }
        }
        
        rid = Integer.valueOf(ind);
        
        return rid;
    } 
    
    public static String checkPinTin(String valPinTin){
        
        String val = valPinTin;
        if(val.length() == 10){
            
            if(!isTINValid(val)){
                return "Y";
            } else{
                return "";
            }
           
        } else if(val.length() == 13){
             if(!isPin(val)){
                 return "Y";
             } else{
                return "";
             }
             
            
        }else{
             return "Y";
        }
    }
    
    public static boolean isTINValid(String sTINr) {
        String sTIN = sTINr . replace ( "-", "" );
        if (sTIN.length() != 10)
                return false;

        if (Double.parseDouble(sTIN) == 0)
                return false;

        int k = 1;
        int intCal = 0;
        for (int i=0; i<9; i++) {
                if (i == 0 || i == 2 || i ==4 || i== 6 || i ==8)
                        intCal += Integer.parseInt(sTIN.substring(i,k)) * 3;
                else 
                        intCal += Integer.parseInt(sTIN.substring(i,k)) * 1;
                k++;
        }

        if (intCal != 0) {
                String sCal = String.valueOf(intCal);
                intCal = Integer.parseInt(sCal.substring(sCal.length() - 1));
                if (intCal != 0)
                    intCal =  10 - Integer.parseInt(sCal.substring(sCal.length() - 1));
        }

        if (intCal != Integer.parseInt(sTIN.substring(9))) 
            return false;

        return true;

    }
    
    public static boolean isPin(String av_pin){
        String lv_pin = av_pin . replace ( "-", "" );
        if( lv_pin . length() != 13){
            return false;
        }
        if( lv_pin.equals("0000000000000")){
            return true;
        }

        int lv_count_j = 0;
        int lv_digit;
        int ln_cal = 0;
        for (int count_i = 13; count_i > 1; count_i-- , lv_count_j++){
            lv_digit = Integer.parseInt(String.valueOf(lv_pin.charAt(lv_count_j))) * count_i;
            ln_cal += lv_digit;
        }
            lv_digit = 11 - (ln_cal % 11);
        String lv_digitr = Integer.toString(lv_digit);    
        int lv_chr_digit = lv_digitr.charAt(lv_digitr.length()-1);
        if (lv_pin.charAt(12) == lv_chr_digit){
            return true;
        }else{
            return false;
        }  
    } 
 
    
    public static String convertPinTin(String code){
        
        String result = "";
        if(code.length() == 10){
            if(!Utils.NVL(code).equals("")){
                String PIN =  code;

                result = PIN.substring(0, 1) + "-" + PIN.substring(1, 5) + "-" + 
                         PIN.substring(5, 9) + "-" + PIN.substring(9, 10); 

                logger.debug(">>format 10 หลัก "+result);
            }
        } else if(code.length() == 13){
            if(!Utils.NVL(code).equals("")){
                 String TIN = code; 
                 result = TIN.substring(0, 1) + "-" + 
                             TIN.substring(1, 5) + "-" +
                             TIN.substring(5, 10) + "-" + 
                             TIN.substring(10, 12)+ "-" + 
                             TIN.substring(12);
                 logger.debug(">>format 13 หลัก "+result);
            }
            
        }
        return result;
        
    }
    
    public static String getThaiMonth(int intMonth, int opt) {
            Hashtable tblMonth = new Hashtable();
            switch (opt) {
                    case 0:
                            tblMonth.put("1", "มกราคม");
                            tblMonth.put("2", "กุมภาพันธ์");
                            tblMonth.put("3", "มีนาคม");
                            tblMonth.put("4", "เมษายน");
                            tblMonth.put("5", "พฤษภาคม");
                            tblMonth.put("6", "มิถุนายน");
                            tblMonth.put("7", "กรกฎาคม");
                            tblMonth.put("8", "สิงหาคม");
                            tblMonth.put("9", "กันยายน");
                            tblMonth.put("10", "ตุลาคม");
                            tblMonth.put("11", "พฤศจิกายน");
                            tblMonth.put("12", "ธันวาคม");
                            break;
                    case 1:
                            tblMonth.put("1", "ม.ค.");
                            tblMonth.put("2", "ก.พ.");
                            tblMonth.put("3", "มี.ค.");
                            tblMonth.put("4", "เม.ย.");
                            tblMonth.put("5", "พ.ค.");
                            tblMonth.put("6", "มิ.ย.");
                            tblMonth.put("7", "ก.ค.");
                            tblMonth.put("8", "ส.ค.");
                            tblMonth.put("9", "ก.ย.");
                            tblMonth.put("10", "ต.ค.");
                            tblMonth.put("11", "พ.ย.");
                            tblMonth.put("12", "ธ.ค.");
                            break;
                    default:
            }

           logger.debug(">>getThaiMonth "+intMonth); 
            
            return tblMonth.get(String.valueOf(intMonth)).toString();


    }  
    
     public static String getENMonth(int intMonth, int opt) {
            Hashtable tblMonth = new Hashtable();
            switch (opt) {
                    case 0:
                            tblMonth.put("1", "January");
                            tblMonth.put("2", "February");
                            tblMonth.put("3", "March");
                            tblMonth.put("4", "April");
                            tblMonth.put("5", "May");
                            tblMonth.put("6", "June");
                            tblMonth.put("7", "July");
                            tblMonth.put("8", "August");
                            tblMonth.put("9", "September");
                            tblMonth.put("10", "October");
                            tblMonth.put("11", "November");
                            tblMonth.put("12", "December");
                            break;
                    case 1:
                            tblMonth.put("1", "ม.ค.");
                            tblMonth.put("2", "ก.พ.");
                            tblMonth.put("3", "มี.ค.");
                            tblMonth.put("4", "เม.ย.");
                            tblMonth.put("5", "พ.ค.");
                            tblMonth.put("6", "มิ.ย.");
                            tblMonth.put("7", "ก.ค.");
                            tblMonth.put("8", "ส.ค.");
                            tblMonth.put("9", "ก.ย.");
                            tblMonth.put("10", "ต.ค.");
                            tblMonth.put("11", "พ.ย.");
                            tblMonth.put("12", "ธ.ค.");
                            break;
                    default:
            }

           logger.debug(">>getENMonth "+intMonth); 
            
            return tblMonth.get(String.valueOf(intMonth)).toString();


    }  
    
    public static String formatDateToStringShowTime(Date dt){
        
        if(dt==null){
            return "";
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
        dateFormat.setLenient(false);
        
        String date = Utils.convertDateStringToScreen(Utils.formatDateToStringToDBEn(dt),"/");
        String time = dateFormat.format(dt);
        
        return date+" at "+time+" ";
    }
    
     public static boolean checkPasswordASCII(String s){ 
        boolean  b = false;
        boolean  w = false;
        boolean  x = false;
        boolean  y = false;
        boolean  z = false; 
        int ss = s.length();  
        int v = 0;
         
        for(int i=0;i<ss;i++){  
          v = (int) s.charAt(i);
          if ( (v >= 33 && v <= 47) || (v >= 58 && v <= 64) 
            || (v >= 92 && v <= 96) || (v >= 123 && v <= 126)){ w = true;} 
          if ( (v >= 48 && v <= 57) ){ x = true; }
          if ( (v >= 65 && v <= 90) ){ y = true; }
          if ( (v >= 97 && v <= 122) ){ z = true; } 
          if (w==true && x==true && y==true && z==true){b=true;}
        }  
        return b;
    } 
     
    public static String padLeft(String input,String spad,int numpad) {
            String out = genStringForPad(input,spad,numpad);
            return out + input;
    }
    
    public static String genStringForPad(String input,String spad,int numpad) {
            int leninput,lenspad,length;
            leninput = input.length();
            lenspad = spad.length();
            String out = "";
            if (((leninput + lenspad) > numpad) || (leninput > numpad) || (lenspad < 1)) {
                    return out;
            }
            if (lenspad > 1) {
                    if (((numpad - leninput)%lenspad) != 0) {
                            return out;
                    }
            }
            length = (numpad - leninput)/lenspad;
            for (int i = 1; i <= length; i++) {
                    out += spad;
            }
            return out;
    }
    
    public static Date getcurDate(){
         Date d = Utils.getcurDateTime();
           d.setHours(0); 
           d.setMinutes(0);
           d.setSeconds(0);
         return d;           
    }
        
    public static List<String> readFile(String pathfile)
    {

        BufferedReader br = null;
        List<String> al = new ArrayList<String>();
        
        try {
            
            br = new BufferedReader(new FileReader(pathfile));
            String sCurrentLine; 
            while ((sCurrentLine = br.readLine()) != null) {
                al.add(sCurrentLine);
            }
   
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }finally{
            try {
                  if (br != null){
                      br.close();
                  }
            } catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
        
        return al;  
   }
    
   public static void writeFile(String pathfile,List<String> data)
   {
        Charset charset = Charset.forName("UTF-8");

//        File path = new File(pathfile);
//        try (
//            BufferedWriter writer = Files.newBufferedWriter(path.toPath(), charset)) {
//            for(String s : data){
//                if(!Utils.NVL(s).equals("")){
//                    s = s+"\r\n";
//                    writer.write(s, 0, s.length());   
//                }
//            }
//        } catch (IOException x) {
//            x.printStackTrace();
//        } 
        
        
        
        try{
            System.out.println(">>>writeFile >>>>" + pathfile );
            File file = new File(pathfile);

//            if (!file.exists()) {
//                 file.createNewFile();
//            }
//
//            FileWriter fw = new FileWriter(file.getAbsoluteFile());
//            BufferedWriter bw = new BufferedWriter(fw);
//            
//            for(String s : data){
//                bw.write(s+"\r\n");
//            }
//            bw.close();
            
            
            Writer writer = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(file), "UTF8"));
            for(String s : data){
                writer.append(s).append("\r\n");
            }

            writer.flush();
            writer.close();
            
            
        }catch (IOException ex) {
                
        }
        
   }
    
   private static String UnicodeToMS874( String _in){ 
            StringBuffer strTemp = new StringBuffer( _in ); 
            int code; 
            for( int i = 0; i < _in.length(); i++){ 
                    code = (int) strTemp.charAt(i); 
                    if ( ( 0xE01 <= code ) && ( code <= 0xE5B ) ){ 
                            strTemp.setCharAt( i, (char) ( code - 0xD60 ) ); 
                    } 
            } 
            return strTemp.toString(); 
    } 
    
    public static String createFolderRealPath(String create_path){
//        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
//        HttpSession session = (HttpSession) extContext.getSession(false);
//        String session_id = session.getId();
//
//        String pathFile = Constant.context_realpath+"/"+create_path+"/"+Utils.getcurDateDB()+"/"+session_id.replace("!", "");
        String pathFile = Constant.context_realpath+"/"+create_path+"/"+Utils.getcurDateDB();
        System.err.println(">>>createFolderRealPath >>>>" + pathFile );
        File result = new File(pathFile);
        
        if (!result.exists()){
            System.err.println(" pathfile >>>>" + pathFile );
            if (!result.mkdirs()){
                 System.err.println(" Error Create Directory ");
            }
        }else{
            System.err.println("Directory Already exists");
        }
        
        return pathFile;
   }
 
    
    public static Date nextDay(Date d,Integer i){  
        
        Date date = null; 
 
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        
        c.setTime(d);
        c.add(Calendar.DATE, +i);
  
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        
        try {
            date = sm.parse(Utils.formatDateToStringToDBThai(c.getTime())); 
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CenterUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return date;
 
    }
        
    public static Date nextDayEn(Date d,Integer i){  
        
        Date date = null; 
 
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        
        c.setTime(d);
        c.add(Calendar.DATE, +i);
  
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        
        try {
            date = sm.parse(Utils.formatDateToStringToDBEn(c.getTime())); 
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CenterUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return date;
 
    }
    
    public static Date nextDayThai(Date d,Integer i){  
        
        Date date = null; 
 
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        
        c.setTime(d);
        c.add(Calendar.DATE, +i);
  
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        
        try {
            date = sm.parse(Utils.formatDateToStringToDBThai(c.getTime())); 
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CenterUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return date;
 
    }
        
    public static String rangeOfString(String val , String start , String end ){
        
        String result = "";
         
        if( !Utils.NVL(start).equals("") && !Utils.NVL(end).equals("")){
            if( Utils.NVL(start).equals(Utils.NVL(end))){
                result += val + start; 
            } else { 
                result += val + start + " - " + end;  
            }     
        }else if(!Utils.NVL(start).equals("") && Utils.NVL(end).equals("")){
            result += val + start;
        }else if( Utils.NVL(start).equals("") && !Utils.NVL(end).equals("")){
            result += val + end;
        } 
 
       return result;
    } 
    
    public static String rangeOfDate(String val , Date start , Date end){
        
        String result = "";
         
        if( !Utils.NVL(start).equals("") && !Utils.NVL(end).equals("")){
            if( Utils.NVL(start).equals(Utils.NVL(end))){
                result += val + Utils.convertDateStringToScreen(Utils.formatDateToStringToDBThai(start), "/");
            } else { 
                result += val + Utils.convertDateStringToScreen(Utils.formatDateToStringToDBThai(start), "/")
                 + "-" + Utils.convertDateStringToScreen(Utils.formatDateToStringToDBThai(end), "/");     
            }     
        }else if(!Utils.NVL(start).equals("") && Utils.NVL(end).equals("")){
            result += val + Utils.convertDateStringToScreen(Utils.formatDateToStringToDBThai(start), "/");
        }else if( Utils.NVL(start).equals("") && !Utils.NVL(end).equals("")){
            result += val + Utils.convertDateStringToScreen(Utils.formatDateToStringToDBThai(end), "/");
        }
             
       return result;
    }
     
    // เซ็ทค่าสตริงสำหรับรีพอร์ท ถ้าเป็นค่าว่างให้ส่ง -
    public static String setStringR(String val , String dpResult){
        
        String result = "";
         
        if( !Utils.NVL(dpResult).equals("") ){
            result += val + dpResult; 
        }else{
            result = "-";
        } 
 
       return result;
    }  
    
    
    public static double NVLD(String s) {
        try {
                return Double.parseDouble(s);
        } catch (Exception e) {}
        return 0;
    }
    
    public static Date formatStringToDateToScreen(String dt){
        int dd = 0;
        int mm = 0;
        int yy = 0;
        
        if(dt==null || dt.length() < 8) return null;
                
        try{
            dd = Integer.parseInt(dt.substring(6,8));
            mm = Integer.parseInt(dt.substring(4,6));
            yy = Integer.parseInt(dt.substring(0,4));

            Calendar cl = Calendar.getInstance(Locale.ENGLISH);

            cl.set(yy, mm - 1, dd , 0 , 0 ,0);

            return cl.getTime();
            
        }catch(Exception ex){
            return null;
        }
    }
    
    public static String nextDayEn(String d,Integer i){  
        
        Date date = null; 
 
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        
        c.setTime(Utils.formatStringToDateToScreen(d));
        c.add(Calendar.DATE, +i);
  
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        
        try {
            //date = sm.parse(Utils.formatDateToStringToDBThai(c.getTime())); 
            date = sm.parse(Utils.formatDateToStringToDBEn(c.getTime())); 
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CenterUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return Utils.formatDateToStringToDBEn(date);
 
    }
    
    public static String previousDayEn(String d,Integer i){  
        
        Date date = null; 
 
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        
        c.setTime(Utils.formatStringToDateToScreen(d));
        c.add(Calendar.DATE, -i);
  
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
        
        try {
            //date = sm.parse(Utils.formatDateToStringToDBThai(c.getTime())); 
            date = sm.parse(Utils.formatDateToStringToDBEn(c.getTime()));            
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CenterUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return Utils.formatDateToStringToDBEn(date);
 
    }
    
    public static Date previousDayEn(Date d,Integer i){  
        
        Date date = null; 
 
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        
        c.setTime(d);
        c.add(Calendar.DATE, -i);
  
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        
        try {
            date = sm.parse(Utils.formatDateToStringToDBEn(c.getTime())); 
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CenterUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return date;
 
    }
            
    public static String getLabelCombotb_currency(String code){
        
        String result = "";
        if(!Utils.NVL(code).equals("")){
            for(SelectItem si :  BKBListData.getCombotb_currency()){
                    if(Utils.NVL(code).equals(Utils.NVL(si.getValue()))){
                        result = si.getLabel();
                        break;
                    }
             }
        }
        return result;
    }
    
    public static void setCellBorder(HSSFCellStyle hCellStyle){
        
        //-- set Border
        hCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        hCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        hCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        hCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //-- set Color Border
        hCellStyle.setTopBorderColor(HSSFColor.BLACK.index);
        hCellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        hCellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        hCellStyle.setRightBorderColor(HSSFColor.BLACK.index);
    }
    
    public static String convertStringMonthYear(String yearmonth){
        String result = "";
        try {
            SimpleDateFormat sm = new SimpleDateFormat("yyyyMM", Locale.ENGLISH);
            Date date = sm.parse(yearmonth);
            
            result = new SimpleDateFormat("MMM yyyy").format(date.getTime());
            logger.debug(">>convertStringMonthYear :"+result);
            
            
            
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CenterUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public static String formatfileNameDatetime(){
        return new SimpleDateFormat("yyyyMMdd-HH-mm-ss").format(Utils.getcurDateTime().getTime());
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
                    SimpleDateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
                    date = df.parse(input);
                    
                    Calendar calw = Calendar.getInstance(Locale.ENGLISH);
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
    
    
    public static String[] getDayofWeekofYear(int yyyy,int month,int weekofyear){
        
        logger.debug(">>getDayofWeekofYear "+yyyy+" // "+month+" // "+weekofyear);
                int monthSelect = (month-1);
                Calendar calori = Calendar.getInstance(Locale.ENGLISH);
                calori.set(Calendar.YEAR, yyyy);
                calori.set(Calendar.MONTH, monthSelect);
            
                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.set(Calendar.YEAR, yyyy);
                
                
                cal.set(Calendar.WEEK_OF_YEAR, weekofyear);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                String dst = formatter.format(cal.getTime());
                cal.add(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                String dfn = formatter.format(cal.getTime());
                
                String yyst = dst.substring(0,4);
                String mmst = dst.substring(4,6);
                String ddst = dst.substring(6,8);
                logger.debug(">>ddst "+yyst+"//"+mmst+"//"+ddst);
                
                String yyfn = dfn.substring(0,4);
                String mmfn = dfn.substring(4,6);
                String ddfn = dfn.substring(6,8);
                System.out.println(">>dfn "+yyfn+"//"+mmfn+"//"+ddfn);
                
                logger.debug(">>dfn "+calori.getActualMinimum(Calendar.DATE));
                logger.debug(">>dfn "+calori.getActualMaximum(Calendar.DATE));
                
                String m = Utils.NVL(monthSelect).length()==1?"0"+Utils.NVL(monthSelect+1):Utils.NVL(monthSelect+1);
                if(!mmst.equals(m)){
                    String d = Utils.NVL(calori.getActualMinimum(Calendar.DATE)).length()==1?"0"+Utils.NVL(calori.getActualMinimum(Calendar.DATE)):Utils.NVL(calori.getActualMinimum(Calendar.DATE));
                    dst = yyst+mmst+d;
                }
                
                if(!mmfn.equals(m)){
                    String d = Utils.NVL(calori.getActualMaximum(Calendar.DATE)).length()==1?"0"+Utils.NVL(calori.getActualMaximum(Calendar.DATE)):Utils.NVL(calori.getActualMaximum(Calendar.DATE));
                    dfn = yyfn+mmst+d;
                }
                
                logger.debug(">>ddst c:"+dst);
                logger.debug(">>dfn c:"+dfn);
                
                String[] result = {dst,dfn};
                return result;
    }
    
     public static String getMaxDayofMonthofYear(int yyyy,int month){
        
                logger.debug(">>getMaxDayofMonthofYear "+yyyy+" // "+month);
                int monthSelect = (month-1);
                Calendar c = Calendar.getInstance(Locale.ENGLISH);
                c.set(Calendar.YEAR, yyyy);
                c.set(Calendar.MONTH, monthSelect);
            
                logger.debug(">>ddst c:"+c.getActualMaximum(Calendar.DAY_OF_MONTH));
                
                return Utils.NVL(c.getActualMaximum(Calendar.DAY_OF_MONTH));
    }
     
    public static String isChangePayby(Daily db,Daily form){
        
        String changeBank = "";
        if(db.getPayby() != form.getPayby()){
              changeBank =  new BigDecimal(db.getPayby()).toString();
        }
        return changeBank;    
    }  
    
    public static String formatStringNumber(String value){
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format( Utils.NVL(value).equals("")?new BigDecimal(0).doubleValue():new BigDecimal(Utils.NVL(value)).doubleValue());
    }
    
    public static double format(String value){
        return Utils.NVL(value).equals("")?new BigDecimal(0).doubleValue():new BigDecimal(Utils.NVL(value)).doubleValue();
    }
}
