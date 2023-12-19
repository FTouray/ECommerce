import java.sql.Timestamp;

import org.apache.struts2.components.Date;

public class Bid {

    private int bidId;
    private int bidderId;
    private int itemId; 
    private double bidAmount;
    private Timestamp bidDate;

    private String itemName;
    private String description;
    private double startPrice;
    private double currentBid;

    private String bidderUsername;
    

    public Bid(){

    }

      


    public Bid(int bidId, int bidderId, int itemId, double bidAmount, Timestamp bidDate, String itemName,
            String description, double startPrice, double currentBid) {
        this.bidId = bidId;
        this.bidderId = bidderId;
        this.itemId = itemId;
        this.bidAmount = bidAmount;
        this.bidDate = bidDate;
        this.itemName = itemName;
        this.description = description;
        this.startPrice = startPrice;
        this.currentBid = currentBid;
    }

    




    public Bid(int bidId, int bidderId, int itemId, double bidAmount, Timestamp bidDate, String itemName,
            String description, double startPrice, double currentBid, String bidderUsername) {
        this.bidId = bidId;
        this.bidderId = bidderId;
        this.itemId = itemId;
        this.bidAmount = bidAmount;
        this.bidDate = bidDate;
        this.itemName = itemName;
        this.description = description;
        this.startPrice = startPrice;
        this.currentBid = currentBid;
        this.bidderUsername = bidderUsername;
    }




    public Bid(int bidId, int bidderId, int itemId, double bidAmount, Timestamp bidDate) {
        this.bidId = bidId;
        this.bidderId = bidderId;
        this.itemId = itemId;
        this.bidAmount = bidAmount;
        this.bidDate = bidDate;
    }




    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

   

    public Timestamp getBidDate() {
        return bidDate;
    }



    public void setBidDate(Timestamp timestamp) {
        this.bidDate = timestamp;
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




    public double getBidAmount() {
        return bidAmount;
    }






    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }




    public String getBidderUsername() {
        return bidderUsername;
    }




    public void setBidderUsername(String bidderUsername) {
        this.bidderUsername = bidderUsername;
    }

   
 
}
