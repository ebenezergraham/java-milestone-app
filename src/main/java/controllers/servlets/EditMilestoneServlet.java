package controllers.servlets;

import DAO.H2Milestone;
import DAO.H2Project;
import com.google.gson.Gson;
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

import static controllers.servlets.MilestoneServlet.getFullURL;

@WebServlet(urlPatterns = "/project/edit/*")
public class EditMilestoneServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    String ptitle = request.getParameter("title");
    System.out.println("Editing Milestone from "+ getFullURL(request));
    System.out.println("------------------------");
    System.out.println(request.getParameter("mlID")+
        request.getParameter("mlTitle")+
        request.getParameter("mlDescription")+
        request.getParameter("mlStatus")+
        request.getParameter("mlStartDate")+
        request.getParameter("mlDueDate")+
        request.getParameter("mlEndDate"));


    H2Milestone dao = new H2Milestone();
    Milestone newML = new Milestone(
        request.getParameter("mlID"),
        request.getParameter("mlTitle"),
        request.getParameter("mlDescription"),
        request.getParameter("mlStatus"),
        request.getParameter("mlStartDate"),
        request.getParameter("mlDueDate"),
        request.getParameter("mlEndDate"),
        ptitle
        );
//    System.out.println(dao.milestoneExists(newML.getId()));
    if(dao.milestoneExists(newML.getId())) {
      System.out.println("NEW ML title and ID "+newML.getTitle()+ newML.getId());
      dao.editMilestone(newML);
    }
    System.out.println("project title"+ptitle);
    request.removeAttribute("mlID");
    response.sendRedirect("/project/?title="+ptitle);
//    System.out.println("--put--");
    System.out.println(request.getPathTranslated());
//    request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);


  }
}
