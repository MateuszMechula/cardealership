package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.MechanicEntity;

import java.util.Optional;

public interface MechanicDAO {

    Optional<MechanicEntity> findByPesel(String pesel);
}
