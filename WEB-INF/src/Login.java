import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware {
    
    // Variables from the JSP page
    private String username;
    private String password;
    private Map<String, Object> session;

    public Login() {
        // TODO Auto-generated constructor stub
    }

    public String login() {

        String result=  ERROR;

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

        try{
          
            // Prepare the SQL statement
            String sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if a matching user is found
                if (resultSet.next()) {
                    
                    if (password.equals(resultSet.getString("password"))){
                            // Store the username in the session
                        session.put("currentUser", username);
                         result = SUCCESS;
                    } else {
                        addActionError("Incorrect password");
                    }
                   
                } else {
                    addActionError("Account not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
            
        }

        return result;
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

