/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "month")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Month.findAll", query = "SELECT m FROM Month m"),
    @NamedQuery(name = "Month.findByMonthID", query = "SELECT m FROM Month m WHERE m.monthID = :monthID"),
    @NamedQuery(name = "Month.findByMonth", query = "SELECT m FROM Month m WHERE m.month = :month"),
    @NamedQuery(name = "Month.findByValue", query = "SELECT m FROM Month m WHERE m.value = :value")})
public class Month implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "monthID")
    private Integer monthID;
    @Column(name = "month")
    private Integer month;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value")
    private Double value;
    @JoinColumn(name = "planID", referencedColumnName = "planID")
    @ManyToOne(optional = false)
    private Plan planID;

    public Month() {
    }

    public Month(Integer monthID) {
        this.monthID = monthID;
    }

    public Integer getMonthID() {
        return monthID;
    }

    public void setMonthID(Integer monthID) {
        this.monthID = monthID;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Plan getPlanID() {
        return planID;
    }

    public void setPlanID(Plan planID) {
        this.planID = planID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monthID != null ? monthID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Month)) {
            return false;
        }
        Month other = (Month) object;
        if ((this.monthID == null && other.monthID != null) || (this.monthID != null && !this.monthID.equals(other.monthID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Month[ monthID=" + monthID + " ]";
    }
    
}
