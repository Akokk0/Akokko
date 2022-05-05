package com.akokko.controller;

import com.akokko.dao.BackTokenMapper;
import com.akokko.entity.Result;
import com.akokko.entity.StatusCode;
import com.akokko.pojo.BackToken;
import com.akokko.pojo.BackUser;
import com.akokko.pojo.StatisticIp;
import com.akokko.service.BackUserService;
import com.akokko.service.StatisticService;
import com.akokko.service.UserService;
import com.akokko.util.CookieUtil;
import com.akokko.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
public class BackUserController {

    @Value("${cookieDomain}")
    private String cookieDomain;

    @Value("${cookieMaxAge}")
    private int cookieMaxAge;

    @Autowired
    private BackUserService backUserService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private UserService userService;

    @Autowired
    private BackTokenMapper backTokenMapper;

    @Autowired
    private IdWorker idWorker;

    @RequestMapping("/backlogin")
    public Result login(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletResponse response) {

        BackUser loginUser = backUserService.login(name, password);

        if (loginUser == null) {
            //账号名密码错误
            return new Result(false, StatusCode.LOGINERROR, "账户名或密码错误！");
        }
        boolean flag = this.addCookie(response);
        if (!flag) {
            return new Result(false, StatusCode.ERROR, "登录失败，请稍后重试！");
        }
        return new Result(true, StatusCode.OK, "登录成功！");

    }

    @RequestMapping("/statisticIp")
    public Result statisticIp() {

        List<StatisticIp> list = statisticService.selectAll();
        int views = list.size();
        int userCount = userService.findUserCount();

        HashMap map = new HashMap();

        map.put("list", list);
        map.put("views", views);
        map.put("userCount", userCount);

        return new Result(true, StatusCode.OK, "查询成功", map);

    }

    private boolean addCookie(HttpServletResponse response) {
        String cookieValue = idWorker.nextId() + "";
        CookieUtil.addCookie(response, cookieDomain, "/", "backUid", cookieValue, cookieMaxAge, false);
        BackToken token = new BackToken();
        token.setName("backUid");
        token.setCookie(cookieValue);
        int count = backTokenMapper.insertSelective(token);
        if (count <= 0) {
            return false;
        } else {
            return true;
        }
    }

}
