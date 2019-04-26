package controllers.services;

import DAO.UserDAO;
import com.google.gson.Gson;
import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class.getName());
  private Gson mGson;
  private static UserService instance = null;

  public static UserService getInstance() {
    if (instance == null) instance = new UserService();
    return instance;
  }

  private UserService() {
    this.mGson = new Gson();
  }
  private UserDAO dao = new UserDAO();
  public User getUser(String username) {
    return dao.getUser(username);
  }
}
