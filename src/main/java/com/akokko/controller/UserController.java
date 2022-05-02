package com.akokko.controller;

import com.akokko.dao.TokenMapper;
import com.akokko.entity.Result;
import com.akokko.entity.StatusCode;
import com.akokko.pojo.Token;
import com.akokko.pojo.User;
import com.akokko.service.UserService;
import com.akokko.util.CookieUtil;
import com.akokko.util.EmailUtil;
import com.akokko.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Value("${cookieDomain}")
    private String cookieDomain;

    @Value("${cookieMaxAge}")
    private int cookieMaxAge;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private IdWorker idWorker;

    @RequestMapping("/login")
    public Result login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) {
//        System.out.println("接收到消息");
//        System.out.println(email + password);
        User user = userService.login(email, password);
        if (user == null) {
            //账号名密码错误
            return new Result(false, StatusCode.LOGINERROR, "账户名或密码错误！");
        }
        if (user.getVerify() != 1) {
            //账号未验证
            CookieUtil.deleteCookie(response, "uid");
            return new Result(false, StatusCode.LOGINERROR, "登录失败，账号未验证！");
        }
        boolean flag = this.addCookie(response);
        if (!flag) {
            return new Result(false, StatusCode.ERROR, "登录失败，请稍后重试！");
        }
        return new Result(true, StatusCode.OK, "登录成功！");
    }

    @RequestMapping("/regist")
    public Result regist(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) {
        //生成验证码
        String code = idWorker.nextId() + "";
        Integer count = userService.regist(name, email, password, code);
        if (count <= 0) {
            return new Result(false, StatusCode.ERROR, "注册失败，请稍后重试！");
        } else {
            //发送验证邮件
            try {
                emailUtil.sendHtmlMail(email, "验证邮件", "<h1>这是您的验证链接，请点击验证链接激活账号:</h1><a>http://akokko.com/verify?code=" + code + "</a>");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return new Result(true, StatusCode.OK, "注册成功,请检查邮箱验证账号！");
        }
    }

    @RequestMapping("/retrievePassword")
    public Result retrievePassword(@RequestParam("name") String name, @RequestParam("email") String email) {
        boolean verify = userService.isVerify(name, email);
        if (!verify) {
            return new Result(false, StatusCode.ACCESSERROR, "您的邮箱还未验证，请验证后重试！");
        }
        User forgetUser = userService.forgetPassword(name, email);
        if (forgetUser == null) {
            return new Result(false, StatusCode.ACCESSERROR, "ID和邮箱不匹配，请检查后重试！");
        } else {
            String code = idWorker.nextId() + "";
            userService.insertPasswordCode(email, code);
            try {
                emailUtil.sendHtmlMail(email, "更改密码", "<h1>请点击链接更改密码:<h1><a>http://akokko.com/change?email=" + email + "&code=" + code +"</a>");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return new Result(true, StatusCode.OK, "请检查邮箱更改密码！");
        }
    }

    @RequestMapping("/changePassword")
    public Result changePassword(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("code") String code, HttpServletResponse response) {
        boolean flag = userService.changePassword(email, password, code);
        if (flag) {
            return new Result(flag, StatusCode.OK, "修改密码成功，请重新访问Akokko");
        } else {
            return new Result(flag, StatusCode.ERROR, "修改密码失败，请稍后重试");
        }
    }

    private boolean addCookie(HttpServletResponse response) {
        String cookieValue = idWorker.nextId() + "";
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", cookieValue, cookieMaxAge, false);
        Token token = new Token();
        token.setName("uid");
        token.setCookie(cookieValue);
        int count = tokenMapper.insertSelective(token);
        if (count <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
