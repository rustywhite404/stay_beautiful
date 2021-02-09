package com.beautiful.stay.domain;

import java.sql.Timestamp;

public class UserVO {
    private int code;
    private String id;
    private String password;
    private String name;
    private int level;
    private Timestamp regdate;

    public UserVO() {
    }

    public UserVO(int code, String id, String password, String name, int level, Timestamp regdate) {
        this.code = code;
        this.id = id;
        this.password = password;
        this.name = name;
        this.level = level;
        this.regdate = regdate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Timestamp getRegdate() {
        return regdate;
    }

    public void setRegdate(Timestamp regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "code=" + code +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", regdate=" + regdate +
                '}';
    }
}
