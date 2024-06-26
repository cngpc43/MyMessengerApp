package com.example.mymessengerapp.model;

import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatMessage {
    private String senderId, message, imgUrl, imgType;
    private boolean isSent;
    ChatRoom chatRoom;
    long timeStamp;
    boolean isLastMessage;
    public ChatMessage() {

    }

    public ChatMessage(String message, long timeStamp, String senderId, boolean isLastMessage) {
        this.message = message;
        this.senderId = senderId;
        this.timeStamp = timeStamp;
        this.isLastMessage = true;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSent() {
        return isSent;
    }
    public Task<String> getUserName() {
        Users user = new Users();
        return user.getUserNameById(senderId);
    }
    public String getLastMessage() {
        return message;
    }
    public String getTime() {
        return convertTimestampToTime(timeStamp);
    }
    public String getSenderId() {
        return senderId;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }
    private String convertTimestampToTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}