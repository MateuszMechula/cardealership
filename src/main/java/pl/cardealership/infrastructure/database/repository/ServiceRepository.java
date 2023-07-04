package pl.cardealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cardealership.business.DAO.ServiceDAO;
import pl.cardealership.domain.Service;
import pl.cardealership.infrastructure.database.repository.jpa.ServiceJpaRepository;
import pl.cardealership.infrastructure.database.repository.mapper.ServiceEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ServiceRepository implements ServiceDAO {

    private final ServiceJpaRepository serviceJpaRepository;
    private final ServiceEntityMapper serviceEntityMapper;

    @Override
    public Optional<Service> findByServiceCode(String serviceCode) {
        return serviceJpaRepository.findByServiceCode(serviceCode)
                .map(serviceEntityMapper::mapFromEntity);
    }
}
