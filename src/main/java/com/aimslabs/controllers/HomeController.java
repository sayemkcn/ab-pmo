package com.aimslabs.controllers;

import com.aimslabs.domains.User;
import com.aimslabs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String sayHello() {
        return "index";
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String dashboard(){
        return "parents/dashboard";
    }

    // -- REGISTER -- //
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
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

    // ---- LOGIN ---- //
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpSession session) {
        if (session.getAttribute("user") != null)
            return "redirect:/";
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, Model model,
                        HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("email", email);
            return "login";
        }
        if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Password doen\'t match!");
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/dashboard";
    }

    // -- LOGOUT -- /
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
