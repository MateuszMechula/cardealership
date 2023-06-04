package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.SalesmanEntity;

import java.util.Optional;

public interface SalesmanDAO {

    Optional<SalesmanEntity> findByPesel(String pesel);
}
