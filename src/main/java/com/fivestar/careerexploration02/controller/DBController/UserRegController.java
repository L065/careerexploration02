package com.fivestar.careerexploration02.controller.DBController;

import com.fivestar.careerexploration02.model.userModel.UserRegModel;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegController
{
    @GetMapping("/user_register")
    public String regpage(Model model)
    {
        UserRegModel userRegModel = new UserRegModel();
        model.addAttribute("userRegForm",userRegModel);
        return "register";
    }

    //@PostMapping+ binding讓使用者把Form送進model裡傳，使用@ModelAttribute+ th:object="${ }"+ th:field="*{ }"
    @PostMapping("/user_register")
    public String userSignin(@ModelAttribute UserRegModel userModel, Model model)
    {
        model.addAttribute("userName",userModel.getUsername()); //此處Key=userName，交給用model傳遞
        model.addAttribute("email", userModel.getEmail());
        return "signin";
    }

}
