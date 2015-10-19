/*
 * To change this template, choose Tools | Templates
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
@Table(name = "tb_bank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbBank.findAll", query = "SELECT t FROM TbBank t")})
public class TbBank implements Serializable {
    @Column(name = "\r\nmonetaryusd")
    private String monetaryusd;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bankid")
    private Integer bankid;
    @Column(name = "bankname")
    private String bankname;
    @Column(name = "banknamesh")
    private String banknamesh;
    @Column(name = "bankdesc")
    private String bankdesc;
    @Column(name = "enttime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enttime;
    @Column(name = "entuser")
    private String entuser;
    @Column(name = "updtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updtime;
    @Column(name = "upduser")
    private String upduser;
    @Column(name = "updlcnt")
    private Integer updlcnt;

    public TbBank() {
    }

    public TbBank(Integer bankid) {
        this.bankid = bankid;
    }

    public Integer getBankid() {
        return bankid;
    }

    public void setBankid(Integer bankid) {
        this.bankid = bankid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBanknamesh() {
        return banknamesh;
    }

    public void setBanknamesh(String banknamesh) {
        this.banknamesh = banknamesh;
    }

    public String getBankdesc() {
        return bankdesc;
    }

    public void setBankdesc(String bankdesc) {
        this.bankdesc = bankdesc;
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

    public Integer getUpdlcnt() {
        return updlcnt;
    }

    public void setUpdlcnt(Integer updlcnt) {
        this.updlcnt = updlcnt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankid != null ? bankid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbBank)) {
            return false;
        }
        TbBank other = (TbBank) object;
        if ((this.bankid == null && other.bankid != null) || (this.bankid != null && !this.bankid.equals(other.bankid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.TbBank[ bankid=" + bankid + " ]";
    }

    public String getMonetaryusd() {
        return monetaryusd;
    }

    public void setMonetaryusd(String monetaryusd) {
        this.monetaryusd = monetaryusd;
    }
    
}
