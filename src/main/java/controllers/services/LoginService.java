package controllers.services;

import domain.model.User;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginService {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class.getName());
  private User mUser = UserService.getInstance().getUser();

  public synchronized boolean login(@NonNull String username, @NonNull String password) {
    try {
      String storedHash = mUser.getHash();
      System.out.println(storedHash);
      return storedHash != null && PasswordHash.validatePassword(password, storedHash);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      LOGGER.error("Can't validate password", e.getCause());
      return false;
    }
  }

  public synchronized boolean register(@NonNull String usename, @NonNull String password) {
    String storedHash = mUser.getHash();
    if (storedHash != null) return false;
    try {
      String newHash = PasswordHash.createHash(password);
      mUser.setHash(newHash);
      return true;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      LOGGER.error("Can't hash password <" + password + ">", e.getCause());
      return false;
    }
  }
}
