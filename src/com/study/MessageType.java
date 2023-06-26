package com.study;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED="1";
    String MESSAGE_LOGIN_FAIL="2";
    String MESSAGE_GETONLINE_USER="3";
    String MESSAGE_RESONLINE_USER="4";
    String MESSAGE_CLOSE_SOCKET="5";
    String MESSAGE_EXIT_SYSTEM="6";
    String MESSAGE_SEND="7";
    String MESSAGE_RECEIVE="8"; //信息接收
    String USER_NOTFOUND="9";//用户未找到
    String MASS_MESSAGE="10"; //群发消息
}
