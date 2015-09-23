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
@Table(name = "TB03_TUMBOL")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tb03Tumbol.findAll", query = "SELECT t FROM Tb03Tumbol t")})
public class Tb03Tumbol implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "TUMB_CODE")
  private String tumbCode;
  @Column(name = "TUMB_NAME")
  private String tumbName;
  @Column(name = "ZIP_CODE")
  private String zipCode;
  @Basic(optional = false)
  @Column(name = "PROV_CODE")
  private String provCode;
  @Basic(optional = false)
  @Column(name = "AMPR_CODE")
  private String amprCode;
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

  public Tb03Tumbol() {
  }

  public Tb03Tumbol(String tumbCode) {
    this.tumbCode = tumbCode;
  }

  public Tb03Tumbol(String tumbCode, String provCode, String amprCode) {
    this.tumbCode = tumbCode;
    this.provCode = provCode;
    this.amprCode = amprCode;
  }

  public String getTumbCode() {
    return tumbCode;
  }

  public void setTumbCode(String tumbCode) {
    this.tumbCode = tumbCode;
  }

  public String getTumbName() {
    return tumbName;
  }

  public void setTumbName(String tumbName) {
    this.tumbName = tumbName;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getProvCode() {
    return provCode;
  }

  public void setProvCode(String provCode) {
    this.provCode = provCode;
  }

  public String getAmprCode() {
    return amprCode;
  }

  public void setAmprCode(String amprCode) {
    this.amprCode = amprCode;
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
    hash += (tumbCode != null ? tumbCode.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tb03Tumbol)) {
      return false;
    }
    Tb03Tumbol other = (Tb03Tumbol) object;
    if ((this.tumbCode == null && other.tumbCode != null) || (this.tumbCode != null && !this.tumbCode.equals(other.tumbCode))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.scc.nstda.rdconline.db.Tb03Tumbol[ tumbCode=" + tumbCode + " ]";
  }
  
}
