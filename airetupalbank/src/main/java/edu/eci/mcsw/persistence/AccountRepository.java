package edu.eci.mcsw.persistence;

import edu.eci.mcsw.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    public Optional<Account> findByNumber(String number);

    public Boolean existsByNumber(String number);
}
