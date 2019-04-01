import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {
  private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
  private static final int PORT = 9000;

  private void start() throws Exception {
    Server server = new Server(PORT);

    ServletContextHandler handler = new ServletContextHandler(server, "/",
        ServletContextHandler.SESSIONS);
    handler.setContextPath("/");
    handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main" +
        "/webapp");
  }
}
