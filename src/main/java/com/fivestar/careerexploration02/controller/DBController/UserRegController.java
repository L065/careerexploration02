package com.fivestar.careerexploration02.controller.DBController;

import com.fivestar.careerexploration02.model.userModel.UserRegModel;
import com.fivestar.careerexploration02.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegController
{
    @Autowired
    UserRegService userRegService;

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

    //先使用老師的controller
    @PostMapping("/user_register02")
    public String UserRegProcess2(@ModelAttribute UserRegModel usermodel, Model model)
    {
        String msg = null;
        // 使用者送 request 進來後 回傳 註冊表單名稱
        // 呼叫 service 開始進行新增
        int result = userRegService.Registration(usermodel);
        switch (result)
        {
            case 0:
                msg = "Registration failed";
                break;
            case 1:
                msg = "您的帳號已經成功註冊完畢";
                break;
            case 2:
                msg = "<h3>您的帳號已經在本系統註冊過,請使用登入功能</h3>";
                break;
            case 3:
                msg = "帳號不可包含系統禁止關鍵字(select,insert,update,delete等 或是惡意字詞)";
                break;
            default:
                msg = "其他原因 請聯絡本站管理人員";
                break;
        }
        // 結果通知

        model.addAttribute("userName",usermodel.getUsername());


        return "signin";
    }


}
