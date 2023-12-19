import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class MakeABid extends ActionSupport implements SessionAware {

    private int itemId;
    private Bid bid;
    private double myBid;
    private String itemName;
    private String description;
    private double startPrice;
    private double currentBid;
    private Map<String, Object> session;

    public MakeABid() {
    }

    public String makeBid() {
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

            String selectItemSql = "SELECT * FROM items WHERE itemId = ?";
            try (PreparedStatement selectItemStatement = connection.prepareStatement(selectItemSql)) {
                selectItemStatement.setInt(1, itemId);

                ResultSet itemResultSet = selectItemStatement.executeQuery();
                if (itemResultSet.next()) {
                    itemName = itemResultSet.getString("itemName");
                    description = itemResultSet.getString("description");
                    startPrice = itemResultSet.getDouble("startPrice");
                    currentBid = itemResultSet.getDouble("currentBid");
                } else {
                    // Item not found, handle this case (throw an exception, return an error code,
                    // etc.)
                    return ERROR;
                }
            }

            return SUCCESS;

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

    }

    public String submitBid() {
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
        Integer id = (Integer) session.get("currentUserId");

       
        if (myBid <= currentBid) {
            addActionError("Bid amount must be greater than the current bid.");
            return INPUT;
        }

        try {

            String insertBidSql = "INSERT INTO bids (bidderId, itemId, bidAmount, bidDate) " +
                    "SELECT ?, i.itemId, ?, CURRENT_TIMESTAMP " +
                    "FROM items i " +
                    "WHERE i.itemId = ?";

            try (PreparedStatement insertBidStatement = connection.prepareStatement(insertBidSql)) {
                insertBidStatement.setInt(1, id);
                insertBidStatement.setDouble(2, myBid);
                insertBidStatement.setInt(3, itemId);

                int rowsAffected = insertBidStatement.executeUpdate();

                if (rowsAffected == 0) {

                    addActionError("Item Could Not Be Found");
                    return ERROR;
                }
            }

            String updateCurrentBidSql = "UPDATE items SET currentBid = ? WHERE itemId = ?";
            try (PreparedStatement updateCurrentBidStatement = connection.prepareStatement(updateCurrentBidSql)) {
                updateCurrentBidStatement.setDouble(1, myBid);
                updateCurrentBidStatement.setInt(2, itemId);

                updateCurrentBidStatement.executeUpdate();
            }
            addActionMessage("Bid Made Successfully");
            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error submitting the bid.");
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

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public double getMyBid() {
        return myBid;
    }

    public void setMyBid(double myBid) {
        this.myBid = myBid;
    }

    @Override
    public void setSession(Map map) {
        session = map;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public double getCurrentBid() {
        return currentBid;
    }

}
