package edu.eci.mcsw.persistence;

import edu.eci.mcsw.Model.userInfo.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEnt, Long> {
    public Optional<UserEnt> findByName(String name);
    public Optional<UserEnt> findByEmail(String email);
    public Boolean existsByEmail(String email);
    public Boolean existsByName(String name);
    public void deleteByEmail(String email);
}
