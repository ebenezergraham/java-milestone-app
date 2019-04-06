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
        Milestone ml = new Milestone("123","ml title","12/12/2010","oiu");
        Milestone ml2 = new Milestone("123","ml title","12/12/2010","oiu");
        Milestone[] allM = {ml,ml2};
        request.setAttribute("title",title);
        request.setAttribute("allMilestones",allM);
//        request.setAttribute("ml",ml);

        request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }
}
