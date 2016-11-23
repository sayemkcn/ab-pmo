package com.aimslabs.repositories;

import com.aimslabs.domains.Parent;
import com.aimslabs.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sayemkcn on 11/23/16.
 */
@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent getByUser(User user);
}
