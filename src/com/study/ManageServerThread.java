package com.study;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ManageServerThread {
    static HashMap<String,ServerThread> ht=new HashMap<>();


    public static void addServerThread(User u,ServerThread serverThread){
        ht.put(u.getUseId(),serverThread);
    }

    public static Set<String> getServerThread(){
        return ht.keySet();
    }
    public static void removeThread(String uid){
        ht.remove(uid);
    }
    //返回传过来的用户的线程
    public static ServerThread getCurrentThread(String uid){
        ServerThread serverThread = ht.get(uid);
        return serverThread;
    }
}
