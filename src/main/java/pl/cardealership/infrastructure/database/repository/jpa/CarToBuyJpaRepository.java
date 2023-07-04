package pl.cardealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cardealership.infrastructure.database.entity.CarToBuyEntity;

import java.util.Optional;

@Repository
public interface CarToBuyJpaRepository extends JpaRepository<CarToBuyEntity, Integer> {

    Optional<CarToBuyEntity> findByVin(String vin);

}
