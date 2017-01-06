package com.aimslabs.controllers;

import com.aimslabs.domains.Parent;
import com.aimslabs.domains.User;
import com.aimslabs.services.ParentService;
import com.aimslabs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sayemkcn on 11/22/16.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ParentService parentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allUsers(Model model) {
        model.addAttribute("userList", this.userService.getAllUsers(0, 100));
        return "users/all";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String userDetails(@PathVariable("id") Long id, Model model) {
        User user = this.userService.getUser(id);
        Parent parent = this.parentService.getParentByUser(user);
        model.addAttribute("parent", parent);
        return "users/details";
    }

    @RequestMapping(value = "/{id}/action", method = RequestMethod.POST)
    public String toggleEnableAction(@PathVariable("id") Long id,
                                     @RequestParam("enabled") Boolean enabled) {
        User user = this.userService.getUser(id);
        if (user != null) {
            System.out.println("isEnabled " + String.valueOf(enabled));
            user.setEnabled(enabled);
            user = this.userService.saveUser(user);
            System.out.println("USER: " + user.toString());
        } else
            return "redirect:/users?message=User not found!";
        return "redirect:/users";
    }

    @RequestMapping(value = "/{id}/roles", method = RequestMethod.POST)
    public String changeUserRoles(@PathVariable("id") Long id,
                                  @RequestParam(value = "roles", required = false) List<String> roles) {
        User user = this.userService.getUser(id);
        if (user == null) return "redirect:/users?message=User not found!";
        // if no role is selected then default role is PARENTS
        if (roles == null) {
            roles = new ArrayList<>();
            roles.add("ROLE_PARENTS");
        }
        user.setRoles(roles);
        this.userService.saveUser(user);
        return "redirect:/users/" + id;
    }

}
