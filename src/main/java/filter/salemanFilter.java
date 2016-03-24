/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entities.Users;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class salemanFilter  implements Filter {
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String login = (String) session.getAttribute("login");
        Users user = (Users) session.getAttribute("userlogin");
        String role = user.getUserRole();
        if ((login != null) && (login.equals("true")) && (role.equals("saleman"))) {         
            chain.doFilter(request, response);
        } else if(role.equals("administrator")) {
            httpResponse.sendRedirect("../administrator/home.xhtml");
        } else if(role.equals("salemanager")) {
            httpResponse.sendRedirect("../salemanager/home.xhtml");
        } else {
            httpResponse.sendRedirect("../login.xhtml");
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
