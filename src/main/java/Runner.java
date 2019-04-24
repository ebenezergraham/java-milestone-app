import DAO.DB;
import DAO.MongoDB;
import domain.model.User;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Runner {
  private static final String WEBAPP_DIR_LOCATION = "src/main/webapp/";
  private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());

  public static void main(String[] args) throws ServletException {

    Tomcat tomcat = new Tomcat();
    MongoDB db = new MongoDB();
    //The port that we should run on can be set into an environment variable
    //Look for that variable and default to 8080 if it isn't there.
    String webPort = System.getenv("PORT");
    if (webPort == null || webPort.isEmpty()) webPort = "8080";

    tomcat.setPort(Integer.valueOf(webPort));
    System.out.println(WEBAPP_DIR_LOCATION);
    StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(WEBAPP_DIR_LOCATION).getAbsolutePath());
    System.out.println("configuring app with basedir: " + new File(WEBAPP_DIR_LOCATION).getAbsolutePath());

    // Declare an alternative location for your "WEB-INF/classes" dir
    // Servlet 3.0 annotation will work
    File additionWebInfClasses = new File("target/classes");
    WebResourceRoot resources = new StandardRoot(ctx);
    resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
        additionWebInfClasses.getAbsolutePath(), "/"));
    ctx.setResources(resources);

    try {
      tomcat.start();
      DB h2db = new DB();
      User user = new User();
      user.setHash("65536:f5c9f37c27e2acebd032a9f50ef7c1168dde823bc8aef826:ac7b763cd4ada38798ec133d8c7e65e627cf39b3eb817e7d65f2fe3b911b435cce3ea9a08df209e67f8477cc68d55ceddca883198890261c43d0dbb40b88e23e");
      user.setSalt("f5c9f37c27e2acebd032a9f50ef7c1168dde823bc8aef826");
      user.setUsername("hermes");
      user.setUsername("email");
      h2db.addUser(user);
//      db.start();
//      db.testStartAndStopMongoImportAndMongod();
    } catch (LifecycleException e) {
      LOGGER.warning(e.getMessage());
      e.printStackTrace();
    }
    tomcat.getServer().await();
  }
}
