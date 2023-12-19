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

public class UserProfile extends ActionSupport implements SessionAware {
    private User myUser;
    private User otherUser;
    private Map<String, Object> session;
    private int idProf;
    private List<Items> myItems;
    private List<Items> otherItems;
    private List<Bid> otherBids;

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
        myItems = new ArrayList<>();

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

            Integer currentUserId = (Integer) session.get("currentUserId");
            String sqlItems = "SELECT * FROM items WHERE sellerId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlItems)) {
                preparedStatement.setInt(1, currentUserId);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Items myItem = new Items();
                    myItem.setItemName(resultSet.getString("itemName"));
                    myItem.setDescription(resultSet.getString("description"));
                    myItem.setCurrentBid(resultSet.getDouble("currentBid"));
                    myItem.setStartPrice(resultSet.getDouble("startPrice"));

                    myItems.add(myItem);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
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

            otherItems = new ArrayList<>();

            String sqlOther = "SELECT * FROM items WHERE sellerId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlOther)) {
                preparedStatement.setInt(1, idProf);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Items otherItem = new Items();
                    otherItem.setItemName(resultSet.getString("itemName"));
                    otherItem.setDescription(resultSet.getString("description"));
                    otherItem.setCurrentBid(resultSet.getDouble("currentBid"));
                    otherItem.setStartPrice(resultSet.getDouble("startPrice"));

                    otherItems.add(otherItem);
                }

            }

            otherBids = new ArrayList<>();

            String sqlBid = "SELECT b.bidId, b.bidderId, b.itemId, b.bidAmount, b.bidDate, " +
                    "i.itemName, i.description, i.startPrice, i.currentBid " +
                    "FROM bids b " +
                    "INNER JOIN items i ON b.itemId = i.itemId " +
                    "WHERE b.bidderId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlBid)) {
                // Replace 1 with the actual user_id of the logged-in user
                preparedStatement.setInt(1, idProf);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Bid otherBid = new Bid();
                    otherBid.setBidId(resultSet.getInt("bidId"));
                    otherBid.setBidderId(resultSet.getInt("bidderId"));
                    otherBid.setItemId(resultSet.getInt("itemId"));
                    otherBid.setBidAmount(resultSet.getDouble("bidAmount"));
                    otherBid.setBidDate(resultSet.getTimestamp("bidDate"));

                    otherBid.setItemName(resultSet.getString("itemName"));
                    otherBid.setDescription(resultSet.getString("description"));
                    otherBid.setStartPrice(resultSet.getDouble("startPrice"));
                    otherBid.setCurrentBid(resultSet.getDouble("currentBid"));

                    otherBids.add(otherBid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public List<Items> getMyItems() {
        return myItems;
    }

    public void setMyItems(List<Items> myItems) {
        this.myItems = myItems;
    }

    public List<Items> getOtherItems() {
        return otherItems;
    }

    public void setOtherItems(List<Items> otherItems) {
        this.otherItems = otherItems;
    }

    public List<Bid> getOtherBids() {
        return otherBids;
    }

    public void setOtherBids(List<Bid> otherBids) {
        this.otherBids = otherBids;
    }

}
