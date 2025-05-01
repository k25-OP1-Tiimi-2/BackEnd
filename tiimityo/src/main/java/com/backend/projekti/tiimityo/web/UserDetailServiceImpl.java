package com.backend.projekti.tiimityo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.projekti.tiimityo.domain.AppUser;
import com.backend.projekti.tiimityo.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AppUserRepository repository;

    @Autowired
    public UserDetailServiceImpl(AppUserRepository uRepository) {
        this.repository = uRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUsername(username);
        UserDetails user = new User(username, curruser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(curruser.getRole()));

        return user;
    }

}
