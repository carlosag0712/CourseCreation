package io.agileintelligence.security;

import io.agileintelligence.io.agileintelligence.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * Created by carlosarosemena on 2017-03-14.
 */
public class CustomUserDetails extends User implements UserDetails{

    public CustomUserDetails(){

    }

    public CustomUserDetails(User user)
    {
        super(user);
    }

    @Override
    public Set<Authorities> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
