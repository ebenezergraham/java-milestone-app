package controllers.servlets;

import DAO.DAOFactory;
import DAO.MilestoneDAO;
import DAO.ProjectDAO;
import domain.model.Milestone;
import domain.model.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/project/completed")
public class completedMilestones extends HttpServlet {
	MilestoneDAO dao = DAOFactory.getMilestoneDAO();
	ProjectDAO daoProject = DAOFactory.getProjectDAO();
	
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		System.out.println("COMPLETED GET");
		Project pr = daoProject.getProject(title);
		if (pr != null && pr.getUserId().equals(request.getSession().getAttribute("userID"))) {
			request.setAttribute("title", title);
			List<Milestone> allM = dao.completedMilestones(pr.getId());
			System.out.println(allM.size());
			request.setAttribute("allMilestones", allM);
			request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);
		} else {
			response.sendRedirect("/project/?title=" + title);
		}
	}
}
