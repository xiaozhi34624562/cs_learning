package com.kaikeba.wx.filter;

import com.kaikeba.bean.Users;
import com.kaikeba.util.UserUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/index.html"})
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();
        Users wxUser = UserUtil.getWxUser(session);
        if(wxUser != null)
            chain.doFilter(req, resp);
        else
            ((HttpServletResponse)resp).sendRedirect("login.html");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
