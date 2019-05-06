package controllers.servlets;

import DAO.DAOFactory;
import DAO.ProjectDAO;
import java.util.List;
import domain.model.Project;
import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServlet.class.getName());
	ProjectDAO dao = DAOFactory.getProjectDAO();
	
	@Override
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
			User usr = (User) request.getSession(false).getAttribute("userobj");
			List<Project> projectList = dao.findProjects(usr.getId());
			request.setAttribute("projectList", projectList);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException, IOException {
		String ptitle = request.getParameter("project");
		System.out.println("title: " + ptitle);
		String userId = (String) request.getSession().getAttribute("userID");
		if (ptitle != null) {
			if (dao.getProject(userId, ptitle) == null) {
				dao.addProject(new Project(ptitle, userId));
				System.out.println("Project added !");
			} else {
				System.out.println("project exists already!");
			}
		}
		response.sendRedirect("/dashboard");
	}
	
	@Override
	protected void doDelete(HttpServletRequest request,
	                        HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Deleting project ...");
		if (request.getSession(false) != null) {
			String projectId = request.getParameter("projectId");
			LOGGER.info("Project Id: {}",projectId);
			if (dao.deleteProject(projectId)) {
				response.setStatus(200);
			}
		} else {
			response.setStatus(403);

		}
	}
}
