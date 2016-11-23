package com.aimslabs.services;

import com.aimslabs.domains.User;
import com.aimslabs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by sayemkcn on 11/22/16.
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User getUser(Long id) {
        return userRepo.getOne(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

}
