package com.example.dinehero;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.util.ArrayList;

public class Product {
    private String productName;
    private String productDiscription;
    private String productPrice;
    private int productPercentOff;
    private User productSeller;
    private int image;

    private Uri uriImage;

    private String location;
    private String productImageURL;
    private String productLink;
    private ArrayList<String> hashtags = new ArrayList();
    private String hashtagWithSpace;
    private String date;

    private int signedUp;
    private int maxSignedUp;
    private int cost;

    private boolean userMadeEvent;
    private boolean hasbeenSaved;

    //Regular Event Builder
    public Product(String productName, String productDiscription, String location, User productSeller, int image, boolean hasBeenSaved, String date, int signedUp, int maxSignedUp, int cost) {
        this.productName = productName;
        this.location = location;
        this.productDiscription = productDiscription;
        this.productSeller = productSeller;
        this.image = image;
        this.hasbeenSaved = hasbeenSaved;
        this.date = date;
        this.maxSignedUp = maxSignedUp;
        this.signedUp = signedUp;
        this.cost = cost;
        userMadeEvent = false;
    }
    //Event Builder for Pictures taken by User
    public Product(String productName, String productDiscription, String location, User productSeller, Uri image, boolean hasBeenSaved, String date, int signedUp, int maxSignedUp, int cost) {
        this.productName = productName;
        this.location = location;
        this.productDiscription = productDiscription;
        this.productSeller = productSeller;
        this.uriImage = image;
        this.hasbeenSaved = hasbeenSaved;
        this.date = date;
        this.maxSignedUp = maxSignedUp;
        this.signedUp = signedUp;
        this.cost = cost;
        userMadeEvent = true;
    }


    public boolean getHasbeenSaved() {
        return hasbeenSaved;
    }

    public boolean getUserMadeEvent(){
        return userMadeEvent;
    }
    public Uri getUriImage(){
        return uriImage;
    }
    public String getDate() {
        return date;
    }

    public int getSignedUp() {
        return signedUp;
    }
    public int getMaxSignedUp() {
        return maxSignedUp;
    }


    public void setHasbeenSaved(boolean hasbeenSaved) {
        this.hasbeenSaved = hasbeenSaved;
    }

    public String getLocation(){
        return location;
    }

    public String getProductLink() {
        return productLink;
    }

    public int getProductImage() {
        return image;
    }
    public String getProductName() {
        return productName;
    }

    public String getProductDiscription() {
        return productDiscription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public int getProductPercentOff() {
        return productPercentOff;
    }

    public User getProductSeller() {
        return productSeller;
    }
    public int getCost(){
        return cost;
    }
    public void setCost(int cost){
        this.cost = cost;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void changeSignedUpBy(int by){
        signedUp += by;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDiscription(String productDiscription) {
        this.productDiscription = productDiscription;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductPercentOff(int productPercentOff) {
        this.productPercentOff = productPercentOff;
    }

    public void setProductSeller(User productSeller) {
        this.productSeller = productSeller;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productDiscription='" + productDiscription + '\'' +
                ",! productPrice=" + productPrice +
                ",* productPercentOff=" + productPercentOff +
                ",& productSeller='" + productSeller + '\'' +
                ",^ productImageURL='" + productImageURL + '\'' +
                ",$ link='" + productLink + '\'' +
                ",# hashtagsWithSpacese='" + hashtagWithSpace + '\'' +
                ",@" +
                '}';
    }
}
