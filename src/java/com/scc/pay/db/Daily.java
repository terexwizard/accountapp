/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @EmbeddedId
    protected DailyPK dailyPK;
    @Column(name = "docno_code")
    private String docnoCode;
    @Column(name = "docno")
    private String docno;
    @Column(name = "description")
    private String description;
    @Column(name = "chqno")
    private String chqno;
    @Column(name = "no")
    private String no;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "received_cash")
    private Double receivedCash;
    @Column(name = "received_bank")
    private String receivedBank;
    @Column(name = "received_cr")
    private Double receivedCr;
    @Column(name = "received_fixaccount")
    private Double receivedFixaccount;
    @Column(name = "received_exchange")
    private String receivedExchange;
    @Column(name = "received_typeofaccount")
    private String receivedTypeofaccount;
    @Column(name = "payment_cash")
    private Double paymentCash;
    @Column(name = "payment_bank")
    private String paymentBank;
    @Column(name = "payment_cr")
    private Double paymentCr;
    @Column(name = "balance_cash")
    private Double balanceCash;
    @Column(name = "balance_cr")
    private Double balanceCr;
    @Column(name = "vendor")
    private String vendor;
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

    public Daily() {
    }

    public Daily(DailyPK dailyPK) {
        this.dailyPK = dailyPK;
    }

    public Daily(String dailydate, String jobno) {
        this.dailyPK = new DailyPK(dailydate, jobno);
    }

    public DailyPK getDailyPK() {
        return dailyPK;
    }

    public void setDailyPK(DailyPK dailyPK) {
        this.dailyPK = dailyPK;
    }

    public String getDocnoCode() {
        return docnoCode;
    }

    public void setDocnoCode(String docnoCode) {
        this.docnoCode = docnoCode;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChqno() {
        return chqno;
    }

    public void setChqno(String chqno) {
        this.chqno = chqno;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Double getReceivedCash() {
        return receivedCash;
    }

    public void setReceivedCash(Double receivedCash) {
        this.receivedCash = receivedCash;
    }

    public String getReceivedBank() {
        return receivedBank;
    }

    public void setReceivedBank(String receivedBank) {
        this.receivedBank = receivedBank;
    }

    public Double getReceivedCr() {
        return receivedCr;
    }

    public void setReceivedCr(Double receivedCr) {
        this.receivedCr = receivedCr;
    }

    public Double getReceivedFixaccount() {
        return receivedFixaccount;
    }

    public void setReceivedFixaccount(Double receivedFixaccount) {
        this.receivedFixaccount = receivedFixaccount;
    }

    public String getReceivedExchange() {
        return receivedExchange;
    }

    public void setReceivedExchange(String receivedExchange) {
        this.receivedExchange = receivedExchange;
    }

    public String getReceivedTypeofaccount() {
        return receivedTypeofaccount;
    }

    public void setReceivedTypeofaccount(String receivedTypeofaccount) {
        this.receivedTypeofaccount = receivedTypeofaccount;
    }

    public Double getPaymentCash() {
        return paymentCash;
    }

    public void setPaymentCash(Double paymentCash) {
        this.paymentCash = paymentCash;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    public Double getPaymentCr() {
        return paymentCr;
    }

    public void setPaymentCr(Double paymentCr) {
        this.paymentCr = paymentCr;
    }

    public Double getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(Double balanceCash) {
        this.balanceCash = balanceCash;
    }

    public Double getBalanceCr() {
        return balanceCr;
    }

    public void setBalanceCr(Double balanceCr) {
        this.balanceCr = balanceCr;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dailyPK != null ? dailyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Daily)) {
            return false;
        }
        Daily other = (Daily) object;
        if ((this.dailyPK == null && other.dailyPK != null) || (this.dailyPK != null && !this.dailyPK.equals(other.dailyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.Daily[ dailyPK=" + dailyPK + " ]";
    }
    
}
