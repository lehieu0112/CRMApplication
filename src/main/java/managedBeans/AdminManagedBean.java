package managedBeans;

import ejb.UsersFacade;
import entities.Users;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "adminManagedBean")
@RequestScoped
public class AdminManagedBean {

    @Inject
    private UsersFacade userEJB;
    private Users user = new Users();
    private List<Users> userList;

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public String doCreateUser() {
        if (userEJB.isExistLeads(user)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "User Email is Existed", "User Email is Existed"));
        } else {
            userEJB.create(user);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success", "Create Success !"));
            user = new Users();
        }
        return "adduser.xhtml";
    }

    public List<Users> doFindAllUsers() {
        return userEJB.findAll();
    }

    public void doFindUserByID() {
        this.user = userEJB.find(user.getUserID());
    }

    public String doEditUser() {
        userEJB.edit(user);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Edit Success", "Edit Success !"));
        return "edituser.xhtml";
    }

    public String doActive(Integer userID) {
        userEJB.activeUser(userID);
        return "listusers.xhtml";
    }

    public String doDeActive(Integer userID) {
        userEJB.deactiveUser(userID);
        return "listusers.xhtml";
    }

    public String doDeleteUser(Integer userID) {
        userEJB.remove(userEJB.find(userID));
        return "listusers.xhtml";
    }
}
