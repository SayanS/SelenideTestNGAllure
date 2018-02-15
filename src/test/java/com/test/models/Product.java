package com.test.models;

public class Product {
    private String id;
    private String modelName;
    private String price;
    private String priceDiscount;
    private String promotionPrice;
    private Integer qty;

    public Product(String id, String modelName, String price, String priceDiscount, String promotionPrice, Integer qty) {
        this.id = id;
        this.modelName = modelName;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.promotionPrice = promotionPrice;
        this.qty = qty;
    }

    public Product(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}