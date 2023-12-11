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

public class ViewMyBids extends ActionSupport implements SessionAware{

    private List<Bid> myBids;
     private Map<String, Object> session;

    public ViewMyBids() {
    }

    public String myBids()  {
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
            String sql = "SELECT i.itemName, i.description, i.startPrice, i.currentBid, b.bidAmount " +
            "FROM items i " +
            "JOIN bids b ON i.itemId = b.itemId " +
            "WHERE b.user_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Replace 1 with the actual user_id of the logged-in user
                preparedStatement.setInt(1, (int) session.get("currentUserId"));

                ResultSet resultSet = preparedStatement.executeQuery();

                myBids = new ArrayList<>();

                while (resultSet.next()) {
                    Bid bid = new Bid();
                    bid.setItemName(resultSet.getString("itemName"));
                    bid.setDescription(resultSet.getString("description"));
                    bid.setStartBid(resultSet.getDouble("startPrice"));
                    bid.setCurrentBid(resultSet.getDouble("currentBid"));
                    bid.setMyBid(resultSet.getDouble("bidAmount"));

                    myBids.add(bid);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return SUCCESS;
    }

    public List<Bid> getMyBids() {
        return myBids;
    }

    

    @Override
    public void setSession(Map map) {
        session = map;

    }
    
    
}