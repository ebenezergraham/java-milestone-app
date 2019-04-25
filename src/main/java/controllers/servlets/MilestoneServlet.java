package controllers.servlets;

import DAO.DAOFactory;
import DAO.MilestoneDAO;
import controllers.services.UserService;
import domain.model.Milestone;
import domain.model.Project;
import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/project/*")
public class MilestoneServlet extends HttpServlet {
    MilestoneDAO dao = DAOFactory.getMilestoneDAO();
	private static final Logger LOGGER = LoggerFactory.getLogger(MilestoneServlet.class.getName());

    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        listMilestones(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String ptitle = request.getParameter("title");
        System.out.println("Adding Milestone");
        System.out.println("------------------------");
        Milestone newML = new Milestone(request.getParameter(ptitle),request.getParameter(ptitle), ptitle);
        dao.addMilestone(newML);
        response.sendRedirect(getFullURL(request));

    }

    @Override
    protected void doDelete(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    	
    	if(request.getSession(false)!=null){
	    String projectId = request.getParameter("projectId");
	    String milestoneID = request.getParameter("milestoneId");
	    User name = (User)request.getSession().getAttribute("userobj");
	    dao.deleteMilestone(projectId,milestoneID);
	    }
    }

    private void listMilestones(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        String ptitle = request.getParameter("title");
        MilestoneDAO dao = new MilestoneDAO();
        List<Milestone> allM = dao.findMilestones(ptitle);
        request.setAttribute("allMilestones",allM);
        request.setAttribute("title",ptitle);
        request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request,response);
    }

    public static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

}
