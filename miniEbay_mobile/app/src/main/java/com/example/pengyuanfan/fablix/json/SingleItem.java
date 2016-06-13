package com.example.pengyuanfan.fablix.json;

import com.example.pengyuanfan.fablix.util.BitmapCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengyuanfan on 6/8/2016.
 */
public class SingleItem {
    private String EndTime;
    private String StartTime;
    private List<String> PictureURL;
    private List<BitmapCache> PictureCache= new ArrayList<BitmapCache>();
    private String Seller_Id;
    private String ItemID;
    private String CategoryId;
    private String ConditionDescription;
    private String Title;
    private String CategoryName;
    private String GalleryURL;
    private double CurrentPrice;
    private String Status;
    private List<Customer_comments> Comments;

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public List<String> getPictureURL() {
        return PictureURL;
    }

    public void setPictureURL(List<String> pictureURL) {
        PictureURL = pictureURL;
    }

    public String getSeller_Id() {
        return Seller_Id;
    }

    public void setSeller_Id(String seller_Id) {
        Seller_Id = seller_Id;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        this.CategoryId = categoryId;
    }

    public String getConditionDescription() {
        return ConditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        ConditionDescription = conditionDescription;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getGalleryURL() {
        return GalleryURL;
    }

    public void setGalleryURL(String galleryURL) {
        GalleryURL = galleryURL;
    }

    public double getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public List<BitmapCache> getPictureCache() {
        return PictureCache;
    }

    public void setPictureCache(List<BitmapCache> pictureCache) {
        PictureCache = pictureCache;
    }

    public List<Customer_comments> getComments() {
        return Comments;
    }

    public void setComments(List<Customer_comments> comments) {
        Comments = comments;
    }
}
