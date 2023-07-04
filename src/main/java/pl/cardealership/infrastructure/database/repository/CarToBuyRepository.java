package pl.cardealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cardealership.business.DAO.CarToBuyDAO;
import pl.cardealership.domain.CarToBuy;
import pl.cardealership.infrastructure.database.repository.jpa.CarToBuyJpaRepository;
import pl.cardealership.infrastructure.database.repository.mapper.CarToBuyEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarToBuyRepository implements CarToBuyDAO {
    private final CarToBuyJpaRepository carToBuyJpaRepository;
    private final CarToBuyEntityMapper carToBuyEntityMapper;
    @Override
    public Optional<CarToBuy> findCarToBuyByVin(String vin) {
        return carToBuyJpaRepository.findByVin(vin)
                .map(carToBuyEntityMapper::mapFromEntity);
    }
}
