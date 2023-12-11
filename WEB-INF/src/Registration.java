import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.opensymphony.xwork2.ActionSupport;

public class Registration extends ActionSupport {

    // Variables from the JSP page
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public Registration() {
    }

    public String register() {
       
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
              // Create the SQL query to insert user data
            String sql = "INSERT INTO users (username, password, email, firstName, lastName) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, firstName);
                 preparedStatement.setString(5, lastName);

                // Execute the query
                preparedStatement.executeUpdate();

                 // Clear the form fields after successful registration
            setUsername("");
            setPassword("");
            setEmail("");
            setFirstName("");
            setLastName("");
            }

            // Close the database connection
            connection.close();

           return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
            
        }
       
    
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    
    
}
