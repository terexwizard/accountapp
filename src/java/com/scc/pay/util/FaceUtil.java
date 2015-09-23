/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.util;

import com.scc.f1.util.Utils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
import java.util.Enumeration;
//import java.util.logging.Level;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 26/07/2555  10:22:02
 */
public final class FaceUtil {
    
    private static final Logger logger = Logger.getLogger(FaceUtil.class);
    
    public static String getSessionID(){
        
        String ssid         = "";
        HttpSession hss     = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        if(hss==null){
            
        }else{
            ssid    = hss.getId();
        }
        
        return ssid;
    }
    
    public static String getRequestContextPath(){
        
        String strout           = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        
        return strout;
        
    }
    
    public static int getRequestPort(){
        
        HttpServletRequest hreq     = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        return hreq.getLocalPort();
        
    }
    
    public static String getClientIPformContext(String curip, int outlenght){
        
        HttpServletRequest request = (HttpServletRequest)(FacesContext.getCurrentInstance().getExternalContext().getRequest());  
    
        return getClientIP(request, curip, outlenght);
    }
    
    public static String getClientIP(HttpServletRequest request, String curip, int outlenght){
        
        String out  = "";
        
        if(curip == null || curip.equals("")){
            
            String raddr    = request.getRemoteAddr(); 
            
//            logger.debug("====== Header =======");
            String xforw    = "";
            
            Enumeration en    = request.getHeaderNames();
            int i = 0;
            while(en.hasMoreElements()){
                i++;
                String hname    = (String)en.nextElement();
//                logger.debug( " "+i+" "+hname +" ="+  request.getHeader(hname));
                if(hname.toLowerCase().equals("x-forwarded-for")){
                    xforw       = Utils.NVL(request.getHeader(hname));
                    break;
                }
            }
            
            if(xforw == null || xforw.equals("")){
                out     = raddr;
            }else{
                out     = xforw;
            }
            
            
        }else{
            out     = curip;
        }
        
        if(outlenght>0 && out.length() >outlenght ){
            out     = out.substring(0,outlenght);
        }
        
        
        return out;
    }
    
    public static String getClientAgentformContext(int outlenght){
        
        HttpServletRequest request = (HttpServletRequest)(FacesContext.getCurrentInstance().getExternalContext().getRequest());  
    
        return getClientAgent(request, outlenght);
        
    }
    
    public static String getClientAgent(HttpServletRequest request, int outlenght){
        
        String clientagent  = "";
        
        Enumeration en    = request.getHeaderNames();
        int i = 0;
        while(en.hasMoreElements()){
            i++;
            String hname    = (String)en.nextElement();
//                logger.debug( " "+i+" "+hname +" ="+  request.getHeader(hname));
            if(hname.toLowerCase().equals("user-agent")){
                clientagent       = Utils.NVL(request.getHeader(hname));
                break;
            }
        }
        
        if(outlenght>0 && clientagent.length() >outlenght ){
            clientagent     = clientagent.substring(0,outlenght);
        }
        
        return clientagent;
    }
    
    
    public static String requestURL(String targeturl, String param){
        
        String responsetext     = null;
        
        try{
        
            String requrl       = targeturl;
            
            if( !Utils.isEmpty(param)){
                
                if(targeturl.indexOf("?") == -1){
                    
                    requrl += param.startsWith("?")? param : "?"+param;
                    
                }else{
                    requrl += param.startsWith("&")? param : "&"+param;
                
                }
                
            }
            
//           String data  =   "report="+reportfile+ 
//                             "&P_REQ_FORM_ID="+ reqformid +
//                             "&init=pdf";
    
            // Send data
           URL url = new URL(requrl);
           URLConnection conn = url.openConnection();
           
//           conn.setDoOutput(true);
//           
//           OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//           wr.write(data);
//           wr.flush();

           byte[] ba            = new byte[4096]  ;
           int    byteread      ;
           
             BufferedInputStream bis      = new BufferedInputStream(conn.getInputStream());
             ByteArrayOutputStream bos      = new ByteArrayOutputStream();
             
             while((byteread=bis.read(ba)) != -1){
                 
                 bos.write(ba, 0, byteread);
                 
             }
             
             bos.flush();
             
//             FileUtil.writeBytetoFile(outfile, bos.toByteArray());
             
             responsetext   = bos.toString();            
            
//            wr.close();
            
            bis.close();
            bos.close();


//            isok    = true;
            
         }catch(Exception ex){
//             logger.error(ex.getMessage(), ex);
             
         }
            
        return responsetext;
    }
    
    
    /**
     * download file
     * @param file 
     */
    public static void getDownloadfile(String file){
        getDownloadfile(file, null);
    }
    
    /**
     * download name to downloadfilename
     * @param file
     * @param downloadfilename 
     */
    public static void getDownloadfile(String file, String downloadfilename){
        
//        try {        
            if( Utils.isEmpty(file)){
                logger.debug("blank file skip");
                return;
            }
            
            String fileName     = FileUtil.getFilename(file)   ;
            String outfilename  = downloadfilename;
            
    //        String[] fp     = file.split("/");
    //        fileName        = fp[fp.length-1];
            
            logger.debug("1.0 file :"+file+" = "+fileName+"/"+downloadfilename);
            
            if( Utils.NVL(downloadfilename).equals("")){
                outfilename     = fileName;
            }
            
            int n;
            byte[] filedata     = FileUtil.readFileToByteArray(file) ;
            
    //        try {
    //            
    //            byte[] b = new byte[2048];
    //            ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //            FileInputStream fis      = new FileInputStream(file);
    //            
    //            while ((n = fis.read(b, 0, 2048)) != -1) {
    //                    baos.write(b, 0, n);
    //                }
    //            
    //            fis.close();
    //            
    //            baos.flush();
    //            baos.close();
    //            
    //            filedata    = baos.toByteArray();
    //        
    //        } catch (Exception ex) {
    ////            Logger.getLogger(BKBVerifyRef.class.getName()).log(Level.SEVERE, null, ex); 
    ////        } catch (IOException ex) {
    //////            Logger.getLogger(BKBVerifyRef.class.getName()).log(Level.SEVERE, null, ex);
    //            
    //            logger.warn(ex.getMessage(),ex);
    //             return ;
    //        }
            
            if(filedata==null) return;
            
            logger.debug("dl size :" +filedata.length);
            
            String contentType  = "application/octet-stream";
            int contentLength   = filedata.length;
            
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();

            ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
            ec.setResponseContentType(contentType); // Check http://www.w3schools.com/media/media_mimeref.asp for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
            ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.

            
//            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + URLDecoder.decode(URLEncoder.encode(outfilename, "UTF-8"), "ISO8859_1") + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
//            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + URLDecoder.decode(URLEncoder.encode(outfilename, "UTF-8"), "UTF-8") + "\""); 
            
            String encd         = ec.getResponseCharacterEncoding();
            String cvname       = "";
            try {
                //            ec.setResponseCharacterEncoding("UTF-8");

    //                    tis      = new String(outfilename.getBytes() , "ISO-8859-1");
                        cvname      = new String(outfilename.getBytes() , encd);

            } catch (UnsupportedEncodingException ex) {
    //            java.util.logging.Logger.getLogger(FaceUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            logger.debug("ecd :"+encd+" "+outfilename +" >> "+cvname);
        
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + cvname + "\""); 
            
       
            OutputStream output;
            
            try {
                
                output = ec.getResponseOutputStream();
                output.write(filedata);
                output.flush();
                output.close();
                
            } catch (IOException ex) {
                logger.warn(ex.getMessage());
                
                return;
            }
            
            fc.responseComplete(); // Important
            
            logger.debug(fileName +" dl finis.");
            
//        } catch (UnsupportedEncodingException ex) {
////            java.util.logging.Logger.getLogger(FaceUtil.class.getName()).log(Level.SEVERE, null, ex);
//            logger.error(ex.getMessage(), ex);
//        }
        
    }
    
    /**
     * download ByteArrayOutputStream to downloadfilename
     * @param bOutput
     * @param downloadfilename 
     */
    public static void getDownloadfile(ByteArrayOutputStream bOutput, String downloadfilename){
        String outfilename  = downloadfilename;
        
        if( Utils.NVL(downloadfilename).equals("")){
            return;
        }
        byte[] filedata     = bOutput.toByteArray();
        
        String contentType  = "application/octet-stream";
        int contentLength   = filedata.length;
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType(contentType); // Check http://www.w3schools.com/media/media_mimeref.asp for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        
//        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + outfilename + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
        
        String encd         = ec.getResponseCharacterEncoding();
        String cvname       = "";
        try {
            //            ec.setResponseCharacterEncoding("UTF-8");

//                    tis      = new String(outfilename.getBytes() , "ISO-8859-1");
                    cvname      = new String(outfilename.getBytes() , encd);

        } catch (UnsupportedEncodingException ex) {
//            java.util.logging.Logger.getLogger(FaceUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        logger.debug("ecd :"+encd+" "+outfilename +" >> "+cvname);

        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + cvname + "\""); 
        
        
        
        OutputStream output;
        
        try {
            
            output = ec.getResponseOutputStream();
            output.write(filedata);
            output.flush();
            output.close();
            
        } catch (IOException ex) {
            logger.warn(ex.getMessage());
            
            return;
        }
        
        fc.responseComplete(); // Important
        
        logger.debug(outfilename +" dl finis.");
        
    }
    
}
