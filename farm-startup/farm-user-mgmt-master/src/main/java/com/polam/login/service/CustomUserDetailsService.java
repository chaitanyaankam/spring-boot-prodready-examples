package com.polam.login.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polam.login.entity.User;
import com.polam.login.repository.RoleRepository;
import com.polam.login.repository.UserRepository;
import com.polam.login.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email));
        List<String> roles = roleRepository.findByRolePkUserid(user.getId())
        					.stream().map(r->r.getRolePk().getRole()).collect(Collectors.toList());
        return UserPrincipal.create(user, roles);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User id "+ id));
        List<String> roles = roleRepository.findByRolePkUserid(user.getId())
				.stream().map(r->r.getRolePk().getRole()).collect(Collectors.toList());
        return UserPrincipal.create(user, roles);
    }
}