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

public class ViewBids extends ActionSupport implements SessionAware {

    private List<Bid> myBids;
    private Map<String, Object> session;
    private List<Bid> allBids;
    private List<Bid> filteredBids;
    private int itemId;
    private String itemName;
    private String description;
    private double startPrice;
    private double currentBid;

    public ViewBids() {
    }

    public String myBids() {
        Integer currentUserId = (Integer) session.get("currentUserId");
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

        myBids = new ArrayList<>();

        try {
            String sql = "SELECT b.bidId, b.bidderId, b.itemId, b.bidAmount, b.bidDate, " +
                    "i.itemName, i.description, i.startPrice, i.currentBid " +
                    "FROM bids b " +
                    "INNER JOIN items i ON b.itemId = i.itemId " +
                    "WHERE b.bidderId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Replace 1 with the actual user_id of the logged-in user
                preparedStatement.setInt(1, currentUserId);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Bid myBid = new Bid();
                    myBid.setBidId(resultSet.getInt("bidId"));
                    myBid.setBidderId(resultSet.getInt("bidderId"));
                    myBid.setItemId(resultSet.getInt("itemId"));
                    myBid.setBidAmount(resultSet.getDouble("bidAmount"));
                    myBid.setBidDate(resultSet.getTimestamp("bidDate"));

                    myBid.setItemName(resultSet.getString("itemName"));
                    myBid.setDescription(resultSet.getString("description"));
                    myBid.setStartPrice(resultSet.getDouble("startPrice"));
                    myBid.setCurrentBid(resultSet.getDouble("currentBid"));

                    myBids.add(myBid);
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

    public String allBids() {
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

            allBids = new ArrayList<>();

            String sql = "SELECT b.bidId, b.bidderId, b.itemId, b.bidAmount, b.bidDate, " +
                    "i.itemName, i.description, i.startPrice, i.currentBid, " +
                    "u.username " +
                    "FROM bids b " +
                    "INNER JOIN items i ON b.itemId = i.itemId " +
                    "INNER JOIN users u ON b.bidderId = u.user_Id";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Bid bid = new Bid();
                    bid.setBidId(resultSet.getInt("bidId"));
                    bid.setBidderId(resultSet.getInt("bidderId"));
                    bid.setItemId(resultSet.getInt("itemId"));
                    bid.setBidAmount(resultSet.getDouble("bidAmount"));
                    bid.setBidDate(resultSet.getTimestamp("bidDate"));

                    bid.setItemName(resultSet.getString("itemName"));
                    bid.setDescription(resultSet.getString("description"));
                    bid.setStartPrice(resultSet.getDouble("startPrice"));
                    bid.setCurrentBid(resultSet.getDouble("currentBid"));

                    bid.setBidderUsername(resultSet.getString("username"));

                    allBids.add(bid);
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

    public String itemBids() {
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

            filteredBids = new ArrayList<>();

            String sql = "SELECT b.bidId, b.bidderId, b.itemId, b.bidAmount, b.bidDate, " +
                    "i.itemName, i.description, i.startPrice, i.currentBid, " +
                    "u.username " +
                    "FROM bids b " +
                    "INNER JOIN items i ON b.itemId = i.itemId " +
                    "INNER JOIN users u ON b.bidderId = u.user_Id " +
                    "WHERE i.itemId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, itemId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Bid bidI = new Bid();
                    bidI.setBidId(resultSet.getInt("bidId"));
                    bidI.setBidderId(resultSet.getInt("bidderId"));
                    bidI.setItemId(resultSet.getInt("itemId"));
                    bidI.setBidAmount(resultSet.getDouble("bidAmount"));
                    bidI.setBidDate(resultSet.getTimestamp("bidDate"));

                    bidI.setItemName(resultSet.getString("itemName"));
                    bidI.setDescription(resultSet.getString("description"));
                    bidI.setStartPrice(resultSet.getDouble("startPrice"));
                    bidI.setCurrentBid(resultSet.getDouble("currentBid"));

                    bidI.setBidderUsername(resultSet.getString("username"));

                    filteredBids.add(bidI);
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

    public List<Bid> getAllBids() {
        return allBids;
    }

    public void setAllBids(List<Bid> allBids) {
        this.allBids = allBids;
    }

    @Override
    public void setSession(Map map) {
        session = map;

    }

    public List<Bid> getFilteredBids() {
        return filteredBids;
    }

    public void setFilteredBids(List<Bid> filteredBids) {
        this.filteredBids = filteredBids;
    }

    public void setMyBids(List<Bid> myBids) {
        this.myBids = myBids;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

}