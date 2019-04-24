package controllers.servlets;

import controllers.services.UserService;
import domain.model.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String name = request.getSession().getAttribute("username").toString();
    
        Project project = UserService.getInstance().getUser(name).getProjects().get(0);
        request.setAttribute("title",title);
        request.setAttribute("allMilestones",project.getMilestones());
//        request.setAttribute("ml",ml);

        request.getRequestDispatcher("/WEB-INF/views/test.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }
}
