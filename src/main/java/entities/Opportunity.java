/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "opportunity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opportunity.findAll", query = "SELECT o FROM Opportunity o"),
    @NamedQuery(name = "Opportunity.findByOpportunityID", query = "SELECT o FROM Opportunity o WHERE o.opportunityID = :opportunityID"),
    @NamedQuery(name = "Opportunity.findByOpportunityDate", query = "SELECT o FROM Opportunity o WHERE o.opportunityDate = :opportunityDate"),
    @NamedQuery(name = "Opportunity.findByQuantity", query = "SELECT o FROM Opportunity o WHERE o.quantity = :quantity")})
public class Opportunity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "opportunityID")
    private Integer opportunityID;
    @Column(name = "opportunityDate")
    @Temporal(TemporalType.DATE)
    private Date opportunityDate;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "leadID", referencedColumnName = "leadID")
    @ManyToOne(optional = false)
    private Leads leadID;
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    @ManyToOne(optional = false)
    private Products productID;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Users userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opportunityID")
    private List<Orders> ordersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opportunityID")
    private List<Follow> followList;

    public Opportunity() {
    }

    public Opportunity(Integer opportunityID) {
        this.opportunityID = opportunityID;
    }

    public Integer getOpportunityID() {
        return opportunityID;
    }

    public void setOpportunityID(Integer opportunityID) {
        this.opportunityID = opportunityID;
    }

    public Date getOpportunityDate() {
        return opportunityDate;
    }

    public void setOpportunityDate(Date opportunityDate) {
        this.opportunityDate = opportunityDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Leads getLeadID() {
        return leadID;
    }

    public void setLeadID(Leads leadID) {
        this.leadID = leadID;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @XmlTransient
    public List<Follow> getFollowList() {
        return followList;
    }

    public void setFollowList(List<Follow> followList) {
        this.followList = followList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opportunityID != null ? opportunityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opportunity)) {
            return false;
        }
        Opportunity other = (Opportunity) object;
        if ((this.opportunityID == null && other.opportunityID != null) || (this.opportunityID != null && !this.opportunityID.equals(other.opportunityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Opportunity[ opportunityID=" + opportunityID + " ]";
    }
    
}
