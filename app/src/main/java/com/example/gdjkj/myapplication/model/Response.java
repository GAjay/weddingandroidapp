package com.example.gdjkj.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdjkj on 11/20/16.
 */

public class Response {
    @SerializedName("status")
    private int Status;

    @SerializedName("msg")
    private String Message;

    @SerializedName("allwishes")
    private ArrayList<Allwishes> allwishes;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ArrayList<Allwishes> getAllwishes() {
        return allwishes;
    }

    public void setAllwishes(ArrayList<Allwishes> allwishes) {
        this.allwishes = allwishes;
    }
}
