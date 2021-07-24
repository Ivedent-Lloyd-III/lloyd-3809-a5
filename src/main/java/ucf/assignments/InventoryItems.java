package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Ivedent Lloyd III
 */

public class InventoryItems {

    private String itemPrice;
    private String itemSerialNumber;
    private String itemName;

    public InventoryItems(){

        this.itemPrice = "";
        this.itemSerialNumber = "";
        this.itemName = "";

    }

    public InventoryItems(String itemPrice, String itemSerialNumber, String itemName){

        this.itemPrice = itemPrice;
        this.itemSerialNumber = itemSerialNumber;
        this.itemName = itemName;

    }


    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemSerialNumber = itemSerialNumber;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemSerialNumber() {
        return itemSerialNumber;
    }

    public String getItemName() {
        return itemName;
    }
}

