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
@Table(name = "TB02_AMPHUR")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tb02Amphur.findAll", query = "SELECT t FROM Tb02Amphur t")})
public class Tb02Amphur implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "AMPR_CODE")
  private String amprCode;
  @Column(name = "AMPR_NAME")
  private String amprName;
  @Basic(optional = false)
  @Column(name = "PROV_CODE")
  private String provCode;
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

  public Tb02Amphur() {
  }

  public Tb02Amphur(String amprCode) {
    this.amprCode = amprCode;
  }

  public Tb02Amphur(String amprCode, String provCode) {
    this.amprCode = amprCode;
    this.provCode = provCode;
  }

  public String getAmprCode() {
    return amprCode;
  }

  public void setAmprCode(String amprCode) {
    this.amprCode = amprCode;
  }

  public String getAmprName() {
    return amprName;
  }

  public void setAmprName(String amprName) {
    this.amprName = amprName;
  }

  public String getProvCode() {
    return provCode;
  }

  public void setProvCode(String provCode) {
    this.provCode = provCode;
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
    hash += (amprCode != null ? amprCode.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tb02Amphur)) {
      return false;
    }
    Tb02Amphur other = (Tb02Amphur) object;
    if ((this.amprCode == null && other.amprCode != null) || (this.amprCode != null && !this.amprCode.equals(other.amprCode))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.scc.nstda.rdconline.db.Tb02Amphur[ amprCode=" + amprCode + " ]";
  }
  
}
