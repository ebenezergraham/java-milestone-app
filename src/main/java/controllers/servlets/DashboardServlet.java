package controllers.servlets;

import controllers.services.UserService;
import domain.model.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    
    List<Project> projectList;
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        projectList = UserService.getInstance().getUser().getProjects();
        request.setAttribute("projectList",projectList);
        request.getRequestDispatcher("index.jsp").forward(request,response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        
    }
}
