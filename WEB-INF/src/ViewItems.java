import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ViewItems extends ActionSupport implements SessionAware{
    private List<Items> allItems;
     private List<Items> bidItems;
    private Map<String, Object> session;
    

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
        Integer currentUserId = (Integer) session.get("currentUserId");

        try {
            String sql = "SELECT * FROM items WHERE sellerId <> ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, currentUserId);
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

    public String loadBidItems(){
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
 

        bidItems = new ArrayList<>();
        Integer currentUserId = (Integer) session.get("currentUserId");

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
                   

                    bidItems.add(item);
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

    @Override
    public void setSession(Map map) {
        session = map;
    }


    public Map<String, Object> getSession() {
        return session;
    }

    public List<Items> getBidItems() {
        return bidItems;
    }

    public void setBidItems(List<Items> bidItems) {
        this.bidItems = bidItems;
    }
    

}
