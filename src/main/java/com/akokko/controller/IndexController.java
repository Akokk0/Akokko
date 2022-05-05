package com.akokko.controller;

import com.akokko.service.StatisticService;
import com.akokko.util.IpAddrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {

    @Autowired
    private StatisticService service;

    @RequestMapping("/food")
    public String toFood() {
        return "food";
    }

    @RequestMapping("/scenery")
    public String toScenery() {
        return "scenery";
    }

    @RequestMapping("/activity")
    public String toActivity() {
        return "activity";
    }

    @RequestMapping("/resource")
    public String toResource() {
        return "resource";
    }

    @RequestMapping
    public String toLoginPage(HttpServletRequest request) {

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

        //访问即增加入数据库
        String addr = IpAddrUtil.getIpAddr(request);
        boolean flag = service.insertIp(addr, dateFormat.format(date));

        if (flag) {

            System.out.println("新的访问已添加进数据库！");

        } else {

            System.out.println("新的访问未能添加进数据库！");

        }

        return "login";

    }

    @RequestMapping("/index")
    public String toIndexPage() {
        return "index";
    }

    @RequestMapping("/forget")
    public String toForgetPage() {
        return "forget";
    }

    @RequestMapping("/demo")
    public String toDemo() {

        return "demo";

    }

    @RequestMapping("/change")
    public String toChangePage(@RequestParam("email") String email, @RequestParam("code") String code, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("code", code);
        return "change";
    }

    @RequestMapping("/back")
    public String toBackPage() {

        return "loginback";

    }

    @RequestMapping("/indexback")
    public String toBackIndex() {

        return "indexback";

    }

}
