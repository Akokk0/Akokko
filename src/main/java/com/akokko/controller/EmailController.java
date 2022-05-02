package com.akokko.controller;

import com.akokko.entity.Result;
import com.akokko.entity.StatusCode;
import com.akokko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    @Autowired
    private UserService userService;

    @RequestMapping("/checkEmail")
    @ResponseBody
    public Result checkEmail(@RequestParam("email") String email) {
        boolean flag = userService.checkEmail(email);
        if (flag) {
            return new Result(flag, StatusCode.OK, "该邮箱可以使用！");
        } else {
            return new Result(flag, StatusCode.OK, "一个邮箱只能注册一个账号哦！");
        }
    }

    @RequestMapping("/verify")
    public String verify(@RequestParam("code") String code, Model model) {
        boolean verify = userService.verify(code);
        if (verify) {
            return "successPage";
        } else {
            model.addAttribute("message", "验证失败请稍后重试");
            return "failPage";
        }
    }
}
