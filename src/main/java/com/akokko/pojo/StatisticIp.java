package com.akokko.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_statisticIp")
public class StatisticIp {

    @Id
    private int id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "time")
    private String time;

    public StatisticIp() {
    }

    public StatisticIp(int id, String ip, String time) {
        this.id = id;
        this.ip = ip;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
