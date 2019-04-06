package controllers.services;

import domain.model.User;

public class LoginService {
    public static boolean isUserValid(String username, String password){
        User user = UserService.getInstance().getUser();
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
}
