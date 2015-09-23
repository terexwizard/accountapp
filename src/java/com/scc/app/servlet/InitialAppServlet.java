/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scc.app.servlet;


//import com.scc.dbd.regcom.dbutil.QueryXML;
//import com.scc.dbd.regcom.dbutil.RdbXML;

import com.scc.f1.AppConfig;
import com.scc.f1.Constant;
import com.scc.f1.util.Utils;
import com.scc.pay.AppConstant;

import java.util.HashMap;


/**
 *
 * @author Administrator
 */
//,urlPatterns="/Initial"
//@WebServlet (name="Initial"  ,loadOnStartup=0 )
public class InitialAppServlet extends com.scc.f1.servlet.InitialAppServlet {
    
   
    @Override
    protected void readAndSetConfig(HashMap<String, String> hm) {
//        throw new UnsupportedOperationException("Not supported yet.");
        
        Constant.PACKAGE_BUSINESS   = "com.scc.pay.business";
        
        AppConstant.EAS_SERVER                  = AppConfig.get("eas_server");
        
        AppConstant.EAS_SERVER_PORT             = convertToInt(AppConfig.get("eas_server_port"));
        AppConstant.EAS_SERVER_TRANSFER_PORT    = convertToInt(AppConfig.get("eas_server_transfer_port"));
        AppConstant.EAS_SERVER_BEAN_PORT        = convertToInt(AppConfig.get("eas_server_bean_port"));
        
    }

    @Override
    protected void initOther(HashMap<String, String> hm) {
//        throw new UnsupportedOperationException("Not supported yet.");
       
        
    }
    
    private int convertToInt(String num){
        
        if(Utils.isEmpty(num)){
            return 0;
        }else{
            try{
                        
                int iout    = Integer.valueOf(num);
                
                return iout;

            }catch(Exception ex){
                return -1;
            }
        }
        
    }


    
}
