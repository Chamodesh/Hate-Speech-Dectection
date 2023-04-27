package com.nlp.openhub.loginpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;

@Controller
public class LoginController {
    public static String loggedUsername = null;

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String viewLoginPage(Model model)
    {

        model.addAttribute("loginDetails", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String getLoginPageData(@ModelAttribute LoginForm loginForm, Model model) throws ExecutionException, InterruptedException {

        model.addAttribute("loginDetails", loginForm);
            if(loginService.hasAccount(loginForm.getUsername()))
            {
                if(!loginForm.getPassword().equals(loginService.getAccountDetails(loginForm.getUsername()).getPassword())) {
                    //Todo: Wrong password error to html page
                    return "/login";
                }
                else {
                    loggedUsername = loginForm.getUsername();
                    return "redirect:/homepage";
                }
            }
            else {
                //Todo: Add no account found error to html page
                return "/login";
            }
        }
    }
