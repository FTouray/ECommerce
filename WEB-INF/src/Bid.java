public class Bid {

    private int bidId;
    private int userId;
    private int itemId; 
    private String itemName;
    private String description;
    private double startBid;
    private double currentBid;
    private double myBid;
    

    public Bid(){

    }

    

    public Bid(int bidId, int userId, int itemId, String itemName, String description, double startBid,
            double currentBid, double myBid) {
        this.bidId = bidId;
        this.userId = userId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.startBid = startBid;
        this.currentBid = currentBid;
        this.myBid = myBid;
    }



    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public double getStartBid() {
        return startBid;
    }

    public void setStartBid(double startBid) {
        this.startBid = startBid;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public double getMyBid() {
        return myBid;
    }

    public void setMyBid(double myBid) {
        this.myBid = myBid;
    }

   
 
}
