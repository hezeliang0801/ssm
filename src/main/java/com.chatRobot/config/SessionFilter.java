package com.chatRobot.config;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {

    private String excludedPages;

    private String[] excludedPageArray;

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        return;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        boolean isExcludedPage = false;
        for (String page : excludedPageArray) {
            String servletPath = ((HttpServletRequest) request).getServletPath();
            if (((HttpServletRequest) request).getServletPath().equals(page)) {
                isExcludedPage = true;
                break;
            }
        }
        if (isExcludedPage) {
            chain.doFilter(request, response);
        } else {//
            HttpSession session = ((HttpServletRequest) request).getSession();
            if (session == null || session.getAttribute("username") == null) {
                ((HttpServletResponse) response).sendRedirect("/ChatRobot/login.html");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        excludedPages = fConfig.getInitParameter("excludedPages");
        if (null != excludedPages && excludedPages.length() > 0) {
            excludedPageArray = excludedPages.split(";");
        }
        return;
    }
}