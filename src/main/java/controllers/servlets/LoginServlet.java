package controllers.servlets;

import controllers.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    System.out.printf("Username: %s\tPassword: %s", name, password);

    if (LoginService.isUserValid(name, password)) {
      request.getSession().setAttribute("name", name);
      System.out.println(request.getSession().getAttribute("name"));
      response.sendRedirect("/");
    } else {
      System.out.println("Invalid credentials");
      String errorMessage = "Invalid credentials";
      request.setAttribute("Invalid credentials", errorMessage);
      request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
  }
}
