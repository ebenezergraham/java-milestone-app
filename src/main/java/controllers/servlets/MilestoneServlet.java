package controllers.servlets;

import DAO.DAOFactory;
import DAO.MilestoneDAO;
import controllers.services.TimeService;
import DAO.ProjectDAO;
import controllers.services.UserService;
import domain.model.Milestone;
import domain.model.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
@WebServlet(urlPatterns = "/project/*")

public class MilestoneServlet extends HttpServlet {
	
	private MilestoneDAO dao = DAOFactory.getMilestoneDAO();
	private ProjectDAO daoProject = DAOFactory.getProjectDAO();
	
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
    System.out.println("completed "+status);
		Project pr = daoProject.getProject(id);
		
		if (pr != null && pr.getUserId().equals(request.getSession().getAttribute("userID"))) {
			request.setAttribute("title", pr.getTitle());
			request.setAttribute("id", pr.getId());
			
			List<Milestone> allM = dao.findMilestones(pr.getId());
			System.out.println(status);
			if (status == null) {
				request.setAttribute("allMilestones", allM);
			} else if (status.equals("0")) {
			  List<Milestone> tr = getPendingMilestones(allM);
				request.setAttribute("allMilestones", tr);
        System.out.println(tr.get(0).getStatus());
			} else if (status.equals("1")) {
				request.setAttribute("allMilestones", getCompletedMilestones(allM));
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
					request.getParameter("mlStatus") == null ? "false" : request.getParameter("mlStatus"),
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
		System.out.println("------------------------");
//		System.out.println(request.getServletPath());
		String projectId = request.getParameter("id");
		String ml = request.getParameter("ml");
		System.out.println("do delete milestone param "+ml);
//		String milestoneID = request.getParameter("id");
//		String username = request.getSession().getAttribute("username").toString();
//		Project project = UserService.getInstance().getUser(name).getProject(projectTitle);
//		Milestone ml = project.getMilestone(milestoneT);
		Project pr = daoProject.getProject(projectId);
		if (pr != null && pr.getUserId().equals(request.getSession().getAttribute("userID"))) {
			System.out.println("deleeting");
			System.out.println();
			if (dao.deleteMilestone(ml,projectId)==0) {
//				response.setStatus(200);
//			}		else {
////			response.setStatus(403);
		}

		}
		response.sendRedirect("/project/?id=" + projectId);

//		project.deleteMilestone(ml);
//		System.out.println(milestoneID);
//		System.out.println(project.getMilestones().size());
		
		System.out.println("AM I deleting stuff??");
		response.sendRedirect(getFullURL(request));

//		response.sendRedirect("/WEB-INF/views/dashboard.jsp");
	}
	
	private void listMilestones(HttpServletRequest request,
	                            HttpServletResponse response) throws ServletException, IOException {
		String ptitle = request.getParameter("title");
		request.setAttribute("title", ptitle);
		List<Milestone> allM = dao.findMilestones(ptitle);
		request.setAttribute("allMilestones", allM);
		request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);
		
	}
	
	private List<Milestone> getCompletedMilestones(List<Milestone> milestones) {
		return milestones.stream().filter(milestone -> milestone.getStatus().equals("true")).collect(Collectors.toList());
	}
	
	private List<Milestone> getPendingMilestones(List<Milestone> milestones) {
		return milestones.stream().filter(milestone -> milestone.getStatus().equals("false")).collect(Collectors.toList());
	}
}
