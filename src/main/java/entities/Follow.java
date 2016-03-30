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
@Table(name = "follow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Follow.findAll", query = "SELECT f FROM Follow f"),
    @NamedQuery(name = "Follow.findByFollowID", query = "SELECT f FROM Follow f WHERE f.followID = :followID"),
    @NamedQuery(name = "Follow.findByFollowDate", query = "SELECT f FROM Follow f WHERE f.followDate = :followDate"),
    @NamedQuery(name = "Follow.findByFollowContent", query = "SELECT f FROM Follow f WHERE f.followContent = :followContent"),
    @NamedQuery(name = "Follow.findByFollowResponse", query = "SELECT f FROM Follow f WHERE f.followResponse = :followResponse"),
    @NamedQuery(name = "Follow.findByFollowNextPlan", query = "SELECT f FROM Follow f WHERE f.followNextPlan = :followNextPlan")})
public class Follow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "followID")
    private Integer followID;
    @Column(name = "followDate")
    @Temporal(TemporalType.DATE)
    private Date followDate;
    @Size(max = 50)
    @Column(name = "followContent")
    private String followContent;
    @Size(max = 255)
    @Column(name = "followResponse")
    private String followResponse;
    @Size(max = 255)
    @Column(name = "followNextPlan")
    private String followNextPlan;
    @JoinColumn(name = "opportunityID", referencedColumnName = "opportunityID")
    @ManyToOne(optional = false)
    private Opportunity opportunityID;
    @Column(name = "nextDate")
    @Temporal(TemporalType.DATE)
    private Date nextDate;

    
    public Follow() {
        opportunityID = new Opportunity();
    }

    public Follow(Integer followID) {
        this.followID = followID;
    }

    public Integer getFollowID() {
        return followID;
    }

    public void setFollowID(Integer followID) {
        this.followID = followID;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    public String getFollowContent() {
        return followContent;
    }

    public void setFollowContent(String followContent) {
        this.followContent = followContent;
    }

    public String getFollowResponse() {
        return followResponse;
    }

    public void setFollowResponse(String followResponse) {
        this.followResponse = followResponse;
    }

    public String getFollowNextPlan() {
        return followNextPlan;
    }

    public void setFollowNextPlan(String followNextPlan) {
        this.followNextPlan = followNextPlan;
    }

    public Opportunity getOpportunityID() {
        return opportunityID;
    }

    public void setOpportunityID(Opportunity opportunityID) {
        this.opportunityID = opportunityID;
    }
    
    public Date getNextDate() {
        return nextDate;
    }

    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (followID != null ? followID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Follow)) {
            return false;
        }
        Follow other = (Follow) object;
        if ((this.followID == null && other.followID != null) || (this.followID != null && !this.followID.equals(other.followID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Follow[ followID=" + followID + " ]";
    }
    
}
