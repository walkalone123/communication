package com.study;

import java.util.HashMap;

public class ManageClientThread {
    static HashMap<String,ClientThread> hm=new HashMap<>();

    public static void addClientThread(String uid,ClientThread clientThread){
        hm.put(uid,clientThread);
    }
    public static ClientThread getThread(String uid){
        return hm.get(uid);
    }

    public static void removeThread(String uid){
        hm.remove(uid);
    }
}
