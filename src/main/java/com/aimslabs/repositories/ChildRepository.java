package com.aimslabs.repositories;

import com.aimslabs.domains.Child;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by sayemkcn on 11/25/16.
 */
@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    Page<Child> findAll(Pageable pageable);
    @Query("FROM Child as C WHERE C.lastUpdated IS NULL")
    Page<Child> findAllPristine(Pageable pageable);
    @Query("FROM Child as C WHERE C.lastUpdated IS NOT NULL")
    Page<Child> findAllDirty(Pageable pageable);
}
