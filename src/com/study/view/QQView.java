package com.study.view;

import com.study.UserClient;

import java.io.IOException;
import java.util.Scanner;

public class QQView {
    private boolean loop=true;
    private int key;
    UserClient uc=new UserClient();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new QQView().menu();
    }
    public void menu() throws IOException, ClassNotFoundException {
        while (loop){
            Scanner sc=new Scanner(System.in);
            System.out.println("===============欢迎登录系统===============");
            System.out.println("\t\t1 登录系统 ");
            System.out.println("\t\t9 退出系统 ");
            System.out.println("输入选择：");
            /*String name=null;
            String pwd=null;*/
            key=sc.nextInt();
            switch (key){
                case 1:
                    System.out.println("输入用户名：");
                     String name=sc.next();
                    System.out.println("输入密码：");
                     String pwd=sc.next();
                    boolean flag=uc.checkUser(name,pwd);
                    if(flag){
                        System.out.println("登录成功！");
                        SecondMenu(name);
                    }else {
                        System.out.println("登录失败！");
                    }
                    break;
                case 9:
                    loop=false;
                    System.out.println("退出系统");
                    uc.exitSystem();
                    break;
                default:
                    System.out.println("请重新选择");
                    break;
            }
        }

    }

    public void SecondMenu(String name) throws IOException, ClassNotFoundException {
        boolean loop=true;
        Scanner sc=new Scanner(System.in);
        while (loop){
            System.out.println("网络通信系统二级菜单("+ name +")");
            System.out.println("\t\t1 显示在线用户列表");
            System.out.println("\t\t2 群发消息");
            System.out.println("\t\t3 私聊消息");
            System.out.println("\t\t4 发送文件");
            System.out.println("\t\t9 退出系统");
            System.out.println("请输入你的选择：");
            int key=sc.nextInt();
            switch (key){
                case 1:
                    uc.getOnlineUser();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //System.out.println("显示在线用户列表");
                    break;
                case 2:
                    uc.MassMessage();
                    System.out.println("群发消息");
                    break;
                case 3:
                    uc.sendMessage();
                    //System.out.println("私聊消息");
                    break;
                case 4:
                    System.out.println("发送文件");
                    break;
                case 9:
                    System.out.println("退出系统");
                    loop=false;
                    uc.exitSystem();
                    break;
                default:
                    System.out.println("重新选择：");
                    break;
            }

        }
    }



}
