package com.example.gdjkj.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gdjkj on 11/20/16.
 */
public class Allwishes {
   /* {"entity_id":"4","name":"Sachin Tendulkar","telephone":"123456789",
            "wish":"Congratulation From Sachin","reply":"Thanks Sachin",
            "token_number":"xyztokennumber"}*/
    @SerializedName("entity_id")
    private String Entityid;
    @SerializedName("name")
    private String Name;
    @SerializedName("telephone")
    private String Telephone;
    @SerializedName("wish")
    private String Wish;
    @SerializedName("reply")
    private String Reply;
    @SerializedName("token_number")
    private String TokenNumber;

    public String getEntityid() {
        return Entityid;
    }

    public void setEntityid(String entityid) {
        Entityid = entityid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getWish() {
        return Wish;
    }

    public void setWish(String wish) {
        Wish = wish;
    }

    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }

    public String getTokenNumber() {
        return TokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        TokenNumber = tokenNumber;
    }
}
