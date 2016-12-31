package com.aimslabs.controllers.admin;

import com.aimslabs.domains.Child;
import com.aimslabs.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("childList", this.childService.getAllPendingChildList(0, 10));
//        System.out.println(this.childService.getAllPendingChildList(0, 10).toString());
        return "autism_center/center_responses";
    }

    @RequestMapping(value = "/child/{id}", method = RequestMethod.GET)
    public String singleChildData(@PathVariable Long id, Model model) {
        model.addAttribute("child", this.childService.getById(id));
        return "autism_center/center_child";
    }

    @RequestMapping(value = "/child/{id}", method = RequestMethod.POST)
    public String saveDoctorReport(@PathVariable("id") Long id,
                                   @RequestParam("doctorResult") String doctorResult,
                                   @RequestParam("doctorNote") String doctorNote) {
        if (!doctorNote.equals("pending")) {
            Child child = this.childService.getById(id);
            child.setDoctorResult(Boolean.parseBoolean(doctorResult));
            child.setDoctorNote(doctorNote);
            this.childService.saveChild(child);
        }
        return "redirect:/admin/center/child/" + id;
    }

    @RequestMapping(value = "/visited",method = RequestMethod.GET)
    public String visitedChildList(Model model){
        model.addAttribute("childList",this.childService.getAllChildPaginated(0,10));
        return "autism_center/center_visited";
    }
}
