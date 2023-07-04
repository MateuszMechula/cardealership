package pl.cardealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cardealership.business.DAO.CarServiceRequestDAO;
import pl.cardealership.domain.CarServiceRequest;
import pl.cardealership.infrastructure.database.repository.jpa.CarServiceRequestJpaRepository;
import pl.cardealership.infrastructure.database.repository.mapper.CarServiceRequestEntityMapper;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CarServiceRequestRepository implements CarServiceRequestDAO {

    private CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private CarServiceRequestEntityMapper carServiceRequestEntityMapper;
    @Override
    public Set<CarServiceRequest> findActiveServiceRequestsByCarVin(String carVin) {
        return carServiceRequestJpaRepository.findActiveServiceRequestsByCarVin(carVin).stream()
                .map(obj -> carServiceRequestEntityMapper.mapFromEntity(obj))
                .collect(Collectors.toSet());
    }
}
