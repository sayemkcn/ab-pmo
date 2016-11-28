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
    @RequestMapping(value="/api/parent", method = RequestMethod.POST)
    public void addParent(@RequestBody Parent parent){
        parentService.saveParent(parent);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/child/submit", method = RequestMethod.POST)
    public String submitChildResult(@RequestBody Child child){

        System.out.println("Child Name: "+child.getName());
        Parent parent = parentService.getParentById(child.getParent().getId());
        System.out.println("Parent Name: "+parent.getName());

        childService.saveChild(child);

        return "Autism";

    }
}
