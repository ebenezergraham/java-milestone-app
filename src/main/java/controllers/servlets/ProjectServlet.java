package controllers.servlets;

import DAO.DAOFactory;
import DAO.ProjectDAO;
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

@WebServlet(urlPatterns = "/projects")
public class ProjectServlet extends HttpServlet {
    private ProjectDAO dao = DAOFactory.getProjectDAO();
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
      String ptitle = request.getParameter("project");
	    System.out.println("title: "+ ptitle);
	    String userId = (String) request.getSession().getAttribute("userID");
        if (ptitle != null) {
            if (dao.getProject(userId,ptitle)==null){
              dao.addProject(new Project(ptitle,userId));
              System.out.println("Project added !");
            }else{
              System.out.println("project exists already!");
            }
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
