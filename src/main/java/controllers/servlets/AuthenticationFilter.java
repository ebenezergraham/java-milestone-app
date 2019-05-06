package controllers.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
ebenezergraham created on 4/24/19
*/
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	
	private ServletContext context;
	
	public void init(FilterConfig fConfig) {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getServletPath();
		HttpSession session = req.getSession(false);
		this.context.log("Requested Resource::" + uri);
		
		if (req.getRequestURL().toString().matches(".*(css|jpg|png|gif|js)")) {
			chain.doFilter(request, response);
		} else if (uri.equals("/login") || uri.equals("/register") || uri.equals("/view") || uri.equals("/readonly")) {
			chain.doFilter(request, response);
		}
		
		if (uri.equals("/") || session == null) {
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		} else if (session.getAttribute("username") == null) {
			this.context.log("Unauthorized Access");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {
	}
	
}

