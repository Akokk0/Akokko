package com.akokko.task;

import com.akokko.dao.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteAllToken {

    @Autowired
    private TokenMapper tokenMapper;

    @Scheduled(cron = "0 0 0 */1 * ?")
    public void delete() {
        int count = tokenMapper.deleteAll();
        System.out.println("本次删除：" + count);
    }

}
