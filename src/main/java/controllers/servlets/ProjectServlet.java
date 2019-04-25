package controllers.servlets;

import DAO.H2Project;
import DAO.H2db;
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
    H2Project dao = new H2Project();
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
      System.out.println("+++++++++++++++++++++++++++++++++++++");
      String ptitle = request.getParameter("project");
	    System.out.println("title: "+ ptitle);
	    String userId = ((User) request.getSession().getAttribute("userobj")).getId();
//      String p = request.getMethod();
//      System.out.println("request method "+p);

        if (ptitle != null) {
            Project project = new Project();
//            project.setTitle(ptitle);
//            (dao.getProject(userId,ptitle);
            if (dao.getProject(userId,ptitle)==null){
              dao.addProject(new Project(ptitle,userId));
              System.out.println("Project added !");
              System.out.println("success!");
            }else{
              System.out.println("project exists already!");
            }
//            project.setUserId(request.getAttribute("username").toString());
//            System.out.println(request.getAttribute("username").toString());
//            User s=request.getSession().getAttribute("username");
//            project.setUserId(request.getSession().getAttribute("username"));
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
