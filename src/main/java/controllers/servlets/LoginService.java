package controllers.servlets;

public class LoginService {
    public boolean isUserValid(String user, String password){
        if (user.equals("mikasa") && password.equals("snk"))
            return true;
        return false;
    }
}
