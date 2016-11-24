package com.aimslabs.services;

import com.aimslabs.domains.Child;

/**
 * Created by sayemkcn on 11/25/16.
 */
public interface ChildService {

    Child saveChild(Child child);

    boolean isAutismDetected(Child child);

}
