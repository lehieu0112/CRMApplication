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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "leads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leads.findAll", query = "SELECT l FROM Leads l"),
    @NamedQuery(name = "Leads.findByLeadID", query = "SELECT l FROM Leads l WHERE l.leadID = :leadID"),
    @NamedQuery(name = "Leads.findByLeadName", query = "SELECT l FROM Leads l WHERE l.leadName = :leadName"),
    @NamedQuery(name = "Leads.findByLeadDOB", query = "SELECT l FROM Leads l WHERE l.leadDOB = :leadDOB"),
    @NamedQuery(name = "Leads.findByLeadAddress", query = "SELECT l FROM Leads l WHERE l.leadAddress = :leadAddress"),
    @NamedQuery(name = "Leads.findByLeadPhone", query = "SELECT l FROM Leads l WHERE l.leadPhone = :leadPhone"),
    @NamedQuery(name = "Leads.findByLeadCareer", query = "SELECT l FROM Leads l WHERE l.leadCareer = :leadCareer"),
    @NamedQuery(name = "Leads.findByLeadEmail", query = "SELECT l FROM Leads l WHERE l.leadEmail = :leadEmail"),
    @NamedQuery(name = "Leads.findByDateCreated", query = "SELECT l FROM Leads l WHERE l.dateCreated = :dateCreated")})
public class Leads implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "leadID")
    private Integer leadID;
    @Size(max = 50)
    @Column(name = "leadName")
    private String leadName;
    @Column(name = "leadDOB")
    @Temporal(TemporalType.DATE)
    private Date leadDOB;
    @Size(max = 255)
    @Column(name = "leadAddress")
    private String leadAddress;
    @Size(max = 20)
    @Column(name = "leadPhone")
    private String leadPhone;
    @Size(max = 50)
    @Column(name = "leadCareer")
    private String leadCareer;
    @Size(max = 100)
    @Column(name = "leadEmail")
    private String leadEmail;
    @Column(name = "dateCreated")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Users userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leadID")
    private List<Opportunity> opportunityList;

    public Leads() {
    }

    public Leads(Integer leadID) {
        this.leadID = leadID;
    }

    public Integer getLeadID() {
        return leadID;
    }

    public void setLeadID(Integer leadID) {
        this.leadID = leadID;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public Date getLeadDOB() {
        return leadDOB;
    }

    public void setLeadDOB(Date leadDOB) {
        this.leadDOB = leadDOB;
    }

    public String getLeadAddress() {
        return leadAddress;
    }

    public void setLeadAddress(String leadAddress) {
        this.leadAddress = leadAddress;
    }

    public String getLeadPhone() {
        return leadPhone;
    }

    public void setLeadPhone(String leadPhone) {
        this.leadPhone = leadPhone;
    }

    public String getLeadCareer() {
        return leadCareer;
    }

    public void setLeadCareer(String leadCareer) {
        this.leadCareer = leadCareer;
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    @XmlTransient
    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leadID != null ? leadID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leads)) {
            return false;
        }
        Leads other = (Leads) object;
        if ((this.leadID == null && other.leadID != null) || (this.leadID != null && !this.leadID.equals(other.leadID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Leads[ leadID=" + leadID + " ]";
    }
    
}
