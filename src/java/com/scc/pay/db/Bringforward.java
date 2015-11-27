/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name = "bringforward")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bringforward.findAll", query = "SELECT b FROM Bringforward b")})
public class Bringforward implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BringforwardPK bringforwardPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "received")
    private Double received;
    @Column(name = "paid")
    private Double paid;
    @Column(name = "bpchqrcv")
    private Double bpchqrcv;
    @Column(name = "bpchqpaid")
    private Double bpchqpaid;
    @Column(name = "btchqrcv")
    private Double btchqrcv;
    @Column(name = "btchqpaid")
    private Double btchqpaid;
    @Column(name = "actualmoney")
    private Double actualmoney;
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

    public Bringforward() {
    }

    public Bringforward(BringforwardPK bringforwardPK) {
        this.bringforwardPK = bringforwardPK;
    }

    public Bringforward(String bfdate, int bankid) {
        this.bringforwardPK = new BringforwardPK(bfdate, bankid);
    }

    public BringforwardPK getBringforwardPK() {
        return bringforwardPK;
    }

    public void setBringforwardPK(BringforwardPK bringforwardPK) {
        this.bringforwardPK = bringforwardPK;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Double getBpchqrcv() {
        return bpchqrcv;
    }

    public void setBpchqrcv(Double bpchqrcv) {
        this.bpchqrcv = bpchqrcv;
    }

    public Double getBpchqpaid() {
        return bpchqpaid;
    }

    public void setBpchqpaid(Double bpchqpaid) {
        this.bpchqpaid = bpchqpaid;
    }

    public Double getBtchqrcv() {
        return btchqrcv;
    }

    public void setBtchqrcv(Double btchqrcv) {
        this.btchqrcv = btchqrcv;
    }

    public Double getBtchqpaid() {
        return btchqpaid;
    }

    public void setBtchqpaid(Double btchqpaid) {
        this.btchqpaid = btchqpaid;
    }

    public Double getActualmoney() {
        return actualmoney;
    }

    public void setActualmoney(Double actualmoney) {
        this.actualmoney = actualmoney;
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
        hash += (bringforwardPK != null ? bringforwardPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bringforward)) {
            return false;
        }
        Bringforward other = (Bringforward) object;
        if ((this.bringforwardPK == null && other.bringforwardPK != null) || (this.bringforwardPK != null && !this.bringforwardPK.equals(other.bringforwardPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.Bringforward[ bringforwardPK=" + bringforwardPK + " ]";
    }
    
}
