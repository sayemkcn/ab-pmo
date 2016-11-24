package com.aimslabs.controllers;

import com.aimslabs.domains.Parent;
import com.aimslabs.domains.User;
import com.aimslabs.services.ParentService;
import com.aimslabs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by sayemkcn on 11/23/16.
 */
@Controller
@RequestMapping("/profile")
public class ParentController {
    @Autowired
    private ParentService parentService;
    @Autowired
    private UserService userService;

    // -------CREATE PROFILE ------- //
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProfilePage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        // check if user loggedIn
        if (user != null) {
            user = this.userService.getUser(user.getId());
            Parent parent = this.parentService.getParentByUser(user);
            if (parent != null)
                return "redirect:/profile/update";
            return "parents/profile/create";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProfile(@ModelAttribute Parent parent, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null)
            return "redirect:/login";
        parent.setUser(loggedInUser);
        this.parentService.saveParent(parent);
        return "redirect:/dashboard?message=Successfully created profile.";
    }

    // -----UPDATE PROFILE ----- //
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateProfilePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        // check if user logged in
        if (user == null)
            return "redirect:/login";
        // check if loggedin user matches
        Parent parent = this.parentService.getParentByUser(user);
        model.addAttribute("parent", parent);
        return "parents/profile/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute Parent parent,HttpSession session) {
        User user = (User) session.getAttribute("user");
        // find exiting user to get id of this entity // set it to the new entity so that it doen't create new row instead of updating.
        Parent existingParent = this.parentService.getParentByUser(user);
        parent.setId(existingParent.getId());
        parent.setCreated(existingParent.getCreated());
        parent.setUser(user);
        this.parentService.saveParent(parent);
        return "redirect:/dashboard?message=Successfully updated profile.";
    }


}
