package com.example.nabaa96.myapplicationnm;

import java.sql.Timestamp;
import java.util.Map;

public class Comment {


    String desc;
    String name;
    String id;
    String  accept;
  String time;
int like;


    public Comment(String desc, String name, String  accept, String  time, int like) {
        this.desc = desc;
        this.name = name;
this.accept=accept;
this.time= time;
this.like=like;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", accept='" + accept + '\'' +
                ", time='" + time + '\'' +
                ", like=" + like +
                '}';
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }


    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;




    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

}
