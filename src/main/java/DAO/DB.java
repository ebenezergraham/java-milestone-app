
package DAO;

import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DB implements AutoCloseable {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(DB.class);

    public static final String MEMORY = "jdbc:h2:mem:mpdb";
    public static final String FILE = "jdbc:h2:~/mpdb";

    private Connection connection;

    static Connection getConnection(String db) throws SQLException, ClassNotFoundException {
            Class.forName("org.h2.Driver");  // ensure the driver class is loaded when the DriverManager looks for an installed class. Idiom.
            return DriverManager.getConnection(db, "sa", "");  // default password, ok for embedded.
    }

    public DB() {
        this(MEMORY);
    }

    public DB(String db) {
        try {
            connection = getConnection(db);
            loadResource("users.sql");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        final String ADD_USER_QUERY = "INSERT INTO users (hash, salt, email,username) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(ADD_USER_QUERY)) {
            ps.setString(1, user.getHash());
            ps.setString(2, user.getSalt());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public User getUser(String username) {
        final String GET_USER_QUERY = "SELECT hash,salt,email,username  FROM users WHERE username='"+username+"'";
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_QUERY)) {
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
                user.setHash(rs.getString("hash"));
                user.setSalt(rs.getString("salt"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
       }
        return user;
    }

//    public List<Project> findProjects() {
//        final String LIST_PROJECT_QUERY = "SELECT first, last, email  FROM person";
//        List<Project> out = new ArrayList<>();
//        try (PreparedStatement ps = connection.prepareStatement(LIST_PROJECT_QUERY)) {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                out.add(new Project(rs.getString(1), rs.getString(2), rs.getString(3)));
//             }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return out;
//    }

    private void loadResource(String name) {
        try {
            //String cmd = new Scanner(getClass().getResource(name).openStream()).useDelimiter("\\Z").next();
            String cmd = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  id int AUTO_INCREMENT PRIMARY KEY,\n" +
                "  hash VARCHAR(255),\n" +
                "  salt VARCHAR(255),\n" +
                "  email VARCHAR(255),\n" +
                "  username VARCHAR(255)\n" +
                ");";
            PreparedStatement ps = connection.prepareStatement(cmd);
            ps.execute();
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }
}
