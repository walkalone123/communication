package com.study;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class UserClient {
    User u=new User();
    Socket socket;
    Scanner sc=new Scanner(System.in);

    //验证登录
    public  boolean checkUser(String user,String pwd) throws IOException, ClassNotFoundException {
        boolean b=false;
        u.setUseId(user);
        u.setPasswd(pwd);
        //创建socket，连接服务器
        socket=new Socket(InetAddress.getByName("127.0.0.1"),10086);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        //把对象传输到服务器
        oos.writeObject(u);
        //读取服务器的发来的消息
        ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
        Message mes=(Message) ois.readObject();
        //如果收到回复成功连接，则创建一个线程，一直等待读取服务器发来的消息，并把线程添加到集合中
        if(mes.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
            //判断信息内容是否为空，不为空说明有留言，把留言显示出来
            if(mes.getContext() !=null){
                System.out.println(mes.getSendTime());
                System.out.println("用户"+mes.getSender()+"给你留言：");
                System.out.println(mes.getContext()+"\n");
            }
            ClientThread ct = new ClientThread(socket);
            //new Thread(ct).start();
            ct.start();
            ManageClientThread.addClientThread(user,ct);
            b=true;
        }else if(mes.getMesType().equals(MessageType.MESSAGE_LOGIN_FAIL)) {
            //socket.close();
            return b;
        }

        //ois.close();
        //oos.close();
        //socket.close();
        //关闭再返回
        return b;
    }

    //获取在线用户列表
    public void getOnlineUser() throws IOException, ClassNotFoundException {
        Message mes=new Message();
        ClientThread thread = ManageClientThread.getThread(u.getUseId());
        Socket socket = thread.getSocket();
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        mes.setMesType(MessageType.MESSAGE_GETONLINE_USER);
        oos.writeObject(mes);
    }

    //结束线程
    public void exitSystem() throws IOException {
        Message mes=new Message();
        ClientThread thread = ManageClientThread.getThread(u.getUseId());
        Socket socket = thread.getSocket();
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        mes.setMesType(MessageType.MESSAGE_EXIT_SYSTEM);
        mes.setSender(u.getUseId());
        oos.writeObject(mes);
        System.exit(0);
    }
   //私发消息
    public void sendMessage() throws IOException {
        Message mes=new Message();
        ClientThread thread = ManageClientThread.getThread(u.getUseId());
        Socket socket = thread.getSocket();
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //发送时间
        String time = df.format(System.currentTimeMillis());
        mes.setSender(u.getUseId());
        System.out.println("输入你要发送的用户：");
        mes.setGetter(sc.next());
        mes.setSendTime(time);
        mes.setMesType(MessageType.MESSAGE_SEND);
        System.out.println("输入你要发送的信息内容：");
        String context=sc.next();
        mes.setContext(context);
        oos.writeObject(mes);
    }

    //群发消息
    public void MassMessage() throws IOException {
        Message mes=new Message();
        ClientThread thread = ManageClientThread.getThread(u.getUseId());
        Socket socket = thread.getSocket();
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //发送时间
        String time = df.format(System.currentTimeMillis());
        mes.setSender(u.getUseId());
        System.out.println("输入你要发送的信息内容：");
        String context=sc.next();
        mes.setSendTime(time);
        mes.setMesType(MessageType.MASS_MESSAGE);
        mes.setContext(context);
        oos.writeObject(mes);
    }

}
