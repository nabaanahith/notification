package com.codeforiraq.drug;

public class obj {
    String name,email,pass,toggle;


    obj(){


    }

    obj(String name,String email,String pass,String toggle){


        this.name=name;
        this.email=email;
        this.pass=pass;
        this.toggle=toggle;



    }

    public String getToggle() {
        return toggle;
    }

    public void setToggle(String toggle) {
        this.toggle = toggle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
