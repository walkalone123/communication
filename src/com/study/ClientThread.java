package com.study;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread{
    Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            try {
                //一直读取服务端发来的消息
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message mes =(Message) ois.readObject();
                if(mes.getMesType().equals(MessageType.MESSAGE_RESONLINE_USER)){
                    String[] users = mes.getContext().split(" ");
                    for (int i = 0; i < users.length; i++) {
                        System.out.println("用户："+users[i]);
                    }
                    //接收来发送过来的信息
                } else if (mes.getMesType().equals(MessageType.MESSAGE_RECEIVE)) {
                    System.out.println(mes.getSendTime());
                    System.out.println("用户"+mes.getSender()+"给你发：");
                    System.out.print(mes.getContext());
                    System.out.println();
                } else if (mes.getMesType().equals(MessageType.USER_NOTFOUND)) {
                    System.out.println("用户未找到，或者没有在线");
                    System.out.println();
                } else if (mes.getMesType().equals(MessageType.MASS_MESSAGE)) {
                    System.out.println("用户"+mes.getSender()+"对大家说："+mes.getContext()+"\n");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
