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
 * @author ANNA
 */
@Entity
@Table(name = "TB14_TITLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tb14Title.findAll", query = "SELECT t FROM Tb14Title t")})
public class Tb14Title implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TITLE_CODE")
    private String titleCode;
    @Column(name = "TITLE_NAMETH")
    private String titleNameth;
    @Column(name = "TITLE_NAMEEN")
    private String titleNameen;
    @Column(name = "TITLE_NAMETHSH")
    private String titleNamethsh;
    @Column(name = "TITLE_ACTIVE")
    private String titleActive;
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

    public Tb14Title() {
    }

    public Tb14Title(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getTitleNameth() {
        return titleNameth;
    }

    public void setTitleNameth(String titleNameth) {
        this.titleNameth = titleNameth;
    }

    public String getTitleNameen() {
        return titleNameen;
    }

    public void setTitleNameen(String titleNameen) {
        this.titleNameen = titleNameen;
    }

    public String getTitleNamethsh() {
        return titleNamethsh;
    }

    public void setTitleNamethsh(String titleNamethsh) {
        this.titleNamethsh = titleNamethsh;
    }

    public String getTitleActive() {
        return titleActive;
    }

    public void setTitleActive(String titleActive) {
        this.titleActive = titleActive;
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
        hash += (titleCode != null ? titleCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tb14Title)) {
            return false;
        }
        Tb14Title other = (Tb14Title) object;
        if ((this.titleCode == null && other.titleCode != null) || (this.titleCode != null && !this.titleCode.equals(other.titleCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.nstda.rdconline.db.Tb14Title[ titleCode=" + titleCode + " ]";
    }
    
}
