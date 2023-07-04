package pl.cardealership.business.DAO;

import pl.cardealership.domain.Mechanic;

import java.util.Optional;

public interface MechanicDAO {

    Optional<Mechanic> findByPesel(String pesel);
}
