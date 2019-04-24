package controllers.servlets;

		import java.io.IOException;
		
		import javax.servlet.Filter;
		import javax.servlet.FilterChain;
		import javax.servlet.FilterConfig;
		import javax.servlet.ServletContext;
		import javax.servlet.ServletException;
		import javax.servlet.ServletRequest;
		import javax.servlet.ServletResponse;
		import javax.servlet.annotation.WebFilter;
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;
		import javax.servlet.http.HttpSession;
/*
ebenezergraham created on 4/24/19
*/
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	
	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		this.context.log("Requested Resource::" + uri);
		
		if(uri.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		if(uri.equals("/") && session ==null){
			res.sendRedirect("/login");
		}else{
			
		
			
				if (session.getAttribute("username") == null) {
				this.context.log("Unauthorized");
				res.sendRedirect("/login");
			} else {
				chain.doFilter(request, response);
			}
		}
		}
		
	public void destroy() {
	}
	
}

