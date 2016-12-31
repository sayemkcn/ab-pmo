package com.aimslabs.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: Rezaul - Date: 29-Dec-16.
 */
@Controller
@RequestMapping("/admin/center")
public class AutismCenterController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String centerHome() {
        return "autism_center/center_home";
    }

    @RequestMapping(value = "/responses", method = RequestMethod.GET)
    public String newResponsesFromOnlineScreening() {
        return "autism_center/center_responses";
    }

    @RequestMapping(value = "/child", method = RequestMethod.GET)
    public String showChildStatus() {
        return "autism_center/center_child";
    }
}
