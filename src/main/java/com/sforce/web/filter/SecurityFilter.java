package com.sforce.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sforce.to.User;
import com.sforce.util.ThreadLocalHolder;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {
	public static final String SESSION_USER = "CONTEXT_USER";
    /**
     * Default constructor. 
     */
    public SecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		User user = (User) session.getAttribute(SESSION_USER);
		if (null != user) {
			ThreadLocalHolder.setUser(user);
		}
		chain.doFilter(request, response);
		if (null != ThreadLocalHolder.getUser()) {
			session.setAttribute(SESSION_USER, ThreadLocalHolder.getUser());
		} else {
			session.removeAttribute(SESSION_USER);
		}
		
		ThreadLocalHolder.removeUser();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
