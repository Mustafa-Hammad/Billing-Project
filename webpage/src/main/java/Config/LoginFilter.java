/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/AdvancedFilter.java to edit this template
 */
package Config;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nora
 */
public class LoginFilter implements Filter {

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest reqHttp = (HttpServletRequest) request;
        HttpServletResponse resHttp = (HttpServletResponse) response;

        HttpSession session = reqHttp.getSession(false);

        if (session == null) {
            resHttp.sendRedirect("/postbaidSystem/login.html");

        } else {
            String loggedIn = (String) session.getAttribute("isAuth");
            System.out.println(loggedIn);
            if (loggedIn != null && loggedIn.equals("true")) {
                chain.doFilter(request, response);

            } else {
                resHttp.sendRedirect("/postbaidSystem/login.html");
            }
        }

    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

}
