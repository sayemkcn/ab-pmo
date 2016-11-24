package com.aimslabs.services;

import com.aimslabs.domains.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sayemkcn on 11/25/16.
 */
@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
}
