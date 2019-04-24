package controllers.servlets;

import controllers.services.UserService;
import domain.model.Project;

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

  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      String name = (String) session.getAttribute("username");
      System.out.println("Session username: " + name);
      List<Project> projectList = UserService.getInstance().getUser(name).getProjects();
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
}
