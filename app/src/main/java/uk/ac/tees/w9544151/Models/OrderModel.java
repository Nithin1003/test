package uk.ac.tees.w9544151.Models;

public class OrderModel {
    String itemName,itemPrice,itemQty,username,mobile,seatno,totalAmount;
    int itemImage;

    public OrderModel(String itemName, String itemPrice, String itemQty, String username, String mobile, String seatno, String totalAmount, int itemImage) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.username = username;
        this.mobile = mobile;
        this.seatno = seatno;
        this.totalAmount = totalAmount;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemQty() {
        return itemQty;
    }

    public String getUsername() {
        return username;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSeatno() {
        return seatno;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}
