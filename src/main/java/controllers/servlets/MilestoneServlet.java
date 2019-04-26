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

  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    String title = request.getParameter("title");
    String completed = request.getParameter("completed");

    Project pr = daoProject.getProject(title);

    if (pr != null && pr.getUserId().equals(request.getSession().getAttribute("userID"))) {
      request.setAttribute("title", title);

      List<Milestone> allM = dao.findMilestones(pr.getId());
      System.out.println(completed);
      if (completed==null){
        request.setAttribute("allMilestones", allM);
      }else if (completed.equals("0")) {
        request.setAttribute("allMilestone", getNullMilestone(allM));
      } else if (completed.equals("1")) {
        request.setAttribute("allMilestones", getMilestoneBy(allM));
      }
      request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);
    } else {
      response.sendRedirect("/dashboard");
    }

  }


  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    String ptitle = request.getParameter("title");
    System.out.println("Adding Milestone");
    System.out.println("------------------------");

    Project pr = daoProject.getProject(ptitle);
    if (pr != null && pr.getUserId().equals(request.getSession().getAttribute("userID"))) {
      request.setAttribute("title", ptitle);
      String mlStartDate = request.getParameter("mlStartDate");
      String mlDueDate = request.getParameter("mlDueDate");

      Milestone newML = new Milestone(
          request.getParameter("mlID"),
          request.getParameter("mlTitle"),
          request.getParameter("mlDescription"),
          request.getParameter("mlStatus"),
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
    System.out.println(request.getServletPath());
    String projectTitle = request.getParameter("title");
    String milestoneT = request.getParameter("ml");
    String milestoneID = request.getParameter("id");
    String name = request.getSession().getAttribute("username").toString();
    Project project = UserService.getInstance().getUser(name).getProject(projectTitle);
    Milestone ml = project.getMilestone(milestoneT);
    project.deleteMilestone(ml);
    System.out.println(milestoneID);
    System.out.println(project.getMilestones().size());

    System.out.println("AM I deleting stuff??");
    response.sendRedirect("/WEB-INF/views/dashboard.jsp");
  }

  private void listMilestones(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
    String ptitle = request.getParameter("title");
    request.setAttribute("title", ptitle);
    List<Milestone> allM = dao.findMilestones(ptitle);
    request.setAttribute("allMilestones", allM);
    request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);

  }

  static String getFullURL(HttpServletRequest request) {
    StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
    String queryString = request.getQueryString();

    if (queryString == null) {
      return requestURL.toString();
    } else {
      return requestURL.append('?').append(queryString).toString();
    }
  }

  private List<Milestone> getMilestoneBy(List<Milestone> milestones) {
    return milestones.stream().filter(milestone -> milestone.getStatus().equals("true")).collect(Collectors.toList());
  }

  private List<Milestone> getNullMilestone(List<Milestone> milestones) {
    return milestones.stream().filter(milestone -> milestone.getStatus() == null).collect(Collectors.toList());
  }
}
