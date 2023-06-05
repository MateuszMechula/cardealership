package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.PartEntity;

import java.util.Optional;

public interface PartDAO {

    Optional<PartEntity> findByPartCode(String serialNumber);
}
