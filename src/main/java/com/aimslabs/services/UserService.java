package com.aimslabs.services;

import com.aimslabs.domains.User;
import com.aimslabs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sayemkcn on 11/22/16.
 */
@Service
public interface UserService {

    User getUser(Long id);

    User saveUser(User user);

    User getUserByEmail(String email);


}
