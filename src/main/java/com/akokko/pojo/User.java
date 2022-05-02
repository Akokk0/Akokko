package com.akokko.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_user")
public class User {

    @Id
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String code;

    private Integer verify;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String code, int verify) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.code = code;
        this.verify = verify;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

}
