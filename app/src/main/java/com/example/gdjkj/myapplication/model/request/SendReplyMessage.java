package com.example.gdjkj.myapplication.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gdjkj on 11/20/16.
 */
public class SendReplyMessage {
    @SerializedName("entity_id")
    private  String EntityId;
    @SerializedName("reply")
    private  String Reply;

    public SendReplyMessage(String entityId, String reply) {
        this.EntityId = entityId;
        this.Reply = reply;
    }
}
