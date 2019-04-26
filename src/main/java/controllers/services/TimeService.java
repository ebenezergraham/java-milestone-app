package controllers.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeService {
  private static TimeService instance;
  private DateTimeFormatter formatter;

  private TimeService() {
    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  }

  public static TimeService getInstance() {
    if (instance == null) instance = new TimeService();
    return instance;
  }

  public String formatDate(String date) {
    LocalDateTime localDateTime = LocalDateTime.parse(date);
    return localDateTime.format(formatter);
  }
}
