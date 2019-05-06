package controllers.servlets;

import DAO.UserDAO;
import controllers.services.AuthenticationService;
import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServlet.class.getName());
	private AuthenticationService authenticationService = new AuthenticationService();
	
	@Override
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		
		HttpSession previousSession = request.getSession(false);
		if (previousSession != null) {
			previousSession.invalidate();
		}
		if (authenticationService.login(username, password)) {
			//generate a new session
			HttpSession newSession = request.getSession(true);
			UserDAO userDB = new UserDAO();
			
			User user = userDB.getUser(username);
			newSession.setAttribute("userobj", user);
			newSession.setAttribute("userID", user.getId());
			newSession.setMaxInactiveInterval(10 * 60);
			
			Cookie cookie = new Cookie("username", username);
			response.addCookie(cookie);
			newSession.setAttribute("username", username);
			LOGGER.info("Login Servlet : " + request.getSession().getAttribute("userobj"));
			response.sendRedirect("/dashboard");
		} else {
			System.out.println("Invalid credentials");
			String errorMessage = "Invalid credentials";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}
}
