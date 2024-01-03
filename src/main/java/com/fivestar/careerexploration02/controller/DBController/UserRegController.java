package com.fivestar.careerexploration02.controller.DBController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRegController
{
    @GetMapping("/user_register")
    String regpage()
    {
        return "register";
    }
}
