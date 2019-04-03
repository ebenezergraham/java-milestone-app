package controllers.services;

public class LoginService {
    public boolean isUserValid(String user, String password){
        return user.equals("mikasa") && password.equals("snk");
    }
}
