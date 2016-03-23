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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserID", query = "SELECT u FROM Users u WHERE u.userID = :userID"),
    @NamedQuery(name = "Users.findByUserFullName", query = "SELECT u FROM Users u WHERE u.userFullName = :userFullName"),
    @NamedQuery(name = "Users.findByUserAddress", query = "SELECT u FROM Users u WHERE u.userAddress = :userAddress"),
    @NamedQuery(name = "Users.findByUserPhone", query = "SELECT u FROM Users u WHERE u.userPhone = :userPhone"),
    @NamedQuery(name = "Users.findByUserEmail", query = "SELECT u FROM Users u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "Users.findByDateCreated", query = "SELECT u FROM Users u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "Users.findByLoginName", query = "SELECT u FROM Users u WHERE u.loginName = :loginName"),
    @NamedQuery(name = "Users.findByLoginPass", query = "SELECT u FROM Users u WHERE u.loginPass = :loginPass"),
    @NamedQuery(name = "Users.findByUserRole", query = "SELECT u FROM Users u WHERE u.userRole = :userRole"),
    @NamedQuery(name = "Users.findByIsActive", query = "SELECT u FROM Users u WHERE u.isActive = :isActive")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userID")
    private Integer userID;
    @Size(max = 50)
    @Column(name = "userFullName")
    private String userFullName;
    @Size(max = 255)
    @Column(name = "userAddress")
    private String userAddress;
    @Size(max = 50)
    @Column(name = "userPhone")
    private String userPhone;
    @Size(max = 50)
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "dateCreated")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Size(max = 50)
    @Column(name = "loginName")
    private String loginName;
    @Size(max = 50)
    @Column(name = "loginPass")
    private String loginPass;
    @Size(max = 20)
    @Column(name = "userRole")
    private String userRole;
    @Column(name = "isActive")
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Leads> leadsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Campaign> campaignList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Opportunity> opportunityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<Plan> planList;

    public Users() {
    }

    public Users(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public List<Leads> getLeadsList() {
        return leadsList;
    }

    public void setLeadsList(List<Leads> leadsList) {
        this.leadsList = leadsList;
    }

    @XmlTransient
    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    @XmlTransient
    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    @XmlTransient
    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Users[ userID=" + userID + " ]";
    }

}
