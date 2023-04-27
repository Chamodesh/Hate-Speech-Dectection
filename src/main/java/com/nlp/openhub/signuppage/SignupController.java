package com.nlp.openhub.signuppage;

import com.nlp.openhub.classes.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String viewSignupPage(Model model) {
        model.addAttribute("signupDetails", new SignupForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String getSignupPage(@ModelAttribute SignupForm signupForm, Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("signupDetails", signupForm);

        if (!signupForm.getPassword().equals(signupForm.getConfirmPassword())) {
            //Todo:passwords do no match error
            return "/signup";
        } else if (signupService.hasAccount(signupForm.getUsername())) {
            //Todo:account already exists error
            return "/signup";
        } else {
            Account account = new Account();
            account.setUsername(signupForm.getUsername());
            account.setAdmissionNo(signupForm.getAdmissionNo());
            account.setEmail(signupForm.getEmail());
            account.setPassword(signupForm.getPassword());
            signupService.saveAccountDetails(account);

            //Todo:account successfully created banner

            return "/login";
        }
    }
}
