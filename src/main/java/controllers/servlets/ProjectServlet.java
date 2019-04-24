package controllers.servlets;

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

@WebServlet(urlPatterns = "/projects/*")
public class ProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        listMilestones(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String milestoneID = request.getParameter("id");
        Project project = UserService.getInstance().getUser("hermes").getProjects().get(0);
        System.out.println(milestoneID);
        System.out.println(project.getMilestones().size());

        Milestone delMilestone = project.getMilestone(milestoneID);
        System.out.println("You want to delete this milestone " + delMilestone.getTitle());
        request.setAttribute("project",project);
        System.out.println(request.getSession().getAttribute("project"));
        if (project.deleteMilestone(project.getMilestone(milestoneID))) {
//            request.setAttribute("name", name);
            System.out.println("AM I deleting stuff??");
            response.sendRedirect("/WEB-INF/views/dashboard.jsp");
        } else {
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
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
        String title = request.getParameter("title");
        String n = request.getParameter("project");
        Gson gson = new Gson();
        Project project = UserService.getInstance().getUser("hermes").getProjects().get(0);
        System.out.println("n is "+n);
//        if(n==null) {
        String projectGson =  gson.toJson(project);
//        }
//        else {
//            project =  UserService.getInstance().getUser().getProjects().get(1);
//        }
        request.setAttribute("title",title);
        request.setAttribute("allMilestones", project.getMilestones());
//        request.setAttribute("allMilestones",project.getMilestones());

        request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request,response);

    }

}
