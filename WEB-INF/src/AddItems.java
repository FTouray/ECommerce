import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddItems extends ActionSupport implements SessionAware{

    private String itemName;
    private String description;
    private double startPrice;
    private Map<String, Object> session;
   
    public AddItems(){

    }
   
    public String add() {
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

            int sellerId = (int) session.get("currentUserId");

             
            String sql = "INSERT INTO items (itemId, description, startPrice, sellerId) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, itemName);
                preparedStatement.setString(2, description);
                preparedStatement.setDouble(3, startPrice);
                preparedStatement.setInt(4, sellerId);
                // Execute the query
                preparedStatement.executeUpdate();

         
            }

            // Close the database connection
            connection.close();

           return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
            
        }
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    @Override
    public void setSession(Map map) {
        session = map;
    }

    

}

