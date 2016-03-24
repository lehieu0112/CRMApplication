/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.UsersFacade;
import entities.Users;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/wellcome"})
public class wellcome extends HttpServlet {

    @Inject
    private UsersFacade userEJB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users userlogin;
        String login;
        String username;
        Cookie[] cookies;
        cookies = request.getCookies();
        String url = "/login.xhtml";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c != null) {
                    if (c.getName().equals("usernameCookie")) {
                        if (c.getValue() != null) {
                            if ((c.getPath() != null)) {
                                if (c.getPath().equals("/")) {
                                    username = c.getValue();
                                    userlogin = userEJB.getUserByLoginName(username);
                                    session.setAttribute("userlogin", userlogin);
                                    login = "true";
                                    session.setAttribute("login", login);
                                    if (userlogin.getUserRole().equals("administrator")) {
                                        url = "/administrator/home.xhtml";
                                    } else if (userlogin.getUserRole().equals("salemanager")) {
                                        url = "/salemanager/home.xhtml";
                                    } else if (userlogin.getUserRole().equals("saleman")) {
                                        url = "/saleman/home.xhtml";
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
