package com.scaler.bookmyshowdec22.repositories;

import com.scaler.bookmyshowdec22.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository <T, ID>
    // Hashmap <ID, User>

    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByEmail(String email);

    @Override
    User save(User user);
}

// JPA : Interface
// Hibernate : Actual Implementation of this interface
