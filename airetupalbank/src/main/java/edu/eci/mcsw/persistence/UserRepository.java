package edu.eci.mcsw.persistence;

import edu.eci.mcsw.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByName(String name);
    public Optional<User> findByEmail(String email);
    public Boolean existsByEmail(String email);
}
