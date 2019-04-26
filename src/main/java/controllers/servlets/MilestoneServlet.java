package controllers.servlets;

import DAO.DAOFactory;
import DAO.MilestoneDAO;
import controllers.services.TimeService;
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

  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

    String title = request.getParameter("title");
    String query = request.getParameter("completed");
    request.setAttribute("title", title);

    List<Milestone> allM = dao.findMilestones(title);

    if (query.equals("0")) {
      request.setAttribute("allMilestone", getNullMilestone(allM));
    } else if (query.equals("1")) {
      request.setAttribute("allMilestones", getMilestoneBy(allM));
    } else request.setAttribute("allMilestones", allM);

    request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
    String pTitle = request.getParameter("title");
    System.out.println("Adding Milestone");
    System.out.println("------------------------");

    String mlStartDate = request.getParameter("mlStartDate");
    String mlDueDate = request.getParameter("mlDueDate");
    Milestone newML = new Milestone(
        request.getParameter("mlID"),
        request.getParameter("mlTitle"),
        request.getParameter("mlDescription"),
        request.getParameter("mlStatus"),
        mlStartDate.isEmpty() ? "" : TimeService.getInstance().formatDate(mlStartDate),
        mlDueDate.isEmpty() ? "" : TimeService.getInstance().formatDate(mlDueDate),
        pTitle
    );
    System.out.println("the new milestone is " + newML.getTitle());
    dao.addMilestone(newML);
    response.sendRedirect(getFullURL(request));

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
//        } else {
//            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
//        }
//        String title = request.getParameter("title");
//        Project p = UserService.getInstance().getUser().getProjects().get(0);
//        request.setAttribute("title",title);
//        request.setAttribute("allMilestones",project.getMilestones());
//        response.sendRedirect("/dashboard");
//        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request,response);

  }

  private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
  }

  private void insertMilestone(HttpServletRequest request, HttpServletResponse response) {
  }

  private void deleteMilestone(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("yaay");
  }

  private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
  }

  private void updateMilestone(HttpServletRequest request, HttpServletResponse response) {
  }


  private void listMilestones(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
    String ptitle = request.getParameter("title");
    request.setAttribute("title", ptitle);
//        String pID = new H2Project().getProject();
    List<Milestone> allM = dao.findMilestones(ptitle);
    request.setAttribute("allMilestones", allM);
//        String n = request.getParameter("project");
//        Gson gson = new Gson();
//        Project project = UserService.getInstance().getUser("hermes").getProjects().get(0);
//        System.out.println("n is "+n);
//        if(n==null) {
//        String projectGson =  gson.toJson(project);
//        }
//        else {
//            project =  UserService.getInstance().getUser().getProjects().get(1);
//        }
//        request.setAttribute("title",title);
//        request.setAttribute("allMilestones", project.getMilestones());

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
