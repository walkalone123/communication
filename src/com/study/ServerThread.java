package com.study;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ServerThread extends Thread{
    Socket socket;

    public ServerThread(Socket socket) {
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
            //Message mes = new Message();
            ObjectOutputStream oos = null;
            ObjectInputStream ois=null;
            try {
                ois=new ObjectInputStream(socket.getInputStream());
                Message mes = (Message) ois.readObject();

                if(mes.getMesType().equals(MessageType.MESSAGE_GETONLINE_USER)){
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    Set<String> keys = ManageServerThread.getServerThread();
                    String users="";
                    for (String key : keys) {
                        users = key + " "+users;
                    }
                    mes.setMesType(MessageType.MESSAGE_RESONLINE_USER);
                    mes.setContext(users);
                    oos.writeObject(mes);
                   //退出系统
                }else if(mes.getMesType().equals(MessageType.MESSAGE_EXIT_SYSTEM)){
                    System.out.println("断开与"+mes.getSender()+"的连接");
                    ManageServerThread.removeThread(mes.getSender());
                    socket.close();
                    break;
                    //服务器转发用户给另一用户的信息
                }else if(mes.getMesType().equals(MessageType.MESSAGE_SEND)){
                    //获取要转发用户的线程
                    ServerThread currentThread = ManageServerThread.getCurrentThread(mes.getGetter());
                    if(currentThread==null){
                        //如果不在线，则把信息存到集合中
                        UserServer.offline.put(mes.getGetter(),mes);
                        /*mes.setMesType(MessageType.USER_NOTFOUND);
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(mes);*/
                    }else {
                        Socket socketReceive = currentThread.getSocket();
                        ObjectOutputStream oosReceive = new ObjectOutputStream(socketReceive.getOutputStream());
                        mes.setMesType(MessageType.MESSAGE_RECEIVE);
                        oosReceive.writeObject(mes);
                    }

                    //群发消息
                } else if (mes.getMesType().equals(MessageType.MASS_MESSAGE)) {
                    Collection cl=ManageServerThread.ht.values();
                    Iterator itr=cl.iterator();
                    while (itr.hasNext()){
                        ServerThread next = (ServerThread) itr.next();
                        Socket nextSocket = next.getSocket();
                        ObjectOutputStream masoos = new ObjectOutputStream(nextSocket.getOutputStream());
                        masoos.writeObject(mes);

                    }

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
