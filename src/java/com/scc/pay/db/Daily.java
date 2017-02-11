/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author terex
 */
@Entity
@Table(name = "daily")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Daily.findAll", query = "SELECT d FROM Daily d")})
public class Daily implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dailyid")
    private Integer dailyid;
    @Column(name = "dailydate")
    private String dailydate;
    @Column(name = "companyid")
    private String companyid;
    @Column(name = "companyname")
    private String companyname;
    @Column(name = "voucherno")
    private String voucherno;
    @Column(name = "docno")
    private String docno;
    @Column(name = "jobref")
    private String jobref;
    @Column(name = "transecsionno")
    private String transecsionno;
    @Column(name = "invno")
    private String invno;
    @Column(name = "dailytype")
    private String dailytype;
    @Column(name = "descriptioncode")
    private String descriptioncode;
    @Column(name = "currency")
    private String currency;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "exchangerate")
    private Double exchangerate;
    @Column(name = "receivedamount")
    private Double receivedamount;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "paidamount")
    private Double paidamount;
    @Column(name = "amount2")
    private Double amount2;
    @Column(name = "payby")
    private Double payby;
    @Column(name = "remark")
    private String remark;
    @Column(name = "receivesuccess")
    private String receivesuccess;
    @Column(name = "receivesuccessdate")
    private String receivesuccessdate;
    @Column(name = "receivesuccessuser")
    private String receivesuccessuser;
    @Column(name = "paymentsuccess")
    private String paymentsuccess;
    @Column(name = "paymentsuccessdate")
    private String paymentsuccessdate;
    @Column(name = "paymentsuccessuser")
    private String paymentsuccessuser;
    @Column(name = "cheque")
    private String cheque;
    @Column(name = "chequeno")
    private String chequeno;
    @Column(name = "chequedate")
    private String chequedate;
    @Column(name = "enttime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enttime;
    @Column(name = "entuser")
    private String entuser;
    @Column(name = "updlcnt")
    private Integer updlcnt;
    @Column(name = "updtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updtime;
    @Column(name = "upduser")
    private String upduser;
    @Column(name = "consigneeid")
    private String consigneeid;
    @Column(name = "consigneename")
    private String consigneename;

    public Daily() {
    }

    public Daily(Integer dailyid) {
        this.dailyid = dailyid;
    }

    public Integer getDailyid() {
        return dailyid;
    }

    public void setDailyid(Integer dailyid) {
        this.dailyid = dailyid;
    }

    public String getDailydate() {
        return dailydate;
    }

    public void setDailydate(String dailydate) {
        this.dailydate = dailydate;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getVoucherno() {
        return voucherno;
    }

    public void setVoucherno(String voucherno) {
        this.voucherno = voucherno;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public String getJobref() {
        return jobref;
    }

    public void setJobref(String jobref) {
        this.jobref = jobref;
    }

    public String getTransecsionno() {
        return transecsionno;
    }

    public void setTransecsionno(String transecsionno) {
        this.transecsionno = transecsionno;
    }

    public String getInvno() {
        return invno;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getDailytype() {
        return dailytype;
    }

    public void setDailytype(String dailytype) {
        this.dailytype = dailytype;
    }

    public String getDescriptioncode() {
        return descriptioncode;
    }

    public void setDescriptioncode(String descriptioncode) {
        this.descriptioncode = descriptioncode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(Double exchangerate) {
        this.exchangerate = exchangerate;
    }

    public Double getReceivedamount() {
        return receivedamount;
    }

    public void setReceivedamount(Double receivedamount) {
        this.receivedamount = receivedamount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPaidamount() {
        return paidamount;
    }

    public void setPaidamount(Double paidamount) {
        this.paidamount = paidamount;
    }

    public Double getAmount2() {
        return amount2;
    }

    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }

    public Double getPayby() {
        return payby;
    }

    public void setPayby(Double payby) {
        this.payby = payby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceivesuccess() {
        return receivesuccess;
    }

    public void setReceivesuccess(String receivesuccess) {
        this.receivesuccess = receivesuccess;
    }

    public String getReceivesuccessdate() {
        return receivesuccessdate;
    }

    public void setReceivesuccessdate(String receivesuccessdate) {
        this.receivesuccessdate = receivesuccessdate;
    }

    public String getReceivesuccessuser() {
        return receivesuccessuser;
    }

    public void setReceivesuccessuser(String receivesuccessuser) {
        this.receivesuccessuser = receivesuccessuser;
    }

    public String getPaymentsuccess() {
        return paymentsuccess;
    }

    public void setPaymentsuccess(String paymentsuccess) {
        this.paymentsuccess = paymentsuccess;
    }

    public String getPaymentsuccessdate() {
        return paymentsuccessdate;
    }

    public void setPaymentsuccessdate(String paymentsuccessdate) {
        this.paymentsuccessdate = paymentsuccessdate;
    }

    public String getPaymentsuccessuser() {
        return paymentsuccessuser;
    }

    public void setPaymentsuccessuser(String paymentsuccessuser) {
        this.paymentsuccessuser = paymentsuccessuser;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getChequeno() {
        return chequeno;
    }

    public void setChequeno(String chequeno) {
        this.chequeno = chequeno;
    }

    public String getChequedate() {
        return chequedate;
    }

    public void setChequedate(String chequedate) {
        this.chequedate = chequedate;
    }

    public Date getEnttime() {
        return enttime;
    }

    public void setEnttime(Date enttime) {
        this.enttime = enttime;
    }

    public String getEntuser() {
        return entuser;
    }

    public void setEntuser(String entuser) {
        this.entuser = entuser;
    }

    public Integer getUpdlcnt() {
        return updlcnt;
    }

    public void setUpdlcnt(Integer updlcnt) {
        this.updlcnt = updlcnt;
    }

    public Date getUpdtime() {
        return updtime;
    }

    public void setUpdtime(Date updtime) {
        this.updtime = updtime;
    }

    public String getUpduser() {
        return upduser;
    }

    public void setUpduser(String upduser) {
        this.upduser = upduser;
    }
    public String getConsigneeid() {
        return consigneeid;
    }

    public void setConsigneeid(String consigneeid) {
        this.consigneeid = consigneeid;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dailyid != null ? dailyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Daily)) {
            return false;
        }
        Daily other = (Daily) object;
        if ((this.dailyid == null && other.dailyid != null) || (this.dailyid != null && !this.dailyid.equals(other.dailyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.Daily[ dailyid=" + dailyid + " ]";
    }
    
}
