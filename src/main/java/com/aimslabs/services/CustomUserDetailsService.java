package com.aimslabs.services;

import com.aimslabs.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sayemkcn on 1/5/17.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = this.userService.getUserByPhone(phoneNumber);
//        System.out.println("SEC_USER"+user.toString());
        if (user == null)
            throw new UsernameNotFoundException("No user found with this phone number " + phoneNumber);
        return user;
    }

//    private class SecurityUser extends User implements UserDetails {
//        private User user;
//
//        public SecurityUser(User user) {
//            this.user = user;
//        }
//
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            List<GrantedAuthority> authorityList = new ArrayList<>();
//            List<String> roles = (List<String>) this.user.getRoles();
//            roles.forEach(role -> {
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
//                authorityList.add(authority);
//            });
//            return authorityList;
//        }
//
//        @Override
//        public String getUsername() {
//            return this.user.getPhoneNumber();
//        }
//
//        @Override
//        public String getPassword() {
//            return this.user.getPassword();
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//
//    }
}
