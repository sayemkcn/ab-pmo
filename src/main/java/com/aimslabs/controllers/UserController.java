package com.aimslabs.controllers;

import com.aimslabs.domains.User;
import com.aimslabs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sayemkcn on 11/22/16.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // -- REGISTER -- //
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult.toString());
        Set<String> roles = new HashSet<>();
        roles.add("PARENTS");
        user.setRoles(roles);
        this.userService.saveUser(user);
        return "redirect:/login?message=Successfully registered!";
    }

}
