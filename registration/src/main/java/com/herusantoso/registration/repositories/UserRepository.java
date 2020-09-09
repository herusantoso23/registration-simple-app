package com.herusantoso.registration.repositories;

import com.herusantoso.registration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);
}
