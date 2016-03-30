/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.UsersFacade;
import entities.Users;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@Named(value = "loginManagedBean")
@RequestScoped
public class LoginManagedBean implements Serializable {

    @Inject
    private UsersFacade userEJB;
    
    private String username;
    private String password;
    private HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
    private Users userlogin = (Users)session.getAttribute("userlogin");
    private boolean rememberme = false;

    public boolean isRememberme() {
        return rememberme;
    }

    public void setRememberme(boolean rememberme) {
        this.rememberme = rememberme;
    }

    public Users getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(Users userlogin) {
        this.userlogin = userlogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginManagedBean() {
    }

    public String doCheckLogin() {
        if (userEJB.checkLogin(username, password)) {
            HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("login", "true");
            userlogin = userEJB.getUserByLoginName(username);
            session.setAttribute("userlogin", userlogin);
            if (rememberme) {
                Cookie c = new Cookie("usernameCookie", userlogin.getLoginName());
                c.setMaxAge(60 * 60 * 24 * 3);
                c.setPath("/");
                HttpServletResponse response = (HttpServletResponse) FacesContext.
                        getCurrentInstance().getExternalContext().getResponse();
                response.addCookie(c);
            }
            if (userlogin.getUserRole().equals("saleman")) {
                return "/saleman/home.xhtml";
            } else if (userlogin.getUserRole().equals("salemanager")) {
                return "/salemanager/home.xhtml";
            } else if (userlogin.getUserRole().equals("administrator")) {
                return "/administrator/home.xhtml";
            } else {
                return "login.xhtml";
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Username or Password is incorrect",
                            "Username or Password is incorrect"));
            return "login.xhtml";
        }
    }

    public String doLogOut() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().getExternalContext().getSession(false);      
        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();
        session.removeAttribute("userlogin");
        session.setAttribute("login", "false");
        Cookie[] cookies;
        cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null) {
                    if (cookie.getName().equals("usernameCookie")) {                       
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        HttpServletResponse response = (HttpServletResponse) FacesContext.
                                getCurrentInstance().getExternalContext().getResponse();
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
        }
        return "/login.xhtml";
    }
    
    public String doUpdateProfile() {
        userEJB.edit(userlogin);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Edit Success", "Edit Success !"));
        return "editprofile.xhtml";
    }

}
