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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String dashboard()
    {
        return "parents/dashboard";
    }

    // -- REGISTER -- //
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult.toString());
        Set<String> roles = new HashSet<>();
        if (user.getPhoneNumber().equals("01710226163") || user.getPhoneNumber().equals("01515667948"))
            roles.add("ROLE_SUPER_ADMIN");
        else
            roles.add("ROLE_PARENTS");
        user.setRoles(roles);
        session.setAttribute("newUser",user);
        return "redirect:/profile/create";
    }

    // ---- LOGIN ---- //
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/digits-login", method = RequestMethod.GET)
    public String digitsLogin() {
        return "digits-login";
    }


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestParam("phoneNumber") String phoneNumber,
//                        @RequestParam("password") String password, Model model,
//                        HttpSession session) {
//        User user = this.userService.getUserByPhone(phoneNumber);
//        if (user == null) {
//            model.addAttribute("phoneNumber", phoneNumber);
//            return "login";
//        }
//        if (!user.getPassword().equals(password)) {
//            model.addAttribute("message", "Password doen\'t match!");
//            return "login";
//        }
//        session.setAttribute("user", user);
//        // check if user already started screening, if yes the start screening again
//        if (session.getAttribute("child") != null)
//            return "redirect:/child/screening/1";
//        return "redirect:/dashboard";
//    }

//    // -- LOGOUT -- /
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login";
//    }
}
