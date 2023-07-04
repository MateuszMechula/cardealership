package pl.cardealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cardealership.business.DAO.CarToServiceDAO;
import pl.cardealership.domain.CarHistory;
import pl.cardealership.domain.CarToService;
import pl.cardealership.infrastructure.database.entity.CarToServiceEntity;
import pl.cardealership.infrastructure.database.repository.jpa.CarToServiceJpaRepository;
import pl.cardealership.infrastructure.database.repository.mapper.CarToServiceEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarToServiceRepository implements CarToServiceDAO {
    private final CarToServiceJpaRepository carToServiceJpaRepository;
    private final CarToServiceEntityMapper carToServiceEntityMapper;
    @Override
    public Optional<CarToService> findCarToServiceByVin(String vin) {
        return carToServiceJpaRepository.findOptionalByVin(vin)
                .map(carToServiceEntityMapper::mapFromEntity);
    }

    @Override
    public CarToService saveCarToService(CarToService car) {
        CarToServiceEntity toSave = carToServiceEntityMapper.mapToEntity(car);
        CarToServiceEntity saved = carToServiceJpaRepository.save(toSave);
        return carToServiceEntityMapper.mapFromEntity(saved);
    }

    @Override
    public CarHistory findCarHistoryByVin(String vin) {
        CarToServiceEntity entity = carToServiceJpaRepository.findByVin(vin);
        return carToServiceEntityMapper.mapFromEntity(vin, entity);
    }
}
