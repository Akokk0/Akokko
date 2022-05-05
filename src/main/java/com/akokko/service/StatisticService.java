package com.akokko.service;

import com.akokko.dao.StatisticIpMapper;
import com.akokko.pojo.StatisticIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    @Autowired
    private StatisticIpMapper statisticIpMapper;

    public boolean insertIp(String addr, String time) {

        StatisticIp ip = new StatisticIp();
        ip.setIp(addr);
        ip.setTime(time);

        int count = statisticIpMapper.insertSelective(ip);

        if (count == 0) {

            return false;

        } else {

            return true;

        }

    }

    public List<StatisticIp> selectAll() {

        return statisticIpMapper.selectAll();

    }

}
