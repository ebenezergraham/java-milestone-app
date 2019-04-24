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

		if (uri.equals("/login")|| (uri.equals("/register"))){
			System.out.println("register redirect ..");
			chain.doFilter(request, response);
			System.out.println("About to leave!");
			return;
		}

		else if(session ==null){
			res.sendRedirect("/login");
		}
		else{
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

