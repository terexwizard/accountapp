/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.business;




import com.scc.f1.backingbean.DetailRow;
import com.scc.f1.business.BusinessImpl;
import com.scc.f1.util.BeanUtil;
import com.scc.f1.util.Utils;
import com.scc.pay.bkbean.ATP020200;
import com.scc.pay.bkbean.ATP020200.DetailInvoice;
import com.scc.pay.db.Invoice;
import com.scc.pay.db.Invoicecompany;

/**
 *
 * @author xtra
 * @version 1.00.00
 * 12/06/2555 12:50:20
 */
public class ATP020200A extends BusinessImpl {

    @Override
    protected Object doProcess(Object inobj) {
        
        
        
        ATP020200 frmi = (ATP020200)inobj;
        
        logger.debug(">>" + frmi.getUserid());
        
        insertInvoicecompany(frmi);
        processInvoice(frmi);
        
        
        frmi.setOk(true);
        
        return inobj;
    }
    
    private void insertInvoicecompany(ATP020200 frmi){
        
        Invoicecompany dbinvoicecompany = em.find(Invoicecompany.class, frmi.getMasterdata().getInvoicecompany().getInvcomid());
        
        
        if(dbinvoicecompany == null){
            
            frmi.getMasterdata().getInvoicecompany().setEntuser(frmi.getUserid());
            frmi.getMasterdata().getInvoicecompany().setEnttime(Utils.getcurDateTime());
            frmi.getMasterdata().getInvoicecompany().setUpdlcnt(1);
            frmi.getMasterdata().getInvoicecompany().setUpdtime( Utils.getcurDateTime() );
            frmi.getMasterdata().getInvoicecompany().setUpduser(frmi.getUserid());

            persist(frmi.getMasterdata().getInvoicecompany());
        }
        
    }
    
    private void processInvoice(ATP020200 frmi){
        
        for(DetailRow<DetailInvoice> item: frmi.getDetailinvoice().getListdetailrowdeleted()){
                Invoice dbu = em.find(Invoice.class, item.getData().getInvoice().getInvid());

                if(dbu != null){
                    remove(dbu);
                }
          }
        
        for(DetailRow<DetailInvoice> item :frmi.getDetailinvoice().getListdetailrow()){
            
//            Invoice db = em.find(Invoice.class, item.getData().getInvoice().getInvid());
//            
//            if(db == null){
               
            if(Utils.NVL(item.getRowstatus()).equals(DetailRow.ROW_STATUS_NEW)){
                Invoice dbbean = new Invoice();
                
                BeanUtil.copyProperties(dbbean, item.getData().getInvoice());
                
                dbbean.setInvcomid(frmi.getMasterdata().getInvoicecompany().getInvcomid());
                dbbean.setCompany(frmi.getMasterdata().getInvoicecompany().getCompanyname());
                dbbean.setInvdate(Utils.formatDateToStringToDBEn(item.getData().getInvdate()));
                dbbean.setDuedate(Utils.formatDateToStringToDBEn(item.getData().getDuedate()));
                dbbean.setReceivedDate(Utils.formatDateToStringToDBEn(item.getData().getReceivedDate()));
                dbbean.setSubmitdate(Utils.formatDateToStringToDBEn(item.getData().getSubmitdate()));
                
                dbbean.setEntuser(frmi.getUserid());
                dbbean.setEnttime(Utils.getcurDateTime());
                dbbean.setUpdlcnt(1);
                dbbean.setUpdtime( Utils.getcurDateTime() );
                dbbean.setUpduser(frmi.getUserid());

                persist(dbbean);
            }else if(Utils.NVL(item.getRowstatus()).equals(DetailRow.ROW_STATUS_EDIT)){
                Invoice db = em.find(Invoice.class, item.getData().getInvoice().getInvid());
                    
                db.setInvno(item.getData().getInvoice().getInvno());
                db.setAmount(item.getData().getInvoice().getAmount());
                db.setPaidAmount(item.getData().getInvoice().getPaidAmount());

                db.setInvcomid(frmi.getMasterdata().getInvoicecompany().getInvcomid());
                db.setCompany(frmi.getMasterdata().getInvoicecompany().getCompanyname());
                db.setInvdate(Utils.formatDateToStringToDBEn(item.getData().getInvdate()));
                db.setDuedate(Utils.formatDateToStringToDBEn(item.getData().getDuedate()));
                db.setReceivedDate(Utils.formatDateToStringToDBEn(item.getData().getReceivedDate()));
                db.setSubmitdate(Utils.formatDateToStringToDBEn(item.getData().getSubmitdate()));

                db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
                db.setUpdtime(Utils.getcurDateTime());
                db.setUpduser(frmi.getUserid());

                merge(db);
            }
//            }else{
//                
//                db.setInvno(item.getData().getInvoice().getInvno());
//                db.setAmount(item.getData().getInvoice().getAmount());
//                db.setPaidAmount(item.getData().getInvoice().getPaidAmount());
//                
//                db.setInvcomid(frmi.getMasterdata().getInvoicecompany().getInvcomid());
//                db.setCompany(frmi.getMasterdata().getInvoicecompany().getCompanyname());
//                db.setInvdate(Utils.formatDateToStringToDBEn(item.getData().getInvdate()));
//                db.setDuedate(Utils.formatDateToStringToDBEn(item.getData().getDuedate()));
//                db.setReceivedDate(Utils.formatDateToStringToDBEn(item.getData().getReceivedDate()));
//                
//                db.setUpdlcnt(addLcnt(db.getUpdlcnt()));
//                db.setUpdtime(Utils.getcurDateTime());
//                db.setUpduser(frmi.getUserid());
//                
//                merge(db);
//            }
            
        }
        
    }
}
