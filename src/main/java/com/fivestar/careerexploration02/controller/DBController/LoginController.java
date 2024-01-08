package com.fivestar.careerexploration02.controller.DBController;

import com.fivestar.careerexploration02.model.userModel.UserLoginModel;
import com.fivestar.careerexploration02.service.UserLoginService;
import com.fivestar.careerexploration02.service.UserRegService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController
{
    @Autowired
        UserLoginService userLoginService;

    @GetMapping("/login")
    public String loginPage(Model model)
    {
        return "login_before";
    }

    @PostMapping("/login")
    public String loginsuss(@RequestParam("accountnum") String accountnum,
                            @RequestParam("passwd") String passwd, HttpSession session,Model model)
    {
        UserLoginModel model01 = new UserLoginModel();
        model01.setAccountnum(accountnum);
        model01.setPasswd(passwd);

        boolean loginResult = userLoginService.loginTest(model01);
        if(loginResult)
        {
            session.setAttribute("logInAcc",accountnum);
            return "loginsuss";
        }
        else
        {
            model.addAttribute("logFail","帳號或密碼輸入錯誤，五秒後返回登入頁面");
            return "login";
        }
//        model.addAttribute("accountnum",accountnum);
//        model.addAttribute("passwd",passwd);
//        model.addAttribute("session",session);
    }
}
