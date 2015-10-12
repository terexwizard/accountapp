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
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")})
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "jobno")
    private String jobno;
    @Column(name = "jobdate")
    private String jobdate;
    @Column(name = "invcomid")
    private String invcomid;
    @Column(name = "company")
    private String company;
    @Column(name = "invno")
    private String invno;
    @Column(name = "invdate")
    private String invdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "duedate")
    private String duedate;
    @Column(name = "paid_amount")
    private Double paidAmount;
    @Column(name = "received_date")
    private String receivedDate;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invid")
    private Integer invid;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "sumbitdate")
    private String sumbitdate;

    public Invoice() {
    }

    public Invoice(Integer invid) {
        this.invid = invid;
    }

    public String getJobno() {
        return jobno;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno;
    }

    public String getJobdate() {
        return jobdate;
    }

    public void setJobdate(String jobdate) {
        this.jobdate = jobdate;
    }

    public String getInvcomid() {
        return invcomid;
    }

    public void setInvcomid(String invcomid) {
        this.invcomid = invcomid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInvno() {
        return invno;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvdate() {
        return invdate;
    }

    public void setInvdate(String invdate) {
        this.invdate = invdate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
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

    public Integer getInvid() {
        return invid;
    }

    public void setInvid(Integer invid) {
        this.invid = invid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getSumbitdate() {
        return sumbitdate;
    }

    public void setSumbitdate(String sumbitdate) {
        this.sumbitdate = sumbitdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invid != null ? invid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invid == null && other.invid != null) || (this.invid != null && !this.invid.equals(other.invid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.Invoice[ invid=" + invid + " ]";
    }
    
}
