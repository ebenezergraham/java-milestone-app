package controllers.servlets;

import DAO.H2db;
import com.google.gson.Gson;
import controllers.services.UserService;
import domain.model.Milestone;
import domain.model.Project;
import domain.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/project")
public class ProjectServlet extends HttpServlet {
    H2db dao = new H2db();
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("project");
	    System.out.println("title: "+ title);
        if (title != null) {
            Project project = new Project();
            project.setTitle(title);
           // dao.addProject(request.getSession().getAttribute("username").toString(),project);
        }
	      response.sendRedirect("/dashboard");
    }

    @Override
    protected void doDelete(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
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
}
