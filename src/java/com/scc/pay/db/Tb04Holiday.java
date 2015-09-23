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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iammutun
 */
@Entity
@Table(name = "TB04_HOLIDAY")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tb04Holiday.findAll", query = "SELECT t FROM Tb04Holiday t")})
public class Tb04Holiday implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "HOLI_DATE")
  private String holiDate;
  @Basic(optional = false)
  @Column(name = "HOLI_DESC")
  private String holiDesc;
  @Basic(optional = false)
  @Column(name = "HOLI_YEAR")
  private String holiYear;
  @Column(name = "HOLI_DAY")
  private String holiDay;
  @Column(name = "TENTUSER")
  private String tentuser;
  @Column(name = "TENTTIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date tenttime;
  @Column(name = "TUPDLCNT")
  private Integer tupdlcnt;
  @Column(name = "TUPDUSER")
  private String tupduser;
  @Column(name = "TUPDTIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date tupdtime;

  public Tb04Holiday() {
  }

  public Tb04Holiday(String holiDate) {
    this.holiDate = holiDate;
  }

  public Tb04Holiday(String holiDate, String holiDesc, String holiYear) {
    this.holiDate = holiDate;
    this.holiDesc = holiDesc;
    this.holiYear = holiYear;
  }

  public String getHoliDate() {
    return holiDate;
  }

  public void setHoliDate(String holiDate) {
    this.holiDate = holiDate;
  }

  public String getHoliDesc() {
    return holiDesc;
  }

  public void setHoliDesc(String holiDesc) {
    this.holiDesc = holiDesc;
  }

  public String getHoliYear() {
    return holiYear;
  }

  public void setHoliYear(String holiYear) {
    this.holiYear = holiYear;
  }

  public String getHoliDay() {
    return holiDay;
  }

  public void setHoliDay(String holiDay) {
    this.holiDay = holiDay;
  }

  public String getTentuser() {
    return tentuser;
  }

  public void setTentuser(String tentuser) {
    this.tentuser = tentuser;
  }

  public Date getTenttime() {
    return tenttime;
  }

  public void setTenttime(Date tenttime) {
    this.tenttime = tenttime;
  }

  public Integer getTupdlcnt() {
    return tupdlcnt;
  }

  public void setTupdlcnt(Integer tupdlcnt) {
    this.tupdlcnt = tupdlcnt;
  }

  public String getTupduser() {
    return tupduser;
  }

  public void setTupduser(String tupduser) {
    this.tupduser = tupduser;
  }

  public Date getTupdtime() {
    return tupdtime;
  }

  public void setTupdtime(Date tupdtime) {
    this.tupdtime = tupdtime;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (holiDate != null ? holiDate.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tb04Holiday)) {
      return false;
    }
    Tb04Holiday other = (Tb04Holiday) object;
    if ((this.holiDate == null && other.holiDate != null) || (this.holiDate != null && !this.holiDate.equals(other.holiDate))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.scc.nstda.rdconline.db.Tb04Holiday[ holiDate=" + holiDate + " ]";
  }
  
}
