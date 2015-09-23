/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scc.pay.util;

import com.scc.f1.Constant;
import com.scc.f1.util.Utils;
//import com.scc.f1.util.Utils;
//import java.io.BufferedInputStream;
//import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLConnection;
//import java.util.logging.Level;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
//import java.util.logging.Level;

import org.apache.log4j.Logger;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 26/04/2556  14:54:15
 */
public final class FileUtil {
    
    private static final Logger logger = Logger.getLogger(FileUtil.class);
    
    public static final String FILE_CONTENT_TYPE_PDF            = "pdf";
    public static final String FILE_CONTENT_TYPE_PICTURE        = "image";
    public static final String FILE_CONTENT_TYPE_PICTURE_JPEG   = "jpeg";
    public static final String FILE_CONTENT_TYPE_WORD           = "word";
    public static final String FILE_CONTENT_TYPE_EXCEL          = "excel";
    
    public static final String FILE_CONTENT_TYPE_PDF_EXT        = ".pdf";
    public static final String FILE_CONTENT_TYPE_PICTURE_EXT    = ".jpg,.jpeg,.png,.gif,.bmp";
    public static final String FILE_CONTENT_TYPE_PICTURE_JPEG_EXT    = ".jpg,.jpeg";
    public static final String FILE_CONTENT_TYPE_WORD_EXT       = ".doc,.docx";
    public static final String FILE_CONTENT_TYPE_EXCEL_EXT      = ".xls,.xlsx";
    
    public static boolean writeBytetoFile(String filename , byte[] content){
        
        boolean isok            = false;
        FileOutputStream fos    = null;
        
        try {
            
                fos = new FileOutputStream(filename);
            
                fos.write(content);
            
                fos.flush();
                //fos.close();
                isok    = true;
            
        } catch (Exception ex) {
            //Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            logger.error(ex.getMessage(), ex);
            
        }finally{
            
            try {
                if(fos!= null){
                    fos.close();
                }
            } catch (IOException ex) {
                //java.util.logging.Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        return isok;
    }
    
    
    public static String getFilename(String filenamepath){
        
//        File.separator
//        String[] fp     = filenamepath.split("/");
//        String filename        = fp[fp.length-1];
        
//        File file       = new File(filenamepath);
//        String filename = file.getName();
        
        String filename = filenamepath.replaceAll("\\\\", "/");
//        String filename = filenamepath.replace("\\", "/");
        String[] fp     = filename.split("/");
        filename        = fp[fp.length-1];
        
        return filename;
    }
    
    public static String getFileExtension(String filenamepath){
        
        String[] farr   = filenamepath.split("\\.");
//        logger.debug(" >> "+filenamepath +" " +farr.length);
        return (farr.length ==1 ? farr[0] : "."+farr[farr.length-1]);
    }
    
    public static long getFileSize(String filenamepath){
        long size   = -1;
        try{
            File file   = new File(filenamepath);
            size        = file.length();
        }catch(Exception ex){
            
        }
        
        return size;
    }
    
    public static FileInputStream getFileInputStream(String file){
        
        FileInputStream fis     = null;
        
        try {
            
            fis = new FileInputStream(file);
            
        } catch (FileNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return fis;
    }
    
    public static byte[] readFileToByteArray(String file){
        
        byte[] filedata     = null;

        try {
            int n;
            byte[] b = new byte[2048];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis      = new FileInputStream(file);
            
            while ((n = fis.read(b, 0, 2048)) != -1) {
                    baos.write(b, 0, n);
                }
            
            fis.close();
            
            baos.flush();
            baos.close();
            
            filedata    = baos.toByteArray();
        
        } catch (Exception ex) {
//            Logger.getLogger(BKBVerifyRef.class.getName()).log(Level.SEVERE, null, ex); 
//        } catch (IOException ex) {
////            Logger.getLogger(BKBVerifyRef.class.getName()).log(Level.SEVERE, null, ex);
            
            //logger.warn(ex.getMessage(),ex);
            logger.warn(ex.getMessage());
        }
        
        return filedata;
        
    }
    
    
//    public static String guessContentType(byte[] content){
//        String mimeType = "";
//        
//        try {
//            
//            InputStream is = new BufferedInputStream(new ByteArrayInputStream(content));
//            mimeType = URLConnection.guessContentTypeFromStream(is);
//            
//        } catch (IOException ex) {
////            java.util.logging.Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return mimeType;
//    }

    
    public static boolean validateFileType(String validtype, String fullfilepath, String filename, String contenttype, byte[] content ){
        boolean isok    = false;
        
//        String mime = guessContentType(content);
        String ext  = getFileExtension(filename).toLowerCase();
        
//        logger.debug("mime :" +mime +" , e :"+ext);
        
      
            
            if( validtype.equals( FILE_CONTENT_TYPE_PDF )  ){
                
                if( contenttype.equals("application/pdf") || 
                        FILE_CONTENT_TYPE_PDF_EXT.indexOf(ext) != -1 ){
                     isok    = true;
                }
            
            }else if( validtype.equals( FILE_CONTENT_TYPE_PICTURE_JPEG )  ){
                
                if( FILE_CONTENT_TYPE_PICTURE_JPEG_EXT.indexOf(ext) != -1 ){
                     isok    = true;
                }
                
            }else if( validtype.equals( FILE_CONTENT_TYPE_PICTURE )  ){
                
                if( FILE_CONTENT_TYPE_PICTURE_EXT.indexOf(ext) != -1 ){
                     isok    = true;
                }
                
            }else if( validtype.equals( FILE_CONTENT_TYPE_WORD )  ){
                
                if( FILE_CONTENT_TYPE_WORD_EXT.indexOf(ext) != -1 ){
                     isok    = true;
                }
                
            }else if( validtype.equals( FILE_CONTENT_TYPE_EXCEL)  ){
                
                if( FILE_CONTENT_TYPE_EXCEL_EXT.indexOf(ext) != -1 ){
                     isok    = true;
                }
                
            }
            
        
        return isok;
    }
    
    public static String cretaeDir(String directory){
        
        File dir        = new File(directory);
        
        dir.mkdirs();
        
        return directory;
    }
    
    public static String cretaeTempFileDirbyDate(String parentdir){
        
        String outdir   = parentdir +"/"+Utils.getcurDateDBThai();
        
        File dir        = new File(outdir);
        
        dir.mkdirs();
        
        return outdir;
    }
    
    public static String cretaeDefaultRealTempFileDirbyDate(){
        
//        String outdir   = Constant.context_realpath +"/tempimages/"+Utils.getcurDateDBThai();
//        
//        File dir        = new File(outdir);
//        
//        dir.mkdirs();
//        
//        return outdir;
        
        return Constant.context_realpath +"/" +cretaeDefaultShortTempFileDirbyDate();
        
    }
    
    public static String cretaeDefaultShortTempFileDirbyDate(){
        
        String cd       = Utils.getcurDateDBThai();
        String outdir   = Constant.context_realpath +"/tempimages/"+cd;
        
        File dir        = new File(outdir);
        
        dir.mkdirs();
        
        return "tempimages/"+cd;
    }
    
    public static String cretaeRealTempFileDirbySession(String parentdir){
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String session_id = session.getId();
        
        String outdir   = Constant.context_realpath + (parentdir.startsWith("/")?"":"/")+ parentdir +(parentdir.endsWith("/")?"":"/")+Utils.getcurDateDBThai()+"/"+session_id;
        
        File dir        = new File(outdir);
        dir.mkdirs();
        
        return outdir;
    }
    
    public static String cretaeDefaultRealTempFileDirbySession(){
        
        return cretaeRealTempFileDirbySession("temp");
    }
    
}
