package controllers.servlets;

import domain.model.Milestone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/project/*")
public class ProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        Milestone ml = new Milestone("123","First","Mikasa","12/12/2010","23/12/2010");
        Milestone ml2 = new Milestone("1200","Second","COOL","12/12/2010","12/12/2011");
        Milestone ml3 = new Milestone("1234","First","Mikasa","12/12/2010","23/12/2010");
        Milestone ml4 = new Milestone("1205","Second","COOL","12/12/2010","12/12/2011");
        Milestone ml5 = new Milestone("1236","First","Mikasa","12/12/2010","23/12/2010");
        Milestone ml6 = new Milestone("1207","Second","COOL","12/12/2010","12/12/2011");
        Milestone ml7 = new Milestone("1239","First","Mikasa","12/12/2010","23/12/2010");
        Milestone ml8 = new Milestone("122","Second","COOL","12/12/2010","12/12/2011");
        Milestone[] allM = {ml,ml2,ml3,ml3,ml4,ml5,ml6,ml7,ml8};
        request.setAttribute("title",title);
        request.setAttribute("allMilestones",allM);
        request.setAttribute("ml",ml);

        request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }
}
