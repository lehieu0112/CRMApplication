package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "emailkey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emailkey.findAll", query = "SELECT e FROM Emailkey e"),
    @NamedQuery(name = "Emailkey.findByKeyID", query = "SELECT e FROM Emailkey e WHERE e.keyID = :keyID"),
    @NamedQuery(name = "Emailkey.findByUserEmail", query = "SELECT e FROM Emailkey e WHERE e.userEmail = :userEmail")})
public class Emailkey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "keyID")
    private Integer keyID;
    @Size(max = 50)
    @Column(name = "userEmail")
    private String userEmail;

    public Emailkey() {
    }

    public Emailkey(Integer keyID, String userEmail) {
        this.keyID = keyID;
        this.userEmail = userEmail;
    }

    public Emailkey(Integer keyID) {
        this.keyID = keyID;
    }

    public Integer getKeyID() {
        return keyID;
    }

    public void setKeyID(Integer keyID) {
        this.keyID = keyID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keyID != null ? keyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emailkey)) {
            return false;
        }
        Emailkey other = (Emailkey) object;
        if ((this.keyID == null && other.keyID != null) || (this.keyID != null && !this.keyID.equals(other.keyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Emailkey[ keyID=" + keyID + " ]";
    }

}
