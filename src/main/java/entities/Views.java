/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Views.findAll", query = "SELECT v FROM Views v"),
    @NamedQuery(name = "Views.findByViewID", query = "SELECT v FROM Views v WHERE v.viewID = :viewID"),
    @NamedQuery(name = "Views.findByViewName", query = "SELECT v FROM Views v WHERE v.viewName = :viewName"),
    @NamedQuery(name = "Views.findByViewDescription", query = "SELECT v FROM Views v WHERE v.viewDescription = :viewDescription"),
    @NamedQuery(name = "Views.findByStartDate", query = "SELECT v FROM Views v WHERE v.startDate = :startDate"),
    @NamedQuery(name = "Views.findByEndDate", query = "SELECT v FROM Views v WHERE v.endDate = :endDate")})
public class Views implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "viewID")
    private Integer viewID;
    @Size(max = 50)
    @Column(name = "viewName")
    private String viewName;
    @Size(max = 255)
    @Column(name = "viewDescription")
    private String viewDescription;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    @ManyToOne
    private Products productID;

    public Views() {
        productID = new Products();
    }

    public Views(Integer viewID) {
        this.viewID = viewID;
    }

    public Integer getViewID() {
        return viewID;
    }

    public void setViewID(Integer viewID) {
        this.viewID = viewID;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewDescription() {
        return viewDescription;
    }

    public void setViewDescription(String viewDescription) {
        this.viewDescription = viewDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (viewID != null ? viewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Views)) {
            return false;
        }
        Views other = (Views) object;
        if ((this.viewID == null && other.viewID != null) || (this.viewID != null && !this.viewID.equals(other.viewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.View[ viewID=" + viewID + " ]";
    }
    
}
