/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author terex
 */
@Embeddable
public class BringforwardPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "bfdate")
    private String bfdate;
    @Basic(optional = false)
    @Column(name = "bankid")
    private int bankid;

    public BringforwardPK() {
    }

    public BringforwardPK(String bfdate, int bankid) {
        this.bfdate = bfdate;
        this.bankid = bankid;
    }

    public String getBfdate() {
        return bfdate;
    }

    public void setBfdate(String bfdate) {
        this.bfdate = bfdate;
    }

    public int getBankid() {
        return bankid;
    }

    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bfdate != null ? bfdate.hashCode() : 0);
        hash += (int) bankid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BringforwardPK)) {
            return false;
        }
        BringforwardPK other = (BringforwardPK) object;
        if ((this.bfdate == null && other.bfdate != null) || (this.bfdate != null && !this.bfdate.equals(other.bfdate))) {
            return false;
        }
        if (this.bankid != other.bankid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.BringforwardPK[ bfdate=" + bfdate + ", bankid=" + bankid + " ]";
    }
    
}
