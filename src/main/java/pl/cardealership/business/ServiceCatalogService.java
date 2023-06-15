package pl.cardealership.business;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.ServiceDAO;
import pl.cardealership.infrastructure.database.entity.ServiceEntity;

import java.util.Optional;

@AllArgsConstructor
public class ServiceCatalogService {

    private final ServiceDAO serviceDAO;

    public ServiceEntity findService(String serviceCode) {
        final Optional<ServiceEntity> service = serviceDAO.findByServiceCode(serviceCode);
        if (service.isEmpty()) {
            throw new RuntimeException("Could not find service by service code: [%s]".formatted(service));
        }
        return service.get();
    }
}
