package controllers.servlets;

import controllers.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout", name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
  
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession(false);
      if (session != null) {
        session.invalidate();
      }
      response.sendRedirect(request.getContextPath() + "/login");
    }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.doGet(request, response);
  }
}
