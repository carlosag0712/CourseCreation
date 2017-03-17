package io.agileintelligence.security;

import io.agileintelligence.io.agileintelligence.domain.User;
import io.agileintelligence.io.agileintelligence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by carlosarosemena on 2017-03-14.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =userRepo.findUserByEmail(username);

        if(user == null)
            throw new UsernameNotFoundException("User with username: "+username+" not found.");
        else
            return new CustomUserDetails(user);
    }
}
