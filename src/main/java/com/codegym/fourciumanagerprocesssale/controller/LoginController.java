package com.codegym.fourciumanagerprocesssale.controller;

import com.codegym.fourciumanagerprocesssale.model.User;
import com.codegym.fourciumanagerprocesssale.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    // Xử lý url / và /login có request tới medthod là GET  bởi method login()
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userDetailsService.findUserByEmail(user.getEmail());
        if  (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user", "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login/registration");
        } else {
            userDetailsService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login/registration");
        }
        return modelAndView;
    }

}
