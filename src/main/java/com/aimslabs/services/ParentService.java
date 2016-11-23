package com.aimslabs.services;

import com.aimslabs.domains.Parent;
import com.aimslabs.domains.User;

/**
 * Created by sayemkcn on 11/23/16.
 */
public interface ParentService {
    Parent saveParent(Parent parent);
    Parent getParentByUser(User user);
}
