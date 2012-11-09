package config;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class StaticServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Tomcat, Jetty, JBoss, and GlassFish 
    private static final String COMMON_DEFAULT_SERVLET_NAME = "default";


    public String scanDefaultServlet(){
        return COMMON_DEFAULT_SERVLET_NAME;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getNamedDispatcher(this.scanDefaultServlet());
        HttpServletRequest wrapped = new HttpServletRequestWrapper(req) {
                public String getServletPath() {return "";}
        };
        rd.forward(wrapped, resp);
    }
}