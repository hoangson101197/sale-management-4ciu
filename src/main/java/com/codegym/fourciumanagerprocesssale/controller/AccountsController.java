package com.codegym.fourciumanagerprocesssale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AccountsController {
    @GetMapping("/accounts")
    public ModelAndView AccountsList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/accounts");
        return modelAndView;
    }
}
