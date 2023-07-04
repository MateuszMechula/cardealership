package pl.cardealership.business.DAO;

import pl.cardealership.domain.Salesman;

import java.util.Optional;

public interface SalesmanDAO {

    Optional<Salesman> findByPesel(String pesel);
}
