package controllers.servlets;

import DAO.DAOFactory;
import DAO.ProjectDAO;
import com.google.gson.Gson;
import domain.model.Project;
import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
  ProjectDAO dao = DAOFactory.getProjectDAO();
  private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServlet.class.getName());
  
  
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      User usr = (User) session.getAttribute("userobj");
      System.out.println("Dashboard Servlet - Session username: " + usr.getUserName());
      List<Project> projectList = dao.findProjects(usr.getId());
      request.setAttribute("projectList", projectList);
      request.getRequestDispatcher("index.jsp").forward(request, response);
    } else {
      response.sendRedirect("/login");
    }

  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

  }
  
  @Override
  protected void doDelete(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    LOGGER.info("deleting project");
    if(request.getSession(false)!= null){
    //String projectId = request.getParameter("projectId");
    String projectId = request.getReader().readLine().split("=")[1];
    projectId = projectId.replaceAll("%20"," ");
      System.out.println(projectId);
    User name = (User)request.getSession().getAttribute("userobj");
    if(dao.deleteProject(projectId)) {
      response.setStatus(200);
    }
    }else {
      response.setStatus(403);
    }
  }
}
