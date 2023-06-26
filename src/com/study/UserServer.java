package com.study;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserServer {
    static HashMap<String,String> hm=new HashMap<>();
    static HashMap<String,Message> offline=new HashMap<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        User u1=new User("100","123456");
        User u2=new User("200","123456");
        User u3=new User("300","123456");
        User u4=new User("400","123456");
        hm.put(u1.getUseId(),u1.getPasswd());
        hm.put(u2.getUseId(),u2.getPasswd());
        hm.put(u3.getUseId(),u3.getPasswd());
        hm.put(u4.getUseId(),u4.getPasswd());
        new UserServer(hm);

    }
    //验证用户登录
    public static boolean checkUser(String useId,String pwd){
        //通过传过来的id得到值
        String password = hm.get(useId);
        //如果值为空的话，判断不存在此用户
        if(password==null){
            return false;
        }else {
            //不为空的话判断得到的值是否和客户端传过来的值相等
            if(!password.equals(pwd)){
                return false;
            }
        }
        return true;
    }

    /*public static void sendoffLineMessage(){
        offline.get(u.)
    }*/

    public UserServer(HashMap<String,String> hm) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(10086);
        Socket socket = null;
        ObjectInputStream ois= null;
        ObjectOutputStream oos= null;
        System.out.println("服务器在10086端口监听.....");
        while (true) {
            socket = ss.accept();
            ois = new ObjectInputStream(socket.getInputStream());
            User u =(User) ois.readObject();
            oos = new ObjectOutputStream(socket.getOutputStream());
            Message mes=new Message();
            //boolean flag=checkUser(u.getUseId(),u.getPasswd());
            if(checkUser(u.getUseId(),u.getPasswd())){
                mes.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                Message message = offline.get(u.getUseId());
                if(message !=null){
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    mes=message;
                }
                oos.writeObject(mes);
                ServerThread st = new ServerThread(socket);
                new Thread(st).start();
                ManageServerThread.addServerThread(u,st);
                System.out.println("信息匹配成功，可以登录！");
                System.out.println("服务端和客户端"+u.getUseId()+"保持通信中....");

            }else {
                mes.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                oos.writeObject(mes);
                System.out.println("信息不匹配，拒绝登录！");
                socket.close();
            }

        }

    }
}
