import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ViewItems extends ActionSupport{
    private List<Items> allItems;

    public ViewItems() {
    }

    public String loadItems(){
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
 

        allItems = new ArrayList<>();


        try {
            String sql = "SELECT * FROM items";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Items item = new Items();
                    item.setItemId(resultSet.getInt("itemId"));
                    item.setSellerId(resultSet.getInt("sellerId"));
                    item.setItemName(resultSet.getString("itemName"));
                    item.setDescription(resultSet.getString("description"));
                    item.setStartPrice(resultSet.getDouble("startPrice"));
                    item.setCurrentBid(resultSet.getDouble("currentBid"));
                   

                    allItems.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        
        return SUCCESS;
    }

    public List<Items> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<Items> allItems) {
        this.allItems = allItems;
    }

    

}
