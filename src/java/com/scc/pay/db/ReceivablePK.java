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
public class ReceivablePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "jobno")
    private String jobno;

    public ReceivablePK() {
    }

    public ReceivablePK(int id, String jobno) {
        this.id = id;
        this.jobno = jobno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobno() {
        return jobno;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (jobno != null ? jobno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReceivablePK)) {
            return false;
        }
        ReceivablePK other = (ReceivablePK) object;
        if (this.id != other.id) {
            return false;
        }
        if ((this.jobno == null && other.jobno != null) || (this.jobno != null && !this.jobno.equals(other.jobno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.ReceivablePK[ id=" + id + ", jobno=" + jobno + " ]";
    }
    
}
