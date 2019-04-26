package controllers.servlets;

import DAO.DAOFactory;
import DAO.MilestoneDAO;
import controllers.services.TimeService;
import domain.model.Milestone;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controllers.servlets.MilestoneServlet.getFullURL;

@SuppressWarnings("Duplicates")
@WebServlet(urlPatterns = "/project/edit/*")
public class EditMilestoneServlet extends HttpServlet {
  private MilestoneDAO dao = DAOFactory.getMilestoneDAO();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String pTitle = request.getParameter("title");
    System.out.println("Editing Milestone from " + getFullURL(request));
    System.out.println("------------------------");
    System.out.println(request.getParameter("mlID") +
        request.getParameter("mlTitle") +
        request.getParameter("mlDescription") +
        request.getParameter("mlStatus") +
        request.getParameter("mlStartDate") +
        request.getParameter("mlDueDate") +
        request.getParameter("mlEndDate"));
    String startDate = request.getParameter("mlStartDate");
    String dueDate = request.getParameter("mlDueDate");
    String endDate = request.getParameter("mlEndDate");

    Milestone newML = new Milestone(
        request.getParameter("mlID"),
        request.getParameter("mlTitle"),
        request.getParameter("mlDescription"),
        request.getParameter("mlStatus"),
        TimeService.getInstance().formatDate(startDate),
        TimeService.getInstance().formatDate(dueDate),
        TimeService.getInstance().formatDate(endDate),
        pTitle
    );
//    System.out.println(dao.milestoneExists(newML.getId()));
    if (dao.milestoneExists(newML.getId())) {
      System.out.println("EXISTS " + newML.getTitle() + newML.getId());
      System.out.println(dao.editMilestone(newML));
    }
    System.out.println("project title" + pTitle);
    request.removeAttribute("mlID");
    response.sendRedirect("/project/?title=" + pTitle);
//    System.out.println("--put--");
    System.out.println(request.getPathTranslated());
//    request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);


  }
}
