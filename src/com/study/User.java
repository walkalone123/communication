package com.study;

import java.io.Serializable;

public class User implements Serializable {
    public static final long uuid=1L;
    private String useId;
    private String passwd;

    public User() {
    }

    public User(String useId, String passwd) {
        this.useId = useId;
        this.passwd = passwd;
    }

    /**
     * 获取
     * @return useId
     */
    public String getUseId() {
        return useId;
    }

    /**
     * 设置
     * @param useId
     */
    public void setUseId(String useId) {
        this.useId = useId;
    }

    /**
     * 获取
     * @return passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * 设置
     * @param passwd
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String toString() {
        return "User{useId = " + useId + ", passwd = " + passwd + "}";
    }
}
