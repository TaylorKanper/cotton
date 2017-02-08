package com.cotton.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequestMapping("/main")
public class HomeController {

    @RequestMapping("/index")
    public ModelAndView goIndex(){
        return new ModelAndView("/index/index");
    }
}
