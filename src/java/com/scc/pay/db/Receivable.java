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
@Table(name = "receivable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receivable.findAll", query = "SELECT r FROM Receivable r")})
public class Receivable implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReceivablePK receivablePK;
    @Column(name = "date")
    private String date;
    @Column(name = "invcomid")
    private String invcomid;
    @Column(name = "company")
    private String company;
    @Column(name = "ref")
    private String ref;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "reimbursement")
    private Double reimbursement;
    @Column(name = "service")
    private Double service;
    @Column(name = "vatdata")
    private Integer vatdata;
    @Column(name = "vat")
    private Double vat;
    @Column(name = "total")
    private Double total;
    @Column(name = "whtaxdata")
    private Integer whtaxdata;
    @Column(name = "whtax")
    private Double whtax;
    @Column(name = "advance")
    private Double advance;
    @Column(name = "totalall")
    private Double totalall;
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

    public Receivable() {
    }

    public Receivable(ReceivablePK receivablePK) {
        this.receivablePK = receivablePK;
    }

    public Receivable(int id, String jobno) {
        this.receivablePK = new ReceivablePK(id, jobno);
    }

    public ReceivablePK getReceivablePK() {
        return receivablePK;
    }

    public void setReceivablePK(ReceivablePK receivablePK) {
        this.receivablePK = receivablePK;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Double getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(Double reimbursement) {
        this.reimbursement = reimbursement;
    }

    public Double getService() {
        return service;
    }

    public void setService(Double service) {
        this.service = service;
    }

    public Integer getVatdata() {
        return vatdata;
    }

    public void setVatdata(Integer vatdata) {
        this.vatdata = vatdata;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getWhtaxdata() {
        return whtaxdata;
    }

    public void setWhtaxdata(Integer whtaxdata) {
        this.whtaxdata = whtaxdata;
    }

    public Double getWhtax() {
        return whtax;
    }

    public void setWhtax(Double whtax) {
        this.whtax = whtax;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public Double getTotalall() {
        return totalall;
    }

    public void setTotalall(Double totalall) {
        this.totalall = totalall;
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
        hash += (receivablePK != null ? receivablePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receivable)) {
            return false;
        }
        Receivable other = (Receivable) object;
        if ((this.receivablePK == null && other.receivablePK != null) || (this.receivablePK != null && !this.receivablePK.equals(other.receivablePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.Receivable[ receivablePK=" + receivablePK + " ]";
    }
    
}
