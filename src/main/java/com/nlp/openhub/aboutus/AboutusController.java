package com.nlp.openhub.aboutus;

import com.nlp.openhub.loginpage.LoginController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutusController {
    @GetMapping("/aboutus")
    public String viewAboutusPage(Model model)
    {
        if (LoginController.loggedUsername == null){
            return "redirect:/login";
        }
        else {
            return "aboutus";
        }

    }
}
