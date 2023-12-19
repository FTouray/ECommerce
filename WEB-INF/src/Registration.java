import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.opensymphony.xwork2.ActionSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            if (isUsernameExists(username)) {
                addActionError("Username already exists. Please choose a different username.");
                return INPUT;
            }

            if (!isPasswordStrong(password)) {
                addActionError(
                        "Password must be at least 8 characters long and include uppercase letters, lowercase letters, numbers, and special characters.");
                return INPUT;
            }

            if (!isEmailValid(email)) {
                addActionError("Invalid email address format.");
                return INPUT;
            }

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
            addActionMessage("Registration Successful");
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("An error occurred during registration.");
            return ERROR;

        }

    }

    private boolean isUsernameExists(String username) throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/auction?serverTimezone=UTC", "root", "root");

            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private boolean isPasswordStrong(String password) {
        // Define password strength criteria
        int minLength = 8;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        // Check each character in the password
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (isSpecialChar(ch)) {
                hasSpecialChar = true;
            }
        }

        // Check if the password meets the criteria
        return password.length() >= minLength &&
                hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

    private boolean isSpecialChar(char ch) {
        // Define your set of special characters
        String specialChars = "!@#$%^&*()-_=+[{]};:'\",<.>/?";

        // Check if the character is a special character
        return specialChars.indexOf(ch) != -1;
    }

    // ... existing code ...

    private boolean isEmailValid(String email) {
        // Regular expression for a simple email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the regular expression, false otherwise
        return matcher.matches();
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
