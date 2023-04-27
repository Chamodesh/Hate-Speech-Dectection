package com.nlp.openhub.homepage;

import com.nlp.openhub.classes.Vlog;
import com.nlp.openhub.loginpage.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class HomepageController {
    @Autowired
    private HomepageService homepageService;

    @GetMapping("/homepage")
    public String viewHomePage(Model model) throws ExecutionException, InterruptedException {
        if (LoginController.loggedUsername == null){
            return "redirect:/login";
        }
        else {
            List<Vlog> vlogList = new ArrayList<>(homepageService.getAllVlogs());
            model.addAttribute("vlogList", vlogList);
            return "homepage";
        }
    }
}
