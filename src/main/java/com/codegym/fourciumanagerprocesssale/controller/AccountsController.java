package com.codegym.fourciumanagerprocesssale.controller;

import com.codegym.fourciumanagerprocesssale.model.Role;
import com.codegym.fourciumanagerprocesssale.model.User;
import com.codegym.fourciumanagerprocesssale.service.AccountsService;
import com.codegym.fourciumanagerprocesssale.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public Page<Role> roles(Pageable pageable) {
        return roleService.findAll(pageable);
    }

    @GetMapping("/accounts")
    public ModelAndView AccountsList(@PageableDefault(size = 5) Pageable pageable) {
        Page<User> accounts = accountsService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accounts);
        modelAndView.setViewName("admin/accounts");
        return modelAndView;
    }
}
