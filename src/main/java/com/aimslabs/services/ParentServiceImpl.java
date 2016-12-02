package com.aimslabs.services;

import com.aimslabs.domains.Child;
import com.aimslabs.domains.Parent;
import com.aimslabs.domains.User;
import com.aimslabs.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sayemkcn on 11/23/16.
 */
@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentRepository parentRepo;

    @Override
    public Parent saveParent(Parent parent) {
        return this.parentRepo.saveAndFlush(parent);
    }

    @Override
    public Parent getParentByUser(User user) {
        return this.parentRepo.getByUser(user);
    }

    @Override
    public Parent getParentById(Long id) {
        return parentRepo.getOne(id);
    }

    @Override
    public List<Child> getAllChildResultByParentId(Long parentId) {

        Parent parent = parentRepo.findOne(parentId);
        System.out.println(parent.getName());
        List<Child> children = parent.getChildList();

        System.out.println("No of Child: "+children.size());

        return children;
    }


}
