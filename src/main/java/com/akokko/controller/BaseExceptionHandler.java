package com.akokko.controller;
import com.akokko.entity.StatusCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
	
    @ExceptionHandler(value = Exception.class)
    public String error(Exception e, Model model){
        model.addAttribute("status", StatusCode.ERROR);
        model.addAttribute("stack", e.toString());
        return "failPage";
    }
}
