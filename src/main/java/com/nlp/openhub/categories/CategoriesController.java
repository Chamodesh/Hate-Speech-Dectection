package com.nlp.openhub.categories;

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
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/innovations")
    public String viewInnovationsPage(Model model) throws ExecutionException, InterruptedException {
        if (LoginController.loggedUsername == null){
            return "redirect:/login";
        }
        else {
            List<Vlog> vlogList = new ArrayList<>(categoriesService.getAllVlogs("Innovations"));
            model.addAttribute("vlogList", vlogList);
            return "innovations";
        }
    }

    @GetMapping("/complaints")
    public String viewComplaintsPage(Model model) throws ExecutionException, InterruptedException {
        if (LoginController.loggedUsername == null){
            return "redirect:/login";
        }
        else {
            List<Vlog> vlogList = new ArrayList<>(categoriesService.getAllVlogs("Complaints"));
            model.addAttribute("vlogList", vlogList);
            return "complaints";
        }
    }

    @GetMapping("/notices")
    public String viewNoticesPage(Model model) throws ExecutionException, InterruptedException {
        if (LoginController.loggedUsername == null){
            return "redirect:/login";
        }
        else {
            List<Vlog> vlogList = new ArrayList<>(categoriesService.getAllVlogs("Notices"));
            model.addAttribute("vlogList", vlogList);
            return "notices";
        }
    }

    @GetMapping("/clubs")
    public String viewClubsPage(Model model) throws ExecutionException, InterruptedException {
        if (LoginController.loggedUsername == null){
            return "redirect:/login";
        }
        else {
            List<Vlog> vlogList = new ArrayList<>(categoriesService.getAllVlogs("Club and Societies"));
            model.addAttribute("vlogList", vlogList);
            return "clubs&societies";
        }
    }
}
