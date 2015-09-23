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
@Table(name = "TB01_PROVINCE")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tb01Province.findAll", query = "SELECT t FROM Tb01Province t")})
public class Tb01Province implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "PROV_CODE")
  private String provCode;
  @Column(name = "PROV_NAME")
  private String provName;
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

  public Tb01Province() {
  }

  public Tb01Province(String provCode) {
    this.provCode = provCode;
  }

  public String getProvCode() {
    return provCode;
  }

  public void setProvCode(String provCode) {
    this.provCode = provCode;
  }

  public String getProvName() {
    return provName;
  }

  public void setProvName(String provName) {
    this.provName = provName;
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
    hash += (provCode != null ? provCode.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tb01Province)) {
      return false;
    }
    Tb01Province other = (Tb01Province) object;
    if ((this.provCode == null && other.provCode != null) || (this.provCode != null && !this.provCode.equals(other.provCode))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.scc.nstda.rdconline.db.Tb01Province[ provCode=" + provCode + " ]";
  }
  
}
