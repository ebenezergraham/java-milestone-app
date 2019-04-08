package controllers.servlets;

import domain.model.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    List<Project> projectList = new ArrayList<>();
    Project project = new Project();
    Project project1 = new Project();
    Project project2 = new Project();
    Project project3 = new Project();
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        projectList.add(project);
        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);
        request.setAttribute("projectList",projectList);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
