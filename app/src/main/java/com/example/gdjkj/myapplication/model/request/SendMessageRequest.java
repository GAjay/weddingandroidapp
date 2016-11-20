package com.example.gdjkj.myapplication.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gdjkj on 11/20/16.
 */

public class SendMessageRequest {
/*    {"name":"Sachin Tendulkar","telephone":"123456789",
            "wish":"Congratulation From Sachin","token_number":"xyztokennumber"}*/
    @SerializedName("name")
    private  String Name;
    @SerializedName("telephone")
    private  String Telephone;
    @SerializedName("wish")
    private  String Wish;
    @SerializedName("token_number")
    private  String TokenNumber;

    public SendMessageRequest(String name, String telephone, String wish, String tokenNumber) {
        this.Name = name;
        this.Telephone = telephone;
        this.Wish = wish;
        this.TokenNumber = tokenNumber;
    }
}
