package com.test.models;

public class Product {
    private String goodsId;
    private String id;
    private String modelName;
    private String price;
    private String priceDiscount;
    private String promotionPrice;
    private String oldPrice;
    private Integer qty;

    public Product(String goodsId, String id, String modelName, String price, String priceDiscount, String promotionPrice, String oldPrice, Integer qty) {
        this.goodsId=goodsId;
        this.id = id;
        this.modelName = modelName;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.promotionPrice = promotionPrice;
        this.oldPrice = oldPrice;
        this.qty = qty;
    }

    public Product() {
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
        this.price = price.replaceAll(" ", "");
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

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void incQty() {
        String priceStr;
        this.qty++;
        this.price = Double.toString(Double.valueOf(this.price.replace("-", "00")) * this.qty)
                .replace(".00", ".-").replace(".0",".-");

    }

    public Double getPriceAsDouble(){
        return Double.valueOf(price.replace("-","00"));
    }

}
