package com.rhanem.bookseller.security;

import com.rhanem.bookseller.model.User;
import com.rhanem.bookseller.service.IUserService;
import com.rhanem.bookseller.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@AllArgsConstructor
public class CustomDetailsUserService implements UserDetailsService {

    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
        Set<GrantedAuthority> authorites = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

        return UserPrincipal.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(authorites)
                .build();
    }
}
