/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;


import com.scc.app.db.ScUser;
import com.scc.app.db.ScUserPermitRole;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.SCP010101;
import java.util.List;
import javax.persistence.Query;
 
/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class SCP010101S extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        SCP010101 frmi = (SCP010101)inobj;
        
        logger.debug("userCode id >>" + frmi.getSearchselectedrow().get("user_code"));
        
        searchScUser(frmi);
        searchScUserPermitRole(frmi);
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void searchScUser(SCP010101 frmi){
        try{
            
            ScUser recordn = new ScUser(frmi.getSearchselectedrow().get("user_code"));
            ScUser rec = em.find(ScUser.class, recordn.getUserCode());
                
            frmi.getMasterdata().setScuser(rec);
            
        }finally{
            
        }
    }
    
     private void searchScUserPermitRole(SCP010101 frmi){
        String sql = "SELECT t FROM ScUserPermitRole t "
                       + "Where r.scUserPermitRolePK.userCode = :userCode ";
        Query query = em.createQuery(sql);
        query.setParameter("userCode",Utils.NVL(frmi.getMasterdata().getScuser().getUserCode()));
        
        List<ScUserPermitRole> l = query.getResultList(); 
        if(!l.isEmpty()){
            for(ScUserPermitRole db : l){
                
                
                frmi.getMasterdata().setScuserpermitrole(db);
                
                break;
            }
        }
     }
   
           
//    private void searchScUserPermitRole(SCP010101 frmi){
//         
//        try{ 
//            
//            HashMap<String, String> hm = new HashMap<String, String>();
//            hm.put("user_code", frmi.getSearchselectedrow().get("user_code"));
//
//            List l = CenterUtils.selectData(hm,"lookup_sc_role_rg95_roletype_ref");
//            List<SCP010101.MainData> rowlist   = new ArrayList<SCP010101.MainData>();
//            int size = l.size();
//            if(size > 0){
//                 
//                for(int s=0;s<size;s++){
//                 
//                    MainData row = frmi.new MainData();
//                    hm = (HashMap)l.get(s); 
//                    row.setUsertypegrpdetail(Utils.NVL(hm.get("usertype_grp_detail")));
//                    row.setRoletname(Utils.NVL(hm.get("role_tname")));
//                    row.setUsertypegrpregdetail(Utils.NVL(hm.get("usertype_grpreg_detail"))); 
//                    rowlist.add(row);
//                }
//            } 
//              
//            frmi.getDetailScUserPermitRole().convertSetListdetailrow(rowlist);
//             
//        }finally{ 
//            
//        }
//    }


    
}
