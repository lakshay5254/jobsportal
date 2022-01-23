package com.lakshay.jobsportal.viewController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {




    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @GetMapping({"/home","/"})
    public String indexPage(){
        return "index";
    }

    @GetMapping("/jobsingle")
    public String jobsingle(){
        return "job-single";
    }

    @GetMapping("/postjob")
    public String postjob(){
        return "post-job";
    }
    @GetMapping("/services")
    public String services(){
        return "services";
    }
    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }






}
