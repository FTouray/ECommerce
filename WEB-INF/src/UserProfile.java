import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;

public class UserProfile extends ActionSupport implements SessionAware{
    private User myUser;
    private User otherUser;
    private Map<String, Object> session;
    private int idProf;

    public User getMyUser() {
        return myUser;
    }

   

    public String myProfile() {
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
            String username = (String) session.get("currentUser");
            String sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    myUser = new User();
                    myUser.setFirstName(resultSet.getString("firstName"));
                    myUser.setLastName(resultSet.getString("lastName"));
                    myUser.setUsername(resultSet.getString("username"));
                    myUser.setEmail(resultSet.getString("email"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return SUCCESS;
    }

     public String otherProfile() {
        
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
            
            String sql = "SELECT * FROM users WHERE user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idProf);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    otherUser = new User();
                    otherUser.setId(resultSet.getInt("user_Id"));
                    otherUser.setFirstName(resultSet.getString("firstName"));
                    otherUser.setLastName(resultSet.getString("lastName"));
                    otherUser.setUsername(resultSet.getString("username"));
                    otherUser.setEmail(resultSet.getString("email"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return SUCCESS;
    }


    @Override
    public void setSession(Map map) {
        session = map;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    public Map<String, Object> getSession() {
        return session;
    }



    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    
}
