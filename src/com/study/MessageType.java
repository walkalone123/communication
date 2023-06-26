package com.study;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED="1";//登录成功
    String MESSAGE_LOGIN_FAIL="2";//登录失败
    String MESSAGE_GETONLINE_USER="3";//请求获取用户列表
    String MESSAGE_RESONLINE_USER="4";//返回用户列表
    String MESSAGE_CLOSE_SOCKET="5";//退出登录
    String MESSAGE_EXIT_SYSTEM="6"; //退出系统
    String MESSAGE_SEND="7"; //信息发送
    String MESSAGE_RECEIVE="8"; //信息接收
    String USER_NOTFOUND="9"; //用户未找到
    String MASS_MESSAGE="10"; //群发消息






}
