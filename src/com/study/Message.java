package com.study;

import java.io.Serializable;

public class Message implements Serializable {
    public static final long uuid=1L;
    //发送者
    private String sender;
    //接收者
    private String getter;
    private String context;
    private String sendTime;
    private String mesType;


    public Message() {
    }

    public Message(String sender, String getter, String context, String sendTime, String mesType) {

        this.sender = sender;
        this.getter = getter;
        this.context = context;
        this.sendTime = sendTime;
        this.mesType = mesType;
    }

    /**
     * 获取
     * @return sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * 设置
     * @param sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 获取
     * @return getter
     */
    public String getGetter() {
        return getter;
    }

    /**
     * 设置
     * @param getter
     */
    public void setGetter(String getter) {
        this.getter = getter;
    }

    /**
     * 获取
     * @return context
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置
     * @param context
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * 获取
     * @return sendTime
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * 设置
     * @param sendTime
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取
     * @return mesType
     */
    public String getMesType() {
        return mesType;
    }

    /**
     * 设置
     * @param mesType
     */
    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public String toString() {
        return "Message{uuid = " + uuid + ", sender = " + sender + ", getter = " + getter + ", context = " + context + ", sendTime = " + sendTime + ", mesType = " + mesType + "}";
    }
}
