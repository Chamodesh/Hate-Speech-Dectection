package com.nlp.openhub.addvlogs;

import com.nlp.openhub.loginpage.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;

@Controller
public class AddvlogsController {
    @Autowired
    private AddvlogsService addvlogsService;
    @GetMapping("/addvlogs")
    public String viewAddVlogsPage(Model model)
    {
        if (LoginController.loggedUsername == null){
            return "redirect:/login";
        }
        else {
            model.addAttribute("vlogDetails", new AddvlogsForm());
            return "addvlogs";
        }
    }

    @PostMapping("/addvlogs")
    public String getSignupPage(@ModelAttribute AddvlogsForm addvlogsForm, Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("vlogDetails", addvlogsForm);

        if(addvlogsService.hasVlog(addvlogsForm.getTopic())) {
            //Todo:account already exists error
            return "addvlogs";
        }
        else {
            AddvlogsForm vlog = new AddvlogsForm();
            vlog.setTopic(addvlogsForm.getTopic());
            vlog.setVlog(addvlogsForm.getVlog());
            vlog.setUsername(LoginController.loggedUsername);

            AddvlogsRestController addvlogsRestController = new AddvlogsRestController();
            try { //using the api to select the category and validity
                if (addvlogsRestController.getFlaskClient(addvlogsForm.getVlog()).getIsApproved().equals("true"))
                {
//                    vlog.setCategory(addvlogsRestController.getFlaskClient(addvlogsForm.getVlog()).getCategory());
                    vlog.setCategory(addvlogsForm.getCategory());
                    addvlogsService.saveVlogDetails(vlog);
                }
                else {
                    //Todo: show error inappropriate language
                    System.out.println("Vlog not added!");
                }

            } catch (Exception e) { //using manually entered category since the api is down
                vlog.setCategory(addvlogsForm.getCategory());
                addvlogsService.saveVlogDetails(vlog);
                System.out.println("Server is down");
            }

            //Todo:account successfully created banner

            return "redirect:/homepage";

        }


    }
}


