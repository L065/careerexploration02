package com.fivestar.careerexploration02.controller.DBController;

import com.fivestar.careerexploration02.model.userModel.UserLogModel02;
import com.fivestar.careerexploration02.service.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class LoginController
{
    @Autowired
        UserLoginService userLoginService;

    Logger logger = LoggerFactory.getLogger(LoginController.class);  //SpringBoot除錯訊息註解

    @GetMapping("/login")
    public String loginPage()
    {
        return "login_before";
    }

    @PostMapping("/login")
    public String loginsussPage(@RequestParam("accountnum") String accountnum,
                                @RequestParam("passwd") String passwd,
                                HttpSession session, Model model)
    {
        UserLogModel02 model01 = new UserLogModel02();
        UserLogModel02 showUserName = userLoginService.transUserName(accountnum);   //從DAO簡單寫從帳號對應的使用者名稱，給Service傳
        model01.setAccountnum(accountnum);
        model01.setPasswd(passwd);

        boolean loginResult = userLoginService.loginTest(model01);
        if(loginResult)
        {
            session.setAttribute("logInAcc",true);  // 登入成功，設定session屬性是true
//            model.addAttribute("loggedIn", session.getAttribute("logInAcc") != null);
//            //把會員session設置到Model中，這樣Thymeleaf就能夠使用它
            model.addAttribute("logSuess","Welcome back, you have successfully logged in.");
            model.addAttribute("showUserName", showUserName.getUsername()); //先判別帳密一致後，印出與帳號對應的使用者名稱
            logger.warn("執行後可以先看到userName內容");   //SpringBoot除錯訊息註解
            logger.warn("執行後可以後看到userName內容"+accountnum);
            return "loginsuss";
        }
        else
        {
            //帳號或密碼輸入錯誤，三秒後返回登入頁面
            model.addAttribute("logFail","Incorrect username or password. Returning to the login page in three seconds.");
            return "loginFail";
        }
    }

//    @GetMapping("/profile")     //防止重複登入
//    public String viewProfile(HttpSession session, Model model)
//    {
//        UserLogModel02 loggedUser=(UserLogModel02) session.setAttribute("loggedUser",loggedUser);
//        if (loggedUser != null)
//        {
//            model.addAttribute("loggedSuess", loggedUser);
//            return "loginsuss";
//        }
//        else
//        {
//            return "login";
//        }
//    }

    @GetMapping("/restricted-api")   //沒有登入的話，做出功能限制的測試API
    @ResponseBody
    public String restrictedApi(HttpSession session)
    {
        // 檢查是否有登入
        if (session.getAttribute("logInAcc") == null)
        {
            return "You are not logged in. Access denied.";
        }
        // 如果已經登入，可以在這裡實現 API 的邏輯
        return "This is a restricted API endpoint. You have access!";
    }

//    @PostMapping("/login")
//    public String showByLogged(HttpSession session, Model model)   //沒有登入的話，用th:if做出遮蓋
//    {
//        // 將會員session設置到Model中
//        model.addAttribute("loggedIn", session.getAttribute("logInAcc") != null);
//        //把會員session設置到Model中，這樣Thymeleaf就能夠使用它
//        return "EnglishProject20240109";
//    }

}
