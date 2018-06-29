package com.shuanghe.j2se.core.serialize.demo03;

import java.io.*;
import java.util.Date;

/**
 * Created by yobdc on 2017/01/12.
 */
public class UserInfo implements Externalizable {

    public String userName;
    public String userPass;
    public int userAge;

    public UserInfo() {
    }

    public UserInfo(String userName, String userPass, int userAge) {
        this.userName = userName;
        this.userPass = userPass;
        this.userAge = userAge;
    }

    /**
     * 对象序列化时，调用此方法
     *
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("开始序列化");

        //可以在序列化时写非自身的变量
        Date d = new Date();
        out.writeObject(d);

        //只序列化userName，userPass变量
        out.writeObject(userName);
        out.writeObject(userPass);
    }

    /**
     * 对象反序列化时，调用此方法
     *
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("开始反序列化");

        Date d = (Date) in.readObject();
        System.out.println(d);

        this.userName = (String) in.readObject();
        this.userPass = (String) in.readObject();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}
