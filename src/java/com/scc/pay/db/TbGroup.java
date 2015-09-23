/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "tb_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbGroup.findAll", query = "SELECT t FROM TbGroup t")})
public class TbGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbGroupPK tbGroupPK;
    @Basic(optional = false)
    @Column(name = "data")
    private String data;
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

    public TbGroup() {
    }

    public TbGroup(TbGroupPK tbGroupPK) {
        this.tbGroupPK = tbGroupPK;
    }

    public TbGroup(TbGroupPK tbGroupPK, String data) {
        this.tbGroupPK = tbGroupPK;
        this.data = data;
    }

    public TbGroup(int id, String type) {
        this.tbGroupPK = new TbGroupPK(id, type);
    }

    public TbGroupPK getTbGroupPK() {
        return tbGroupPK;
    }

    public void setTbGroupPK(TbGroupPK tbGroupPK) {
        this.tbGroupPK = tbGroupPK;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        hash += (tbGroupPK != null ? tbGroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbGroup)) {
            return false;
        }
        TbGroup other = (TbGroup) object;
        if ((this.tbGroupPK == null && other.tbGroupPK != null) || (this.tbGroupPK != null && !this.tbGroupPK.equals(other.tbGroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scc.pay.db.TbGroup[ tbGroupPK=" + tbGroupPK + " ]";
    }
    
}
