package controllers.servlets;

import DAO.DAOFactory;
import DAO.MilestoneDAO;
import controllers.services.TimeService;
import domain.model.Milestone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controllers.servlets.MilestoneServlet.getFullURL;

@SuppressWarnings("Duplicates")
@WebServlet(urlPatterns = "/project/edit/*")
public class EditMilestoneServlet extends HttpServlet {
	private MilestoneDAO dao = DAOFactory.getMilestoneDAO();
	
	@Override
	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException, IOException {
		String project_id = request.getParameter("id");
		String mlStartDate = request.getParameter("mlStartDate");
		String mlDueDate = request.getParameter("mlDueDate");
		System.out.println("Editing Milestone from " + getFullURL(request));
		System.out.println("------------------------");
		String status = request.getParameter("mlStatus");
		Milestone newML = new Milestone(
				request.getParameter("mlID"),
				request.getParameter("mlTitle"),
				request.getParameter("mlDescription"),
				status == null ? "false":"true",
				mlStartDate.isEmpty() ? "" : TimeService.getInstance().formatDate(mlStartDate),
				mlDueDate.isEmpty() ? "" : TimeService.getInstance().formatDate(mlDueDate),
				project_id
		);
		if (dao.milestoneExists(newML.getId())) {
			System.out.println("EXISTS " + newML.getTitle() + newML.getId());
			System.out.println(dao.editMilestone(newML));

		} else {
			System.out.println("ACTION NOT ALLOWED");
			request.removeAttribute("mlID");
		}
		response.sendRedirect("/project/?id=" + project_id);

	}
}
