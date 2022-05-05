package com.akokko.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_backUser")
public class BackUser {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public BackUser() {
    }

    public BackUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public BackUser(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
