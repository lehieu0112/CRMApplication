package managedBeans;

import ejb.EmailkeyFacade;
import ejb.UsersFacade;
import entities.Emailkey;
import entities.Users;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.MessagingException;
import util.MailUtil;

@Named(value = "adminManagedBean")
@RequestScoped
public class AdminManagedBean {

    @Inject
    private UsersFacade userEJB;
    private Users user = new Users();

    @Inject
    private EmailkeyFacade emailkeyEJB;
    private Emailkey emailkey = new Emailkey();

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public void setEmailKey(Emailkey emailkey) {
        this.emailkey = emailkey;
    }

    public Emailkey getEmailKey() {
        return emailkey;
    }

    public String doCreateUser() {
        if (userEJB.isExistUsers(user)) {
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

    public void doFindUserByEmail() {
        userEJB.doFindUserByEmail(user);
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

    public String doCheckEmail(String userEmail) {
        if (userEJB.isExistUsers(user)) {
            int keyID = (int) (Math.random() * 10000);
            emailkey = new Emailkey(keyID, userEmail);
            emailkeyEJB.create(emailkey);

            String to = userEmail;
            String from = "iviettechwebshopdemo@gmail.com";
            String subject = "Reset Your Password from Webshop";
            String body = "Thanks for use webshop from us. "
                    + "You must check this link to reset your password to login webshop.\n"
                    + "http://localhost:8080/CRMSystem/faces/resetpassword.xhtml?userEmail=" + userEmail
                    + "&keyID=" + keyID + "\n"
                    + "Please contact us if you have any questions.\n"
                    + "Have a great day and thanks again!\n";
            boolean isBodyHTML = false;
            try {
                MailUtil.sendEmail(to, from, subject, body, isBodyHTML);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Send Email Success", "Send Email Success"));
            } catch (MessagingException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Send Email Fail", "Send Email Fail"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "User Email is not Existed", "User Email is not Existed"));
        }
        return "emailvalidate.xhtml";
    }

    public String doResetPassword(Integer keyID, String userEmail, String loginPass) {
        emailkey = new Emailkey(keyID, userEmail);
        if (emailkeyEJB.checkEmailKey(emailkey)) {
            userEJB.resetPass(userEmail, loginPass);
            emailkeyEJB.remove(emailkey);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Reset password success", "Reset password success"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Link has been deleted", "Link has been deleted"));
        }
        return "resetpassword.xhtml";
    }

}
