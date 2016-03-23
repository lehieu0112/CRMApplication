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
        userEJB.create(user);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success",
                "Create Success !"));
        return "adduser.xhtml";
    }

    public List<Users> doFindAllUsers() {
        return userEJB.findAll();
    }

    public void doFindUserByID() {
        this.user = userEJB.find(user.getUserID());
    }

    public String doActive() {
        userEJB.activeUser();
        return "listusers.xhtml";
    }

    public String doDeActive() {
        userEJB.deactiveUser();
        return "listusers.xhtml";
    }

    public String doDeleteUser() {
        userEJB.deleteUser();
        return "listusers.xhtml";
    }
}
