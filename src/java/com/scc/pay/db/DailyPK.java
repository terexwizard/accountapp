/*
 * To change this template, choose Tools | Templates
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
public class DailyPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "dailydate")
    private String dailydate;
    @Basic(optional = false)
    @Column(name = "jobno")
    private String jobno;

    public DailyPK() {
    }

    public DailyPK(String dailydate, String jobno) {
        this.dailydate = dailydate;
        this.jobno = jobno;
    }

    public String getDailydate() {
        return dailydate;
    }

    public void setDailydate(String dailydate) {
        this.dailydate = dailydate;
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
        hash += (dailydate != null ? dailydate.hashCode() : 0);
        hash += (jobno != null ? jobno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DailyPK)) {
            return false;
        }
        DailyPK other = (DailyPK) object;
        if ((this.dailydate == null && other.dailydate != null) || (this.dailydate != null && !this.dailydate.equals(other.dailydate))) {
            return false;
        }
        if ((this.jobno == null && other.jobno != null) || (this.jobno != null && !this.jobno.equals(other.jobno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.DailyPK[ dailydate=" + dailydate + ", jobno=" + jobno + " ]";
    }
    
}
