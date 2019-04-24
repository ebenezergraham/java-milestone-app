package controllers.servlets;

import controllers.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login", name = "RegisterServlet")
public class LoginServlet extends HttpServlet {

  private LoginService mLoginService = new LoginService();

  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("name");
    String password = request.getParameter("password");
    
    HttpSession previousSession = request.getSession(false);
    if(previousSession!=null){
      previousSession.invalidate();
    }
    if (mLoginService.login(username, password)) {
      //generate a new session
      HttpSession newSession = request.getSession(true);
  
      newSession.setMaxInactiveInterval(10*60);
  
//      Cookie cookie = new Cookie("username", username);
//      response.addCookie(cookie);
      newSession.setAttribute("username", username);
      System.out.println(request.getSession().getAttribute("username"));
      response.sendRedirect("/dashboard");
    } else {
      System.out.println("Invalid credentials");
      String errorMessage = "Invalid credentials";
      request.setAttribute("Invalid credentials", errorMessage);
      request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
  }
}
