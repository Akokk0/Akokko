package com.akokko.task;

import com.akokko.dao.BackTokenMapper;
import com.akokko.dao.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteAllToken {

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private BackTokenMapper backTokenMapper;

    @Scheduled(cron = "0 0 0 */1 * ?")
    public void delete() {
        int count = tokenMapper.deleteAll();
        System.out.println("本次删除用户Cookie：" + count + "个");
    }

    @Scheduled(cron = "0 0 0 */1 * ?")
    public void deleteBack() {
        int count = backTokenMapper.deleteAll();
        System.out.println("本次删除后台Cookie：" + count + "个");
    }

}
