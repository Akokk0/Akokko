package com.akokko.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "tb_cookie")
public class Token {

    @Column(name = "name")
    private String name;

    @Column(name = "cookie")
    private String cookie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

}
