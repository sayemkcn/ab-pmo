package com.aimslabs.rest;

import com.aimslabs.domains.Child;
import com.aimslabs.domains.Parent;
import com.aimslabs.services.ChildService;
import com.aimslabs.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: rezaul || Date: 11/28/16.
 */
@RestController
public class ChildRestController {

    @Autowired
    ChildService childService;

    @Autowired
    ParentService parentService;

    @CrossOrigin
    @RequestMapping(value = "/api/parent/{parentId}", method = RequestMethod.GET)
    List<Child> getChildResultListByParentId(@PathVariable("parentId") Long parentId){

        return parentService.getAllChildResultByParentId(parentId);

    }

    @CrossOrigin
    @RequestMapping(value="/api/parent/submit", method = RequestMethod.POST)
    public String addParent(@RequestBody Parent parent){
        System.out.println("Requested");
        Parent parentForId = parentService.saveParent(parent);
        String parentId = String.valueOf(parentForId.getId());
        System.out.println("Parent ID: "+parentId);
        return parentId;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/child/submit", method = RequestMethod.POST)
    public String submitChildResult(@RequestBody Child child){

        System.out.println("Child Name: "+child.getName());
        Parent parent = parentService.getParentById(child.getParent().getId());
        System.out.println("Parent Name: "+parent.getName());
        child.setParent(parent);
        boolean isAutism = childService.isAutismDetected(child);
        child.setAppResult(isAutism);
        child.setSentFromMobileApp(true);


        if (isAutism) {
            child.setAppResult(true);
            childService.saveChild(child);
            return "Autism";
        } else {
            child.setAppResult(false);
            childService.saveChild(child);
            return "Not Autism";
        }


    }
}
