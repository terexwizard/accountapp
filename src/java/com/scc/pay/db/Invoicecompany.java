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
@Table(name = "invoicecompany")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoicecompany.findAll", query = "SELECT i FROM Invoicecompany i")})
public class Invoicecompany implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "invcomid")
    private String invcomid;
    @Basic(optional = false)
    @Column(name = "companyname")
    private String companyname;
    @Column(name = "taxid")
    private String taxid;
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
    @Column(name = "remark")
    private String remark;
    @Column(name = "address")
    private String address;
    @Column(name = "transport")
    private String transport;

    public Invoicecompany() {
    }

    public Invoicecompany(String invcomid) {
        this.invcomid = invcomid;
    }

    public Invoicecompany(String invcomid, String companyname) {
        this.invcomid = invcomid;
        this.companyname = companyname;
    }

    public String getInvcomid() {
        return invcomid;
    }

    public void setInvcomid(String invcomid) {
        this.invcomid = invcomid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invcomid != null ? invcomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoicecompany)) {
            return false;
        }
        Invoicecompany other = (Invoicecompany) object;
        if ((this.invcomid == null && other.invcomid != null) || (this.invcomid != null && !this.invcomid.equals(other.invcomid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.Invoicecompany[ invcomid=" + invcomid + " ]";
    }
    
}
