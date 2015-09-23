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
@Table(name = "tb_currency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCurrency.findAll", query = "SELECT t FROM TbCurrency t")})
public class TbCurrency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "currencyid")
    private Integer currencyid;
    @Basic(optional = false)
    @Column(name = "currencyname")
    private String currencyname;
    @Basic(optional = false)
    @Column(name = "currencydesc")
    private String currencydesc;
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

    public TbCurrency() {
    }

    public TbCurrency(Integer currencyid) {
        this.currencyid = currencyid;
    }

    public TbCurrency(Integer currencyid, String currencyname, String currencydesc) {
        this.currencyid = currencyid;
        this.currencyname = currencyname;
        this.currencydesc = currencydesc;
    }

    public Integer getCurrencyid() {
        return currencyid;
    }

    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    public String getCurrencyname() {
        return currencyname;
    }

    public void setCurrencyname(String currencyname) {
        this.currencyname = currencyname;
    }

    public String getCurrencydesc() {
        return currencydesc;
    }

    public void setCurrencydesc(String currencydesc) {
        this.currencydesc = currencydesc;
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
        hash += (currencyid != null ? currencyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCurrency)) {
            return false;
        }
        TbCurrency other = (TbCurrency) object;
        if ((this.currencyid == null && other.currencyid != null) || (this.currencyid != null && !this.currencyid.equals(other.currencyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.TbCurrency[ currencyid=" + currencyid + " ]";
    }
    
}
