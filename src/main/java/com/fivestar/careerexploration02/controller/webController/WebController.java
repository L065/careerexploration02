package com.fivestar.careerexploration02.controller.webController;

import com.fivestar.careerexploration02.model.userModel.UserLogModel02;
import com.fivestar.careerexploration02.service.UserLoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController
{
    @Autowired
    UserLoginService userLoginService;

    @GetMapping("/homepage")

    String hompage()
    {
        return "EnglishProject20240109";
    }
}
