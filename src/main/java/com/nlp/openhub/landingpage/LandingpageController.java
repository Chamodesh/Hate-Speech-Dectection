package com.nlp.openhub.landingpage;

import com.nlp.openhub.loginpage.LoginController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingpageController {
    @GetMapping("/index")
    public String viewLandingPage(Model model)
    {
        return "index";
    }

    @GetMapping("/logout")
    public String viewLogout(Model model)
    {
        LoginController.loggedUsername = null;
        return "redirect:/login";
    }
}
