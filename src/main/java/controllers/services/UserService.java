package controllers.services;

import DAO.DAO;
import com.google.gson.Gson;
import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
  DAO dao = new DAO();
/*  public User getUser() {
    User user = null;
    try (BufferedReader br = new BufferedReader(new FileReader("data.json"))) {
      user = this.mGson.fromJson(br, User.class);
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e.getCause());
    }
    return user;
  }*/
  
  public User getUser() {
    return dao.getUser("hermes");
  }
}
