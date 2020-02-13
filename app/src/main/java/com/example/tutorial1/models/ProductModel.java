package com.example.tutorial1.models;

public class ProductModel {

    private String Key,pName,pCount,pImage;

    public ProductModel() {
    }

    public ProductModel(String key, String pName, String pCount, String pImage) {
        Key = key;
        this.pName = pName;
        this.pCount = pCount;
        this.pImage = pImage;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpCount() {
        return pCount;
    }

    public void setpCount(String pCount) {
        this.pCount = pCount;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }
}
