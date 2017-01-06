package com.aimslabs.services;

import com.aimslabs.domains.User;
import com.aimslabs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sayemkcn on 11/22/16.
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getAllUsers(int page, int size) {
        return this.userRepo.findAll(new PageRequest(page, size, Sort.Direction.DESC, "id")).getContent();
    }

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

    @Override
    public User getUserByPhone(String phoneNumber) {
        return this.userRepo.findByPhoneNumber(phoneNumber);
    }

}
