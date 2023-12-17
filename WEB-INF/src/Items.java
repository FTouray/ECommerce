public class Items {
    private int itemId;
     private int sellerId;
    private String itemName;
    private String description;
    private double startPrice;
    private double currentBid;
   

    public Items(){

    }

    public Items(int itemId, int sellerId, String itemName, String description, double startPrice, double currentBid) {
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.itemName = itemName;
        this.description = description;
        this.startPrice = startPrice;
        this.currentBid = currentBid;

    }

    
    
        public boolean hasNoBids() {
            return currentBid == 0.0;
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

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

        
}
