package com.aimslabs.services;

import com.aimslabs.domains.Parent;
import com.aimslabs.domains.User;
import com.aimslabs.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
