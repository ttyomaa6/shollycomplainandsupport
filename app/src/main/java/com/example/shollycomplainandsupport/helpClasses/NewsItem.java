package com.example.shollycomplainandsupport.helpClasses;

import com.example.shollycomplainandsupport.helpClasses.Clothes;

import java.util.ArrayList;

public class NewsItem {
    private String item_description, likes_count, newsId, postTime, nick,
            comments, ImageUrl;
    private long lookPrice, lookPriceDollar, viewCount;
    private ArrayList<Clothes> clothesArrayList;

    public NewsItem() {

    }

    public NewsItem(String ImageUrl, String item_description, String likes_count, String newsId,
                    String comments, ArrayList<Clothes> clothesArrayList, long lookPrice, long viewCount
            , String postTime, String nick, long lookPriceDollar) {
        this.ImageUrl = ImageUrl;
        this.likes_count = likes_count;
        this.item_description = item_description;
        this.newsId = newsId;
        this.comments = comments;
        this.clothesArrayList = clothesArrayList;
        this.lookPrice = lookPrice;
        this.viewCount = viewCount;
        this.postTime = postTime;
        this.nick = nick;
        this.lookPriceDollar = lookPriceDollar;
    }

    public String getImageUrl() {
        return this.ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getItem_description() {
        return this.item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getLikes_count() {
        return this.likes_count;
    }


    public void setLikesCount(String likes_count) {
        this.likes_count = likes_count;
    }

    public String getNewsId() {
        return this.newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<Clothes> getClothesCreators() {
        return this.clothesArrayList;
    }

    public void setClothesCreators(ArrayList<Clothes> clothesArrayList) {
        this.clothesArrayList = clothesArrayList;
    }

    public long getLookPrice() {
        return this.lookPrice;
    }

    public void setLookPrice(long lookPrice) {
        this.lookPrice = lookPrice;
    }

    public long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public String getPostTime() {
        return this.postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getLookPriceDollar() {
        return this.lookPriceDollar;
    }

    public void setLookPriceDollar(long lookPriceDollar) {
        this.lookPriceDollar = lookPriceDollar;
    }

}
