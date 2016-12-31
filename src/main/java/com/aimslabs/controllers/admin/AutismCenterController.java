package com.aimslabs.controllers.admin;

import com.aimslabs.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: Rezaul - Date: 29-Dec-16.
 */
@Controller
@RequestMapping("/admin/center")
public class AutismCenterController {

    @Autowired
    private ChildService childService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String centerHome() {
        return "autism_center/center_home";
    }

    @RequestMapping(value = "/responses", method = RequestMethod.GET)
    public String newResponsesFromOnlineScreening(Model model) {
        model.addAttribute("childList",this.childService.getAllPendingChildList(0,10));
        System.out.println(this.childService.getAllPendingChildList(0,10).toString());
        return "autism_center/center_responses";
    }


}
