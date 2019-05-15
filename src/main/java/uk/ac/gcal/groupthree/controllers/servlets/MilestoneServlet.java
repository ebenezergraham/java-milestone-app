package uk.ac.gcal.groupthree.controllers.servlets;

import uk.ac.gcal.groupthree.DAO.DAOFactory;
import uk.ac.gcal.groupthree.DAO.MilestoneDAO;
import uk.ac.gcal.groupthree.controllers.services.MilestoneService;
import uk.ac.gcal.groupthree.controllers.services.TimeService;
import uk.ac.gcal.groupthree.DAO.ProjectDAO;
import uk.ac.gcal.groupthree.domain.model.Milestone;
import uk.ac.gcal.groupthree.domain.model.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("Duplicates")
@WebServlet(name = "MilestoneServlet",urlPatterns = "/project/*")

public class MilestoneServlet extends HttpServlet {
	
	private MilestoneDAO dao = DAOFactory.getMilestoneDAO();
	private ProjectDAO daoProject = DAOFactory.getProjectDAO();
	private MilestoneService milestoneService = new MilestoneService();
	
	static String getFullURL(HttpServletRequest request) {
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
		String queryString = request.getQueryString();
		
		if (queryString == null) {
			return requestURL.toString();
		} else {
			return requestURL.append('?').append(queryString).toString();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String status = request.getParameter("completed");
		Project pr = daoProject.getProject(id);
		
		if (pr != null && pr.getUserId().equals(request.getSession().getAttribute("userID"))) {
			request.setAttribute("title", pr.getTitle());
			request.setAttribute("id", pr.getId());
			
			List<Milestone> allM = dao.findMilestones(pr.getId());
			System.out.println(status);
			if (status == null) {
				request.setAttribute("allMilestones", allM);
			} else if (status.equals("0")) {
				request.setAttribute("allMilestones", this.milestoneService.getPendingMilestones(allM));
			} else if (status.equals("1")) {
				request.setAttribute("allMilestones", this.milestoneService.getCompletedMilestones(allM));
			}
			request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);
		} else {
			response.sendRedirect("/dashboard");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("id: " + id);
		System.out.println("Adding Milestone");
		System.out.println("------------------------");
		String status = request.getParameter("mlStatus");
		Project pr = daoProject.getProject(id);
		if (pr != null && pr.getUserId().equals(request.getSession().getAttribute("userID"))) {
			request.setAttribute("id", pr.getId());
			request.setAttribute("title", pr.getTitle());
			String mlStartDate = request.getParameter("mlStartDate");
			String mlDueDate = request.getParameter("mlDueDate");
			Milestone newML = new Milestone(
					request.getParameter("mlID"),
					request.getParameter("mlTitle"),
					request.getParameter("mlDescription"),
					((status == null) || (status.equals("false"))) ? "false":"true",
					mlStartDate.isEmpty() ? "" : TimeService.getInstance().formatDate(mlStartDate),
					mlDueDate.isEmpty() ? "" : TimeService.getInstance().formatDate(mlDueDate),
					pr.getId()
			);
			
			System.out.println("the new milestone is " + newML.getTitle());
			dao.addMilestone(newML);
			response.sendRedirect(getFullURL(request));
		} else {
			response.sendRedirect("/dashboard");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request,
	                        HttpServletResponse response) throws IOException {
		System.out.println("Deleting Milestone");
		System.out.println("-------------------");
		String projectId = request.getParameter("id");
		String ml = request.getParameter("ml");
		System.out.println("do delete milestone param " + ml);
		Project pr = daoProject.getProject(projectId);
		if (pr != null && pr.getUserId().equals(request.getSession().getAttribute("userID"))) {
			if (dao.deleteMilestone(ml, projectId) == 0) {
				response.setStatus(200);
			}		else {
			response.setStatus(403);
			}
			
		}
		response.sendRedirect("/project/?id=" + projectId);
	}
}
