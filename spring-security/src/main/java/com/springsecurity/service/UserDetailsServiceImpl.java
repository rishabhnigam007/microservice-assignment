package com.springsecurity.service;

import com.springsecurity.model.Login;
import com.springsecurity.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Login login = loginRepository.getUserByUserName(username);

        if (login == null) {
            throw new UsernameNotFoundException("User Not Found !!");
        }

        return new CustomUserDetails(login);
    }
}