package com.aimslabs.controllers.admin;

import com.aimslabs.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sayemkcn on 12/2/16.
 */
@Controller
@RequestMapping("/admin/child")
public class ChildCtrl {

    @Autowired
    private ChildService childService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allChilds(@RequestParam("page") Integer page, Model model) {
        model.addAttribute("childList", childService.getAllChildPaginated(page, 10));
        return "child/all";
    }
}
