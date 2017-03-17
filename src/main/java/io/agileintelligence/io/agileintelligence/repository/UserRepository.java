package io.agileintelligence.io.agileintelligence.repository;

import io.agileintelligence.io.agileintelligence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Created by carlosarosemena on 2017-03-14.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByEmail(String email);
}
