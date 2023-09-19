package com.productnew.com.productnew;

public class ProductData {

private String partNumber, price, description;
	
	public ProductData(String partNumber, String price, String description) {
        this.partNumber = partNumber;
        this.price = price;
        this.description = description;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
