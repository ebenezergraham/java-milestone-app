package uk.ac.gcal.groupthree.controllers.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout", name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			session.setMaxInactiveInterval(1);
		}
		response.sendRedirect(request.getContextPath() + "/login");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		this.doGet(request, response);
	}
}
