package com.aimslabs.services;

import com.aimslabs.domains.Child;

import java.util.List;

/**
 * Created by sayemkcn on 11/25/16.
 */
public interface ChildService {

    List<Child> getAllChildPaginated(int page,int size);

    Child saveChild(Child child);

    Child getById(Long id);

    boolean isAutismDetected(Child child);

    List<Child> getAllPendingChildList(int page,int size);

    List<Child> getAllVisitedChildList(int page,int size);
}
