package com.akokko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

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
    public String toLoginPage() {
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

}
