import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LoginLogout extends ActionSupport implements SessionAware {

    // Variables from the JSP page
    private String username;
    private String password;
    private Map<String, Object> session;

    public LoginLogout() {
        // TODO Auto-generated constructor stub
    }

    public String login() {

        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/auction?serverTimezone=UTC", "root", "root");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if a matching user is found
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    String storedUsername = resultSet.getString("username");
                    String storedPassword = resultSet.getString("password");
                    if (username.equals(storedUsername)) {
                        if (password.equals(storedPassword)) {
                            // Store the username in the session
                            session.put("currentUser", username);
                            session.put("currentUserId", userId);
                            addActionMessage("Login Successful");
                            return SUCCESS;
                        } else {
                            addActionError("Incorrect password");
                            session.clear();
                            return INPUT;
                        }
                    } else {
                        addActionError("Username Incorrect");
                        session.clear();
                        return INPUT;
                    }

                } else {
                    addActionError("Account not found");
                    session.clear();
                    return INPUT;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        } finally {
            // Close the connection in a finally block to ensure it's closed
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String execute() {
        // Invalidate the session
        session.clear();
        session.put("loggedOut", true);

        String logoutMessage = "You have successfully logged out.";
        addActionMessage(logoutMessage);
        return SUCCESS;
    }

    // Getter and Setter methods

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map map) {
        session = map;
    }
}
