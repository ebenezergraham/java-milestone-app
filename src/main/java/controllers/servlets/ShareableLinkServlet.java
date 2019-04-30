package controllers.servlets;

import DAO.DAOFactory;
import DAO.ProjectDAO;
import controllers.services.ShareableLinkService;
import domain.model.Milestone;
import domain.model.Project;
import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/view/*")
public class ShareableLinkServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShareableLinkServlet.class.getName());
	ProjectDAO dao = DAOFactory.getProjectDAO();
	ShareableLinkService shareableLinkService = new ShareableLinkService();
	
	@Override
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		System.out.println("url" + request.getRequestURL());
		String id = request.getRequestURL().toString().split("/")[4];
		if (!id.equalsIgnoreCase("project.jsp")) {
			Map<String, Object> result = shareableLinkService.getMilestones(id);
			request.setAttribute("title", dao.getProject(result.get("id").toString()).getTitle());
			request.setAttribute("id", result.get("id"));
			request.setAttribute("visibility", "hidden");
			request.setAttribute("allMilestones", result.get("milestones"));
			
			request.getRequestDispatcher("/WEB-INF/views/project.jsp").include(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("projectId");
		System.out.println(id);
		String result = shareableLinkService.generateLink(id);
		if (result == null) {
			response.setStatus(403);
		} else {
			response.setStatus(200);
			response.getWriter().print(result);
		}
	}
}
