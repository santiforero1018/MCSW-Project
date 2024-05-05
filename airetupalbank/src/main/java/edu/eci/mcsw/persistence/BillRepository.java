package edu.eci.mcsw.persistence;

import edu.eci.mcsw.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    public Optional<Bill> findByReference(String reference);

}
