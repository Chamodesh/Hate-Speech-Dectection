package com.nlp.openhub.profile;

import com.nlp.openhub.classes.Vlog;
import com.nlp.openhub.loginpage.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public String viewProfilePage(Model model) throws ExecutionException, InterruptedException {
        if (LoginController.loggedUsername == null){
            return "redirect:/login";
        }
        else {
            List<Vlog> vlogList = new ArrayList<>(profileService.getAllVlogs(LoginController.loggedUsername));
            model.addAttribute("vlogList", vlogList);
            return "profile";
        }
    }

    @PostMapping("/deletevlog/{topic}")
    public String deleteMedicineDetails(@PathVariable String topic) throws ExecutionException, InterruptedException
    {
        profileService.deleteVlog(topic);
        return "redirect:/profile";
    }

}
